import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { UsageComponent } from './list/usage.component';
import { UsageDetailComponent } from './detail/usage-detail.component';
import { UsageUpdateComponent } from './update/usage-update.component';
import UsageResolve from './route/usage-routing-resolve.service';

const usageRoute: Routes = [
  {
    path: '',
    component: UsageComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UsageDetailComponent,
    resolve: {
      usage: UsageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UsageUpdateComponent,
    resolve: {
      usage: UsageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UsageUpdateComponent,
    resolve: {
      usage: UsageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default usageRoute;
