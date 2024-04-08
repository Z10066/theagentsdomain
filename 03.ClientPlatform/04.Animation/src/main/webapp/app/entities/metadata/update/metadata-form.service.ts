import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMetadata, NewMetadata } from '../metadata.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMetadata for edit and NewMetadataFormGroupInput for create.
 */
type MetadataFormGroupInput = IMetadata | PartialWithRequiredKeyOf<NewMetadata>;

type MetadataFormDefaults = Pick<NewMetadata, 'id'>;

type MetadataFormGroupContent = {
  id: FormControl<IMetadata['id'] | NewMetadata['id']>;
  audienceFeedback: FormControl<IMetadata['audienceFeedback']>;
  relatedVideos: FormControl<IMetadata['relatedVideos']>;
};

export type MetadataFormGroup = FormGroup<MetadataFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MetadataFormService {
  createMetadataFormGroup(metadata: MetadataFormGroupInput = { id: null }): MetadataFormGroup {
    const metadataRawValue = {
      ...this.getFormDefaults(),
      ...metadata,
    };
    return new FormGroup<MetadataFormGroupContent>({
      id: new FormControl(
        { value: metadataRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      audienceFeedback: new FormControl(metadataRawValue.audienceFeedback),
      relatedVideos: new FormControl(metadataRawValue.relatedVideos),
    });
  }

  getMetadata(form: MetadataFormGroup): IMetadata | NewMetadata {
    return form.getRawValue() as IMetadata | NewMetadata;
  }

  resetForm(form: MetadataFormGroup, metadata: MetadataFormGroupInput): void {
    const metadataRawValue = { ...this.getFormDefaults(), ...metadata };
    form.reset(
      {
        ...metadataRawValue,
        id: { value: metadataRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MetadataFormDefaults {
    return {
      id: null,
    };
  }
}
