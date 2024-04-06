import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IVideo, NewVideo } from '../video.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVideo for edit and NewVideoFormGroupInput for create.
 */
type VideoFormGroupInput = IVideo | PartialWithRequiredKeyOf<NewVideo>;

type VideoFormDefaults = Pick<NewVideo, 'id' | 'keywords'>;

type VideoFormGroupContent = {
  id: FormControl<IVideo['id'] | NewVideo['id']>;
  title: FormControl<IVideo['title']>;
  duration: FormControl<IVideo['duration']>;
  format: FormControl<IVideo['format']>;
  resolution: FormControl<IVideo['resolution']>;
  publishedDate: FormControl<IVideo['publishedDate']>;
  description: FormControl<IVideo['description']>;
  viewingLink: FormControl<IVideo['viewingLink']>;
  creator: FormControl<IVideo['creator']>;
  category: FormControl<IVideo['category']>;
  copyright: FormControl<IVideo['copyright']>;
  extraInfo: FormControl<IVideo['extraInfo']>;
  keywords: FormControl<IVideo['keywords']>;
};

export type VideoFormGroup = FormGroup<VideoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VideoFormService {
  createVideoFormGroup(video: VideoFormGroupInput = { id: null }): VideoFormGroup {
    const videoRawValue = {
      ...this.getFormDefaults(),
      ...video,
    };
    return new FormGroup<VideoFormGroupContent>({
      id: new FormControl(
        { value: videoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      title: new FormControl(videoRawValue.title, {
        validators: [Validators.required],
      }),
      duration: new FormControl(videoRawValue.duration),
      format: new FormControl(videoRawValue.format),
      resolution: new FormControl(videoRawValue.resolution),
      publishedDate: new FormControl(videoRawValue.publishedDate),
      description: new FormControl(videoRawValue.description),
      viewingLink: new FormControl(videoRawValue.viewingLink),
      creator: new FormControl(videoRawValue.creator, {
        validators: [Validators.required],
      }),
      category: new FormControl(videoRawValue.category, {
        validators: [Validators.required],
      }),
      copyright: new FormControl(videoRawValue.copyright, {
        validators: [Validators.required],
      }),
      extraInfo: new FormControl(videoRawValue.extraInfo),
      keywords: new FormControl(videoRawValue.keywords ?? []),
    });
  }

  getVideo(form: VideoFormGroup): IVideo | NewVideo {
    return form.getRawValue() as IVideo | NewVideo;
  }

  resetForm(form: VideoFormGroup, video: VideoFormGroupInput): void {
    const videoRawValue = { ...this.getFormDefaults(), ...video };
    form.reset(
      {
        ...videoRawValue,
        id: { value: videoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VideoFormDefaults {
    return {
      id: null,
      keywords: [],
    };
  }
}
