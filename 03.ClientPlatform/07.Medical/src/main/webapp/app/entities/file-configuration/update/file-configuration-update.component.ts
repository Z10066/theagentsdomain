import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IFileConfiguration, FileConfiguration } from '../file-configuration.model';
import { FileConfigurationService } from '../service/file-configuration.service';

@Component({
  selector: 'jhi-file-configuration-update',
  templateUrl: './file-configuration-update.component.html',
})
export class FileConfigurationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [],
    path: [null, [Validators.required]],
    types: [null, [Validators.required]],
  });

  constructor(
    protected fileConfigurationService: FileConfigurationService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileConfiguration }) => {
      this.updateForm(fileConfiguration);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fileConfiguration = this.createFromForm();
    if (fileConfiguration.id !== undefined) {
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
    this.editForm.patchValue({
      id: fileConfiguration.id,
      name: fileConfiguration.name,
      description: fileConfiguration.description,
      path: fileConfiguration.path,
      types: fileConfiguration.types,
    });
  }

  protected createFromForm(): IFileConfiguration {
    return {
      ...new FileConfiguration(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      path: this.editForm.get(['path'])!.value,
      types: this.editForm.get(['types'])!.value,
    };
  }
}
