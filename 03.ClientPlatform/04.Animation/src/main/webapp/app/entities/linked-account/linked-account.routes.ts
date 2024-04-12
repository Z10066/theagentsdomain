import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { LinkedAccountComponent } from './list/linked-account.component';
import { LinkedAccountDetailComponent } from './detail/linked-account-detail.component';
import { LinkedAccountUpdateComponent } from './update/linked-account-update.component';
import LinkedAccountResolve from './route/linked-account-routing-resolve.service';

const linkedAccountRoute: Routes = [
  {
    path: '',
    component: LinkedAccountComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LinkedAccountDetailComponent,
    resolve: {
      linkedAccount: LinkedAccountResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LinkedAccountUpdateComponent,
    resolve: {
      linkedAccount: LinkedAccountResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LinkedAccountUpdateComponent,
    resolve: {
      linkedAccount: LinkedAccountResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default linkedAccountRoute;
