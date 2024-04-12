import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IWorkspace } from '../workspace.model';
import { WorkspaceService } from '../service/workspace.service';

@Component({
  standalone: true,
  templateUrl: './workspace-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class WorkspaceDeleteDialogComponent {
  workspace?: IWorkspace;

  constructor(
    protected workspaceService: WorkspaceService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.workspaceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
