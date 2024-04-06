import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICopyright } from '../copyright.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../copyright.test-samples';

import { CopyrightService } from './copyright.service';

const requireRestSample: ICopyright = {
  ...sampleWithRequiredData,
};

describe('Copyright Service', () => {
  let service: CopyrightService;
  let httpMock: HttpTestingController;
  let expectedResult: ICopyright | ICopyright[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CopyrightService);
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

    it('should create a Copyright', () => {
      const copyright = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(copyright).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Copyright', () => {
      const copyright = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(copyright).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Copyright', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Copyright', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Copyright', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCopyrightToCollectionIfMissing', () => {
      it('should add a Copyright to an empty array', () => {
        const copyright: ICopyright = sampleWithRequiredData;
        expectedResult = service.addCopyrightToCollectionIfMissing([], copyright);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(copyright);
      });

      it('should not add a Copyright to an array that contains it', () => {
        const copyright: ICopyright = sampleWithRequiredData;
        const copyrightCollection: ICopyright[] = [
          {
            ...copyright,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCopyrightToCollectionIfMissing(copyrightCollection, copyright);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Copyright to an array that doesn't contain it", () => {
        const copyright: ICopyright = sampleWithRequiredData;
        const copyrightCollection: ICopyright[] = [sampleWithPartialData];
        expectedResult = service.addCopyrightToCollectionIfMissing(copyrightCollection, copyright);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(copyright);
      });

      it('should add only unique Copyright to an array', () => {
        const copyrightArray: ICopyright[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const copyrightCollection: ICopyright[] = [sampleWithRequiredData];
        expectedResult = service.addCopyrightToCollectionIfMissing(copyrightCollection, ...copyrightArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const copyright: ICopyright = sampleWithRequiredData;
        const copyright2: ICopyright = sampleWithPartialData;
        expectedResult = service.addCopyrightToCollectionIfMissing([], copyright, copyright2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(copyright);
        expect(expectedResult).toContain(copyright2);
      });

      it('should accept null and undefined values', () => {
        const copyright: ICopyright = sampleWithRequiredData;
        expectedResult = service.addCopyrightToCollectionIfMissing([], null, copyright, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(copyright);
      });

      it('should return initial array if no Copyright is added', () => {
        const copyrightCollection: ICopyright[] = [sampleWithRequiredData];
        expectedResult = service.addCopyrightToCollectionIfMissing(copyrightCollection, undefined, null);
        expect(expectedResult).toEqual(copyrightCollection);
      });
    });

    describe('compareCopyright', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCopyright(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCopyright(entity1, entity2);
        const compareResult2 = service.compareCopyright(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCopyright(entity1, entity2);
        const compareResult2 = service.compareCopyright(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCopyright(entity1, entity2);
        const compareResult2 = service.compareCopyright(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
