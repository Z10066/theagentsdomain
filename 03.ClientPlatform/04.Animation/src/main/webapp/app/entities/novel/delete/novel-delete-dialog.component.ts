import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { INovel } from '../novel.model';
import { NovelService } from '../service/novel.service';

@Component({
  standalone: true,
  templateUrl: './novel-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class NovelDeleteDialogComponent {
  novel?: INovel;

  constructor(
    protected novelService: NovelService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.novelService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
