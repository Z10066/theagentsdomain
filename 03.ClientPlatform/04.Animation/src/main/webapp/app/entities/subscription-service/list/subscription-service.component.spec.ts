import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SubscriptionServiceService } from '../service/subscription-service.service';

import { SubscriptionServiceComponent } from './subscription-service.component';

describe('SubscriptionService Management Component', () => {
  let comp: SubscriptionServiceComponent;
  let fixture: ComponentFixture<SubscriptionServiceComponent>;
  let service: SubscriptionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'subscription-service', component: SubscriptionServiceComponent }]),
        HttpClientTestingModule,
        SubscriptionServiceComponent,
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
      .overrideTemplate(SubscriptionServiceComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SubscriptionServiceComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SubscriptionServiceService);

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
    expect(comp.subscriptionServices?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to subscriptionServiceService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getSubscriptionServiceIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getSubscriptionServiceIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
