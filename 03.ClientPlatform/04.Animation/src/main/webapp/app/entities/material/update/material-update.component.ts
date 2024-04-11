import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IExtraInfo } from 'app/entities/extra-info/extra-info.model';
import { ExtraInfoService } from 'app/entities/extra-info/service/extra-info.service';
import { IWorkspace } from 'app/entities/workspace/workspace.model';
import { WorkspaceService } from 'app/entities/workspace/service/workspace.service';
import { MaterialService } from '../service/material.service';
import { IMaterial } from '../material.model';
import { MaterialFormService, MaterialFormGroup } from './material-form.service';

@Component({
  standalone: true,
  selector: 'jhi-material-update',
  templateUrl: './material-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MaterialUpdateComponent implements OnInit {
  isSaving = false;
  material: IMaterial | null = null;

  extraInfosCollection: IExtraInfo[] = [];
  workspacesSharedCollection: IWorkspace[] = [];

  editForm: MaterialFormGroup = this.materialFormService.createMaterialFormGroup();

  constructor(
    protected materialService: MaterialService,
    protected materialFormService: MaterialFormService,
    protected extraInfoService: ExtraInfoService,
    protected workspaceService: WorkspaceService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareExtraInfo = (o1: IExtraInfo | null, o2: IExtraInfo | null): boolean => this.extraInfoService.compareExtraInfo(o1, o2);

  compareWorkspace = (o1: IWorkspace | null, o2: IWorkspace | null): boolean => this.workspaceService.compareWorkspace(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ material }) => {
      this.material = material;
      if (material) {
        this.updateForm(material);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const material = this.materialFormService.getMaterial(this.editForm);
    if (material.id !== null) {
      this.subscribeToSaveResponse(this.materialService.update(material));
    } else {
      this.subscribeToSaveResponse(this.materialService.create(material));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaterial>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(material: IMaterial): void {
    this.material = material;
    this.materialFormService.resetForm(this.editForm, material);

    this.extraInfosCollection = this.extraInfoService.addExtraInfoToCollectionIfMissing<IExtraInfo>(
      this.extraInfosCollection,
      material.extraInfo,
    );
    this.workspacesSharedCollection = this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(
      this.workspacesSharedCollection,
      material.workspace,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.extraInfoService
      .query({ filter: 'material-is-null' })
      .pipe(map((res: HttpResponse<IExtraInfo[]>) => res.body ?? []))
      .pipe(
        map((extraInfos: IExtraInfo[]) =>
          this.extraInfoService.addExtraInfoToCollectionIfMissing<IExtraInfo>(extraInfos, this.material?.extraInfo),
        ),
      )
      .subscribe((extraInfos: IExtraInfo[]) => (this.extraInfosCollection = extraInfos));

    this.workspaceService
      .query()
      .pipe(map((res: HttpResponse<IWorkspace[]>) => res.body ?? []))
      .pipe(
        map((workspaces: IWorkspace[]) =>
          this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(workspaces, this.material?.workspace),
        ),
      )
      .subscribe((workspaces: IWorkspace[]) => (this.workspacesSharedCollection = workspaces));
  }
}
