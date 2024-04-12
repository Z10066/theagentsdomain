import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IWorkspace, NewWorkspace } from '../workspace.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IWorkspace for edit and NewWorkspaceFormGroupInput for create.
 */
type WorkspaceFormGroupInput = IWorkspace | PartialWithRequiredKeyOf<NewWorkspace>;

type WorkspaceFormDefaults = Pick<NewWorkspace, 'id' | 'betaFeatures' | 'collaborationCursor' | 'defaultExportVisibility' | 'publicAccess'>;

type WorkspaceFormGroupContent = {
  id: FormControl<IWorkspace['id'] | NewWorkspace['id']>;
  name: FormControl<IWorkspace['name']>;
  identifier: FormControl<IWorkspace['identifier']>;
  betaFeatures: FormControl<IWorkspace['betaFeatures']>;
  collaborationCursor: FormControl<IWorkspace['collaborationCursor']>;
  defaultExportVisibility: FormControl<IWorkspace['defaultExportVisibility']>;
  publicAccess: FormControl<IWorkspace['publicAccess']>;
};

export type WorkspaceFormGroup = FormGroup<WorkspaceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class WorkspaceFormService {
  createWorkspaceFormGroup(workspace: WorkspaceFormGroupInput = { id: null }): WorkspaceFormGroup {
    const workspaceRawValue = {
      ...this.getFormDefaults(),
      ...workspace,
    };
    return new FormGroup<WorkspaceFormGroupContent>({
      id: new FormControl(
        { value: workspaceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(workspaceRawValue.name, {
        validators: [Validators.required],
      }),
      identifier: new FormControl(workspaceRawValue.identifier, {
        validators: [Validators.required],
      }),
      betaFeatures: new FormControl(workspaceRawValue.betaFeatures),
      collaborationCursor: new FormControl(workspaceRawValue.collaborationCursor),
      defaultExportVisibility: new FormControl(workspaceRawValue.defaultExportVisibility),
      publicAccess: new FormControl(workspaceRawValue.publicAccess),
    });
  }

  getWorkspace(form: WorkspaceFormGroup): IWorkspace | NewWorkspace {
    return form.getRawValue() as IWorkspace | NewWorkspace;
  }

  resetForm(form: WorkspaceFormGroup, workspace: WorkspaceFormGroupInput): void {
    const workspaceRawValue = { ...this.getFormDefaults(), ...workspace };
    form.reset(
      {
        ...workspaceRawValue,
        id: { value: workspaceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): WorkspaceFormDefaults {
    return {
      id: null,
      betaFeatures: false,
      collaborationCursor: false,
      defaultExportVisibility: false,
      publicAccess: false,
    };
  }
}
