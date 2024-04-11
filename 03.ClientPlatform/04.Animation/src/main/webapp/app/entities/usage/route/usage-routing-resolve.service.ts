import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUsage } from '../usage.model';
import { UsageService } from '../service/usage.service';

export const usageResolve = (route: ActivatedRouteSnapshot): Observable<null | IUsage> => {
  const id = route.params['id'];
  if (id) {
    return inject(UsageService)
      .find(id)
      .pipe(
        mergeMap((usage: HttpResponse<IUsage>) => {
          if (usage.body) {
            return of(usage.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default usageResolve;
