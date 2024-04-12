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
import { IHistory } from '../history.model';
import { HistoryService } from '../service/history.service';
import { HistoryFormService } from './history-form.service';

import { HistoryUpdateComponent } from './history-update.component';

describe('History Management Update Component', () => {
  let comp: HistoryUpdateComponent;
  let fixture: ComponentFixture<HistoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let historyFormService: HistoryFormService;
  let historyService: HistoryService;
  let extraInfoService: ExtraInfoService;
  let workspaceService: WorkspaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), HistoryUpdateComponent],
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
      .overrideTemplate(HistoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HistoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    historyFormService = TestBed.inject(HistoryFormService);
    historyService = TestBed.inject(HistoryService);
    extraInfoService = TestBed.inject(ExtraInfoService);
    workspaceService = TestBed.inject(WorkspaceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call extraInfo query and add missing value', () => {
      const history: IHistory = { id: 456 };
      const extraInfo: IExtraInfo = { id: 29124 };
      history.extraInfo = extraInfo;

      const extraInfoCollection: IExtraInfo[] = [{ id: 31722 }];
      jest.spyOn(extraInfoService, 'query').mockReturnValue(of(new HttpResponse({ body: extraInfoCollection })));
      const expectedCollection: IExtraInfo[] = [extraInfo, ...extraInfoCollection];
      jest.spyOn(extraInfoService, 'addExtraInfoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ history });
      comp.ngOnInit();

      expect(extraInfoService.query).toHaveBeenCalled();
      expect(extraInfoService.addExtraInfoToCollectionIfMissing).toHaveBeenCalledWith(extraInfoCollection, extraInfo);
      expect(comp.extraInfosCollection).toEqual(expectedCollection);
    });

    it('Should call Workspace query and add missing value', () => {
      const history: IHistory = { id: 456 };
      const workspace: IWorkspace = { id: 3570 };
      history.workspace = workspace;

      const workspaceCollection: IWorkspace[] = [{ id: 24642 }];
      jest.spyOn(workspaceService, 'query').mockReturnValue(of(new HttpResponse({ body: workspaceCollection })));
      const additionalWorkspaces = [workspace];
      const expectedCollection: IWorkspace[] = [...additionalWorkspaces, ...workspaceCollection];
      jest.spyOn(workspaceService, 'addWorkspaceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ history });
      comp.ngOnInit();

      expect(workspaceService.query).toHaveBeenCalled();
      expect(workspaceService.addWorkspaceToCollectionIfMissing).toHaveBeenCalledWith(
        workspaceCollection,
        ...additionalWorkspaces.map(expect.objectContaining),
      );
      expect(comp.workspacesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const history: IHistory = { id: 456 };
      const extraInfo: IExtraInfo = { id: 12148 };
      history.extraInfo = extraInfo;
      const workspace: IWorkspace = { id: 28096 };
      history.workspace = workspace;

      activatedRoute.data = of({ history });
      comp.ngOnInit();

      expect(comp.extraInfosCollection).toContain(extraInfo);
      expect(comp.workspacesSharedCollection).toContain(workspace);
      expect(comp.history).toEqual(history);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHistory>>();
      const history = { id: 123 };
      jest.spyOn(historyFormService, 'getHistory').mockReturnValue(history);
      jest.spyOn(historyService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ history });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: history }));
      saveSubject.complete();

      // THEN
      expect(historyFormService.getHistory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(historyService.update).toHaveBeenCalledWith(expect.objectContaining(history));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHistory>>();
      const history = { id: 123 };
      jest.spyOn(historyFormService, 'getHistory').mockReturnValue({ id: null });
      jest.spyOn(historyService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ history: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: history }));
      saveSubject.complete();

      // THEN
      expect(historyFormService.getHistory).toHaveBeenCalled();
      expect(historyService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHistory>>();
      const history = { id: 123 };
      jest.spyOn(historyService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ history });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(historyService.update).toHaveBeenCalled();
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
