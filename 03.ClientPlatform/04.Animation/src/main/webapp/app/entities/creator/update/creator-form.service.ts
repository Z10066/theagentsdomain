import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreator, NewCreator } from '../creator.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreator for edit and NewCreatorFormGroupInput for create.
 */
type CreatorFormGroupInput = ICreator | PartialWithRequiredKeyOf<NewCreator>;

type CreatorFormDefaults = Pick<NewCreator, 'id'>;

type CreatorFormGroupContent = {
  id: FormControl<ICreator['id'] | NewCreator['id']>;
  name: FormControl<ICreator['name']>;
};

export type CreatorFormGroup = FormGroup<CreatorFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreatorFormService {
  createCreatorFormGroup(creator: CreatorFormGroupInput = { id: null }): CreatorFormGroup {
    const creatorRawValue = {
      ...this.getFormDefaults(),
      ...creator,
    };
    return new FormGroup<CreatorFormGroupContent>({
      id: new FormControl(
        { value: creatorRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(creatorRawValue.name, {
        validators: [Validators.required],
      }),
    });
  }

  getCreator(form: CreatorFormGroup): ICreator | NewCreator {
    return form.getRawValue() as ICreator | NewCreator;
  }

  resetForm(form: CreatorFormGroup, creator: CreatorFormGroupInput): void {
    const creatorRawValue = { ...this.getFormDefaults(), ...creator };
    form.reset(
      {
        ...creatorRawValue,
        id: { value: creatorRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CreatorFormDefaults {
    return {
      id: null,
    };
  }
}
