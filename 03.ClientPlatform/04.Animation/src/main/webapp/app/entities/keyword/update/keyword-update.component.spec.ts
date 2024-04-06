import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { KeywordService } from '../service/keyword.service';
import { IKeyword } from '../keyword.model';
import { KeywordFormService } from './keyword-form.service';

import { KeywordUpdateComponent } from './keyword-update.component';

describe('Keyword Management Update Component', () => {
  let comp: KeywordUpdateComponent;
  let fixture: ComponentFixture<KeywordUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let keywordFormService: KeywordFormService;
  let keywordService: KeywordService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), KeywordUpdateComponent],
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
      .overrideTemplate(KeywordUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(KeywordUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    keywordFormService = TestBed.inject(KeywordFormService);
    keywordService = TestBed.inject(KeywordService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const keyword: IKeyword = { id: 456 };

      activatedRoute.data = of({ keyword });
      comp.ngOnInit();

      expect(comp.keyword).toEqual(keyword);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IKeyword>>();
      const keyword = { id: 123 };
      jest.spyOn(keywordFormService, 'getKeyword').mockReturnValue(keyword);
      jest.spyOn(keywordService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ keyword });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: keyword }));
      saveSubject.complete();

      // THEN
      expect(keywordFormService.getKeyword).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(keywordService.update).toHaveBeenCalledWith(expect.objectContaining(keyword));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IKeyword>>();
      const keyword = { id: 123 };
      jest.spyOn(keywordFormService, 'getKeyword').mockReturnValue({ id: null });
      jest.spyOn(keywordService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ keyword: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: keyword }));
      saveSubject.complete();

      // THEN
      expect(keywordFormService.getKeyword).toHaveBeenCalled();
      expect(keywordService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IKeyword>>();
      const keyword = { id: 123 };
      jest.spyOn(keywordService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ keyword });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(keywordService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
