import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INovel } from '../novel.model';
import { NovelService } from '../service/novel.service';

export const novelResolve = (route: ActivatedRouteSnapshot): Observable<null | INovel> => {
  const id = route.params['id'];
  if (id) {
    return inject(NovelService)
      .find(id)
      .pipe(
        mergeMap((novel: HttpResponse<INovel>) => {
          if (novel.body) {
            return of(novel.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default novelResolve;
