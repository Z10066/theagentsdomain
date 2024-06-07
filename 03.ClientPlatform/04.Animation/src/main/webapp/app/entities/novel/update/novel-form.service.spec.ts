import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../novel.test-samples';

import { NovelFormService } from './novel-form.service';

describe('Novel Form Service', () => {
  let service: NovelFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NovelFormService);
  });

  describe('Service methods', () => {
    describe('createNovelFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createNovelFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            noveltext: expect.any(Object),
            novelname: expect.any(Object),
          }),
        );
      });

      it('passing INovel should create a new form with FormGroup', () => {
        const formGroup = service.createNovelFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            noveltext: expect.any(Object),
            novelname: expect.any(Object),
          }),
        );
      });
    });

    describe('getNovel', () => {
      it('should return NewNovel for default Novel initial value', () => {
        const formGroup = service.createNovelFormGroup(sampleWithNewData);

        const novel = service.getNovel(formGroup) as any;

        expect(novel).toMatchObject(sampleWithNewData);
      });

      it('should return NewNovel for empty Novel initial value', () => {
        const formGroup = service.createNovelFormGroup();

        const novel = service.getNovel(formGroup) as any;

        expect(novel).toMatchObject({});
      });

      it('should return INovel', () => {
        const formGroup = service.createNovelFormGroup(sampleWithRequiredData);

        const novel = service.getNovel(formGroup) as any;

        expect(novel).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing INovel should not enable id FormControl', () => {
        const formGroup = service.createNovelFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewNovel should disable id FormControl', () => {
        const formGroup = service.createNovelFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
