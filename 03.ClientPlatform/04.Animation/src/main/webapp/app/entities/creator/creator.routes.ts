import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CreatorComponent } from './list/creator.component';
import { CreatorDetailComponent } from './detail/creator-detail.component';
import { CreatorUpdateComponent } from './update/creator-update.component';
import CreatorResolve from './route/creator-routing-resolve.service';

const creatorRoute: Routes = [
  {
    path: '',
    component: CreatorComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreatorDetailComponent,
    resolve: {
      creator: CreatorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreatorUpdateComponent,
    resolve: {
      creator: CreatorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreatorUpdateComponent,
    resolve: {
      creator: CreatorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default creatorRoute;
