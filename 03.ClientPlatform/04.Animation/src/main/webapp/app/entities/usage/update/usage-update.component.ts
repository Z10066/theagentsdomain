import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISubscriptionService } from 'app/entities/subscription-service/subscription-service.model';
import { SubscriptionServiceService } from 'app/entities/subscription-service/service/subscription-service.service';
import { IUsage } from '../usage.model';
import { UsageService } from '../service/usage.service';
import { UsageFormService, UsageFormGroup } from './usage-form.service';

@Component({
  standalone: true,
  selector: 'jhi-usage-update',
  templateUrl: './usage-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class UsageUpdateComponent implements OnInit {
  isSaving = false;
  usage: IUsage | null = null;

  subscriptionServicesSharedCollection: ISubscriptionService[] = [];

  editForm: UsageFormGroup = this.usageFormService.createUsageFormGroup();

  constructor(
    protected usageService: UsageService,
    protected usageFormService: UsageFormService,
    protected subscriptionServiceService: SubscriptionServiceService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareSubscriptionService = (o1: ISubscriptionService | null, o2: ISubscriptionService | null): boolean =>
    this.subscriptionServiceService.compareSubscriptionService(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usage }) => {
      this.usage = usage;
      if (usage) {
        this.updateForm(usage);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usage = this.usageFormService.getUsage(this.editForm);
    if (usage.id !== null) {
      this.subscribeToSaveResponse(this.usageService.update(usage));
    } else {
      this.subscribeToSaveResponse(this.usageService.create(usage));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsage>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(usage: IUsage): void {
    this.usage = usage;
    this.usageFormService.resetForm(this.editForm, usage);

    this.subscriptionServicesSharedCollection =
      this.subscriptionServiceService.addSubscriptionServiceToCollectionIfMissing<ISubscriptionService>(
        this.subscriptionServicesSharedCollection,
        usage.subscriptionService,
      );
  }

  protected loadRelationshipsOptions(): void {
    this.subscriptionServiceService
      .query()
      .pipe(map((res: HttpResponse<ISubscriptionService[]>) => res.body ?? []))
      .pipe(
        map((subscriptionServices: ISubscriptionService[]) =>
          this.subscriptionServiceService.addSubscriptionServiceToCollectionIfMissing<ISubscriptionService>(
            subscriptionServices,
            this.usage?.subscriptionService,
          ),
        ),
      )
      .subscribe((subscriptionServices: ISubscriptionService[]) => (this.subscriptionServicesSharedCollection = subscriptionServices));
  }
}
