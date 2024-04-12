import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IMember } from 'app/entities/member/member.model';
import { MemberService } from 'app/entities/member/service/member.service';
import { AccountType } from 'app/entities/enumerations/account-type.model';
import { LinkedAccountService } from '../service/linked-account.service';
import { ILinkedAccount } from '../linked-account.model';
import { LinkedAccountFormService, LinkedAccountFormGroup } from './linked-account-form.service';

@Component({
  standalone: true,
  selector: 'jhi-linked-account-update',
  templateUrl: './linked-account-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LinkedAccountUpdateComponent implements OnInit {
  isSaving = false;
  linkedAccount: ILinkedAccount | null = null;
  accountTypeValues = Object.keys(AccountType);

  membersSharedCollection: IMember[] = [];

  editForm: LinkedAccountFormGroup = this.linkedAccountFormService.createLinkedAccountFormGroup();

  constructor(
    protected linkedAccountService: LinkedAccountService,
    protected linkedAccountFormService: LinkedAccountFormService,
    protected memberService: MemberService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareMember = (o1: IMember | null, o2: IMember | null): boolean => this.memberService.compareMember(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ linkedAccount }) => {
      this.linkedAccount = linkedAccount;
      if (linkedAccount) {
        this.updateForm(linkedAccount);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const linkedAccount = this.linkedAccountFormService.getLinkedAccount(this.editForm);
    if (linkedAccount.id !== null) {
      this.subscribeToSaveResponse(this.linkedAccountService.update(linkedAccount));
    } else {
      this.subscribeToSaveResponse(this.linkedAccountService.create(linkedAccount));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILinkedAccount>>): void {
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

  protected updateForm(linkedAccount: ILinkedAccount): void {
    this.linkedAccount = linkedAccount;
    this.linkedAccountFormService.resetForm(this.editForm, linkedAccount);

    this.membersSharedCollection = this.memberService.addMemberToCollectionIfMissing<IMember>(
      this.membersSharedCollection,
      linkedAccount.member,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.memberService
      .query()
      .pipe(map((res: HttpResponse<IMember[]>) => res.body ?? []))
      .pipe(map((members: IMember[]) => this.memberService.addMemberToCollectionIfMissing<IMember>(members, this.linkedAccount?.member)))
      .subscribe((members: IMember[]) => (this.membersSharedCollection = members));
  }
}
