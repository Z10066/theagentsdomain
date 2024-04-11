import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../subscription-service.test-samples';

import { SubscriptionServiceFormService } from './subscription-service-form.service';

describe('SubscriptionService Form Service', () => {
  let service: SubscriptionServiceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubscriptionServiceFormService);
  });

  describe('Service methods', () => {
    describe('createSubscriptionServiceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSubscriptionServiceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            serviceLevel: expect.any(Object),
            totalUsageTime: expect.any(Object),
            startTime: expect.any(Object),
            endTime: expect.any(Object),
          }),
        );
      });

      it('passing ISubscriptionService should create a new form with FormGroup', () => {
        const formGroup = service.createSubscriptionServiceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            serviceLevel: expect.any(Object),
            totalUsageTime: expect.any(Object),
            startTime: expect.any(Object),
            endTime: expect.any(Object),
          }),
        );
      });
    });

    describe('getSubscriptionService', () => {
      it('should return NewSubscriptionService for default SubscriptionService initial value', () => {
        const formGroup = service.createSubscriptionServiceFormGroup(sampleWithNewData);

        const subscriptionService = service.getSubscriptionService(formGroup) as any;

        expect(subscriptionService).toMatchObject(sampleWithNewData);
      });

      it('should return NewSubscriptionService for empty SubscriptionService initial value', () => {
        const formGroup = service.createSubscriptionServiceFormGroup();

        const subscriptionService = service.getSubscriptionService(formGroup) as any;

        expect(subscriptionService).toMatchObject({});
      });

      it('should return ISubscriptionService', () => {
        const formGroup = service.createSubscriptionServiceFormGroup(sampleWithRequiredData);

        const subscriptionService = service.getSubscriptionService(formGroup) as any;

        expect(subscriptionService).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISubscriptionService should not enable id FormControl', () => {
        const formGroup = service.createSubscriptionServiceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSubscriptionService should disable id FormControl', () => {
        const formGroup = service.createSubscriptionServiceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
