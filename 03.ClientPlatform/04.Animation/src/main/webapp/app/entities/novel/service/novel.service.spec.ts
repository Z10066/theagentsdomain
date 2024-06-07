import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { INovel } from '../novel.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../novel.test-samples';

import { NovelService } from './novel.service';

const requireRestSample: INovel = {
  ...sampleWithRequiredData,
};

describe('Novel Service', () => {
  let service: NovelService;
  let httpMock: HttpTestingController;
  let expectedResult: INovel | INovel[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NovelService);
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

    it('should create a Novel', () => {
      const novel = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(novel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Novel', () => {
      const novel = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(novel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Novel', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Novel', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Novel', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addNovelToCollectionIfMissing', () => {
      it('should add a Novel to an empty array', () => {
        const novel: INovel = sampleWithRequiredData;
        expectedResult = service.addNovelToCollectionIfMissing([], novel);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(novel);
      });

      it('should not add a Novel to an array that contains it', () => {
        const novel: INovel = sampleWithRequiredData;
        const novelCollection: INovel[] = [
          {
            ...novel,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addNovelToCollectionIfMissing(novelCollection, novel);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Novel to an array that doesn't contain it", () => {
        const novel: INovel = sampleWithRequiredData;
        const novelCollection: INovel[] = [sampleWithPartialData];
        expectedResult = service.addNovelToCollectionIfMissing(novelCollection, novel);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(novel);
      });

      it('should add only unique Novel to an array', () => {
        const novelArray: INovel[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const novelCollection: INovel[] = [sampleWithRequiredData];
        expectedResult = service.addNovelToCollectionIfMissing(novelCollection, ...novelArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const novel: INovel = sampleWithRequiredData;
        const novel2: INovel = sampleWithPartialData;
        expectedResult = service.addNovelToCollectionIfMissing([], novel, novel2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(novel);
        expect(expectedResult).toContain(novel2);
      });

      it('should accept null and undefined values', () => {
        const novel: INovel = sampleWithRequiredData;
        expectedResult = service.addNovelToCollectionIfMissing([], null, novel, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(novel);
      });

      it('should return initial array if no Novel is added', () => {
        const novelCollection: INovel[] = [sampleWithRequiredData];
        expectedResult = service.addNovelToCollectionIfMissing(novelCollection, undefined, null);
        expect(expectedResult).toEqual(novelCollection);
      });
    });

    describe('compareNovel', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareNovel(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareNovel(entity1, entity2);
        const compareResult2 = service.compareNovel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareNovel(entity1, entity2);
        const compareResult2 = service.compareNovel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareNovel(entity1, entity2);
        const compareResult2 = service.compareNovel(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
