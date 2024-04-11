import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISubscriptionService } from '../subscription-service.model';
import { SubscriptionServiceService } from '../service/subscription-service.service';
import { SubscriptionServiceFormService, SubscriptionServiceFormGroup } from './subscription-service-form.service';

@Component({
  standalone: true,
  selector: 'jhi-subscription-service-update',
  templateUrl: './subscription-service-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SubscriptionServiceUpdateComponent implements OnInit {
  isSaving = false;
  subscriptionService: ISubscriptionService | null = null;

  editForm: SubscriptionServiceFormGroup = this.subscriptionServiceFormService.createSubscriptionServiceFormGroup();

  constructor(
    protected subscriptionServiceService: SubscriptionServiceService,
    protected subscriptionServiceFormService: SubscriptionServiceFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionService }) => {
      this.subscriptionService = subscriptionService;
      if (subscriptionService) {
        this.updateForm(subscriptionService);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subscriptionService = this.subscriptionServiceFormService.getSubscriptionService(this.editForm);
    if (subscriptionService.id !== null) {
      this.subscribeToSaveResponse(this.subscriptionServiceService.update(subscriptionService));
    } else {
      this.subscribeToSaveResponse(this.subscriptionServiceService.create(subscriptionService));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubscriptionService>>): void {
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

  protected updateForm(subscriptionService: ISubscriptionService): void {
    this.subscriptionService = subscriptionService;
    this.subscriptionServiceFormService.resetForm(this.editForm, subscriptionService);
  }
}
