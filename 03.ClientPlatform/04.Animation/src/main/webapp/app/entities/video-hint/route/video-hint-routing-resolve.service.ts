import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVideoHint } from '../video-hint.model';
import { VideoHintService } from '../service/video-hint.service';

export const videoHintResolve = (route: ActivatedRouteSnapshot): Observable<null | IVideoHint> => {
  const id = route.params['id'];
  if (id) {
    return inject(VideoHintService)
      .find(id)
      .pipe(
        mergeMap((videoHint: HttpResponse<IVideoHint>) => {
          if (videoHint.body) {
            return of(videoHint.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default videoHintResolve;
