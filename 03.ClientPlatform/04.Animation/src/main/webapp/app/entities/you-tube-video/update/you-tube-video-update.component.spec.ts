import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { YouTubeVideoService } from '../service/you-tube-video.service';
import { IYouTubeVideo } from '../you-tube-video.model';
import { YouTubeVideoFormService } from './you-tube-video-form.service';

import { YouTubeVideoUpdateComponent } from './you-tube-video-update.component';

describe('YouTubeVideo Management Update Component', () => {
  let comp: YouTubeVideoUpdateComponent;
  let fixture: ComponentFixture<YouTubeVideoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let youTubeVideoFormService: YouTubeVideoFormService;
  let youTubeVideoService: YouTubeVideoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), YouTubeVideoUpdateComponent],
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
      .overrideTemplate(YouTubeVideoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(YouTubeVideoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    youTubeVideoFormService = TestBed.inject(YouTubeVideoFormService);
    youTubeVideoService = TestBed.inject(YouTubeVideoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const youTubeVideo: IYouTubeVideo = { id: 456 };

      activatedRoute.data = of({ youTubeVideo });
      comp.ngOnInit();

      expect(comp.youTubeVideo).toEqual(youTubeVideo);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IYouTubeVideo>>();
      const youTubeVideo = { id: 123 };
      jest.spyOn(youTubeVideoFormService, 'getYouTubeVideo').mockReturnValue(youTubeVideo);
      jest.spyOn(youTubeVideoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ youTubeVideo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: youTubeVideo }));
      saveSubject.complete();

      // THEN
      expect(youTubeVideoFormService.getYouTubeVideo).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(youTubeVideoService.update).toHaveBeenCalledWith(expect.objectContaining(youTubeVideo));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IYouTubeVideo>>();
      const youTubeVideo = { id: 123 };
      jest.spyOn(youTubeVideoFormService, 'getYouTubeVideo').mockReturnValue({ id: null });
      jest.spyOn(youTubeVideoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ youTubeVideo: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: youTubeVideo }));
      saveSubject.complete();

      // THEN
      expect(youTubeVideoFormService.getYouTubeVideo).toHaveBeenCalled();
      expect(youTubeVideoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IYouTubeVideo>>();
      const youTubeVideo = { id: 123 };
      jest.spyOn(youTubeVideoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ youTubeVideo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(youTubeVideoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
