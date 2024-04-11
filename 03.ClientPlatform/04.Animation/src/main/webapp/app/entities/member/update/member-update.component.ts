import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IWorkspace } from 'app/entities/workspace/workspace.model';
import { WorkspaceService } from 'app/entities/workspace/service/workspace.service';
import { IMember } from '../member.model';
import { MemberService } from '../service/member.service';
import { MemberFormService, MemberFormGroup } from './member-form.service';

@Component({
  standalone: true,
  selector: 'jhi-member-update',
  templateUrl: './member-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MemberUpdateComponent implements OnInit {
  isSaving = false;
  member: IMember | null = null;

  workspacesSharedCollection: IWorkspace[] = [];

  editForm: MemberFormGroup = this.memberFormService.createMemberFormGroup();

  constructor(
    protected memberService: MemberService,
    protected memberFormService: MemberFormService,
    protected workspaceService: WorkspaceService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareWorkspace = (o1: IWorkspace | null, o2: IWorkspace | null): boolean => this.workspaceService.compareWorkspace(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ member }) => {
      this.member = member;
      if (member) {
        this.updateForm(member);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const member = this.memberFormService.getMember(this.editForm);
    if (member.id !== null) {
      this.subscribeToSaveResponse(this.memberService.update(member));
    } else {
      this.subscribeToSaveResponse(this.memberService.create(member));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMember>>): void {
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

  protected updateForm(member: IMember): void {
    this.member = member;
    this.memberFormService.resetForm(this.editForm, member);

    this.workspacesSharedCollection = this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(
      this.workspacesSharedCollection,
      member.workspace,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.workspaceService
      .query()
      .pipe(map((res: HttpResponse<IWorkspace[]>) => res.body ?? []))
      .pipe(
        map((workspaces: IWorkspace[]) =>
          this.workspaceService.addWorkspaceToCollectionIfMissing<IWorkspace>(workspaces, this.member?.workspace),
        ),
      )
      .subscribe((workspaces: IWorkspace[]) => (this.workspacesSharedCollection = workspaces));
  }
}
