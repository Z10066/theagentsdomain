import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { VideoHintService } from '../service/video-hint.service';

import { VideoHintComponent } from './video-hint.component';

describe('VideoHint Management Component', () => {
  let comp: VideoHintComponent;
  let fixture: ComponentFixture<VideoHintComponent>;
  let service: VideoHintService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'video-hint', component: VideoHintComponent }]),
        HttpClientTestingModule,
        VideoHintComponent,
      ],
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
      .overrideTemplate(VideoHintComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VideoHintComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(VideoHintService);

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
    expect(comp.videoHints?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to videoHintService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getVideoHintIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getVideoHintIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
