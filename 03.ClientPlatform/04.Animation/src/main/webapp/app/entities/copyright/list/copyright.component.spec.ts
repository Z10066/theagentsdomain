import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CopyrightService } from '../service/copyright.service';

import { CopyrightComponent } from './copyright.component';

describe('Copyright Management Component', () => {
  let comp: CopyrightComponent;
  let fixture: ComponentFixture<CopyrightComponent>;
  let service: CopyrightService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'copyright', component: CopyrightComponent }]),
        HttpClientTestingModule,
        CopyrightComponent,
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
      .overrideTemplate(CopyrightComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CopyrightComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CopyrightService);

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
    expect(comp.copyrights?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to copyrightService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCopyrightIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCopyrightIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
