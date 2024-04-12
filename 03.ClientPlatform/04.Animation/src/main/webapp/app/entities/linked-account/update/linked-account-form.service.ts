import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ILinkedAccount, NewLinkedAccount } from '../linked-account.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILinkedAccount for edit and NewLinkedAccountFormGroupInput for create.
 */
type LinkedAccountFormGroupInput = ILinkedAccount | PartialWithRequiredKeyOf<NewLinkedAccount>;

type LinkedAccountFormDefaults = Pick<NewLinkedAccount, 'id'>;

type LinkedAccountFormGroupContent = {
  id: FormControl<ILinkedAccount['id'] | NewLinkedAccount['id']>;
  accountType: FormControl<ILinkedAccount['accountType']>;
  accountIdentifier: FormControl<ILinkedAccount['accountIdentifier']>;
  member: FormControl<ILinkedAccount['member']>;
};

export type LinkedAccountFormGroup = FormGroup<LinkedAccountFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LinkedAccountFormService {
  createLinkedAccountFormGroup(linkedAccount: LinkedAccountFormGroupInput = { id: null }): LinkedAccountFormGroup {
    const linkedAccountRawValue = {
      ...this.getFormDefaults(),
      ...linkedAccount,
    };
    return new FormGroup<LinkedAccountFormGroupContent>({
      id: new FormControl(
        { value: linkedAccountRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      accountType: new FormControl(linkedAccountRawValue.accountType, {
        validators: [Validators.required],
      }),
      accountIdentifier: new FormControl(linkedAccountRawValue.accountIdentifier, {
        validators: [Validators.required],
      }),
      member: new FormControl(linkedAccountRawValue.member),
    });
  }

  getLinkedAccount(form: LinkedAccountFormGroup): ILinkedAccount | NewLinkedAccount {
    return form.getRawValue() as ILinkedAccount | NewLinkedAccount;
  }

  resetForm(form: LinkedAccountFormGroup, linkedAccount: LinkedAccountFormGroupInput): void {
    const linkedAccountRawValue = { ...this.getFormDefaults(), ...linkedAccount };
    form.reset(
      {
        ...linkedAccountRawValue,
        id: { value: linkedAccountRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): LinkedAccountFormDefaults {
    return {
      id: null,
    };
  }
}
