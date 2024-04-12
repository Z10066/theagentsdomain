import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ExtraInfoService } from '../service/extra-info.service';
import { IExtraInfo } from '../extra-info.model';
import { ExtraInfoFormService } from './extra-info-form.service';

import { ExtraInfoUpdateComponent } from './extra-info-update.component';

describe('ExtraInfo Management Update Component', () => {
  let comp: ExtraInfoUpdateComponent;
  let fixture: ComponentFixture<ExtraInfoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let extraInfoFormService: ExtraInfoFormService;
  let extraInfoService: ExtraInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ExtraInfoUpdateComponent],
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
      .overrideTemplate(ExtraInfoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ExtraInfoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    extraInfoFormService = TestBed.inject(ExtraInfoFormService);
    extraInfoService = TestBed.inject(ExtraInfoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const extraInfo: IExtraInfo = { id: 456 };

      activatedRoute.data = of({ extraInfo });
      comp.ngOnInit();

      expect(comp.extraInfo).toEqual(extraInfo);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IExtraInfo>>();
      const extraInfo = { id: 123 };
      jest.spyOn(extraInfoFormService, 'getExtraInfo').mockReturnValue(extraInfo);
      jest.spyOn(extraInfoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ extraInfo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: extraInfo }));
      saveSubject.complete();

      // THEN
      expect(extraInfoFormService.getExtraInfo).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(extraInfoService.update).toHaveBeenCalledWith(expect.objectContaining(extraInfo));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IExtraInfo>>();
      const extraInfo = { id: 123 };
      jest.spyOn(extraInfoFormService, 'getExtraInfo').mockReturnValue({ id: null });
      jest.spyOn(extraInfoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ extraInfo: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: extraInfo }));
      saveSubject.complete();

      // THEN
      expect(extraInfoFormService.getExtraInfo).toHaveBeenCalled();
      expect(extraInfoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IExtraInfo>>();
      const extraInfo = { id: 123 };
      jest.spyOn(extraInfoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ extraInfo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(extraInfoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
