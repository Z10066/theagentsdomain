import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { VideoHintService } from '../service/video-hint.service';
import { IVideoHint } from '../video-hint.model';
import { VideoHintFormService } from './video-hint-form.service';

import { VideoHintUpdateComponent } from './video-hint-update.component';

describe('VideoHint Management Update Component', () => {
  let comp: VideoHintUpdateComponent;
  let fixture: ComponentFixture<VideoHintUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let videoHintFormService: VideoHintFormService;
  let videoHintService: VideoHintService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), VideoHintUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(VideoHintUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VideoHintUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    videoHintFormService = TestBed.inject(VideoHintFormService);
    videoHintService = TestBed.inject(VideoHintService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const videoHint: IVideoHint = { id: 456 };

      activatedRoute.data = of({ videoHint });
      comp.ngOnInit();

      expect(comp.videoHint).toEqual(videoHint);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideoHint>>();
      const videoHint = { id: 123 };
      jest.spyOn(videoHintFormService, 'getVideoHint').mockReturnValue(videoHint);
      jest.spyOn(videoHintService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ videoHint });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: videoHint }));
      saveSubject.complete();

      // THEN
      expect(videoHintFormService.getVideoHint).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(videoHintService.update).toHaveBeenCalledWith(expect.objectContaining(videoHint));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideoHint>>();
      const videoHint = { id: 123 };
      jest.spyOn(videoHintFormService, 'getVideoHint').mockReturnValue({ id: null });
      jest.spyOn(videoHintService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ videoHint: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: videoHint }));
      saveSubject.complete();

      // THEN
      expect(videoHintFormService.getVideoHint).toHaveBeenCalled();
      expect(videoHintService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideoHint>>();
      const videoHint = { id: 123 };
      jest.spyOn(videoHintService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ videoHint });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(videoHintService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
