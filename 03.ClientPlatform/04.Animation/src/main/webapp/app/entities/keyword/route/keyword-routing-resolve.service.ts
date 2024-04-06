import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IKeyword } from '../keyword.model';
import { KeywordService } from '../service/keyword.service';

export const keywordResolve = (route: ActivatedRouteSnapshot): Observable<null | IKeyword> => {
  const id = route.params['id'];
  if (id) {
    return inject(KeywordService)
      .find(id)
      .pipe(
        mergeMap((keyword: HttpResponse<IKeyword>) => {
          if (keyword.body) {
            return of(keyword.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default keywordResolve;
