import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EventManager } from 'app/core/util/event-manager.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { EventWithContent } from 'app/core/util/event-manager.service';
import { YouTubeVideoService } from 'app/entities/you-tube-video/service/you-tube-video.service';
import { YouTubeVideoFormGroup, YouTubeVideoFormService } from 'app/entities/you-tube-video/update/you-tube-video-form.service';
import { IYouTubeVideo } from 'app/entities/you-tube-video/you-tube-video.model';
import { FootMenuComponent } from 'app/layouts/foot-menu/foot-menu.component';
import SharedModule from 'app/shared/shared.module';
import { Observable, finalize } from 'rxjs';
import { IWorkspace } from 'app/entities/workspace/workspace.model';

@Component({
  selector: 'jhi-createxplainer-prompt',
  standalone: true,
  imports: [SharedModule, RouterModule,FootMenuComponent],
  templateUrl: './createExplainerPrompt.html',
  styleUrl: './createExplainerPromptStyles.scss'
})
export class CreatexplainerPromptComponent implements OnInit {
  isSaving = false;
  youTubeVideo: IYouTubeVideo | null = null;


  editForm: YouTubeVideoFormGroup = this.youTubeVideoFormService.createYouTubeVideoFormGroup();
  workspaceService: any;
  workspaces: IWorkspace[] = [];
  constructor(
    public router: Router
    ){
      
    }
  continue() {
    this.router.navigate(['/ThinkingScreen']);
  }
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ youTubeVideo }) => {
      this.youTubeVideo = youTubeVideo;
      if (youTubeVideo) {
        this.updateForm(youTubeVideo);
      }
    });

    this.loadWorkspaces();
  }
  loadWorkspaces(): void {
    this.workspaceService.query().subscribe((response: { body: never[]; }) => {
      this.workspaces = response.body ?? [];
      this.editForm.get('workspace')?.setValue(this.workspaces[0].id.toString())
      // 在这里处理工作区数据
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
    console.log("save")
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
