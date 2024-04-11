import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../usage.test-samples';

import { UsageFormService } from './usage-form.service';

describe('Usage Form Service', () => {
  let service: UsageFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsageFormService);
  });

  describe('Service methods', () => {
    describe('createUsageFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createUsageFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            usageType: expect.any(Object),
            usageTime: expect.any(Object),
            startTime: expect.any(Object),
            endTime: expect.any(Object),
            subscriptionService: expect.any(Object),
          }),
        );
      });

      it('passing IUsage should create a new form with FormGroup', () => {
        const formGroup = service.createUsageFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            usageType: expect.any(Object),
            usageTime: expect.any(Object),
            startTime: expect.any(Object),
            endTime: expect.any(Object),
            subscriptionService: expect.any(Object),
          }),
        );
      });
    });

    describe('getUsage', () => {
      it('should return NewUsage for default Usage initial value', () => {
        const formGroup = service.createUsageFormGroup(sampleWithNewData);

        const usage = service.getUsage(formGroup) as any;

        expect(usage).toMatchObject(sampleWithNewData);
      });

      it('should return NewUsage for empty Usage initial value', () => {
        const formGroup = service.createUsageFormGroup();

        const usage = service.getUsage(formGroup) as any;

        expect(usage).toMatchObject({});
      });

      it('should return IUsage', () => {
        const formGroup = service.createUsageFormGroup(sampleWithRequiredData);

        const usage = service.getUsage(formGroup) as any;

        expect(usage).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IUsage should not enable id FormControl', () => {
        const formGroup = service.createUsageFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewUsage should disable id FormControl', () => {
        const formGroup = service.createUsageFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
