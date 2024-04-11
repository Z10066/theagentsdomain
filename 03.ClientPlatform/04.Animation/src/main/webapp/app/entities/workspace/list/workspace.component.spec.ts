import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { WorkspaceService } from '../service/workspace.service';

import { WorkspaceComponent } from './workspace.component';

describe('Workspace Management Component', () => {
  let comp: WorkspaceComponent;
  let fixture: ComponentFixture<WorkspaceComponent>;
  let service: WorkspaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'workspace', component: WorkspaceComponent }]),
        HttpClientTestingModule,
        WorkspaceComponent,
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
      .overrideTemplate(WorkspaceComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(WorkspaceComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(WorkspaceService);

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
    expect(comp.workspaces?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to workspaceService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getWorkspaceIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getWorkspaceIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
