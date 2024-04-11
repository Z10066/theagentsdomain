import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMember, NewMember } from '../member.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMember for edit and NewMemberFormGroupInput for create.
 */
type MemberFormGroupInput = IMember | PartialWithRequiredKeyOf<NewMember>;

type MemberFormDefaults = Pick<NewMember, 'id' | 'activeStatus'>;

type MemberFormGroupContent = {
  id: FormControl<IMember['id'] | NewMember['id']>;
  username: FormControl<IMember['username']>;
  fullName: FormControl<IMember['fullName']>;
  role: FormControl<IMember['role']>;
  activeStatus: FormControl<IMember['activeStatus']>;
  workspace: FormControl<IMember['workspace']>;
};

export type MemberFormGroup = FormGroup<MemberFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MemberFormService {
  createMemberFormGroup(member: MemberFormGroupInput = { id: null }): MemberFormGroup {
    const memberRawValue = {
      ...this.getFormDefaults(),
      ...member,
    };
    return new FormGroup<MemberFormGroupContent>({
      id: new FormControl(
        { value: memberRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      username: new FormControl(memberRawValue.username, {
        validators: [Validators.required],
      }),
      fullName: new FormControl(memberRawValue.fullName, {
        validators: [Validators.required],
      }),
      role: new FormControl(memberRawValue.role),
      activeStatus: new FormControl(memberRawValue.activeStatus),
      workspace: new FormControl(memberRawValue.workspace),
    });
  }

  getMember(form: MemberFormGroup): IMember | NewMember {
    return form.getRawValue() as IMember | NewMember;
  }

  resetForm(form: MemberFormGroup, member: MemberFormGroupInput): void {
    const memberRawValue = { ...this.getFormDefaults(), ...member };
    form.reset(
      {
        ...memberRawValue,
        id: { value: memberRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MemberFormDefaults {
    return {
      id: null,
      activeStatus: false,
    };
  }
}
