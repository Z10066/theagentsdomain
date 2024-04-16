import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IYouTubeVideo } from '../you-tube-video.model';
import { YouTubeVideoService } from '../service/you-tube-video.service';

export const youTubeVideoResolve = (route: ActivatedRouteSnapshot): Observable<null | IYouTubeVideo> => {
  const id = route.params['id'];
  if (id) {
    return inject(YouTubeVideoService)
      .find(id)
      .pipe(
        mergeMap((youTubeVideo: HttpResponse<IYouTubeVideo>) => {
          if (youTubeVideo.body) {
            return of(youTubeVideo.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default youTubeVideoResolve;
