import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFileConfiguration, NewFileConfiguration } from '../file-configuration.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFileConfiguration for edit and NewFileConfigurationFormGroupInput for create.
 */
type FileConfigurationFormGroupInput = IFileConfiguration | PartialWithRequiredKeyOf<NewFileConfiguration>;

type FileConfigurationFormDefaults = Pick<NewFileConfiguration, 'id'>;

type FileConfigurationFormGroupContent = {
  id: FormControl<IFileConfiguration['id'] | NewFileConfiguration['id']>;
  name: FormControl<IFileConfiguration['name']>;
  description: FormControl<IFileConfiguration['description']>;
  path: FormControl<IFileConfiguration['path']>;
  types: FormControl<IFileConfiguration['types']>;
};

export type FileConfigurationFormGroup = FormGroup<FileConfigurationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FileConfigurationFormService {
  createFileConfigurationFormGroup(fileConfiguration: FileConfigurationFormGroupInput = { id: null }): FileConfigurationFormGroup {
    const fileConfigurationRawValue = {
      ...this.getFormDefaults(),
      ...fileConfiguration,
    };
    return new FormGroup<FileConfigurationFormGroupContent>({
      id: new FormControl(
        { value: fileConfigurationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(fileConfigurationRawValue.name, {
        validators: [Validators.required],
      }),
      description: new FormControl(fileConfigurationRawValue.description),
      path: new FormControl(fileConfigurationRawValue.path, {
        validators: [Validators.required],
      }),
      types: new FormControl(fileConfigurationRawValue.types, {
        validators: [Validators.required],
      }),
    });
  }

  getFileConfiguration(form: FileConfigurationFormGroup): IFileConfiguration | NewFileConfiguration {
    return form.getRawValue() as IFileConfiguration | NewFileConfiguration;
  }

  resetForm(form: FileConfigurationFormGroup, fileConfiguration: FileConfigurationFormGroupInput): void {
    const fileConfigurationRawValue = { ...this.getFormDefaults(), ...fileConfiguration };
    form.reset(
      {
        ...fileConfigurationRawValue,
        id: { value: fileConfigurationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FileConfigurationFormDefaults {
    return {
      id: null,
    };
  }
}
