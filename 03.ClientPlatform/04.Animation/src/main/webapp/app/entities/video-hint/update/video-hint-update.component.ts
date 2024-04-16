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
import { VideoHintService } from '../service/video-hint.service';
import { IVideoHint } from '../video-hint.model';
import { VideoHintFormService, VideoHintFormGroup } from './video-hint-form.service';

@Component({
  standalone: true,
  selector: 'jhi-video-hint-update',
  templateUrl: './video-hint-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VideoHintUpdateComponent implements OnInit {
  isSaving = false;
  videoHint: IVideoHint | null = null;

  editForm: VideoHintFormGroup = this.videoHintFormService.createVideoHintFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected videoHintService: VideoHintService,
    protected videoHintFormService: VideoHintFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ videoHint }) => {
      this.videoHint = videoHint;
      if (videoHint) {
        this.updateForm(videoHint);
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
    const videoHint = this.videoHintFormService.getVideoHint(this.editForm);
    if (videoHint.id !== null) {
      this.subscribeToSaveResponse(this.videoHintService.update(videoHint));
    } else {
      this.subscribeToSaveResponse(this.videoHintService.create(videoHint));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVideoHint>>): void {
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

  protected updateForm(videoHint: IVideoHint): void {
    this.videoHint = videoHint;
    this.videoHintFormService.resetForm(this.editForm, videoHint);
  }
}
