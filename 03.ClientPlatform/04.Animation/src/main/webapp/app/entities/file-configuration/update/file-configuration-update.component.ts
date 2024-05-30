import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFileConfiguration } from '../file-configuration.model';
import { FileConfigurationService } from '../service/file-configuration.service';
import { FileConfigurationFormService, FileConfigurationFormGroup } from './file-configuration-form.service';

@Component({
  standalone: true,
  selector: 'jhi-file-configuration-update',
  templateUrl: './file-configuration-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FileConfigurationUpdateComponent implements OnInit {
  isSaving = false;
  fileConfiguration: IFileConfiguration | null = null;

  editForm: FileConfigurationFormGroup = this.fileConfigurationFormService.createFileConfigurationFormGroup();

  constructor(
    protected fileConfigurationService: FileConfigurationService,
    protected fileConfigurationFormService: FileConfigurationFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileConfiguration }) => {
      this.fileConfiguration = fileConfiguration;
      if (fileConfiguration) {
        this.updateForm(fileConfiguration);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fileConfiguration = this.fileConfigurationFormService.getFileConfiguration(this.editForm);
    if (fileConfiguration.id !== null) {
      this.subscribeToSaveResponse(this.fileConfigurationService.update(fileConfiguration));
    } else {
      this.subscribeToSaveResponse(this.fileConfigurationService.create(fileConfiguration));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFileConfiguration>>): void {
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

  protected updateForm(fileConfiguration: IFileConfiguration): void {
    this.fileConfiguration = fileConfiguration;
    this.fileConfigurationFormService.resetForm(this.editForm, fileConfiguration);
  }
}
