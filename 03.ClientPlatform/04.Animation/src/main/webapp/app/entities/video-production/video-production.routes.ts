import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VideoProductionComponent } from './list/video-production.component';
import { VideoProductionDetailComponent } from './detail/video-production-detail.component';
import { VideoProductionUpdateComponent } from './update/video-production-update.component';
import VideoProductionResolve from './route/video-production-routing-resolve.service';

const videoProductionRoute: Routes = [
  {
    path: '',
    component: VideoProductionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VideoProductionDetailComponent,
    resolve: {
      videoProduction: VideoProductionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VideoProductionUpdateComponent,
    resolve: {
      videoProduction: VideoProductionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VideoProductionUpdateComponent,
    resolve: {
      videoProduction: VideoProductionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default videoProductionRoute;
