import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VideoHintComponent } from './list/video-hint.component';
import { VideoHintDetailComponent } from './detail/video-hint-detail.component';
import { VideoHintUpdateComponent } from './update/video-hint-update.component';
import VideoHintResolve from './route/video-hint-routing-resolve.service';

const videoHintRoute: Routes = [
  {
    path: '',
    component: VideoHintComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VideoHintDetailComponent,
    resolve: {
      videoHint: VideoHintResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VideoHintUpdateComponent,
    resolve: {
      videoHint: VideoHintResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VideoHintUpdateComponent,
    resolve: {
      videoHint: VideoHintResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default videoHintRoute;
