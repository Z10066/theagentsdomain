import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreator } from '../creator.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../creator.test-samples';

import { CreatorService } from './creator.service';

const requireRestSample: ICreator = {
  ...sampleWithRequiredData,
};

describe('Creator Service', () => {
  let service: CreatorService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreator | ICreator[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreatorService);
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

    it('should create a Creator', () => {
      const creator = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(creator).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Creator', () => {
      const creator = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(creator).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Creator', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Creator', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Creator', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreatorToCollectionIfMissing', () => {
      it('should add a Creator to an empty array', () => {
        const creator: ICreator = sampleWithRequiredData;
        expectedResult = service.addCreatorToCollectionIfMissing([], creator);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(creator);
      });

      it('should not add a Creator to an array that contains it', () => {
        const creator: ICreator = sampleWithRequiredData;
        const creatorCollection: ICreator[] = [
          {
            ...creator,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreatorToCollectionIfMissing(creatorCollection, creator);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Creator to an array that doesn't contain it", () => {
        const creator: ICreator = sampleWithRequiredData;
        const creatorCollection: ICreator[] = [sampleWithPartialData];
        expectedResult = service.addCreatorToCollectionIfMissing(creatorCollection, creator);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(creator);
      });

      it('should add only unique Creator to an array', () => {
        const creatorArray: ICreator[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const creatorCollection: ICreator[] = [sampleWithRequiredData];
        expectedResult = service.addCreatorToCollectionIfMissing(creatorCollection, ...creatorArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const creator: ICreator = sampleWithRequiredData;
        const creator2: ICreator = sampleWithPartialData;
        expectedResult = service.addCreatorToCollectionIfMissing([], creator, creator2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(creator);
        expect(expectedResult).toContain(creator2);
      });

      it('should accept null and undefined values', () => {
        const creator: ICreator = sampleWithRequiredData;
        expectedResult = service.addCreatorToCollectionIfMissing([], null, creator, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(creator);
      });

      it('should return initial array if no Creator is added', () => {
        const creatorCollection: ICreator[] = [sampleWithRequiredData];
        expectedResult = service.addCreatorToCollectionIfMissing(creatorCollection, undefined, null);
        expect(expectedResult).toEqual(creatorCollection);
      });
    });

    describe('compareCreator', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreator(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreator(entity1, entity2);
        const compareResult2 = service.compareCreator(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreator(entity1, entity2);
        const compareResult2 = service.compareCreator(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreator(entity1, entity2);
        const compareResult2 = service.compareCreator(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
