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
import { IVideoProduction } from '../video-production.model';
import { VideoProductionService } from '../service/video-production.service';
import { VideoProductionFormService } from './video-production-form.service';

import { VideoProductionUpdateComponent } from './video-production-update.component';

describe('VideoProduction Management Update Component', () => {
  let comp: VideoProductionUpdateComponent;
  let fixture: ComponentFixture<VideoProductionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let videoProductionFormService: VideoProductionFormService;
  let videoProductionService: VideoProductionService;
  let extraInfoService: ExtraInfoService;
  let workspaceService: WorkspaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), VideoProductionUpdateComponent],
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
      .overrideTemplate(VideoProductionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VideoProductionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    videoProductionFormService = TestBed.inject(VideoProductionFormService);
    videoProductionService = TestBed.inject(VideoProductionService);
    extraInfoService = TestBed.inject(ExtraInfoService);
    workspaceService = TestBed.inject(WorkspaceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call extraInfo query and add missing value', () => {
      const videoProduction: IVideoProduction = { id: 456 };
      const extraInfo: IExtraInfo = { id: 29465 };
      videoProduction.extraInfo = extraInfo;

      const extraInfoCollection: IExtraInfo[] = [{ id: 1133 }];
      jest.spyOn(extraInfoService, 'query').mockReturnValue(of(new HttpResponse({ body: extraInfoCollection })));
      const expectedCollection: IExtraInfo[] = [extraInfo, ...extraInfoCollection];
      jest.spyOn(extraInfoService, 'addExtraInfoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ videoProduction });
      comp.ngOnInit();

      expect(extraInfoService.query).toHaveBeenCalled();
      expect(extraInfoService.addExtraInfoToCollectionIfMissing).toHaveBeenCalledWith(extraInfoCollection, extraInfo);
      expect(comp.extraInfosCollection).toEqual(expectedCollection);
    });

    it('Should call Workspace query and add missing value', () => {
      const videoProduction: IVideoProduction = { id: 456 };
      const workspace: IWorkspace = { id: 22983 };
      videoProduction.workspace = workspace;

      const workspaceCollection: IWorkspace[] = [{ id: 23485 }];
      jest.spyOn(workspaceService, 'query').mockReturnValue(of(new HttpResponse({ body: workspaceCollection })));
      const additionalWorkspaces = [workspace];
      const expectedCollection: IWorkspace[] = [...additionalWorkspaces, ...workspaceCollection];
      jest.spyOn(workspaceService, 'addWorkspaceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ videoProduction });
      comp.ngOnInit();

      expect(workspaceService.query).toHaveBeenCalled();
      expect(workspaceService.addWorkspaceToCollectionIfMissing).toHaveBeenCalledWith(
        workspaceCollection,
        ...additionalWorkspaces.map(expect.objectContaining),
      );
      expect(comp.workspacesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const videoProduction: IVideoProduction = { id: 456 };
      const extraInfo: IExtraInfo = { id: 28492 };
      videoProduction.extraInfo = extraInfo;
      const workspace: IWorkspace = { id: 12062 };
      videoProduction.workspace = workspace;

      activatedRoute.data = of({ videoProduction });
      comp.ngOnInit();

      expect(comp.extraInfosCollection).toContain(extraInfo);
      expect(comp.workspacesSharedCollection).toContain(workspace);
      expect(comp.videoProduction).toEqual(videoProduction);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideoProduction>>();
      const videoProduction = { id: 123 };
      jest.spyOn(videoProductionFormService, 'getVideoProduction').mockReturnValue(videoProduction);
      jest.spyOn(videoProductionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ videoProduction });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: videoProduction }));
      saveSubject.complete();

      // THEN
      expect(videoProductionFormService.getVideoProduction).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(videoProductionService.update).toHaveBeenCalledWith(expect.objectContaining(videoProduction));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideoProduction>>();
      const videoProduction = { id: 123 };
      jest.spyOn(videoProductionFormService, 'getVideoProduction').mockReturnValue({ id: null });
      jest.spyOn(videoProductionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ videoProduction: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: videoProduction }));
      saveSubject.complete();

      // THEN
      expect(videoProductionFormService.getVideoProduction).toHaveBeenCalled();
      expect(videoProductionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVideoProduction>>();
      const videoProduction = { id: 123 };
      jest.spyOn(videoProductionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ videoProduction });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(videoProductionService.update).toHaveBeenCalled();
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
