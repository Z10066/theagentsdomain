import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { YouTubeVideoComponent } from './list/you-tube-video.component';
import { YouTubeVideoDetailComponent } from './detail/you-tube-video-detail.component';
import { YouTubeVideoUpdateComponent } from './update/you-tube-video-update.component';
import YouTubeVideoResolve from './route/you-tube-video-routing-resolve.service';

const youTubeVideoRoute: Routes = [
  {
    path: '',
    component: YouTubeVideoComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: YouTubeVideoDetailComponent,
    resolve: {
      youTubeVideo: YouTubeVideoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: YouTubeVideoUpdateComponent,
    resolve: {
      youTubeVideo: YouTubeVideoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: YouTubeVideoUpdateComponent,
    resolve: {
      youTubeVideo: YouTubeVideoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default youTubeVideoRoute;
