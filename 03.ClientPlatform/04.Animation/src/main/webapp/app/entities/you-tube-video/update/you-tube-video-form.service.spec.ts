import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../you-tube-video.test-samples';

import { YouTubeVideoFormService } from './you-tube-video-form.service';

describe('YouTubeVideo Form Service', () => {
  let service: YouTubeVideoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(YouTubeVideoFormService);
  });

  describe('Service methods', () => {
    describe('createYouTubeVideoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createYouTubeVideoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            workspace: expect.any(Object),
            creator: expect.any(Object),
            theme: expect.any(Object),
            content: expect.any(Object),
            backgroundMusic: expect.any(Object),
            videoTime: expect.any(Object),
          }),
        );
      });

      it('passing IYouTubeVideo should create a new form with FormGroup', () => {
        const formGroup = service.createYouTubeVideoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            workspace: expect.any(Object),
            creator: expect.any(Object),
            theme: expect.any(Object),
            content: expect.any(Object),
            backgroundMusic: expect.any(Object),
            videoTime: expect.any(Object),
          }),
        );
      });
    });

    describe('getYouTubeVideo', () => {
      it('should return NewYouTubeVideo for default YouTubeVideo initial value', () => {
        const formGroup = service.createYouTubeVideoFormGroup(sampleWithNewData);

        const youTubeVideo = service.getYouTubeVideo(formGroup) as any;

        expect(youTubeVideo).toMatchObject(sampleWithNewData);
      });

      it('should return NewYouTubeVideo for empty YouTubeVideo initial value', () => {
        const formGroup = service.createYouTubeVideoFormGroup();

        const youTubeVideo = service.getYouTubeVideo(formGroup) as any;

        expect(youTubeVideo).toMatchObject({});
      });

      it('should return IYouTubeVideo', () => {
        const formGroup = service.createYouTubeVideoFormGroup(sampleWithRequiredData);

        const youTubeVideo = service.getYouTubeVideo(formGroup) as any;

        expect(youTubeVideo).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IYouTubeVideo should not enable id FormControl', () => {
        const formGroup = service.createYouTubeVideoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewYouTubeVideo should disable id FormControl', () => {
        const formGroup = service.createYouTubeVideoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
