import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../creator.test-samples';

import { CreatorFormService } from './creator-form.service';

describe('Creator Form Service', () => {
  let service: CreatorFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreatorFormService);
  });

  describe('Service methods', () => {
    describe('createCreatorFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreatorFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
          }),
        );
      });

      it('passing ICreator should create a new form with FormGroup', () => {
        const formGroup = service.createCreatorFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
          }),
        );
      });
    });

    describe('getCreator', () => {
      it('should return NewCreator for default Creator initial value', () => {
        const formGroup = service.createCreatorFormGroup(sampleWithNewData);

        const creator = service.getCreator(formGroup) as any;

        expect(creator).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreator for empty Creator initial value', () => {
        const formGroup = service.createCreatorFormGroup();

        const creator = service.getCreator(formGroup) as any;

        expect(creator).toMatchObject({});
      });

      it('should return ICreator', () => {
        const formGroup = service.createCreatorFormGroup(sampleWithRequiredData);

        const creator = service.getCreator(formGroup) as any;

        expect(creator).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreator should not enable id FormControl', () => {
        const formGroup = service.createCreatorFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreator should disable id FormControl', () => {
        const formGroup = service.createCreatorFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
