import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IWorkspace } from '../workspace.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../workspace.test-samples';

import { WorkspaceService } from './workspace.service';

const requireRestSample: IWorkspace = {
  ...sampleWithRequiredData,
};

describe('Workspace Service', () => {
  let service: WorkspaceService;
  let httpMock: HttpTestingController;
  let expectedResult: IWorkspace | IWorkspace[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(WorkspaceService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Workspace', () => {
      const workspace = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(workspace).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Workspace', () => {
      const workspace = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(workspace).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Workspace', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Workspace', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Workspace', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addWorkspaceToCollectionIfMissing', () => {
      it('should add a Workspace to an empty array', () => {
        const workspace: IWorkspace = sampleWithRequiredData;
        expectedResult = service.addWorkspaceToCollectionIfMissing([], workspace);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workspace);
      });

      it('should not add a Workspace to an array that contains it', () => {
        const workspace: IWorkspace = sampleWithRequiredData;
        const workspaceCollection: IWorkspace[] = [
          {
            ...workspace,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addWorkspaceToCollectionIfMissing(workspaceCollection, workspace);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Workspace to an array that doesn't contain it", () => {
        const workspace: IWorkspace = sampleWithRequiredData;
        const workspaceCollection: IWorkspace[] = [sampleWithPartialData];
        expectedResult = service.addWorkspaceToCollectionIfMissing(workspaceCollection, workspace);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workspace);
      });

      it('should add only unique Workspace to an array', () => {
        const workspaceArray: IWorkspace[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const workspaceCollection: IWorkspace[] = [sampleWithRequiredData];
        expectedResult = service.addWorkspaceToCollectionIfMissing(workspaceCollection, ...workspaceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const workspace: IWorkspace = sampleWithRequiredData;
        const workspace2: IWorkspace = sampleWithPartialData;
        expectedResult = service.addWorkspaceToCollectionIfMissing([], workspace, workspace2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workspace);
        expect(expectedResult).toContain(workspace2);
      });

      it('should accept null and undefined values', () => {
        const workspace: IWorkspace = sampleWithRequiredData;
        expectedResult = service.addWorkspaceToCollectionIfMissing([], null, workspace, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workspace);
      });

      it('should return initial array if no Workspace is added', () => {
        const workspaceCollection: IWorkspace[] = [sampleWithRequiredData];
        expectedResult = service.addWorkspaceToCollectionIfMissing(workspaceCollection, undefined, null);
        expect(expectedResult).toEqual(workspaceCollection);
      });
    });

    describe('compareWorkspace', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareWorkspace(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareWorkspace(entity1, entity2);
        const compareResult2 = service.compareWorkspace(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareWorkspace(entity1, entity2);
        const compareResult2 = service.compareWorkspace(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareWorkspace(entity1, entity2);
        const compareResult2 = service.compareWorkspace(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
