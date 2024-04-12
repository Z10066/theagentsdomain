import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IWorkspace } from 'app/entities/workspace/workspace.model';
import { WorkspaceService } from 'app/entities/workspace/service/workspace.service';
import { MemberService } from '../service/member.service';
import { IMember } from '../member.model';
import { MemberFormService } from './member-form.service';

import { MemberUpdateComponent } from './member-update.component';

describe('Member Management Update Component', () => {
  let comp: MemberUpdateComponent;
  let fixture: ComponentFixture<MemberUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let memberFormService: MemberFormService;
  let memberService: MemberService;
  let workspaceService: WorkspaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), MemberUpdateComponent],
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
      .overrideTemplate(MemberUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MemberUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    memberFormService = TestBed.inject(MemberFormService);
    memberService = TestBed.inject(MemberService);
    workspaceService = TestBed.inject(WorkspaceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Workspace query and add missing value', () => {
      const member: IMember = { id: 456 };
      const workspace: IWorkspace = { id: 11127 };
      member.workspace = workspace;

      const workspaceCollection: IWorkspace[] = [{ id: 13577 }];
      jest.spyOn(workspaceService, 'query').mockReturnValue(of(new HttpResponse({ body: workspaceCollection })));
      const additionalWorkspaces = [workspace];
      const expectedCollection: IWorkspace[] = [...additionalWorkspaces, ...workspaceCollection];
      jest.spyOn(workspaceService, 'addWorkspaceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ member });
      comp.ngOnInit();

      expect(workspaceService.query).toHaveBeenCalled();
      expect(workspaceService.addWorkspaceToCollectionIfMissing).toHaveBeenCalledWith(
        workspaceCollection,
        ...additionalWorkspaces.map(expect.objectContaining),
      );
      expect(comp.workspacesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const member: IMember = { id: 456 };
      const workspace: IWorkspace = { id: 22302 };
      member.workspace = workspace;

      activatedRoute.data = of({ member });
      comp.ngOnInit();

      expect(comp.workspacesSharedCollection).toContain(workspace);
      expect(comp.member).toEqual(member);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMember>>();
      const member = { id: 123 };
      jest.spyOn(memberFormService, 'getMember').mockReturnValue(member);
      jest.spyOn(memberService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ member });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: member }));
      saveSubject.complete();

      // THEN
      expect(memberFormService.getMember).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(memberService.update).toHaveBeenCalledWith(expect.objectContaining(member));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMember>>();
      const member = { id: 123 };
      jest.spyOn(memberFormService, 'getMember').mockReturnValue({ id: null });
      jest.spyOn(memberService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ member: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: member }));
      saveSubject.complete();

      // THEN
      expect(memberFormService.getMember).toHaveBeenCalled();
      expect(memberService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMember>>();
      const member = { id: 123 };
      jest.spyOn(memberService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ member });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(memberService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
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
