import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IMember } from 'app/entities/member/member.model';
import { MemberService } from 'app/entities/member/service/member.service';
import { LinkedAccountService } from '../service/linked-account.service';
import { ILinkedAccount } from '../linked-account.model';
import { LinkedAccountFormService } from './linked-account-form.service';

import { LinkedAccountUpdateComponent } from './linked-account-update.component';

describe('LinkedAccount Management Update Component', () => {
  let comp: LinkedAccountUpdateComponent;
  let fixture: ComponentFixture<LinkedAccountUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let linkedAccountFormService: LinkedAccountFormService;
  let linkedAccountService: LinkedAccountService;
  let memberService: MemberService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), LinkedAccountUpdateComponent],
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
      .overrideTemplate(LinkedAccountUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LinkedAccountUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    linkedAccountFormService = TestBed.inject(LinkedAccountFormService);
    linkedAccountService = TestBed.inject(LinkedAccountService);
    memberService = TestBed.inject(MemberService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Member query and add missing value', () => {
      const linkedAccount: ILinkedAccount = { id: 456 };
      const member: IMember = { id: 30770 };
      linkedAccount.member = member;

      const memberCollection: IMember[] = [{ id: 32557 }];
      jest.spyOn(memberService, 'query').mockReturnValue(of(new HttpResponse({ body: memberCollection })));
      const additionalMembers = [member];
      const expectedCollection: IMember[] = [...additionalMembers, ...memberCollection];
      jest.spyOn(memberService, 'addMemberToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ linkedAccount });
      comp.ngOnInit();

      expect(memberService.query).toHaveBeenCalled();
      expect(memberService.addMemberToCollectionIfMissing).toHaveBeenCalledWith(
        memberCollection,
        ...additionalMembers.map(expect.objectContaining),
      );
      expect(comp.membersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const linkedAccount: ILinkedAccount = { id: 456 };
      const member: IMember = { id: 23497 };
      linkedAccount.member = member;

      activatedRoute.data = of({ linkedAccount });
      comp.ngOnInit();

      expect(comp.membersSharedCollection).toContain(member);
      expect(comp.linkedAccount).toEqual(linkedAccount);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILinkedAccount>>();
      const linkedAccount = { id: 123 };
      jest.spyOn(linkedAccountFormService, 'getLinkedAccount').mockReturnValue(linkedAccount);
      jest.spyOn(linkedAccountService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ linkedAccount });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: linkedAccount }));
      saveSubject.complete();

      // THEN
      expect(linkedAccountFormService.getLinkedAccount).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(linkedAccountService.update).toHaveBeenCalledWith(expect.objectContaining(linkedAccount));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILinkedAccount>>();
      const linkedAccount = { id: 123 };
      jest.spyOn(linkedAccountFormService, 'getLinkedAccount').mockReturnValue({ id: null });
      jest.spyOn(linkedAccountService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ linkedAccount: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: linkedAccount }));
      saveSubject.complete();

      // THEN
      expect(linkedAccountFormService.getLinkedAccount).toHaveBeenCalled();
      expect(linkedAccountService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILinkedAccount>>();
      const linkedAccount = { id: 123 };
      jest.spyOn(linkedAccountService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ linkedAccount });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(linkedAccountService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareMember', () => {
      it('Should forward to memberService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(memberService, 'compareMember');
        comp.compareMember(entity, entity2);
        expect(memberService.compareMember).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
