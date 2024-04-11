import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ICreator } from 'app/entities/creator/creator.model';
import { CreatorService } from 'app/entities/creator/service/creator.service';
import { ICategory } from 'app/entities/category/category.model';
import { CategoryService } from 'app/entities/category/service/category.service';
import { ICopyright } from 'app/entities/copyright/copyright.model';
import { CopyrightService } from 'app/entities/copyright/service/copyright.service';
import { IMetadata } from 'app/entities/metadata/metadata.model';
import { MetadataService } from 'app/entities/metadata/service/metadata.service';
import { IKeyword } from 'app/entities/keyword/keyword.model';
import { KeywordService } from 'app/entities/keyword/service/keyword.service';
import { VideoService } from '../service/video.service';
import { IVideo } from '../video.model';
import { VideoFormService, VideoFormGroup } from './video-form.service';

@Component({
  standalone: true,
  selector: 'jhi-video-update',
  templateUrl: './video-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VideoUpdateComponent implements OnInit {
  isSaving = false;
  video: IVideo | null = null;

  creatorsCollection: ICreator[] = [];
  categoriesCollection: ICategory[] = [];
  copyrightsCollection: ICopyright[] = [];
  extraInfosCollection: IMetadata[] = [];
  keywordsSharedCollection: IKeyword[] = [];

  editForm: VideoFormGroup = this.videoFormService.createVideoFormGroup();

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
  ) {}

  compareCreator = (o1: ICreator | null, o2: ICreator | null): boolean => this.creatorService.compareCreator(o1, o2);

  compareCategory = (o1: ICategory | null, o2: ICategory | null): boolean => this.categoryService.compareCategory(o1, o2);

  compareCopyright = (o1: ICopyright | null, o2: ICopyright | null): boolean => this.copyrightService.compareCopyright(o1, o2);

  compareMetadata = (o1: IMetadata | null, o2: IMetadata | null): boolean => this.metadataService.compareMetadata(o1, o2);

  compareKeyword = (o1: IKeyword | null, o2: IKeyword | null): boolean => this.keywordService.compareKeyword(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ video }) => {
      this.video = video;
      if (video) {
        this.updateForm(video);
      }

      this.loadRelationshipsOptions();
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
    const video = this.videoFormService.getVideo(this.editForm);
    if (video.id !== null) {
      this.subscribeToSaveResponse(this.videoService.update(video));
    } else {
      this.subscribeToSaveResponse(this.videoService.create(video));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVideo>>): void {
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

  protected updateForm(video: IVideo): void {
    this.video = video;
    this.videoFormService.resetForm(this.editForm, video);

    this.creatorsCollection = this.creatorService.addCreatorToCollectionIfMissing<ICreator>(this.creatorsCollection, video.creator);
    this.categoriesCollection = this.categoryService.addCategoryToCollectionIfMissing<ICategory>(this.categoriesCollection, video.category);
    this.copyrightsCollection = this.copyrightService.addCopyrightToCollectionIfMissing<ICopyright>(
      this.copyrightsCollection,
      video.copyright,
    );
    this.extraInfosCollection = this.metadataService.addMetadataToCollectionIfMissing<IMetadata>(
      this.extraInfosCollection,
      video.extraInfo,
    );
    this.keywordsSharedCollection = this.keywordService.addKeywordToCollectionIfMissing<IKeyword>(
      this.keywordsSharedCollection,
      ...(video.keywords ?? []),
    );
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
}
