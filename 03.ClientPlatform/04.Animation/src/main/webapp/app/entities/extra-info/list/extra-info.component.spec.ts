import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ExtraInfoService } from '../service/extra-info.service';

import { ExtraInfoComponent } from './extra-info.component';

describe('ExtraInfo Management Component', () => {
  let comp: ExtraInfoComponent;
  let fixture: ComponentFixture<ExtraInfoComponent>;
  let service: ExtraInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'extra-info', component: ExtraInfoComponent }]),
        HttpClientTestingModule,
        ExtraInfoComponent,
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
      .overrideTemplate(ExtraInfoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ExtraInfoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ExtraInfoService);

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
    expect(comp.extraInfos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to extraInfoService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getExtraInfoIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getExtraInfoIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
