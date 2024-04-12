import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IExtraInfo } from '../extra-info.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../extra-info.test-samples';

import { ExtraInfoService } from './extra-info.service';

const requireRestSample: IExtraInfo = {
  ...sampleWithRequiredData,
};

describe('ExtraInfo Service', () => {
  let service: ExtraInfoService;
  let httpMock: HttpTestingController;
  let expectedResult: IExtraInfo | IExtraInfo[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ExtraInfoService);
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

    it('should create a ExtraInfo', () => {
      const extraInfo = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(extraInfo).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ExtraInfo', () => {
      const extraInfo = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(extraInfo).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ExtraInfo', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ExtraInfo', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ExtraInfo', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addExtraInfoToCollectionIfMissing', () => {
      it('should add a ExtraInfo to an empty array', () => {
        const extraInfo: IExtraInfo = sampleWithRequiredData;
        expectedResult = service.addExtraInfoToCollectionIfMissing([], extraInfo);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(extraInfo);
      });

      it('should not add a ExtraInfo to an array that contains it', () => {
        const extraInfo: IExtraInfo = sampleWithRequiredData;
        const extraInfoCollection: IExtraInfo[] = [
          {
            ...extraInfo,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addExtraInfoToCollectionIfMissing(extraInfoCollection, extraInfo);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ExtraInfo to an array that doesn't contain it", () => {
        const extraInfo: IExtraInfo = sampleWithRequiredData;
        const extraInfoCollection: IExtraInfo[] = [sampleWithPartialData];
        expectedResult = service.addExtraInfoToCollectionIfMissing(extraInfoCollection, extraInfo);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(extraInfo);
      });

      it('should add only unique ExtraInfo to an array', () => {
        const extraInfoArray: IExtraInfo[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const extraInfoCollection: IExtraInfo[] = [sampleWithRequiredData];
        expectedResult = service.addExtraInfoToCollectionIfMissing(extraInfoCollection, ...extraInfoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const extraInfo: IExtraInfo = sampleWithRequiredData;
        const extraInfo2: IExtraInfo = sampleWithPartialData;
        expectedResult = service.addExtraInfoToCollectionIfMissing([], extraInfo, extraInfo2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(extraInfo);
        expect(expectedResult).toContain(extraInfo2);
      });

      it('should accept null and undefined values', () => {
        const extraInfo: IExtraInfo = sampleWithRequiredData;
        expectedResult = service.addExtraInfoToCollectionIfMissing([], null, extraInfo, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(extraInfo);
      });

      it('should return initial array if no ExtraInfo is added', () => {
        const extraInfoCollection: IExtraInfo[] = [sampleWithRequiredData];
        expectedResult = service.addExtraInfoToCollectionIfMissing(extraInfoCollection, undefined, null);
        expect(expectedResult).toEqual(extraInfoCollection);
      });
    });

    describe('compareExtraInfo', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareExtraInfo(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareExtraInfo(entity1, entity2);
        const compareResult2 = service.compareExtraInfo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareExtraInfo(entity1, entity2);
        const compareResult2 = service.compareExtraInfo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareExtraInfo(entity1, entity2);
        const compareResult2 = service.compareExtraInfo(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
