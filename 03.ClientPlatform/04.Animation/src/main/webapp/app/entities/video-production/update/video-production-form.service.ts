import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IVideoProduction, NewVideoProduction } from '../video-production.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVideoProduction for edit and NewVideoProductionFormGroupInput for create.
 */
type VideoProductionFormGroupInput = IVideoProduction | PartialWithRequiredKeyOf<NewVideoProduction>;

type VideoProductionFormDefaults = Pick<NewVideoProduction, 'id'>;

type VideoProductionFormGroupContent = {
  id: FormControl<IVideoProduction['id'] | NewVideoProduction['id']>;
  title: FormControl<IVideoProduction['title']>;
  creator: FormControl<IVideoProduction['creator']>;
  description: FormControl<IVideoProduction['description']>;
  copyright: FormControl<IVideoProduction['copyright']>;
  watchLink: FormControl<IVideoProduction['watchLink']>;
  extraInfo: FormControl<IVideoProduction['extraInfo']>;
  workspace: FormControl<IVideoProduction['workspace']>;
};

export type VideoProductionFormGroup = FormGroup<VideoProductionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VideoProductionFormService {
  createVideoProductionFormGroup(videoProduction: VideoProductionFormGroupInput = { id: null }): VideoProductionFormGroup {
    const videoProductionRawValue = {
      ...this.getFormDefaults(),
      ...videoProduction,
    };
    return new FormGroup<VideoProductionFormGroupContent>({
      id: new FormControl(
        { value: videoProductionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      title: new FormControl(videoProductionRawValue.title, {
        validators: [Validators.required],
      }),
      creator: new FormControl(videoProductionRawValue.creator, {
        validators: [Validators.required],
      }),
      description: new FormControl(videoProductionRawValue.description),
      copyright: new FormControl(videoProductionRawValue.copyright),
      watchLink: new FormControl(videoProductionRawValue.watchLink),
      extraInfo: new FormControl(videoProductionRawValue.extraInfo),
      workspace: new FormControl(videoProductionRawValue.workspace),
    });
  }

  getVideoProduction(form: VideoProductionFormGroup): IVideoProduction | NewVideoProduction {
    return form.getRawValue() as IVideoProduction | NewVideoProduction;
  }

  resetForm(form: VideoProductionFormGroup, videoProduction: VideoProductionFormGroupInput): void {
    const videoProductionRawValue = { ...this.getFormDefaults(), ...videoProduction };
    form.reset(
      {
        ...videoProductionRawValue,
        id: { value: videoProductionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VideoProductionFormDefaults {
    return {
      id: null,
    };
  }
}
