import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFileConfiguration } from '../file-configuration.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../file-configuration.test-samples';

import { FileConfigurationService } from './file-configuration.service';

const requireRestSample: IFileConfiguration = {
  ...sampleWithRequiredData,
};

describe('FileConfiguration Service', () => {
  let service: FileConfigurationService;
  let httpMock: HttpTestingController;
  let expectedResult: IFileConfiguration | IFileConfiguration[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FileConfigurationService);
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

    it('should create a FileConfiguration', () => {
      const fileConfiguration = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(fileConfiguration).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FileConfiguration', () => {
      const fileConfiguration = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(fileConfiguration).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FileConfiguration', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FileConfiguration', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FileConfiguration', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFileConfigurationToCollectionIfMissing', () => {
      it('should add a FileConfiguration to an empty array', () => {
        const fileConfiguration: IFileConfiguration = sampleWithRequiredData;
        expectedResult = service.addFileConfigurationToCollectionIfMissing([], fileConfiguration);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fileConfiguration);
      });

      it('should not add a FileConfiguration to an array that contains it', () => {
        const fileConfiguration: IFileConfiguration = sampleWithRequiredData;
        const fileConfigurationCollection: IFileConfiguration[] = [
          {
            ...fileConfiguration,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, fileConfiguration);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FileConfiguration to an array that doesn't contain it", () => {
        const fileConfiguration: IFileConfiguration = sampleWithRequiredData;
        const fileConfigurationCollection: IFileConfiguration[] = [sampleWithPartialData];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, fileConfiguration);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fileConfiguration);
      });

      it('should add only unique FileConfiguration to an array', () => {
        const fileConfigurationArray: IFileConfiguration[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const fileConfigurationCollection: IFileConfiguration[] = [sampleWithRequiredData];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, ...fileConfigurationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const fileConfiguration: IFileConfiguration = sampleWithRequiredData;
        const fileConfiguration2: IFileConfiguration = sampleWithPartialData;
        expectedResult = service.addFileConfigurationToCollectionIfMissing([], fileConfiguration, fileConfiguration2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fileConfiguration);
        expect(expectedResult).toContain(fileConfiguration2);
      });

      it('should accept null and undefined values', () => {
        const fileConfiguration: IFileConfiguration = sampleWithRequiredData;
        expectedResult = service.addFileConfigurationToCollectionIfMissing([], null, fileConfiguration, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fileConfiguration);
      });

      it('should return initial array if no FileConfiguration is added', () => {
        const fileConfigurationCollection: IFileConfiguration[] = [sampleWithRequiredData];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, undefined, null);
        expect(expectedResult).toEqual(fileConfigurationCollection);
      });
    });

    describe('compareFileConfiguration', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFileConfiguration(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFileConfiguration(entity1, entity2);
        const compareResult2 = service.compareFileConfiguration(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFileConfiguration(entity1, entity2);
        const compareResult2 = service.compareFileConfiguration(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFileConfiguration(entity1, entity2);
        const compareResult2 = service.compareFileConfiguration(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
