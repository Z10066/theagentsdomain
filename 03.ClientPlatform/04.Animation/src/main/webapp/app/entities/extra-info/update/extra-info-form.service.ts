import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IExtraInfo, NewExtraInfo } from '../extra-info.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IExtraInfo for edit and NewExtraInfoFormGroupInput for create.
 */
type ExtraInfoFormGroupInput = IExtraInfo | PartialWithRequiredKeyOf<NewExtraInfo>;

type ExtraInfoFormDefaults = Pick<NewExtraInfo, 'id'>;

type ExtraInfoFormGroupContent = {
  id: FormControl<IExtraInfo['id'] | NewExtraInfo['id']>;
  audienceFeedback: FormControl<IExtraInfo['audienceFeedback']>;
  relatedVideos: FormControl<IExtraInfo['relatedVideos']>;
};

export type ExtraInfoFormGroup = FormGroup<ExtraInfoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ExtraInfoFormService {
  createExtraInfoFormGroup(extraInfo: ExtraInfoFormGroupInput = { id: null }): ExtraInfoFormGroup {
    const extraInfoRawValue = {
      ...this.getFormDefaults(),
      ...extraInfo,
    };
    return new FormGroup<ExtraInfoFormGroupContent>({
      id: new FormControl(
        { value: extraInfoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      audienceFeedback: new FormControl(extraInfoRawValue.audienceFeedback),
      relatedVideos: new FormControl(extraInfoRawValue.relatedVideos),
    });
  }

  getExtraInfo(form: ExtraInfoFormGroup): IExtraInfo | NewExtraInfo {
    return form.getRawValue() as IExtraInfo | NewExtraInfo;
  }

  resetForm(form: ExtraInfoFormGroup, extraInfo: ExtraInfoFormGroupInput): void {
    const extraInfoRawValue = { ...this.getFormDefaults(), ...extraInfo };
    form.reset(
      {
        ...extraInfoRawValue,
        id: { value: extraInfoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ExtraInfoFormDefaults {
    return {
      id: null,
    };
  }
}
