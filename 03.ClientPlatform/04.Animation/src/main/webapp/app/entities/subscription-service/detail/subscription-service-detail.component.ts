import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ISubscriptionService } from '../subscription-service.model';

@Component({
  standalone: true,
  selector: 'jhi-subscription-service-detail',
  templateUrl: './subscription-service-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class SubscriptionServiceDetailComponent {
  @Input() subscriptionService: ISubscriptionService | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
