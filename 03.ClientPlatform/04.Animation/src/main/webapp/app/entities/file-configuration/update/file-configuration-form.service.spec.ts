import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../file-configuration.test-samples';

import { FileConfigurationFormService } from './file-configuration-form.service';

describe('FileConfiguration Form Service', () => {
  let service: FileConfigurationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileConfigurationFormService);
  });

  describe('Service methods', () => {
    describe('createFileConfigurationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFileConfigurationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            path: expect.any(Object),
            types: expect.any(Object),
          }),
        );
      });

      it('passing IFileConfiguration should create a new form with FormGroup', () => {
        const formGroup = service.createFileConfigurationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            path: expect.any(Object),
            types: expect.any(Object),
          }),
        );
      });
    });

    describe('getFileConfiguration', () => {
      it('should return NewFileConfiguration for default FileConfiguration initial value', () => {
        const formGroup = service.createFileConfigurationFormGroup(sampleWithNewData);

        const fileConfiguration = service.getFileConfiguration(formGroup) as any;

        expect(fileConfiguration).toMatchObject(sampleWithNewData);
      });

      it('should return NewFileConfiguration for empty FileConfiguration initial value', () => {
        const formGroup = service.createFileConfigurationFormGroup();

        const fileConfiguration = service.getFileConfiguration(formGroup) as any;

        expect(fileConfiguration).toMatchObject({});
      });

      it('should return IFileConfiguration', () => {
        const formGroup = service.createFileConfigurationFormGroup(sampleWithRequiredData);

        const fileConfiguration = service.getFileConfiguration(formGroup) as any;

        expect(fileConfiguration).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFileConfiguration should not enable id FormControl', () => {
        const formGroup = service.createFileConfigurationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFileConfiguration should disable id FormControl', () => {
        const formGroup = service.createFileConfigurationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
