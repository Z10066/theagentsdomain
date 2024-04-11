import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVideoProduction } from '../video-production.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../video-production.test-samples';

import { VideoProductionService } from './video-production.service';

const requireRestSample: IVideoProduction = {
  ...sampleWithRequiredData,
};

describe('VideoProduction Service', () => {
  let service: VideoProductionService;
  let httpMock: HttpTestingController;
  let expectedResult: IVideoProduction | IVideoProduction[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VideoProductionService);
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

    it('should create a VideoProduction', () => {
      const videoProduction = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(videoProduction).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a VideoProduction', () => {
      const videoProduction = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(videoProduction).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a VideoProduction', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of VideoProduction', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a VideoProduction', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVideoProductionToCollectionIfMissing', () => {
      it('should add a VideoProduction to an empty array', () => {
        const videoProduction: IVideoProduction = sampleWithRequiredData;
        expectedResult = service.addVideoProductionToCollectionIfMissing([], videoProduction);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(videoProduction);
      });

      it('should not add a VideoProduction to an array that contains it', () => {
        const videoProduction: IVideoProduction = sampleWithRequiredData;
        const videoProductionCollection: IVideoProduction[] = [
          {
            ...videoProduction,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVideoProductionToCollectionIfMissing(videoProductionCollection, videoProduction);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a VideoProduction to an array that doesn't contain it", () => {
        const videoProduction: IVideoProduction = sampleWithRequiredData;
        const videoProductionCollection: IVideoProduction[] = [sampleWithPartialData];
        expectedResult = service.addVideoProductionToCollectionIfMissing(videoProductionCollection, videoProduction);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(videoProduction);
      });

      it('should add only unique VideoProduction to an array', () => {
        const videoProductionArray: IVideoProduction[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const videoProductionCollection: IVideoProduction[] = [sampleWithRequiredData];
        expectedResult = service.addVideoProductionToCollectionIfMissing(videoProductionCollection, ...videoProductionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const videoProduction: IVideoProduction = sampleWithRequiredData;
        const videoProduction2: IVideoProduction = sampleWithPartialData;
        expectedResult = service.addVideoProductionToCollectionIfMissing([], videoProduction, videoProduction2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(videoProduction);
        expect(expectedResult).toContain(videoProduction2);
      });

      it('should accept null and undefined values', () => {
        const videoProduction: IVideoProduction = sampleWithRequiredData;
        expectedResult = service.addVideoProductionToCollectionIfMissing([], null, videoProduction, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(videoProduction);
      });

      it('should return initial array if no VideoProduction is added', () => {
        const videoProductionCollection: IVideoProduction[] = [sampleWithRequiredData];
        expectedResult = service.addVideoProductionToCollectionIfMissing(videoProductionCollection, undefined, null);
        expect(expectedResult).toEqual(videoProductionCollection);
      });
    });

    describe('compareVideoProduction', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVideoProduction(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVideoProduction(entity1, entity2);
        const compareResult2 = service.compareVideoProduction(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVideoProduction(entity1, entity2);
        const compareResult2 = service.compareVideoProduction(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVideoProduction(entity1, entity2);
        const compareResult2 = service.compareVideoProduction(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
