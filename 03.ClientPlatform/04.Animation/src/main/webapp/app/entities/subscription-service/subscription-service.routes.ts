import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { SubscriptionServiceComponent } from './list/subscription-service.component';
import { SubscriptionServiceDetailComponent } from './detail/subscription-service-detail.component';
import { SubscriptionServiceUpdateComponent } from './update/subscription-service-update.component';
import SubscriptionServiceResolve from './route/subscription-service-routing-resolve.service';

const subscriptionServiceRoute: Routes = [
  {
    path: '',
    component: SubscriptionServiceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SubscriptionServiceDetailComponent,
    resolve: {
      subscriptionService: SubscriptionServiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SubscriptionServiceUpdateComponent,
    resolve: {
      subscriptionService: SubscriptionServiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SubscriptionServiceUpdateComponent,
    resolve: {
      subscriptionService: SubscriptionServiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default subscriptionServiceRoute;
