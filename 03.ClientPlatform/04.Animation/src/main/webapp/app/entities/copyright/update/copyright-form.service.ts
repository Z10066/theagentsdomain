import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICopyright, NewCopyright } from '../copyright.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICopyright for edit and NewCopyrightFormGroupInput for create.
 */
type CopyrightFormGroupInput = ICopyright | PartialWithRequiredKeyOf<NewCopyright>;

type CopyrightFormDefaults = Pick<NewCopyright, 'id'>;

type CopyrightFormGroupContent = {
  id: FormControl<ICopyright['id'] | NewCopyright['id']>;
  details: FormControl<ICopyright['details']>;
};

export type CopyrightFormGroup = FormGroup<CopyrightFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CopyrightFormService {
  createCopyrightFormGroup(copyright: CopyrightFormGroupInput = { id: null }): CopyrightFormGroup {
    const copyrightRawValue = {
      ...this.getFormDefaults(),
      ...copyright,
    };
    return new FormGroup<CopyrightFormGroupContent>({
      id: new FormControl(
        { value: copyrightRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      details: new FormControl(copyrightRawValue.details, {
        validators: [Validators.required],
      }),
    });
  }

  getCopyright(form: CopyrightFormGroup): ICopyright | NewCopyright {
    return form.getRawValue() as ICopyright | NewCopyright;
  }

  resetForm(form: CopyrightFormGroup, copyright: CopyrightFormGroupInput): void {
    const copyrightRawValue = { ...this.getFormDefaults(), ...copyright };
    form.reset(
      {
        ...copyrightRawValue,
        id: { value: copyrightRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CopyrightFormDefaults {
    return {
      id: null,
    };
  }
}
