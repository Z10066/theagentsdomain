import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUsage } from '../usage.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../usage.test-samples';

import { UsageService, RestUsage } from './usage.service';

const requireRestSample: RestUsage = {
  ...sampleWithRequiredData,
  startTime: sampleWithRequiredData.startTime?.toJSON(),
  endTime: sampleWithRequiredData.endTime?.toJSON(),
};

describe('Usage Service', () => {
  let service: UsageService;
  let httpMock: HttpTestingController;
  let expectedResult: IUsage | IUsage[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UsageService);
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

    it('should create a Usage', () => {
      const usage = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(usage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Usage', () => {
      const usage = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(usage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Usage', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Usage', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Usage', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addUsageToCollectionIfMissing', () => {
      it('should add a Usage to an empty array', () => {
        const usage: IUsage = sampleWithRequiredData;
        expectedResult = service.addUsageToCollectionIfMissing([], usage);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(usage);
      });

      it('should not add a Usage to an array that contains it', () => {
        const usage: IUsage = sampleWithRequiredData;
        const usageCollection: IUsage[] = [
          {
            ...usage,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addUsageToCollectionIfMissing(usageCollection, usage);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Usage to an array that doesn't contain it", () => {
        const usage: IUsage = sampleWithRequiredData;
        const usageCollection: IUsage[] = [sampleWithPartialData];
        expectedResult = service.addUsageToCollectionIfMissing(usageCollection, usage);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(usage);
      });

      it('should add only unique Usage to an array', () => {
        const usageArray: IUsage[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const usageCollection: IUsage[] = [sampleWithRequiredData];
        expectedResult = service.addUsageToCollectionIfMissing(usageCollection, ...usageArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const usage: IUsage = sampleWithRequiredData;
        const usage2: IUsage = sampleWithPartialData;
        expectedResult = service.addUsageToCollectionIfMissing([], usage, usage2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(usage);
        expect(expectedResult).toContain(usage2);
      });

      it('should accept null and undefined values', () => {
        const usage: IUsage = sampleWithRequiredData;
        expectedResult = service.addUsageToCollectionIfMissing([], null, usage, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(usage);
      });

      it('should return initial array if no Usage is added', () => {
        const usageCollection: IUsage[] = [sampleWithRequiredData];
        expectedResult = service.addUsageToCollectionIfMissing(usageCollection, undefined, null);
        expect(expectedResult).toEqual(usageCollection);
      });
    });

    describe('compareUsage', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareUsage(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareUsage(entity1, entity2);
        const compareResult2 = service.compareUsage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareUsage(entity1, entity2);
        const compareResult2 = service.compareUsage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareUsage(entity1, entity2);
        const compareResult2 = service.compareUsage(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
