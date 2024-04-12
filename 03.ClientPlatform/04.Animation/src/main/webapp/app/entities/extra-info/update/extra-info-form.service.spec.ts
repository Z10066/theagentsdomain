import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../extra-info.test-samples';

import { ExtraInfoFormService } from './extra-info-form.service';

describe('ExtraInfo Form Service', () => {
  let service: ExtraInfoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExtraInfoFormService);
  });

  describe('Service methods', () => {
    describe('createExtraInfoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createExtraInfoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            audienceFeedback: expect.any(Object),
            relatedVideos: expect.any(Object),
          }),
        );
      });

      it('passing IExtraInfo should create a new form with FormGroup', () => {
        const formGroup = service.createExtraInfoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            audienceFeedback: expect.any(Object),
            relatedVideos: expect.any(Object),
          }),
        );
      });
    });

    describe('getExtraInfo', () => {
      it('should return NewExtraInfo for default ExtraInfo initial value', () => {
        const formGroup = service.createExtraInfoFormGroup(sampleWithNewData);

        const extraInfo = service.getExtraInfo(formGroup) as any;

        expect(extraInfo).toMatchObject(sampleWithNewData);
      });

      it('should return NewExtraInfo for empty ExtraInfo initial value', () => {
        const formGroup = service.createExtraInfoFormGroup();

        const extraInfo = service.getExtraInfo(formGroup) as any;

        expect(extraInfo).toMatchObject({});
      });

      it('should return IExtraInfo', () => {
        const formGroup = service.createExtraInfoFormGroup(sampleWithRequiredData);

        const extraInfo = service.getExtraInfo(formGroup) as any;

        expect(extraInfo).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IExtraInfo should not enable id FormControl', () => {
        const formGroup = service.createExtraInfoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewExtraInfo should disable id FormControl', () => {
        const formGroup = service.createExtraInfoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
