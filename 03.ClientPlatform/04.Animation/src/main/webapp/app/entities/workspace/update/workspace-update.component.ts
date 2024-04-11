import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IWorkspace } from '../workspace.model';
import { WorkspaceService } from '../service/workspace.service';
import { WorkspaceFormService, WorkspaceFormGroup } from './workspace-form.service';

@Component({
  standalone: true,
  selector: 'jhi-workspace-update',
  templateUrl: './workspace-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class WorkspaceUpdateComponent implements OnInit {
  isSaving = false;
  workspace: IWorkspace | null = null;

  editForm: WorkspaceFormGroup = this.workspaceFormService.createWorkspaceFormGroup();

  constructor(
    protected workspaceService: WorkspaceService,
    protected workspaceFormService: WorkspaceFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ workspace }) => {
      this.workspace = workspace;
      if (workspace) {
        this.updateForm(workspace);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const workspace = this.workspaceFormService.getWorkspace(this.editForm);
    if (workspace.id !== null) {
      this.subscribeToSaveResponse(this.workspaceService.update(workspace));
    } else {
      this.subscribeToSaveResponse(this.workspaceService.create(workspace));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkspace>>): void {
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

  protected updateForm(workspace: IWorkspace): void {
    this.workspace = workspace;
    this.workspaceFormService.resetForm(this.editForm, workspace);
  }
}
