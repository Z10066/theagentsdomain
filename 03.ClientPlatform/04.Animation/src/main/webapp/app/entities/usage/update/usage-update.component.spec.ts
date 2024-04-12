import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ISubscriptionService } from 'app/entities/subscription-service/subscription-service.model';
import { SubscriptionServiceService } from 'app/entities/subscription-service/service/subscription-service.service';
import { UsageService } from '../service/usage.service';
import { IUsage } from '../usage.model';
import { UsageFormService } from './usage-form.service';

import { UsageUpdateComponent } from './usage-update.component';

describe('Usage Management Update Component', () => {
  let comp: UsageUpdateComponent;
  let fixture: ComponentFixture<UsageUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let usageFormService: UsageFormService;
  let usageService: UsageService;
  let subscriptionServiceService: SubscriptionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), UsageUpdateComponent],
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
      .overrideTemplate(UsageUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UsageUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    usageFormService = TestBed.inject(UsageFormService);
    usageService = TestBed.inject(UsageService);
    subscriptionServiceService = TestBed.inject(SubscriptionServiceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call SubscriptionService query and add missing value', () => {
      const usage: IUsage = { id: 456 };
      const subscriptionService: ISubscriptionService = { id: 18993 };
      usage.subscriptionService = subscriptionService;

      const subscriptionServiceCollection: ISubscriptionService[] = [{ id: 1051 }];
      jest.spyOn(subscriptionServiceService, 'query').mockReturnValue(of(new HttpResponse({ body: subscriptionServiceCollection })));
      const additionalSubscriptionServices = [subscriptionService];
      const expectedCollection: ISubscriptionService[] = [...additionalSubscriptionServices, ...subscriptionServiceCollection];
      jest.spyOn(subscriptionServiceService, 'addSubscriptionServiceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ usage });
      comp.ngOnInit();

      expect(subscriptionServiceService.query).toHaveBeenCalled();
      expect(subscriptionServiceService.addSubscriptionServiceToCollectionIfMissing).toHaveBeenCalledWith(
        subscriptionServiceCollection,
        ...additionalSubscriptionServices.map(expect.objectContaining),
      );
      expect(comp.subscriptionServicesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const usage: IUsage = { id: 456 };
      const subscriptionService: ISubscriptionService = { id: 19903 };
      usage.subscriptionService = subscriptionService;

      activatedRoute.data = of({ usage });
      comp.ngOnInit();

      expect(comp.subscriptionServicesSharedCollection).toContain(subscriptionService);
      expect(comp.usage).toEqual(usage);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUsage>>();
      const usage = { id: 123 };
      jest.spyOn(usageFormService, 'getUsage').mockReturnValue(usage);
      jest.spyOn(usageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ usage });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: usage }));
      saveSubject.complete();

      // THEN
      expect(usageFormService.getUsage).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(usageService.update).toHaveBeenCalledWith(expect.objectContaining(usage));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUsage>>();
      const usage = { id: 123 };
      jest.spyOn(usageFormService, 'getUsage').mockReturnValue({ id: null });
      jest.spyOn(usageService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ usage: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: usage }));
      saveSubject.complete();

      // THEN
      expect(usageFormService.getUsage).toHaveBeenCalled();
      expect(usageService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUsage>>();
      const usage = { id: 123 };
      jest.spyOn(usageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ usage });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(usageService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareSubscriptionService', () => {
      it('Should forward to subscriptionServiceService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(subscriptionServiceService, 'compareSubscriptionService');
        comp.compareSubscriptionService(entity, entity2);
        expect(subscriptionServiceService.compareSubscriptionService).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
