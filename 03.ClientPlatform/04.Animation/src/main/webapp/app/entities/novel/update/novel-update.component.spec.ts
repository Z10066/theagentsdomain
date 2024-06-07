import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NovelService } from '../service/novel.service';
import { INovel } from '../novel.model';
import { NovelFormService } from './novel-form.service';

import { NovelUpdateComponent } from './novel-update.component';

describe('Novel Management Update Component', () => {
  let comp: NovelUpdateComponent;
  let fixture: ComponentFixture<NovelUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let novelFormService: NovelFormService;
  let novelService: NovelService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), NovelUpdateComponent],
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
      .overrideTemplate(NovelUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NovelUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    novelFormService = TestBed.inject(NovelFormService);
    novelService = TestBed.inject(NovelService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const novel: INovel = { id: 456 };

      activatedRoute.data = of({ novel });
      comp.ngOnInit();

      expect(comp.novel).toEqual(novel);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INovel>>();
      const novel = { id: 123 };
      jest.spyOn(novelFormService, 'getNovel').mockReturnValue(novel);
      jest.spyOn(novelService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ novel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: novel }));
      saveSubject.complete();

      // THEN
      expect(novelFormService.getNovel).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(novelService.update).toHaveBeenCalledWith(expect.objectContaining(novel));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INovel>>();
      const novel = { id: 123 };
      jest.spyOn(novelFormService, 'getNovel').mockReturnValue({ id: null });
      jest.spyOn(novelService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ novel: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: novel }));
      saveSubject.complete();

      // THEN
      expect(novelFormService.getNovel).toHaveBeenCalled();
      expect(novelService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INovel>>();
      const novel = { id: 123 };
      jest.spyOn(novelService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ novel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(novelService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
