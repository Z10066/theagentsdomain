import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { NovelComponent } from './list/novel.component';
import { NovelDetailComponent } from './detail/novel-detail.component';
import { NovelUpdateComponent } from './update/novel-update.component';
import NovelResolve from './route/novel-routing-resolve.service';

const novelRoute: Routes = [
  {
    path: '',
    component: NovelComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NovelDetailComponent,
    resolve: {
      novel: NovelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NovelUpdateComponent,
    resolve: {
      novel: NovelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NovelUpdateComponent,
    resolve: {
      novel: NovelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default novelRoute;
