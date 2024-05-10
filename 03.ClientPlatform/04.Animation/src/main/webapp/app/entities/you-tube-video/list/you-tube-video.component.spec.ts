import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { YouTubeVideoService } from '../service/you-tube-video.service';

import { YouTubeVideoComponent } from './you-tube-video.component';

describe('YouTubeVideo Management Component', () => {
  let comp: YouTubeVideoComponent;
  let fixture: ComponentFixture<YouTubeVideoComponent>;
  let service: YouTubeVideoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, YouTubeVideoComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(YouTubeVideoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(YouTubeVideoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(YouTubeVideoService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        }),
      ),
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.youTubeVideos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to youTubeVideoService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getYouTubeVideoIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getYouTubeVideoIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
