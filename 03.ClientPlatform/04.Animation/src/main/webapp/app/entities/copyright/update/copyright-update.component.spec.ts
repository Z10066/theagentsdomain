import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CopyrightService } from '../service/copyright.service';
import { ICopyright } from '../copyright.model';
import { CopyrightFormService } from './copyright-form.service';

import { CopyrightUpdateComponent } from './copyright-update.component';

describe('Copyright Management Update Component', () => {
  let comp: CopyrightUpdateComponent;
  let fixture: ComponentFixture<CopyrightUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let copyrightFormService: CopyrightFormService;
  let copyrightService: CopyrightService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CopyrightUpdateComponent],
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
      .overrideTemplate(CopyrightUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CopyrightUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    copyrightFormService = TestBed.inject(CopyrightFormService);
    copyrightService = TestBed.inject(CopyrightService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const copyright: ICopyright = { id: 456 };

      activatedRoute.data = of({ copyright });
      comp.ngOnInit();

      expect(comp.copyright).toEqual(copyright);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICopyright>>();
      const copyright = { id: 123 };
      jest.spyOn(copyrightFormService, 'getCopyright').mockReturnValue(copyright);
      jest.spyOn(copyrightService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ copyright });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: copyright }));
      saveSubject.complete();

      // THEN
      expect(copyrightFormService.getCopyright).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(copyrightService.update).toHaveBeenCalledWith(expect.objectContaining(copyright));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICopyright>>();
      const copyright = { id: 123 };
      jest.spyOn(copyrightFormService, 'getCopyright').mockReturnValue({ id: null });
      jest.spyOn(copyrightService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ copyright: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: copyright }));
      saveSubject.complete();

      // THEN
      expect(copyrightFormService.getCopyright).toHaveBeenCalled();
      expect(copyrightService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICopyright>>();
      const copyright = { id: 123 };
      jest.spyOn(copyrightService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ copyright });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(copyrightService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
