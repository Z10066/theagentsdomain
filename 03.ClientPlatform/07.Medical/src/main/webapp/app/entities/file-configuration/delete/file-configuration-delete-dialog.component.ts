import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFileConfiguration } from '../file-configuration.model';
import { FileConfigurationService } from '../service/file-configuration.service';

@Component({
  templateUrl: './file-configuration-delete-dialog.component.html',
})
export class FileConfigurationDeleteDialogComponent {
  fileConfiguration?: IFileConfiguration;

  constructor(protected fileConfigurationService: FileConfigurationService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fileConfigurationService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
