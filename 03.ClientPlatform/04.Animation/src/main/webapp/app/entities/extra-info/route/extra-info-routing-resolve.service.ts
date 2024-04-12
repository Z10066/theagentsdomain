import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IExtraInfo } from '../extra-info.model';
import { ExtraInfoService } from '../service/extra-info.service';

export const extraInfoResolve = (route: ActivatedRouteSnapshot): Observable<null | IExtraInfo> => {
  const id = route.params['id'];
  if (id) {
    return inject(ExtraInfoService)
      .find(id)
      .pipe(
        mergeMap((extraInfo: HttpResponse<IExtraInfo>) => {
          if (extraInfo.body) {
            return of(extraInfo.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default extraInfoResolve;
