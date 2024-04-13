import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ISystemSetting } from '../system-setting.model';
import { SystemSettingService } from '../service/system-setting.service';

@Component({
  standalone: true,
  templateUrl: './system-setting-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class SystemSettingDeleteDialogComponent {
  systemSetting?: ISystemSetting;

  constructor(
    protected systemSettingService: SystemSettingService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.systemSettingService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
