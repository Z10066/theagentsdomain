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
import { VideoProductionService } from '../service/video-production.service';
import { IVideoProduction } from '../video-production.model';
import { VideoProductionFormService, VideoProductionFormGroup } from './video-production-form.service';

@Component({
  standalone: true,
  selector: 'jhi-video-production-update',
  templateUrl: './video-production-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VideoProductionUpdateComponent implements OnInit {
  isSaving = false;
  videoProduction: IVideoProduction | null = null;

  extraInfosCollection: IExtraInfo[] = [];
  workspacesSharedCollection: IWorkspace[] = [];

  editForm: VideoProductionFormGroup = this.videoProductionFormService.createVideoProductionFormGroup();

  constructor(
    protected videoProductionService: VideoProductionService,
    protected videoProductionFormService: VideoProductionFormService,
    protected extraInfoService: ExtraInfoService,
    protected workspaceService: WorkspaceService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareExtraInfo = (o1: IExtraInfo | null, o2: IExtraInfo | null): boolean => this.extraInfoService.compareExtraInfo(o1, o2);

  compareWorkspace = (o1: IWorkspace | null, o2: IWorkspace | null): boolean => this.workspaceService.compareWorkspace(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ videoProduction }) => {
      this.videoProduction = videoProduction;
      if (videoProduction) {
        this.updateForm(videoProduction);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const videoProduction = this.videoProductionFormService.getVideoProduction(this.editForm);
    if (videoProduction.id !== null) {
      this.subscribeToSaveResponse(this.videoProductionService.update(videoProduction));
    } else {
      this.subscribeToSaveResponse(this.videoProductionService.create(videoProduction));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVideoProduction>>): void {
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

  protected updateForm(videoProduction: IVideoProduction): void {
    this.videoProduction = videoProduction;
    this.videoProductionFormService.resetForm(this.editForm, videoProduction);

    this.extraInfosCollection = this.extraInfoService.addExtraInfoToCollectionIfMissing<IExtraInfo>(
      this.extraInfosCollection,
      videoProduction.extraInfo,
    );
    this.workspacesSharedCollection = this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(
      this.workspacesSharedCollection,
      videoProduction.workspace,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.extraInfoService
      .query({ filter: 'videoproduction-is-null' })
      .pipe(map((res: HttpResponse<IExtraInfo[]>) => res.body ?? []))
      .pipe(
        map((extraInfos: IExtraInfo[]) =>
          this.extraInfoService.addExtraInfoToCollectionIfMissing<IExtraInfo>(extraInfos, this.videoProduction?.extraInfo),
        ),
      )
      .subscribe((extraInfos: IExtraInfo[]) => (this.extraInfosCollection = extraInfos));

    this.workspaceService
      .query()
      .pipe(map((res: HttpResponse<IWorkspace[]>) => res.body ?? []))
      .pipe(
        map((workspaces: IWorkspace[]) =>
          this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(workspaces, this.videoProduction?.workspace),
        ),
      )
      .subscribe((workspaces: IWorkspace[]) => (this.workspacesSharedCollection = workspaces));
  }
}
