import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IYouTubeVideo } from '../you-tube-video.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../you-tube-video.test-samples';

import { YouTubeVideoService } from './you-tube-video.service';

const requireRestSample: IYouTubeVideo = {
  ...sampleWithRequiredData,
};

describe('YouTubeVideo Service', () => {
  let service: YouTubeVideoService;
  let httpMock: HttpTestingController;
  let expectedResult: IYouTubeVideo | IYouTubeVideo[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(YouTubeVideoService);
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

    it('should create a YouTubeVideo', () => {
      const youTubeVideo = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(youTubeVideo).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a YouTubeVideo', () => {
      const youTubeVideo = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(youTubeVideo).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a YouTubeVideo', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of YouTubeVideo', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a YouTubeVideo', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addYouTubeVideoToCollectionIfMissing', () => {
      it('should add a YouTubeVideo to an empty array', () => {
        const youTubeVideo: IYouTubeVideo = sampleWithRequiredData;
        expectedResult = service.addYouTubeVideoToCollectionIfMissing([], youTubeVideo);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(youTubeVideo);
      });

      it('should not add a YouTubeVideo to an array that contains it', () => {
        const youTubeVideo: IYouTubeVideo = sampleWithRequiredData;
        const youTubeVideoCollection: IYouTubeVideo[] = [
          {
            ...youTubeVideo,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addYouTubeVideoToCollectionIfMissing(youTubeVideoCollection, youTubeVideo);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a YouTubeVideo to an array that doesn't contain it", () => {
        const youTubeVideo: IYouTubeVideo = sampleWithRequiredData;
        const youTubeVideoCollection: IYouTubeVideo[] = [sampleWithPartialData];
        expectedResult = service.addYouTubeVideoToCollectionIfMissing(youTubeVideoCollection, youTubeVideo);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(youTubeVideo);
      });

      it('should add only unique YouTubeVideo to an array', () => {
        const youTubeVideoArray: IYouTubeVideo[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const youTubeVideoCollection: IYouTubeVideo[] = [sampleWithRequiredData];
        expectedResult = service.addYouTubeVideoToCollectionIfMissing(youTubeVideoCollection, ...youTubeVideoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const youTubeVideo: IYouTubeVideo = sampleWithRequiredData;
        const youTubeVideo2: IYouTubeVideo = sampleWithPartialData;
        expectedResult = service.addYouTubeVideoToCollectionIfMissing([], youTubeVideo, youTubeVideo2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(youTubeVideo);
        expect(expectedResult).toContain(youTubeVideo2);
      });

      it('should accept null and undefined values', () => {
        const youTubeVideo: IYouTubeVideo = sampleWithRequiredData;
        expectedResult = service.addYouTubeVideoToCollectionIfMissing([], null, youTubeVideo, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(youTubeVideo);
      });

      it('should return initial array if no YouTubeVideo is added', () => {
        const youTubeVideoCollection: IYouTubeVideo[] = [sampleWithRequiredData];
        expectedResult = service.addYouTubeVideoToCollectionIfMissing(youTubeVideoCollection, undefined, null);
        expect(expectedResult).toEqual(youTubeVideoCollection);
      });
    });

    describe('compareYouTubeVideo', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareYouTubeVideo(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareYouTubeVideo(entity1, entity2);
        const compareResult2 = service.compareYouTubeVideo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareYouTubeVideo(entity1, entity2);
        const compareResult2 = service.compareYouTubeVideo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareYouTubeVideo(entity1, entity2);
        const compareResult2 = service.compareYouTubeVideo(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
