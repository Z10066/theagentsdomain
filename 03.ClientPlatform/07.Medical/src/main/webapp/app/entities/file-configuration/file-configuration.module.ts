import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FileConfigurationComponent } from './list/file-configuration.component';
import { FileConfigurationDetailComponent } from './detail/file-configuration-detail.component';
import { FileConfigurationUpdateComponent } from './update/file-configuration-update.component';
import { FileConfigurationDeleteDialogComponent } from './delete/file-configuration-delete-dialog.component';
import { FileConfigurationRoutingModule } from './route/file-configuration-routing.module';

@NgModule({
  imports: [SharedModule, FileConfigurationRoutingModule],
  declarations: [
    FileConfigurationComponent,
    FileConfigurationDetailComponent,
    FileConfigurationUpdateComponent,
    FileConfigurationDeleteDialogComponent,
  ],
  entryComponents: [FileConfigurationDeleteDialogComponent],
})
export class FileConfigurationModule {}
