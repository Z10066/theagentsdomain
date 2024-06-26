import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FileConfigurationService } from '../service/file-configuration.service';
import { IFileConfiguration } from '../file-configuration.model';
import { FileConfigurationFormService } from './file-configuration-form.service';

import { FileConfigurationUpdateComponent } from './file-configuration-update.component';

describe('FileConfiguration Management Update Component', () => {
  let comp: FileConfigurationUpdateComponent;
  let fixture: ComponentFixture<FileConfigurationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let fileConfigurationFormService: FileConfigurationFormService;
  let fileConfigurationService: FileConfigurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FileConfigurationUpdateComponent],
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
      .overrideTemplate(FileConfigurationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FileConfigurationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fileConfigurationFormService = TestBed.inject(FileConfigurationFormService);
    fileConfigurationService = TestBed.inject(FileConfigurationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const fileConfiguration: IFileConfiguration = { id: 456 };

      activatedRoute.data = of({ fileConfiguration });
      comp.ngOnInit();

      expect(comp.fileConfiguration).toEqual(fileConfiguration);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFileConfiguration>>();
      const fileConfiguration = { id: 123 };
      jest.spyOn(fileConfigurationFormService, 'getFileConfiguration').mockReturnValue(fileConfiguration);
      jest.spyOn(fileConfigurationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fileConfiguration });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fileConfiguration }));
      saveSubject.complete();

      // THEN
      expect(fileConfigurationFormService.getFileConfiguration).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(fileConfigurationService.update).toHaveBeenCalledWith(expect.objectContaining(fileConfiguration));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFileConfiguration>>();
      const fileConfiguration = { id: 123 };
      jest.spyOn(fileConfigurationFormService, 'getFileConfiguration').mockReturnValue({ id: null });
      jest.spyOn(fileConfigurationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fileConfiguration: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fileConfiguration }));
      saveSubject.complete();

      // THEN
      expect(fileConfigurationFormService.getFileConfiguration).toHaveBeenCalled();
      expect(fileConfigurationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFileConfiguration>>();
      const fileConfiguration = { id: 123 };
      jest.spyOn(fileConfigurationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fileConfiguration });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(fileConfigurationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
