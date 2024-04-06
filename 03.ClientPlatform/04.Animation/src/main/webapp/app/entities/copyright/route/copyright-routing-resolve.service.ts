import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICopyright } from '../copyright.model';
import { CopyrightService } from '../service/copyright.service';

export const copyrightResolve = (route: ActivatedRouteSnapshot): Observable<null | ICopyright> => {
  const id = route.params['id'];
  if (id) {
    return inject(CopyrightService)
      .find(id)
      .pipe(
        mergeMap((copyright: HttpResponse<ICopyright>) => {
          if (copyright.body) {
            return of(copyright.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default copyrightResolve;
