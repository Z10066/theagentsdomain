import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IWorkspace } from '../workspace.model';
import { WorkspaceService } from '../service/workspace.service';

export const workspaceResolve = (route: ActivatedRouteSnapshot): Observable<null | IWorkspace> => {
  const id = route.params['id'];
  if (id) {
    return inject(WorkspaceService)
      .find(id)
      .pipe(
        mergeMap((workspace: HttpResponse<IWorkspace>) => {
          if (workspace.body) {
            return of(workspace.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default workspaceResolve;
