import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IKeyword } from '../keyword.model';
import { KeywordService } from '../service/keyword.service';

@Component({
  standalone: true,
  templateUrl: './keyword-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class KeywordDeleteDialogComponent {
  keyword?: IKeyword;

  constructor(
    protected keywordService: KeywordService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.keywordService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
