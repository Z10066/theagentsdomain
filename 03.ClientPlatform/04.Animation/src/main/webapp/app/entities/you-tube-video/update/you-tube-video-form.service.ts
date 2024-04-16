import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IYouTubeVideo, NewYouTubeVideo } from '../you-tube-video.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IYouTubeVideo for edit and NewYouTubeVideoFormGroupInput for create.
 */
type YouTubeVideoFormGroupInput = IYouTubeVideo | PartialWithRequiredKeyOf<NewYouTubeVideo>;

type YouTubeVideoFormDefaults = Pick<NewYouTubeVideo, 'id'>;

type YouTubeVideoFormGroupContent = {
  id: FormControl<IYouTubeVideo['id'] | NewYouTubeVideo['id']>;
  workspace: FormControl<IYouTubeVideo['workspace']>;
  creator: FormControl<IYouTubeVideo['creator']>;
  theme: FormControl<IYouTubeVideo['theme']>;
  content: FormControl<IYouTubeVideo['content']>;
  backgroundMusic: FormControl<IYouTubeVideo['backgroundMusic']>;
};

export type YouTubeVideoFormGroup = FormGroup<YouTubeVideoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class YouTubeVideoFormService {
  createYouTubeVideoFormGroup(youTubeVideo: YouTubeVideoFormGroupInput = { id: null }): YouTubeVideoFormGroup {
    const youTubeVideoRawValue = {
      ...this.getFormDefaults(),
      ...youTubeVideo,
    };
    return new FormGroup<YouTubeVideoFormGroupContent>({
      id: new FormControl(
        { value: youTubeVideoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      workspace: new FormControl(youTubeVideoRawValue.workspace, {
        validators: [Validators.required],
      }),
      creator: new FormControl(youTubeVideoRawValue.creator, {
        validators: [Validators.required],
      }),
      theme: new FormControl(youTubeVideoRawValue.theme, {
        validators: [Validators.required],
      }),
      content: new FormControl(youTubeVideoRawValue.content, {
        validators: [Validators.required],
      }),
      backgroundMusic: new FormControl(youTubeVideoRawValue.backgroundMusic, {
        validators: [Validators.required],
      }),
    });
  }

  getYouTubeVideo(form: YouTubeVideoFormGroup): IYouTubeVideo | NewYouTubeVideo {
    return form.getRawValue() as IYouTubeVideo | NewYouTubeVideo;
  }

  resetForm(form: YouTubeVideoFormGroup, youTubeVideo: YouTubeVideoFormGroupInput): void {
    const youTubeVideoRawValue = { ...this.getFormDefaults(), ...youTubeVideo };
    form.reset(
      {
        ...youTubeVideoRawValue,
        id: { value: youTubeVideoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): YouTubeVideoFormDefaults {
    return {
      id: null,
    };
  }
}
