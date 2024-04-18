import { HttpResponse } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { ICategory } from 'app/entities/category/category.model';
import { CategoryService } from 'app/entities/category/service/category.service';
import { ICopyright } from 'app/entities/copyright/copyright.model';
import { CopyrightService } from 'app/entities/copyright/service/copyright.service';
import { ICreator } from 'app/entities/creator/creator.model';
import { CreatorService } from 'app/entities/creator/service/creator.service';
import { IKeyword } from 'app/entities/keyword/keyword.model';
import { KeywordService } from 'app/entities/keyword/service/keyword.service';
import { IMetadata } from 'app/entities/metadata/metadata.model';
import { MetadataService } from 'app/entities/metadata/service/metadata.service';
import { VideoHintService } from 'app/entities/video-hint/service/video-hint.service';
import { VideoHintFormGroup, VideoHintFormService } from 'app/entities/video-hint/update/video-hint-form.service';
import { IVideoHint } from 'app/entities/video-hint/video-hint.model';
import { VideoService } from 'app/entities/video/service/video.service';
import { VideoFormGroup, VideoFormService } from 'app/entities/video/update/video-form.service';
import { IVideo } from 'app/entities/video/video.model';
import { WorkspaceComponent } from 'app/entities/workspace/list/workspace.component';
import { FootMenuComponent } from 'app/layouts/foot-menu/foot-menu.component';
import { AlertError } from 'app/shared/alert/alert-error.model';
import SharedModule from 'app/shared/shared.module';
import { Observable } from 'rxjs/internal/Observable';
import { finalize } from 'rxjs/internal/operators/finalize';
import { map } from 'rxjs/internal/operators/map';

@Component({
  selector: 'jhi-createvideoprompt',
  standalone: true,
  imports: [SharedModule, RouterModule,FootMenuComponent,ReactiveFormsModule],
  templateUrl: './createVideoPrompt.html',
  styleUrl: './createVideoPromptStyles.scss'
})
export class CreatevideopromptComponent implements OnInit {
  // constructor(
  //   public router: Router
  //   ){
      
  //   }
  // continue() {
  //   this.router.navigate(['/ThinkingScreen']);
  // }

  isSaving = false;
  video: IVideo | null = null;

  creatorsCollection: ICreator[] = [];
  categoriesCollection: ICategory[] = [];
  copyrightsCollection: ICopyright[] = [];
  extraInfosCollection: IMetadata[] = [];
  keywordsSharedCollection: IKeyword[] = [];

  //editForm: VideoFormGroup = this.videoFormService.createVideoFormGroup();
  editForm: VideoHintFormGroup = this.videoHintFormService.createVideoHintFormGroup();

  addValue = "wang"

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected videoService: VideoService,
    protected videoFormService: VideoFormService,
    protected creatorService: CreatorService,
    protected categoryService: CategoryService,
    protected copyrightService: CopyrightService,
    protected metadataService: MetadataService,
    protected keywordService: KeywordService,
    protected activatedRoute: ActivatedRoute,
    protected videoHintService: VideoHintService,
    protected videoHintFormService: VideoHintFormService,

  ) {}

  compareCreator = (o1: ICreator | null, o2: ICreator | null): boolean => this.creatorService.compareCreator(o1, o2);

  compareCategory = (o1: ICategory | null, o2: ICategory | null): boolean => this.categoryService.compareCategory(o1, o2);

  compareCopyright = (o1: ICopyright | null, o2: ICopyright | null): boolean => this.copyrightService.compareCopyright(o1, o2);

  compareMetadata = (o1: IMetadata | null, o2: IMetadata | null): boolean => this.metadataService.compareMetadata(o1, o2);

  compareKeyword = (o1: IKeyword | null, o2: IKeyword | null): boolean => this.keywordService.compareKeyword(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ videoHint }) => {
      this.videoHint = videoHint;
      if (videoHint) {
        this.updateForm(videoHint);
      }

      this.loadRelationshipsOptions();
    });

    //workspace设定值
    this.editForm.get('workspace')?.setValue(this.addValue)
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

  protected loadRelationshipsOptions(): void {
    this.creatorService
      .query({ filter: 'name' })
      .pipe(map((res: HttpResponse<ICreator[]>) => res.body ?? []))
      .pipe(map((creators: ICreator[]) => this.creatorService.addCreatorToCollectionIfMissing<ICreator>(creators, this.video?.creator)))
      .subscribe((creators: ICreator[]) => (this.creatorsCollection = creators));

    this.categoryService
      .query({ filter: 'name' })
      .pipe(map((res: HttpResponse<ICategory[]>) => res.body ?? []))
      .pipe(
        map((categories: ICategory[]) =>
          this.categoryService.addCategoryToCollectionIfMissing<ICategory>(categories, this.video?.category),
        ),
      )
      .subscribe((categories: ICategory[]) => (this.categoriesCollection = categories));

    this.copyrightService
      .query({ filter: 'details' })
      .pipe(map((res: HttpResponse<ICopyright[]>) => res.body ?? []))
      .pipe(
        map((copyrights: ICopyright[]) =>
          this.copyrightService.addCopyrightToCollectionIfMissing<ICopyright>(copyrights, this.video?.copyright),
        ),
      )
      .subscribe((copyrights: ICopyright[]) => (this.copyrightsCollection = copyrights));

    this.metadataService
      .query({ filter: 'related_videos' })
      .pipe(map((res: HttpResponse<IMetadata[]>) => res.body ?? []))
      .pipe(
        map((metadata: IMetadata[]) => this.metadataService.addMetadataToCollectionIfMissing<IMetadata>(metadata, this.video?.extraInfo)),
      )
      .subscribe((metadata: IMetadata[]) => (this.extraInfosCollection = metadata));

    this.keywordService
      .query()
      .pipe(map((res: HttpResponse<IKeyword[]>) => res.body ?? []))
      .pipe(
        map((keywords: IKeyword[]) =>
          this.keywordService.addKeywordToCollectionIfMissing<IKeyword>(keywords, ...(this.video?.keywords ?? [])),
        ),
      )
      .subscribe((keywords: IKeyword[]) => (this.keywordsSharedCollection = keywords));
  }


  //入力框计数
  updateCharCount(event: Event): void {
    const target = event.target as HTMLTextAreaElement;
    const charCount = target.value.length;
    document.getElementById('charCount')!.textContent = charCount.toString();
  }


  videoHint: IVideoHint | null = null;







 



 

}


