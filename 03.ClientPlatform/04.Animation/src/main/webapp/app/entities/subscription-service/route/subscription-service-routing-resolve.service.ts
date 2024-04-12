import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISubscriptionService } from '../subscription-service.model';
import { SubscriptionServiceService } from '../service/subscription-service.service';

export const subscriptionServiceResolve = (route: ActivatedRouteSnapshot): Observable<null | ISubscriptionService> => {
  const id = route.params['id'];
  if (id) {
    return inject(SubscriptionServiceService)
      .find(id)
      .pipe(
        mergeMap((subscriptionService: HttpResponse<ISubscriptionService>) => {
          if (subscriptionService.body) {
            return of(subscriptionService.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default subscriptionServiceResolve;
