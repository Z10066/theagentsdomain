import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../linked-account.test-samples';

import { LinkedAccountFormService } from './linked-account-form.service';

describe('LinkedAccount Form Service', () => {
  let service: LinkedAccountFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LinkedAccountFormService);
  });

  describe('Service methods', () => {
    describe('createLinkedAccountFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLinkedAccountFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            accountType: expect.any(Object),
            accountIdentifier: expect.any(Object),
            member: expect.any(Object),
          }),
        );
      });

      it('passing ILinkedAccount should create a new form with FormGroup', () => {
        const formGroup = service.createLinkedAccountFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            accountType: expect.any(Object),
            accountIdentifier: expect.any(Object),
            member: expect.any(Object),
          }),
        );
      });
    });

    describe('getLinkedAccount', () => {
      it('should return NewLinkedAccount for default LinkedAccount initial value', () => {
        const formGroup = service.createLinkedAccountFormGroup(sampleWithNewData);

        const linkedAccount = service.getLinkedAccount(formGroup) as any;

        expect(linkedAccount).toMatchObject(sampleWithNewData);
      });

      it('should return NewLinkedAccount for empty LinkedAccount initial value', () => {
        const formGroup = service.createLinkedAccountFormGroup();

        const linkedAccount = service.getLinkedAccount(formGroup) as any;

        expect(linkedAccount).toMatchObject({});
      });

      it('should return ILinkedAccount', () => {
        const formGroup = service.createLinkedAccountFormGroup(sampleWithRequiredData);

        const linkedAccount = service.getLinkedAccount(formGroup) as any;

        expect(linkedAccount).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILinkedAccount should not enable id FormControl', () => {
        const formGroup = service.createLinkedAccountFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLinkedAccount should disable id FormControl', () => {
        const formGroup = service.createLinkedAccountFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
