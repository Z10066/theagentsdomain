import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVideoProduction } from '../video-production.model';
import { VideoProductionService } from '../service/video-production.service';

export const videoProductionResolve = (route: ActivatedRouteSnapshot): Observable<null | IVideoProduction> => {
  const id = route.params['id'];
  if (id) {
    return inject(VideoProductionService)
      .find(id)
      .pipe(
        mergeMap((videoProduction: HttpResponse<IVideoProduction>) => {
          if (videoProduction.body) {
            return of(videoProduction.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default videoProductionResolve;
