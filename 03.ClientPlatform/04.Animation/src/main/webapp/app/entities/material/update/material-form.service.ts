import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMaterial, NewMaterial } from '../material.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMaterial for edit and NewMaterialFormGroupInput for create.
 */
type MaterialFormGroupInput = IMaterial | PartialWithRequiredKeyOf<NewMaterial>;

type MaterialFormDefaults = Pick<NewMaterial, 'id'>;

type MaterialFormGroupContent = {
  id: FormControl<IMaterial['id'] | NewMaterial['id']>;
  title: FormControl<IMaterial['title']>;
  creator: FormControl<IMaterial['creator']>;
  description: FormControl<IMaterial['description']>;
  copyright: FormControl<IMaterial['copyright']>;
  watchLink: FormControl<IMaterial['watchLink']>;
  extraInfo: FormControl<IMaterial['extraInfo']>;
  workspace: FormControl<IMaterial['workspace']>;
};

export type MaterialFormGroup = FormGroup<MaterialFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MaterialFormService {
  createMaterialFormGroup(material: MaterialFormGroupInput = { id: null }): MaterialFormGroup {
    const materialRawValue = {
      ...this.getFormDefaults(),
      ...material,
    };
    return new FormGroup<MaterialFormGroupContent>({
      id: new FormControl(
        { value: materialRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      title: new FormControl(materialRawValue.title, {
        validators: [Validators.required],
      }),
      creator: new FormControl(materialRawValue.creator, {
        validators: [Validators.required],
      }),
      description: new FormControl(materialRawValue.description),
      copyright: new FormControl(materialRawValue.copyright),
      watchLink: new FormControl(materialRawValue.watchLink),
      extraInfo: new FormControl(materialRawValue.extraInfo),
      workspace: new FormControl(materialRawValue.workspace),
    });
  }

  getMaterial(form: MaterialFormGroup): IMaterial | NewMaterial {
    return form.getRawValue() as IMaterial | NewMaterial;
  }

  resetForm(form: MaterialFormGroup, material: MaterialFormGroupInput): void {
    const materialRawValue = { ...this.getFormDefaults(), ...material };
    form.reset(
      {
        ...materialRawValue,
        id: { value: materialRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MaterialFormDefaults {
    return {
      id: null,
    };
  }
}
