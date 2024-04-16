import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IVideoHint } from '../video-hint.model';
import { VideoHintService } from '../service/video-hint.service';

@Component({
  standalone: true,
  templateUrl: './video-hint-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class VideoHintDeleteDialogComponent {
  videoHint?: IVideoHint;

  constructor(
    protected videoHintService: VideoHintService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.videoHintService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
