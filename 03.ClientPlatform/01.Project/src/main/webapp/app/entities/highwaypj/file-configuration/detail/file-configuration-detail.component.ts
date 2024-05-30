import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IFileConfiguration } from '../file-configuration.model';

@Component({
  standalone: true,
  selector: 'jhi-file-configuration-detail',
  templateUrl: './file-configuration-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FileConfigurationDetailComponent {
  @Input() fileConfiguration: IFileConfiguration | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
