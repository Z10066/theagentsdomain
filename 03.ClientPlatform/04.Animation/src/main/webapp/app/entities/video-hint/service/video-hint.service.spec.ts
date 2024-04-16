import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVideoHint } from '../video-hint.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../video-hint.test-samples';

import { VideoHintService } from './video-hint.service';

const requireRestSample: IVideoHint = {
  ...sampleWithRequiredData,
};

describe('VideoHint Service', () => {
  let service: VideoHintService;
  let httpMock: HttpTestingController;
  let expectedResult: IVideoHint | IVideoHint[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VideoHintService);
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

    it('should create a VideoHint', () => {
      const videoHint = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(videoHint).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a VideoHint', () => {
      const videoHint = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(videoHint).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a VideoHint', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of VideoHint', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a VideoHint', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVideoHintToCollectionIfMissing', () => {
      it('should add a VideoHint to an empty array', () => {
        const videoHint: IVideoHint = sampleWithRequiredData;
        expectedResult = service.addVideoHintToCollectionIfMissing([], videoHint);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(videoHint);
      });

      it('should not add a VideoHint to an array that contains it', () => {
        const videoHint: IVideoHint = sampleWithRequiredData;
        const videoHintCollection: IVideoHint[] = [
          {
            ...videoHint,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVideoHintToCollectionIfMissing(videoHintCollection, videoHint);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a VideoHint to an array that doesn't contain it", () => {
        const videoHint: IVideoHint = sampleWithRequiredData;
        const videoHintCollection: IVideoHint[] = [sampleWithPartialData];
        expectedResult = service.addVideoHintToCollectionIfMissing(videoHintCollection, videoHint);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(videoHint);
      });

      it('should add only unique VideoHint to an array', () => {
        const videoHintArray: IVideoHint[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const videoHintCollection: IVideoHint[] = [sampleWithRequiredData];
        expectedResult = service.addVideoHintToCollectionIfMissing(videoHintCollection, ...videoHintArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const videoHint: IVideoHint = sampleWithRequiredData;
        const videoHint2: IVideoHint = sampleWithPartialData;
        expectedResult = service.addVideoHintToCollectionIfMissing([], videoHint, videoHint2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(videoHint);
        expect(expectedResult).toContain(videoHint2);
      });

      it('should accept null and undefined values', () => {
        const videoHint: IVideoHint = sampleWithRequiredData;
        expectedResult = service.addVideoHintToCollectionIfMissing([], null, videoHint, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(videoHint);
      });

      it('should return initial array if no VideoHint is added', () => {
        const videoHintCollection: IVideoHint[] = [sampleWithRequiredData];
        expectedResult = service.addVideoHintToCollectionIfMissing(videoHintCollection, undefined, null);
        expect(expectedResult).toEqual(videoHintCollection);
      });
    });

    describe('compareVideoHint', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVideoHint(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVideoHint(entity1, entity2);
        const compareResult2 = service.compareVideoHint(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVideoHint(entity1, entity2);
        const compareResult2 = service.compareVideoHint(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVideoHint(entity1, entity2);
        const compareResult2 = service.compareVideoHint(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
