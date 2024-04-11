import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SubscriptionServiceService } from '../service/subscription-service.service';
import { ISubscriptionService } from '../subscription-service.model';
import { SubscriptionServiceFormService } from './subscription-service-form.service';

import { SubscriptionServiceUpdateComponent } from './subscription-service-update.component';

describe('SubscriptionService Management Update Component', () => {
  let comp: SubscriptionServiceUpdateComponent;
  let fixture: ComponentFixture<SubscriptionServiceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let subscriptionServiceFormService: SubscriptionServiceFormService;
  let subscriptionServiceService: SubscriptionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), SubscriptionServiceUpdateComponent],
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
      .overrideTemplate(SubscriptionServiceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SubscriptionServiceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    subscriptionServiceFormService = TestBed.inject(SubscriptionServiceFormService);
    subscriptionServiceService = TestBed.inject(SubscriptionServiceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const subscriptionService: ISubscriptionService = { id: 456 };

      activatedRoute.data = of({ subscriptionService });
      comp.ngOnInit();

      expect(comp.subscriptionService).toEqual(subscriptionService);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISubscriptionService>>();
      const subscriptionService = { id: 123 };
      jest.spyOn(subscriptionServiceFormService, 'getSubscriptionService').mockReturnValue(subscriptionService);
      jest.spyOn(subscriptionServiceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ subscriptionService });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: subscriptionService }));
      saveSubject.complete();

      // THEN
      expect(subscriptionServiceFormService.getSubscriptionService).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(subscriptionServiceService.update).toHaveBeenCalledWith(expect.objectContaining(subscriptionService));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISubscriptionService>>();
      const subscriptionService = { id: 123 };
      jest.spyOn(subscriptionServiceFormService, 'getSubscriptionService').mockReturnValue({ id: null });
      jest.spyOn(subscriptionServiceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ subscriptionService: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: subscriptionService }));
      saveSubject.complete();

      // THEN
      expect(subscriptionServiceFormService.getSubscriptionService).toHaveBeenCalled();
      expect(subscriptionServiceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISubscriptionService>>();
      const subscriptionService = { id: 123 };
      jest.spyOn(subscriptionServiceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ subscriptionService });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(subscriptionServiceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
