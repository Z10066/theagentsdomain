import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ISubscriptionService, NewSubscriptionService } from '../subscription-service.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISubscriptionService for edit and NewSubscriptionServiceFormGroupInput for create.
 */
type SubscriptionServiceFormGroupInput = ISubscriptionService | PartialWithRequiredKeyOf<NewSubscriptionService>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ISubscriptionService | NewSubscriptionService> = Omit<T, 'startTime' | 'endTime'> & {
  startTime?: string | null;
  endTime?: string | null;
};

type SubscriptionServiceFormRawValue = FormValueOf<ISubscriptionService>;

type NewSubscriptionServiceFormRawValue = FormValueOf<NewSubscriptionService>;

type SubscriptionServiceFormDefaults = Pick<NewSubscriptionService, 'id' | 'startTime' | 'endTime'>;

type SubscriptionServiceFormGroupContent = {
  id: FormControl<SubscriptionServiceFormRawValue['id'] | NewSubscriptionService['id']>;
  serviceLevel: FormControl<SubscriptionServiceFormRawValue['serviceLevel']>;
  totalUsageTime: FormControl<SubscriptionServiceFormRawValue['totalUsageTime']>;
  startTime: FormControl<SubscriptionServiceFormRawValue['startTime']>;
  endTime: FormControl<SubscriptionServiceFormRawValue['endTime']>;
};

export type SubscriptionServiceFormGroup = FormGroup<SubscriptionServiceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SubscriptionServiceFormService {
  createSubscriptionServiceFormGroup(subscriptionService: SubscriptionServiceFormGroupInput = { id: null }): SubscriptionServiceFormGroup {
    const subscriptionServiceRawValue = this.convertSubscriptionServiceToSubscriptionServiceRawValue({
      ...this.getFormDefaults(),
      ...subscriptionService,
    });
    return new FormGroup<SubscriptionServiceFormGroupContent>({
      id: new FormControl(
        { value: subscriptionServiceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      serviceLevel: new FormControl(subscriptionServiceRawValue.serviceLevel, {
        validators: [Validators.required],
      }),
      totalUsageTime: new FormControl(subscriptionServiceRawValue.totalUsageTime),
      startTime: new FormControl(subscriptionServiceRawValue.startTime),
      endTime: new FormControl(subscriptionServiceRawValue.endTime),
    });
  }

  getSubscriptionService(form: SubscriptionServiceFormGroup): ISubscriptionService | NewSubscriptionService {
    return this.convertSubscriptionServiceRawValueToSubscriptionService(
      form.getRawValue() as SubscriptionServiceFormRawValue | NewSubscriptionServiceFormRawValue,
    );
  }

  resetForm(form: SubscriptionServiceFormGroup, subscriptionService: SubscriptionServiceFormGroupInput): void {
    const subscriptionServiceRawValue = this.convertSubscriptionServiceToSubscriptionServiceRawValue({
      ...this.getFormDefaults(),
      ...subscriptionService,
    });
    form.reset(
      {
        ...subscriptionServiceRawValue,
        id: { value: subscriptionServiceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): SubscriptionServiceFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      startTime: currentTime,
      endTime: currentTime,
    };
  }

  private convertSubscriptionServiceRawValueToSubscriptionService(
    rawSubscriptionService: SubscriptionServiceFormRawValue | NewSubscriptionServiceFormRawValue,
  ): ISubscriptionService | NewSubscriptionService {
    return {
      ...rawSubscriptionService,
      startTime: dayjs(rawSubscriptionService.startTime, DATE_TIME_FORMAT),
      endTime: dayjs(rawSubscriptionService.endTime, DATE_TIME_FORMAT),
    };
  }

  private convertSubscriptionServiceToSubscriptionServiceRawValue(
    subscriptionService: ISubscriptionService | (Partial<NewSubscriptionService> & SubscriptionServiceFormDefaults),
  ): SubscriptionServiceFormRawValue | PartialWithRequiredKeyOf<NewSubscriptionServiceFormRawValue> {
    return {
      ...subscriptionService,
      startTime: subscriptionService.startTime ? subscriptionService.startTime.format(DATE_TIME_FORMAT) : undefined,
      endTime: subscriptionService.endTime ? subscriptionService.endTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
