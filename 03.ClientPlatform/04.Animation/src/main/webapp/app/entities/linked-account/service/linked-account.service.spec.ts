import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILinkedAccount } from '../linked-account.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../linked-account.test-samples';

import { LinkedAccountService } from './linked-account.service';

const requireRestSample: ILinkedAccount = {
  ...sampleWithRequiredData,
};

describe('LinkedAccount Service', () => {
  let service: LinkedAccountService;
  let httpMock: HttpTestingController;
  let expectedResult: ILinkedAccount | ILinkedAccount[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LinkedAccountService);
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

    it('should create a LinkedAccount', () => {
      const linkedAccount = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(linkedAccount).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LinkedAccount', () => {
      const linkedAccount = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(linkedAccount).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LinkedAccount', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LinkedAccount', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a LinkedAccount', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addLinkedAccountToCollectionIfMissing', () => {
      it('should add a LinkedAccount to an empty array', () => {
        const linkedAccount: ILinkedAccount = sampleWithRequiredData;
        expectedResult = service.addLinkedAccountToCollectionIfMissing([], linkedAccount);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(linkedAccount);
      });

      it('should not add a LinkedAccount to an array that contains it', () => {
        const linkedAccount: ILinkedAccount = sampleWithRequiredData;
        const linkedAccountCollection: ILinkedAccount[] = [
          {
            ...linkedAccount,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLinkedAccountToCollectionIfMissing(linkedAccountCollection, linkedAccount);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LinkedAccount to an array that doesn't contain it", () => {
        const linkedAccount: ILinkedAccount = sampleWithRequiredData;
        const linkedAccountCollection: ILinkedAccount[] = [sampleWithPartialData];
        expectedResult = service.addLinkedAccountToCollectionIfMissing(linkedAccountCollection, linkedAccount);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(linkedAccount);
      });

      it('should add only unique LinkedAccount to an array', () => {
        const linkedAccountArray: ILinkedAccount[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const linkedAccountCollection: ILinkedAccount[] = [sampleWithRequiredData];
        expectedResult = service.addLinkedAccountToCollectionIfMissing(linkedAccountCollection, ...linkedAccountArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const linkedAccount: ILinkedAccount = sampleWithRequiredData;
        const linkedAccount2: ILinkedAccount = sampleWithPartialData;
        expectedResult = service.addLinkedAccountToCollectionIfMissing([], linkedAccount, linkedAccount2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(linkedAccount);
        expect(expectedResult).toContain(linkedAccount2);
      });

      it('should accept null and undefined values', () => {
        const linkedAccount: ILinkedAccount = sampleWithRequiredData;
        expectedResult = service.addLinkedAccountToCollectionIfMissing([], null, linkedAccount, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(linkedAccount);
      });

      it('should return initial array if no LinkedAccount is added', () => {
        const linkedAccountCollection: ILinkedAccount[] = [sampleWithRequiredData];
        expectedResult = service.addLinkedAccountToCollectionIfMissing(linkedAccountCollection, undefined, null);
        expect(expectedResult).toEqual(linkedAccountCollection);
      });
    });

    describe('compareLinkedAccount', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLinkedAccount(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareLinkedAccount(entity1, entity2);
        const compareResult2 = service.compareLinkedAccount(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareLinkedAccount(entity1, entity2);
        const compareResult2 = service.compareLinkedAccount(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareLinkedAccount(entity1, entity2);
        const compareResult2 = service.compareLinkedAccount(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
