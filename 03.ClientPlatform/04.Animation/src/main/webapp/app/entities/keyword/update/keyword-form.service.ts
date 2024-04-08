import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IKeyword, NewKeyword } from '../keyword.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IKeyword for edit and NewKeywordFormGroupInput for create.
 */
type KeywordFormGroupInput = IKeyword | PartialWithRequiredKeyOf<NewKeyword>;

type KeywordFormDefaults = Pick<NewKeyword, 'id'>;

type KeywordFormGroupContent = {
  id: FormControl<IKeyword['id'] | NewKeyword['id']>;
  word: FormControl<IKeyword['word']>;
};

export type KeywordFormGroup = FormGroup<KeywordFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class KeywordFormService {
  createKeywordFormGroup(keyword: KeywordFormGroupInput = { id: null }): KeywordFormGroup {
    const keywordRawValue = {
      ...this.getFormDefaults(),
      ...keyword,
    };
    return new FormGroup<KeywordFormGroupContent>({
      id: new FormControl(
        { value: keywordRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      word: new FormControl(keywordRawValue.word, {
        validators: [Validators.required],
      }),
    });
  }

  getKeyword(form: KeywordFormGroup): IKeyword | NewKeyword {
    return form.getRawValue() as IKeyword | NewKeyword;
  }

  resetForm(form: KeywordFormGroup, keyword: KeywordFormGroupInput): void {
    const keywordRawValue = { ...this.getFormDefaults(), ...keyword };
    form.reset(
      {
        ...keywordRawValue,
        id: { value: keywordRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): KeywordFormDefaults {
    return {
      id: null,
    };
  }
}
