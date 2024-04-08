import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../copyright.test-samples';

import { CopyrightFormService } from './copyright-form.service';

describe('Copyright Form Service', () => {
  let service: CopyrightFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CopyrightFormService);
  });

  describe('Service methods', () => {
    describe('createCopyrightFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCopyrightFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            details: expect.any(Object),
          }),
        );
      });

      it('passing ICopyright should create a new form with FormGroup', () => {
        const formGroup = service.createCopyrightFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            details: expect.any(Object),
          }),
        );
      });
    });

    describe('getCopyright', () => {
      it('should return NewCopyright for default Copyright initial value', () => {
        const formGroup = service.createCopyrightFormGroup(sampleWithNewData);

        const copyright = service.getCopyright(formGroup) as any;

        expect(copyright).toMatchObject(sampleWithNewData);
      });

      it('should return NewCopyright for empty Copyright initial value', () => {
        const formGroup = service.createCopyrightFormGroup();

        const copyright = service.getCopyright(formGroup) as any;

        expect(copyright).toMatchObject({});
      });

      it('should return ICopyright', () => {
        const formGroup = service.createCopyrightFormGroup(sampleWithRequiredData);

        const copyright = service.getCopyright(formGroup) as any;

        expect(copyright).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICopyright should not enable id FormControl', () => {
        const formGroup = service.createCopyrightFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCopyright should disable id FormControl', () => {
        const formGroup = service.createCopyrightFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
