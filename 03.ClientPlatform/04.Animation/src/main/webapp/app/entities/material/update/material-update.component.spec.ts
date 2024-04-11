import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IExtraInfo } from 'app/entities/extra-info/extra-info.model';
import { ExtraInfoService } from 'app/entities/extra-info/service/extra-info.service';
import { IWorkspace } from 'app/entities/workspace/workspace.model';
import { WorkspaceService } from 'app/entities/workspace/service/workspace.service';
import { IMaterial } from '../material.model';
import { MaterialService } from '../service/material.service';
import { MaterialFormService } from './material-form.service';

import { MaterialUpdateComponent } from './material-update.component';

describe('Material Management Update Component', () => {
  let comp: MaterialUpdateComponent;
  let fixture: ComponentFixture<MaterialUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let materialFormService: MaterialFormService;
  let materialService: MaterialService;
  let extraInfoService: ExtraInfoService;
  let workspaceService: WorkspaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), MaterialUpdateComponent],
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
      .overrideTemplate(MaterialUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MaterialUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    materialFormService = TestBed.inject(MaterialFormService);
    materialService = TestBed.inject(MaterialService);
    extraInfoService = TestBed.inject(ExtraInfoService);
    workspaceService = TestBed.inject(WorkspaceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call extraInfo query and add missing value', () => {
      const material: IMaterial = { id: 456 };
      const extraInfo: IExtraInfo = { id: 1455 };
      material.extraInfo = extraInfo;

      const extraInfoCollection: IExtraInfo[] = [{ id: 2258 }];
      jest.spyOn(extraInfoService, 'query').mockReturnValue(of(new HttpResponse({ body: extraInfoCollection })));
      const expectedCollection: IExtraInfo[] = [extraInfo, ...extraInfoCollection];
      jest.spyOn(extraInfoService, 'addExtraInfoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ material });
      comp.ngOnInit();

      expect(extraInfoService.query).toHaveBeenCalled();
      expect(extraInfoService.addExtraInfoToCollectionIfMissing).toHaveBeenCalledWith(extraInfoCollection, extraInfo);
      expect(comp.extraInfosCollection).toEqual(expectedCollection);
    });

    it('Should call Workspace query and add missing value', () => {
      const material: IMaterial = { id: 456 };
      const workspace: IWorkspace = { id: 10963 };
      material.workspace = workspace;

      const workspaceCollection: IWorkspace[] = [{ id: 28398 }];
      jest.spyOn(workspaceService, 'query').mockReturnValue(of(new HttpResponse({ body: workspaceCollection })));
      const additionalWorkspaces = [workspace];
      const expectedCollection: IWorkspace[] = [...additionalWorkspaces, ...workspaceCollection];
      jest.spyOn(workspaceService, 'addWorkspaceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ material });
      comp.ngOnInit();

      expect(workspaceService.query).toHaveBeenCalled();
      expect(workspaceService.addWorkspaceToCollectionIfMissing).toHaveBeenCalledWith(
        workspaceCollection,
        ...additionalWorkspaces.map(expect.objectContaining),
      );
      expect(comp.workspacesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const material: IMaterial = { id: 456 };
      const extraInfo: IExtraInfo = { id: 32766 };
      material.extraInfo = extraInfo;
      const workspace: IWorkspace = { id: 8467 };
      material.workspace = workspace;

      activatedRoute.data = of({ material });
      comp.ngOnInit();

      expect(comp.extraInfosCollection).toContain(extraInfo);
      expect(comp.workspacesSharedCollection).toContain(workspace);
      expect(comp.material).toEqual(material);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMaterial>>();
      const material = { id: 123 };
      jest.spyOn(materialFormService, 'getMaterial').mockReturnValue(material);
      jest.spyOn(materialService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ material });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: material }));
      saveSubject.complete();

      // THEN
      expect(materialFormService.getMaterial).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(materialService.update).toHaveBeenCalledWith(expect.objectContaining(material));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMaterial>>();
      const material = { id: 123 };
      jest.spyOn(materialFormService, 'getMaterial').mockReturnValue({ id: null });
      jest.spyOn(materialService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ material: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: material }));
      saveSubject.complete();

      // THEN
      expect(materialFormService.getMaterial).toHaveBeenCalled();
      expect(materialService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMaterial>>();
      const material = { id: 123 };
      jest.spyOn(materialService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ material });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(materialService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareExtraInfo', () => {
      it('Should forward to extraInfoService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(extraInfoService, 'compareExtraInfo');
        comp.compareExtraInfo(entity, entity2);
        expect(extraInfoService.compareExtraInfo).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareWorkspace', () => {
      it('Should forward to workspaceService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(workspaceService, 'compareWorkspace');
        comp.compareWorkspace(entity, entity2);
        expect(workspaceService.compareWorkspace).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
