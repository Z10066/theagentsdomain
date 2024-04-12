import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IExtraInfo } from '../extra-info.model';
import { ExtraInfoService } from '../service/extra-info.service';

@Component({
  standalone: true,
  templateUrl: './extra-info-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ExtraInfoDeleteDialogComponent {
  extraInfo?: IExtraInfo;

  constructor(
    protected extraInfoService: ExtraInfoService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.extraInfoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
