import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICreator } from '../creator.model';
import { CreatorService } from '../service/creator.service';

@Component({
  standalone: true,
  templateUrl: './creator-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CreatorDeleteDialogComponent {
  creator?: ICreator;

  constructor(
    protected creatorService: CreatorService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.creatorService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
