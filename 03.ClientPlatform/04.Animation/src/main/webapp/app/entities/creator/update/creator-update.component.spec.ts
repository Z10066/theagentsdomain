import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreatorService } from '../service/creator.service';
import { ICreator } from '../creator.model';
import { CreatorFormService } from './creator-form.service';

import { CreatorUpdateComponent } from './creator-update.component';

describe('Creator Management Update Component', () => {
  let comp: CreatorUpdateComponent;
  let fixture: ComponentFixture<CreatorUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let creatorFormService: CreatorFormService;
  let creatorService: CreatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CreatorUpdateComponent],
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
      .overrideTemplate(CreatorUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreatorUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    creatorFormService = TestBed.inject(CreatorFormService);
    creatorService = TestBed.inject(CreatorService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const creator: ICreator = { id: 456 };

      activatedRoute.data = of({ creator });
      comp.ngOnInit();

      expect(comp.creator).toEqual(creator);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreator>>();
      const creator = { id: 123 };
      jest.spyOn(creatorFormService, 'getCreator').mockReturnValue(creator);
      jest.spyOn(creatorService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creator });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: creator }));
      saveSubject.complete();

      // THEN
      expect(creatorFormService.getCreator).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(creatorService.update).toHaveBeenCalledWith(expect.objectContaining(creator));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreator>>();
      const creator = { id: 123 };
      jest.spyOn(creatorFormService, 'getCreator').mockReturnValue({ id: null });
      jest.spyOn(creatorService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creator: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: creator }));
      saveSubject.complete();

      // THEN
      expect(creatorFormService.getCreator).toHaveBeenCalled();
      expect(creatorService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreator>>();
      const creator = { id: 123 };
      jest.spyOn(creatorService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creator });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(creatorService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
