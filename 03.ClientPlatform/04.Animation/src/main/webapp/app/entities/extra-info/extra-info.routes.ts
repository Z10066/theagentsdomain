import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ExtraInfoComponent } from './list/extra-info.component';
import { ExtraInfoDetailComponent } from './detail/extra-info-detail.component';
import { ExtraInfoUpdateComponent } from './update/extra-info-update.component';
import ExtraInfoResolve from './route/extra-info-routing-resolve.service';

const extraInfoRoute: Routes = [
  {
    path: '',
    component: ExtraInfoComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ExtraInfoDetailComponent,
    resolve: {
      extraInfo: ExtraInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ExtraInfoUpdateComponent,
    resolve: {
      extraInfo: ExtraInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ExtraInfoUpdateComponent,
    resolve: {
      extraInfo: ExtraInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default extraInfoRoute;
