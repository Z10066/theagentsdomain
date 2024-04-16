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
import { YouTubeVideoService } from '../service/you-tube-video.service';
import { IYouTubeVideo } from '../you-tube-video.model';
import { YouTubeVideoFormService, YouTubeVideoFormGroup } from './you-tube-video-form.service';

@Component({
  standalone: true,
  selector: 'jhi-you-tube-video-update',
  templateUrl: './you-tube-video-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class YouTubeVideoUpdateComponent implements OnInit {
  isSaving = false;
  youTubeVideo: IYouTubeVideo | null = null;

  editForm: YouTubeVideoFormGroup = this.youTubeVideoFormService.createYouTubeVideoFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected youTubeVideoService: YouTubeVideoService,
    protected youTubeVideoFormService: YouTubeVideoFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ youTubeVideo }) => {
      this.youTubeVideo = youTubeVideo;
      if (youTubeVideo) {
        this.updateForm(youTubeVideo);
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
    const youTubeVideo = this.youTubeVideoFormService.getYouTubeVideo(this.editForm);
    if (youTubeVideo.id !== null) {
      this.subscribeToSaveResponse(this.youTubeVideoService.update(youTubeVideo));
    } else {
      this.subscribeToSaveResponse(this.youTubeVideoService.create(youTubeVideo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IYouTubeVideo>>): void {
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

  protected updateForm(youTubeVideo: IYouTubeVideo): void {
    this.youTubeVideo = youTubeVideo;
    this.youTubeVideoFormService.resetForm(this.editForm, youTubeVideo);
  }
}
