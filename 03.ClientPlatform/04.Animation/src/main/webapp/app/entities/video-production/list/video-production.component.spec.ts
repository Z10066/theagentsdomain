import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { VideoProductionService } from '../service/video-production.service';

import { VideoProductionComponent } from './video-production.component';

describe('VideoProduction Management Component', () => {
  let comp: VideoProductionComponent;
  let fixture: ComponentFixture<VideoProductionComponent>;
  let service: VideoProductionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'video-production', component: VideoProductionComponent }]),
        HttpClientTestingModule,
        VideoProductionComponent,
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
      .overrideTemplate(VideoProductionComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VideoProductionComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(VideoProductionService);

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
    expect(comp.videoProductions?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to videoProductionService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getVideoProductionIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getVideoProductionIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
