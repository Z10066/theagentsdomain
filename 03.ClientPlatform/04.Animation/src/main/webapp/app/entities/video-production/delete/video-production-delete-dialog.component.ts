import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IVideoProduction } from '../video-production.model';
import { VideoProductionService } from '../service/video-production.service';

@Component({
  standalone: true,
  templateUrl: './video-production-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class VideoProductionDeleteDialogComponent {
  videoProduction?: IVideoProduction;

  constructor(
    protected videoProductionService: VideoProductionService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.videoProductionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
