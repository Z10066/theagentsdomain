import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { MetadataService } from '../service/metadata.service';
import { IMetadata } from '../metadata.model';
import { MetadataFormService, MetadataFormGroup } from './metadata-form.service';

@Component({
  standalone: true,
  selector: 'jhi-metadata-update',
  templateUrl: './metadata-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MetadataUpdateComponent implements OnInit {
  isSaving = false;
  metadata: IMetadata | null = null;

  editForm: MetadataFormGroup = this.metadataFormService.createMetadataFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected metadataService: MetadataService,
    protected metadataFormService: MetadataFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ metadata }) => {
      this.metadata = metadata;
      if (metadata) {
        this.updateForm(metadata);
      }
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('highwayacApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const metadata = this.metadataFormService.getMetadata(this.editForm);
    if (metadata.id !== null) {
      this.subscribeToSaveResponse(this.metadataService.update(metadata));
    } else {
      this.subscribeToSaveResponse(this.metadataService.create(metadata));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMetadata>>): void {
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

  protected updateForm(metadata: IMetadata): void {
    this.metadata = metadata;
    this.metadataFormService.resetForm(this.editForm, metadata);
  }
}
