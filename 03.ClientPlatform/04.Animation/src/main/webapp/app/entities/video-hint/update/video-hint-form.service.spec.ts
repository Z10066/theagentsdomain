import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../video-hint.test-samples';

import { VideoHintFormService } from './video-hint-form.service';

describe('VideoHint Form Service', () => {
  let service: VideoHintFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VideoHintFormService);
  });

  describe('Service methods', () => {
    describe('createVideoHintFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVideoHintFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            workspace: expect.any(Object),
            creator: expect.any(Object),
            creationContent: expect.any(Object),
            backgroundMusic: expect.any(Object),
          }),
        );
      });

      it('passing IVideoHint should create a new form with FormGroup', () => {
        const formGroup = service.createVideoHintFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            workspace: expect.any(Object),
            creator: expect.any(Object),
            creationContent: expect.any(Object),
            backgroundMusic: expect.any(Object),
          }),
        );
      });
    });

    describe('getVideoHint', () => {
      it('should return NewVideoHint for default VideoHint initial value', () => {
        const formGroup = service.createVideoHintFormGroup(sampleWithNewData);

        const videoHint = service.getVideoHint(formGroup) as any;

        expect(videoHint).toMatchObject(sampleWithNewData);
      });

      it('should return NewVideoHint for empty VideoHint initial value', () => {
        const formGroup = service.createVideoHintFormGroup();

        const videoHint = service.getVideoHint(formGroup) as any;

        expect(videoHint).toMatchObject({});
      });

      it('should return IVideoHint', () => {
        const formGroup = service.createVideoHintFormGroup(sampleWithRequiredData);

        const videoHint = service.getVideoHint(formGroup) as any;

        expect(videoHint).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVideoHint should not enable id FormControl', () => {
        const formGroup = service.createVideoHintFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVideoHint should disable id FormControl', () => {
        const formGroup = service.createVideoHintFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
