import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFileConfiguration, FileConfiguration } from '../file-configuration.model';
import { FileConfigurationService } from '../service/file-configuration.service';

@Injectable({ providedIn: 'root' })
export class FileConfigurationRoutingResolveService implements Resolve<IFileConfiguration> {
  constructor(protected service: FileConfigurationService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFileConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((fileConfiguration: HttpResponse<FileConfiguration>) => {
          if (fileConfiguration.body) {
            return of(fileConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FileConfiguration());
  }
}
