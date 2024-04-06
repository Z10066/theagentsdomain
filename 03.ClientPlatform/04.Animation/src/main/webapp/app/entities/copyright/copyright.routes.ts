import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CopyrightComponent } from './list/copyright.component';
import { CopyrightDetailComponent } from './detail/copyright-detail.component';
import { CopyrightUpdateComponent } from './update/copyright-update.component';
import CopyrightResolve from './route/copyright-routing-resolve.service';

const copyrightRoute: Routes = [
  {
    path: '',
    component: CopyrightComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CopyrightDetailComponent,
    resolve: {
      copyright: CopyrightResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CopyrightUpdateComponent,
    resolve: {
      copyright: CopyrightResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CopyrightUpdateComponent,
    resolve: {
      copyright: CopyrightResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default copyrightRoute;
