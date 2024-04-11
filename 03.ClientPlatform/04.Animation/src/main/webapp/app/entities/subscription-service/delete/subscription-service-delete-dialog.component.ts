import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ISubscriptionService } from '../subscription-service.model';
import { SubscriptionServiceService } from '../service/subscription-service.service';

@Component({
  standalone: true,
  templateUrl: './subscription-service-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class SubscriptionServiceDeleteDialogComponent {
  subscriptionService?: ISubscriptionService;

  constructor(
    protected subscriptionServiceService: SubscriptionServiceService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.subscriptionServiceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
