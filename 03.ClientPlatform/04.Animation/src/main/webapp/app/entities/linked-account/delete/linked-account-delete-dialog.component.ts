import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILinkedAccount } from '../linked-account.model';
import { LinkedAccountService } from '../service/linked-account.service';

@Component({
  standalone: true,
  templateUrl: './linked-account-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LinkedAccountDeleteDialogComponent {
  linkedAccount?: ILinkedAccount;

  constructor(
    protected linkedAccountService: LinkedAccountService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.linkedAccountService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
