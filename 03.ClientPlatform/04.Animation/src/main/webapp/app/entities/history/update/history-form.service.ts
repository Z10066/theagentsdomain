import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IHistory, NewHistory } from '../history.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHistory for edit and NewHistoryFormGroupInput for create.
 */
type HistoryFormGroupInput = IHistory | PartialWithRequiredKeyOf<NewHistory>;

type HistoryFormDefaults = Pick<NewHistory, 'id'>;

type HistoryFormGroupContent = {
  id: FormControl<IHistory['id'] | NewHistory['id']>;
  title: FormControl<IHistory['title']>;
  creator: FormControl<IHistory['creator']>;
  description: FormControl<IHistory['description']>;
  copyright: FormControl<IHistory['copyright']>;
  watchLink: FormControl<IHistory['watchLink']>;
  extraInfo: FormControl<IHistory['extraInfo']>;
  workspace: FormControl<IHistory['workspace']>;
};

export type HistoryFormGroup = FormGroup<HistoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HistoryFormService {
  createHistoryFormGroup(history: HistoryFormGroupInput = { id: null }): HistoryFormGroup {
    const historyRawValue = {
      ...this.getFormDefaults(),
      ...history,
    };
    return new FormGroup<HistoryFormGroupContent>({
      id: new FormControl(
        { value: historyRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      title: new FormControl(historyRawValue.title, {
        validators: [Validators.required],
      }),
      creator: new FormControl(historyRawValue.creator, {
        validators: [Validators.required],
      }),
      description: new FormControl(historyRawValue.description),
      copyright: new FormControl(historyRawValue.copyright),
      watchLink: new FormControl(historyRawValue.watchLink),
      extraInfo: new FormControl(historyRawValue.extraInfo),
      workspace: new FormControl(historyRawValue.workspace),
    });
  }

  getHistory(form: HistoryFormGroup): IHistory | NewHistory {
    return form.getRawValue() as IHistory | NewHistory;
  }

  resetForm(form: HistoryFormGroup, history: HistoryFormGroupInput): void {
    const historyRawValue = { ...this.getFormDefaults(), ...history };
    form.reset(
      {
        ...historyRawValue,
        id: { value: historyRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HistoryFormDefaults {
    return {
      id: null,
    };
  }
}
