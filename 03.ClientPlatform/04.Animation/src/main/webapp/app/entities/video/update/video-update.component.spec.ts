import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

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
import { IVideo } from '../video.model';
import { VideoService } from '../service/video.service';
import { VideoFormService } from './video-form.service';

import { VideoUpdateComponent } from './video-update.component';

describe('Video Management Update Component', () => {
  let comp: VideoUpdateComponent;
  let fixture: ComponentFixture<VideoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let videoFormService: VideoFormService;
  let videoService: VideoService;
  let creatorService: CreatorService;
  let categoryService: CategoryService;
  let copyrightService: CopyrightService;
  let metadataService: MetadataService;
  let keywordService: KeywordService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), VideoUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(VideoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VideoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    videoFormService = TestBed.inject(VideoFormService);
    videoService = TestBed.inject(VideoService);
    creatorService = TestBed.inject(CreatorService);
    categoryService = TestBed.inject(CategoryService);
    copyrightService = TestBed.inject(CopyrightService);
    metadataService = TestBed.inject(MetadataService);
    keywordService = TestBed.inject(KeywordService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call creator query and add missing value', () => {
      const video: IVideo = { id: 456 };
      const creator: ICreator = { id: 1210 };
      video.creator = creator;

      const creatorCollection: ICreator[] = [{ id: 23883 }];
      jest.spyOn(creatorService, 'query').mockReturnValue(of(new HttpResponse({ body: creatorCollection })));
      const expectedCollection: ICreator[] = [creator, ...creatorCollection];
      jest.spyOn(creatorService, 'addCreatorToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ video });
      comp.ngOnInit();

      expect(creatorService.query).toHaveBeenCalled();
      expect(creatorService.addCreatorToCollectionIfMissing).toHaveBeenCalledWith(creatorCollection, creator);
      expect(comp.creatorsCollection).toEqual(expectedCollection);
    });

    it('Should call category query and add missing value', () => {
      const video: IVideo = { id: 456 };
      const category: ICategory = { id: 13443 };
      video.category = category;

      const categoryCollection: ICategory[] = [{ id: 23355 }];
      jest.spyOn(categoryService, 'query').mockReturnValue(of(new HttpResponse({ body: categoryCollection })));
      const expectedCollection: ICategory[] = [category, ...categoryCollection];
      jest.spyOn(categoryService, 'addCategoryToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ video });
      comp.ngOnInit();

      expect(categoryService.query).toHaveBeenCalled();
      expect(categoryService.addCategoryToCollectionIfMissing).toHaveBeenCalledWith(categoryCollection, category);
      expect(comp.categoriesCollection).toEqual(expectedCollection);
    });

    it('Should call copyright query and add missing value', () => {
      const video: IVideo = { id: 456 };
      const copyright: ICopyright = { id: 20160 };
      video.copyright = copyright;

      const copyrightCollection: ICopyright[] = [{ id: 9705 }];
      jest.spyOn(copyrightService, 'query').mockReturnValue(of(new HttpResponse({ body: copyrightCollection })));
      const expectedCollection: ICopyright[] = [copyright, ...copyrightCollection];
      jest.spyOn(copyrightService, 'addCopyrightToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ video });
      comp.ngOnInit();

      expect(copyrightService.query).toHaveBeenCalled();
      expect(copyrightService.addCopyrightToCollectionIfMissing).toHaveBeenCalledWith(copyrightCollection, copyright);
      expect(comp.copyrightsCollection).toEqual(expectedCollection);
    });

    it('Should call extraInfo query and add missing value', () => {
      const video: IVideo = { id: 456 };
      const extraInfo: IMetadata = { id: 1173 };
      video.extraInfo = extraInfo;

      const extraInfoCollection: IMetadata[] = [{ id: 17823 }];
      jest.spyOn(metadataService, 'query').mockReturnValue(of(new HttpResponse({ body: extraInfoCollection })));
      const expectedCollection: IMetadata[] = [extraInfo, ...extraInfoCollection];
      jest.spyOn(metadataService, 'addMetadataToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ video });
      comp.ngOnInit();

      expect(metadataService.query).toHaveBeenCalled();
      expect(metadataService.addMetadataToCollectionIfMissing).toHaveBeenCalledWith(extraInfoCollection, extraInfo);
      expect(comp.extraInfosCollection).toEqual(expectedCollection);
    });

    it('Should call Keyword query and add missing value', () => {
      const video: IVideo = { id: 456 };
      const keywords: IKeyword[] = [{ id: 17309 }];
      video.keywords = keywords;

      const keywordCollection: IKeyword[] = [{ id: 6259 }];
      jest.spyOn(keywordService, 'query').mockReturnValue(of(new HttpResponse({ body: keywordCollection })));
      const additionalKeywords = [...keywords];
      const expectedCollection: IKeyword[] = [...additionalKeywords, ...keywordCollection];
      jest.spyOn(keywordService, 'addKeywordToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ video });
      comp.ngOnInit();

      expect(keywordService.query).toHaveBeenCalled();
      expect(keywordService.addKeywordToCollectionIfMissing).toHaveBeenCalledWith(
        keywordCollection,
        ...additionalKeywords.map(expect.objectContaining),
      );
      expect(comp.keywordsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const video: IVideo = { id: 456 };
      const creator: ICreator = { id: 13169 };
      video.creator = creator;
      const category: ICategory = { id: 10737 };
      video.category = category;
      const copyright: ICopyright = { id: 17165 };
      video.copyright = copyright;
      const extraInfo: IMetadata = { id: 5737 };
      video.extraInfo = extraInfo;
      const keyword: IKeyword = { id: 23350 };
      video.keywords = [keyword];

      activatedRoute.data = of({ video });
      comp.ngOnInit();

      expect(comp.creatorsCollection).toContain(creator);
      expect(comp.categoriesCollection).toContain(category);
      expect(comp.copyrightsCollection).toContain(copyright);
      expect(comp.extraInfosCollection).toContain(extraInfo);
      expect(comp.keywordsSharedCollection).toContain(keyword);
      expect(comp.video).toEqual(video);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideo>>();
      const video = { id: 123 };
      jest.spyOn(videoFormService, 'getVideo').mockReturnValue(video);
      jest.spyOn(videoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ video });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: video }));
      saveSubject.complete();

      // THEN
      expect(videoFormService.getVideo).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(videoService.update).toHaveBeenCalledWith(expect.objectContaining(video));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideo>>();
      const video = { id: 123 };
      jest.spyOn(videoFormService, 'getVideo').mockReturnValue({ id: null });
      jest.spyOn(videoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ video: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: video }));
      saveSubject.complete();

      // THEN
      expect(videoFormService.getVideo).toHaveBeenCalled();
      expect(videoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideo>>();
      const video = { id: 123 };
      jest.spyOn(videoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ video });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(videoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCreator', () => {
      it('Should forward to creatorService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(creatorService, 'compareCreator');
        comp.compareCreator(entity, entity2);
        expect(creatorService.compareCreator).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCategory', () => {
      it('Should forward to categoryService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(categoryService, 'compareCategory');
        comp.compareCategory(entity, entity2);
        expect(categoryService.compareCategory).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCopyright', () => {
      it('Should forward to copyrightService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(copyrightService, 'compareCopyright');
        comp.compareCopyright(entity, entity2);
        expect(copyrightService.compareCopyright).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareMetadata', () => {
      it('Should forward to metadataService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(metadataService, 'compareMetadata');
        comp.compareMetadata(entity, entity2);
        expect(metadataService.compareMetadata).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareKeyword', () => {
      it('Should forward to keywordService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(keywordService, 'compareKeyword');
        comp.compareKeyword(entity, entity2);
        expect(keywordService.compareKeyword).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
