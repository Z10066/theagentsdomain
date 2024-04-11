import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IExtraInfo } from '../extra-info.model';
import { ExtraInfoService } from '../service/extra-info.service';
import { ExtraInfoFormService, ExtraInfoFormGroup } from './extra-info-form.service';

@Component({
  standalone: true,
  selector: 'jhi-extra-info-update',
  templateUrl: './extra-info-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ExtraInfoUpdateComponent implements OnInit {
  isSaving = false;
  extraInfo: IExtraInfo | null = null;

  editForm: ExtraInfoFormGroup = this.extraInfoFormService.createExtraInfoFormGroup();

  constructor(
    protected extraInfoService: ExtraInfoService,
    protected extraInfoFormService: ExtraInfoFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ extraInfo }) => {
      this.extraInfo = extraInfo;
      if (extraInfo) {
        this.updateForm(extraInfo);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const extraInfo = this.extraInfoFormService.getExtraInfo(this.editForm);
    if (extraInfo.id !== null) {
      this.subscribeToSaveResponse(this.extraInfoService.update(extraInfo));
    } else {
      this.subscribeToSaveResponse(this.extraInfoService.create(extraInfo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtraInfo>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(extraInfo: IExtraInfo): void {
    this.extraInfo = extraInfo;
    this.extraInfoFormService.resetForm(this.editForm, extraInfo);
  }
}
