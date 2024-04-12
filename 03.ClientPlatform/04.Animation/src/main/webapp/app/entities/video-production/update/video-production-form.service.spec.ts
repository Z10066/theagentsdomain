import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../video-production.test-samples';

import { VideoProductionFormService } from './video-production-form.service';

describe('VideoProduction Form Service', () => {
  let service: VideoProductionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VideoProductionFormService);
  });

  describe('Service methods', () => {
    describe('createVideoProductionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVideoProductionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            creator: expect.any(Object),
            description: expect.any(Object),
            copyright: expect.any(Object),
            watchLink: expect.any(Object),
            extraInfo: expect.any(Object),
            workspace: expect.any(Object),
          }),
        );
      });

      it('passing IVideoProduction should create a new form with FormGroup', () => {
        const formGroup = service.createVideoProductionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            creator: expect.any(Object),
            description: expect.any(Object),
            copyright: expect.any(Object),
            watchLink: expect.any(Object),
            extraInfo: expect.any(Object),
            workspace: expect.any(Object),
          }),
        );
      });
    });

    describe('getVideoProduction', () => {
      it('should return NewVideoProduction for default VideoProduction initial value', () => {
        const formGroup = service.createVideoProductionFormGroup(sampleWithNewData);

        const videoProduction = service.getVideoProduction(formGroup) as any;

        expect(videoProduction).toMatchObject(sampleWithNewData);
      });

      it('should return NewVideoProduction for empty VideoProduction initial value', () => {
        const formGroup = service.createVideoProductionFormGroup();

        const videoProduction = service.getVideoProduction(formGroup) as any;

        expect(videoProduction).toMatchObject({});
      });

      it('should return IVideoProduction', () => {
        const formGroup = service.createVideoProductionFormGroup(sampleWithRequiredData);

        const videoProduction = service.getVideoProduction(formGroup) as any;

        expect(videoProduction).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVideoProduction should not enable id FormControl', () => {
        const formGroup = service.createVideoProductionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVideoProduction should disable id FormControl', () => {
        const formGroup = service.createVideoProductionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
