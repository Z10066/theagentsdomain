import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IUsage, NewUsage } from '../usage.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUsage for edit and NewUsageFormGroupInput for create.
 */
type UsageFormGroupInput = IUsage | PartialWithRequiredKeyOf<NewUsage>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IUsage | NewUsage> = Omit<T, 'startTime' | 'endTime'> & {
  startTime?: string | null;
  endTime?: string | null;
};

type UsageFormRawValue = FormValueOf<IUsage>;

type NewUsageFormRawValue = FormValueOf<NewUsage>;

type UsageFormDefaults = Pick<NewUsage, 'id' | 'startTime' | 'endTime'>;

type UsageFormGroupContent = {
  id: FormControl<UsageFormRawValue['id'] | NewUsage['id']>;
  usageType: FormControl<UsageFormRawValue['usageType']>;
  usageTime: FormControl<UsageFormRawValue['usageTime']>;
  startTime: FormControl<UsageFormRawValue['startTime']>;
  endTime: FormControl<UsageFormRawValue['endTime']>;
  subscriptionService: FormControl<UsageFormRawValue['subscriptionService']>;
};

export type UsageFormGroup = FormGroup<UsageFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UsageFormService {
  createUsageFormGroup(usage: UsageFormGroupInput = { id: null }): UsageFormGroup {
    const usageRawValue = this.convertUsageToUsageRawValue({
      ...this.getFormDefaults(),
      ...usage,
    });
    return new FormGroup<UsageFormGroupContent>({
      id: new FormControl(
        { value: usageRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      usageType: new FormControl(usageRawValue.usageType, {
        validators: [Validators.required],
      }),
      usageTime: new FormControl(usageRawValue.usageTime),
      startTime: new FormControl(usageRawValue.startTime),
      endTime: new FormControl(usageRawValue.endTime),
      subscriptionService: new FormControl(usageRawValue.subscriptionService),
    });
  }

  getUsage(form: UsageFormGroup): IUsage | NewUsage {
    return this.convertUsageRawValueToUsage(form.getRawValue() as UsageFormRawValue | NewUsageFormRawValue);
  }

  resetForm(form: UsageFormGroup, usage: UsageFormGroupInput): void {
    const usageRawValue = this.convertUsageToUsageRawValue({ ...this.getFormDefaults(), ...usage });
    form.reset(
      {
        ...usageRawValue,
        id: { value: usageRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): UsageFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      startTime: currentTime,
      endTime: currentTime,
    };
  }

  private convertUsageRawValueToUsage(rawUsage: UsageFormRawValue | NewUsageFormRawValue): IUsage | NewUsage {
    return {
      ...rawUsage,
      startTime: dayjs(rawUsage.startTime, DATE_TIME_FORMAT),
      endTime: dayjs(rawUsage.endTime, DATE_TIME_FORMAT),
    };
  }

  private convertUsageToUsageRawValue(
    usage: IUsage | (Partial<NewUsage> & UsageFormDefaults),
  ): UsageFormRawValue | PartialWithRequiredKeyOf<NewUsageFormRawValue> {
    return {
      ...usage,
      startTime: usage.startTime ? usage.startTime.format(DATE_TIME_FORMAT) : undefined,
      endTime: usage.endTime ? usage.endTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
