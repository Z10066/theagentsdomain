import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IVideoHint, NewVideoHint } from '../video-hint.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVideoHint for edit and NewVideoHintFormGroupInput for create.
 */
type VideoHintFormGroupInput = IVideoHint | PartialWithRequiredKeyOf<NewVideoHint>;

type VideoHintFormDefaults = Pick<NewVideoHint, 'id'>;

type VideoHintFormGroupContent = {
  id: FormControl<IVideoHint['id'] | NewVideoHint['id']>;
  workspace: FormControl<IVideoHint['workspace']>;
  creator: FormControl<IVideoHint['creator']>;
  creationContent: FormControl<IVideoHint['creationContent']>;
  backgroundMusic: FormControl<IVideoHint['backgroundMusic']>;
};

export type VideoHintFormGroup = FormGroup<VideoHintFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VideoHintFormService {
  createVideoHintFormGroup(videoHint: VideoHintFormGroupInput = { id: null }): VideoHintFormGroup {
    const videoHintRawValue = {
      ...this.getFormDefaults(),
      ...videoHint,
    };
    return new FormGroup<VideoHintFormGroupContent>({
      id: new FormControl(
        { value: videoHintRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      workspace: new FormControl(videoHintRawValue.workspace, {
        validators: [Validators.required],
      }),
      creator: new FormControl(videoHintRawValue.creator, {
        validators: [Validators.required],
      }),
      creationContent: new FormControl(videoHintRawValue.creationContent, {
        validators: [Validators.required],
      }),
      backgroundMusic: new FormControl(videoHintRawValue.backgroundMusic, {
        validators: [Validators.required],
      }),
    });
  }

  getVideoHint(form: VideoHintFormGroup): IVideoHint | NewVideoHint {
    return form.getRawValue() as IVideoHint | NewVideoHint;
  }

  resetForm(form: VideoHintFormGroup, videoHint: VideoHintFormGroupInput): void {
    const videoHintRawValue = { ...this.getFormDefaults(), ...videoHint };
    form.reset(
      {
        ...videoHintRawValue,
        id: { value: videoHintRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VideoHintFormDefaults {
    return {
      id: null,
    };
  }
}
