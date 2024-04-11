import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../workspace.test-samples';

import { WorkspaceFormService } from './workspace-form.service';

describe('Workspace Form Service', () => {
  let service: WorkspaceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkspaceFormService);
  });

  describe('Service methods', () => {
    describe('createWorkspaceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createWorkspaceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            identifier: expect.any(Object),
            betaFeatures: expect.any(Object),
            collaborationCursor: expect.any(Object),
            defaultExportVisibility: expect.any(Object),
            publicAccess: expect.any(Object),
          }),
        );
      });

      it('passing IWorkspace should create a new form with FormGroup', () => {
        const formGroup = service.createWorkspaceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            identifier: expect.any(Object),
            betaFeatures: expect.any(Object),
            collaborationCursor: expect.any(Object),
            defaultExportVisibility: expect.any(Object),
            publicAccess: expect.any(Object),
          }),
        );
      });
    });

    describe('getWorkspace', () => {
      it('should return NewWorkspace for default Workspace initial value', () => {
        const formGroup = service.createWorkspaceFormGroup(sampleWithNewData);

        const workspace = service.getWorkspace(formGroup) as any;

        expect(workspace).toMatchObject(sampleWithNewData);
      });

      it('should return NewWorkspace for empty Workspace initial value', () => {
        const formGroup = service.createWorkspaceFormGroup();

        const workspace = service.getWorkspace(formGroup) as any;

        expect(workspace).toMatchObject({});
      });

      it('should return IWorkspace', () => {
        const formGroup = service.createWorkspaceFormGroup(sampleWithRequiredData);

        const workspace = service.getWorkspace(formGroup) as any;

        expect(workspace).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IWorkspace should not enable id FormControl', () => {
        const formGroup = service.createWorkspaceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewWorkspace should disable id FormControl', () => {
        const formGroup = service.createWorkspaceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
