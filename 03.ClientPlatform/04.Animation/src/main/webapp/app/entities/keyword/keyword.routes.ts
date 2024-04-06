import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { KeywordComponent } from './list/keyword.component';
import { KeywordDetailComponent } from './detail/keyword-detail.component';
import { KeywordUpdateComponent } from './update/keyword-update.component';
import KeywordResolve from './route/keyword-routing-resolve.service';

const keywordRoute: Routes = [
  {
    path: '',
    component: KeywordComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: KeywordDetailComponent,
    resolve: {
      keyword: KeywordResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: KeywordUpdateComponent,
    resolve: {
      keyword: KeywordResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: KeywordUpdateComponent,
    resolve: {
      keyword: KeywordResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default keywordRoute;
