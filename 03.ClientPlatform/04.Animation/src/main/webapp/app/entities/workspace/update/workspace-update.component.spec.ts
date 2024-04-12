import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { WorkspaceService } from '../service/workspace.service';
import { IWorkspace } from '../workspace.model';
import { WorkspaceFormService } from './workspace-form.service';

import { WorkspaceUpdateComponent } from './workspace-update.component';

describe('Workspace Management Update Component', () => {
  let comp: WorkspaceUpdateComponent;
  let fixture: ComponentFixture<WorkspaceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let workspaceFormService: WorkspaceFormService;
  let workspaceService: WorkspaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), WorkspaceUpdateComponent],
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
      .overrideTemplate(WorkspaceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(WorkspaceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    workspaceFormService = TestBed.inject(WorkspaceFormService);
    workspaceService = TestBed.inject(WorkspaceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const workspace: IWorkspace = { id: 456 };

      activatedRoute.data = of({ workspace });
      comp.ngOnInit();

      expect(comp.workspace).toEqual(workspace);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkspace>>();
      const workspace = { id: 123 };
      jest.spyOn(workspaceFormService, 'getWorkspace').mockReturnValue(workspace);
      jest.spyOn(workspaceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workspace });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: workspace }));
      saveSubject.complete();

      // THEN
      expect(workspaceFormService.getWorkspace).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(workspaceService.update).toHaveBeenCalledWith(expect.objectContaining(workspace));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkspace>>();
      const workspace = { id: 123 };
      jest.spyOn(workspaceFormService, 'getWorkspace').mockReturnValue({ id: null });
      jest.spyOn(workspaceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workspace: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: workspace }));
      saveSubject.complete();

      // THEN
      expect(workspaceFormService.getWorkspace).toHaveBeenCalled();
      expect(workspaceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkspace>>();
      const workspace = { id: 123 };
      jest.spyOn(workspaceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workspace });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(workspaceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
