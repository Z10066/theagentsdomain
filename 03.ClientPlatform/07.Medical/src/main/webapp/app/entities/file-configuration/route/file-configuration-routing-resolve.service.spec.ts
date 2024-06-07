import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IFileConfiguration, FileConfiguration } from '../file-configuration.model';
import { FileConfigurationService } from '../service/file-configuration.service';

import { FileConfigurationRoutingResolveService } from './file-configuration-routing-resolve.service';

describe('FileConfiguration routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: FileConfigurationRoutingResolveService;
  let service: FileConfigurationService;
  let resultFileConfiguration: IFileConfiguration | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(FileConfigurationRoutingResolveService);
    service = TestBed.inject(FileConfigurationService);
    resultFileConfiguration = undefined;
  });

  describe('resolve', () => {
    it('should return IFileConfiguration returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFileConfiguration = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFileConfiguration).toEqual({ id: 123 });
    });

    it('should return new IFileConfiguration if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFileConfiguration = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFileConfiguration).toEqual(new FileConfiguration());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as FileConfiguration })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFileConfiguration = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFileConfiguration).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
