import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFileConfiguration, FileConfiguration } from '../file-configuration.model';

import { FileConfigurationService } from './file-configuration.service';

describe('FileConfiguration Service', () => {
  let service: FileConfigurationService;
  let httpMock: HttpTestingController;
  let elemDefault: IFileConfiguration;
  let expectedResult: IFileConfiguration | IFileConfiguration[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FileConfigurationService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      description: 'AAAAAAA',
      path: 'AAAAAAA',
      types: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a FileConfiguration', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new FileConfiguration()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FileConfiguration', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          description: 'BBBBBB',
          path: 'BBBBBB',
          types: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FileConfiguration', () => {
      const patchObject = Object.assign(
        {
          description: 'BBBBBB',
          types: 'BBBBBB',
        },
        new FileConfiguration()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FileConfiguration', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          description: 'BBBBBB',
          path: 'BBBBBB',
          types: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a FileConfiguration', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addFileConfigurationToCollectionIfMissing', () => {
      it('should add a FileConfiguration to an empty array', () => {
        const fileConfiguration: IFileConfiguration = { id: 123 };
        expectedResult = service.addFileConfigurationToCollectionIfMissing([], fileConfiguration);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fileConfiguration);
      });

      it('should not add a FileConfiguration to an array that contains it', () => {
        const fileConfiguration: IFileConfiguration = { id: 123 };
        const fileConfigurationCollection: IFileConfiguration[] = [
          {
            ...fileConfiguration,
          },
          { id: 456 },
        ];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, fileConfiguration);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FileConfiguration to an array that doesn't contain it", () => {
        const fileConfiguration: IFileConfiguration = { id: 123 };
        const fileConfigurationCollection: IFileConfiguration[] = [{ id: 456 }];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, fileConfiguration);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fileConfiguration);
      });

      it('should add only unique FileConfiguration to an array', () => {
        const fileConfigurationArray: IFileConfiguration[] = [{ id: 123 }, { id: 456 }, { id: 37233 }];
        const fileConfigurationCollection: IFileConfiguration[] = [{ id: 123 }];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, ...fileConfigurationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const fileConfiguration: IFileConfiguration = { id: 123 };
        const fileConfiguration2: IFileConfiguration = { id: 456 };
        expectedResult = service.addFileConfigurationToCollectionIfMissing([], fileConfiguration, fileConfiguration2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fileConfiguration);
        expect(expectedResult).toContain(fileConfiguration2);
      });

      it('should accept null and undefined values', () => {
        const fileConfiguration: IFileConfiguration = { id: 123 };
        expectedResult = service.addFileConfigurationToCollectionIfMissing([], null, fileConfiguration, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fileConfiguration);
      });

      it('should return initial array if no FileConfiguration is added', () => {
        const fileConfigurationCollection: IFileConfiguration[] = [{ id: 123 }];
        expectedResult = service.addFileConfigurationToCollectionIfMissing(fileConfigurationCollection, undefined, null);
        expect(expectedResult).toEqual(fileConfigurationCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
