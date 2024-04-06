import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreator } from '../creator.model';
import { CreatorService } from '../service/creator.service';

export const creatorResolve = (route: ActivatedRouteSnapshot): Observable<null | ICreator> => {
  const id = route.params['id'];
  if (id) {
    return inject(CreatorService)
      .find(id)
      .pipe(
        mergeMap((creator: HttpResponse<ICreator>) => {
          if (creator.body) {
            return of(creator.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default creatorResolve;
