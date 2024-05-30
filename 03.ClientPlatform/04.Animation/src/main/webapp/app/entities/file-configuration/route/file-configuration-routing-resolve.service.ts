import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFileConfiguration } from '../file-configuration.model';
import { FileConfigurationService } from '../service/file-configuration.service';

export const fileConfigurationResolve = (route: ActivatedRouteSnapshot): Observable<null | IFileConfiguration> => {
  const id = route.params['id'];
  if (id) {
    return inject(FileConfigurationService)
      .find(id)
      .pipe(
        mergeMap((fileConfiguration: HttpResponse<IFileConfiguration>) => {
          if (fileConfiguration.body) {
            return of(fileConfiguration.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default fileConfigurationResolve;
