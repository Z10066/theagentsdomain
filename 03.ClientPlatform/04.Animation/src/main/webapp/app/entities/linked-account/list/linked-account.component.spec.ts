import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LinkedAccountService } from '../service/linked-account.service';

import { LinkedAccountComponent } from './linked-account.component';

describe('LinkedAccount Management Component', () => {
  let comp: LinkedAccountComponent;
  let fixture: ComponentFixture<LinkedAccountComponent>;
  let service: LinkedAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'linked-account', component: LinkedAccountComponent }]),
        HttpClientTestingModule,
        LinkedAccountComponent,
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
      .overrideTemplate(LinkedAccountComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LinkedAccountComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(LinkedAccountService);

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
    expect(comp.linkedAccounts?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to linkedAccountService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getLinkedAccountIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getLinkedAccountIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
