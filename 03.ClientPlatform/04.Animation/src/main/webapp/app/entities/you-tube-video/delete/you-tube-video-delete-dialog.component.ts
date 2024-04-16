import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IYouTubeVideo } from '../you-tube-video.model';
import { YouTubeVideoService } from '../service/you-tube-video.service';

@Component({
  standalone: true,
  templateUrl: './you-tube-video-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class YouTubeVideoDeleteDialogComponent {
  youTubeVideo?: IYouTubeVideo;

  constructor(
    protected youTubeVideoService: YouTubeVideoService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.youTubeVideoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
