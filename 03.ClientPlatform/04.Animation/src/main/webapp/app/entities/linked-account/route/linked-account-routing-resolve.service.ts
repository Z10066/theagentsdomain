import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILinkedAccount } from '../linked-account.model';
import { LinkedAccountService } from '../service/linked-account.service';

export const linkedAccountResolve = (route: ActivatedRouteSnapshot): Observable<null | ILinkedAccount> => {
  const id = route.params['id'];
  if (id) {
    return inject(LinkedAccountService)
      .find(id)
      .pipe(
        mergeMap((linkedAccount: HttpResponse<ILinkedAccount>) => {
          if (linkedAccount.body) {
            return of(linkedAccount.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default linkedAccountResolve;
