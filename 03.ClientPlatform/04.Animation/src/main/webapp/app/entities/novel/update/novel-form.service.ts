import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { INovel, NewNovel } from '../novel.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts INovel for edit and NewNovelFormGroupInput for create.
 */
type NovelFormGroupInput = INovel | PartialWithRequiredKeyOf<NewNovel>;

type NovelFormDefaults = Pick<NewNovel, 'id'>;

type NovelFormGroupContent = {
  id: FormControl<INovel['id'] | NewNovel['id']>;
  noveltext: FormControl<INovel['noveltext']>;
  novelname: FormControl<INovel['novelname']>;
  noveltype: FormControl<INovel['noveltype']>;
};

export type NovelFormGroup = FormGroup<NovelFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class NovelFormService {
  createNovelFormGroup(novel: NovelFormGroupInput = { id: null }): NovelFormGroup {
    const novelRawValue = {
      ...this.getFormDefaults(),
      ...novel,
    };
    return new FormGroup<NovelFormGroupContent>({
      id: new FormControl(
        { value: novelRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      noveltext: new FormControl(novelRawValue.noveltext, {
        validators: [Validators.required],
      }),
      novelname: new FormControl(novelRawValue.novelname, {
        validators: [Validators.required],
      }),
      noveltype: new FormControl(novelRawValue.noveltype),
    });
  }

  getNovel(form: NovelFormGroup): INovel | NewNovel {
    return form.getRawValue() as INovel | NewNovel;
  }

  resetForm(form: NovelFormGroup, novel: NovelFormGroupInput): void {
    const novelRawValue = { ...this.getFormDefaults(), ...novel };
    form.reset(
      {
        ...novelRawValue,
        id: { value: novelRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): NovelFormDefaults {
    return {
      id: null,
    };
  }
}
