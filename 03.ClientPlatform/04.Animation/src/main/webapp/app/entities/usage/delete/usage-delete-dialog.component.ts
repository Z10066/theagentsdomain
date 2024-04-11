import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IUsage } from '../usage.model';
import { UsageService } from '../service/usage.service';

@Component({
  standalone: true,
  templateUrl: './usage-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class UsageDeleteDialogComponent {
  usage?: IUsage;

  constructor(
    protected usageService: UsageService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.usageService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
