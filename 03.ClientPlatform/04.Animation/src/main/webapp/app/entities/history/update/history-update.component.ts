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
import { HistoryService } from '../service/history.service';
import { IHistory } from '../history.model';
import { HistoryFormService, HistoryFormGroup } from './history-form.service';

@Component({
  standalone: true,
  selector: 'jhi-history-update',
  templateUrl: './history-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HistoryUpdateComponent implements OnInit {
  isSaving = false;
  history: IHistory | null = null;

  extraInfosCollection: IExtraInfo[] = [];
  workspacesSharedCollection: IWorkspace[] = [];

  editForm: HistoryFormGroup = this.historyFormService.createHistoryFormGroup();

  constructor(
    protected historyService: HistoryService,
    protected historyFormService: HistoryFormService,
    protected extraInfoService: ExtraInfoService,
    protected workspaceService: WorkspaceService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareExtraInfo = (o1: IExtraInfo | null, o2: IExtraInfo | null): boolean => this.extraInfoService.compareExtraInfo(o1, o2);

  compareWorkspace = (o1: IWorkspace | null, o2: IWorkspace | null): boolean => this.workspaceService.compareWorkspace(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ history }) => {
      this.history = history;
      if (history) {
        this.updateForm(history);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const history = this.historyFormService.getHistory(this.editForm);
    if (history.id !== null) {
      this.subscribeToSaveResponse(this.historyService.update(history));
    } else {
      this.subscribeToSaveResponse(this.historyService.create(history));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistory>>): void {
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

  protected updateForm(history: IHistory): void {
    this.history = history;
    this.historyFormService.resetForm(this.editForm, history);

    this.extraInfosCollection = this.extraInfoService.addExtraInfoToCollectionIfMissing<IExtraInfo>(
      this.extraInfosCollection,
      history.extraInfo,
    );
    this.workspacesSharedCollection = this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(
      this.workspacesSharedCollection,
      history.workspace,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.extraInfoService
      .query({ filter: 'history-is-null' })
      .pipe(map((res: HttpResponse<IExtraInfo[]>) => res.body ?? []))
      .pipe(
        map((extraInfos: IExtraInfo[]) =>
          this.extraInfoService.addExtraInfoToCollectionIfMissing<IExtraInfo>(extraInfos, this.history?.extraInfo),
        ),
      )
      .subscribe((extraInfos: IExtraInfo[]) => (this.extraInfosCollection = extraInfos));

    this.workspaceService
      .query()
      .pipe(map((res: HttpResponse<IWorkspace[]>) => res.body ?? []))
      .pipe(
        map((workspaces: IWorkspace[]) =>
          this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(workspaces, this.history?.workspace),
        ),
      )
      .subscribe((workspaces: IWorkspace[]) => (this.workspacesSharedCollection = workspaces));
  }
}
