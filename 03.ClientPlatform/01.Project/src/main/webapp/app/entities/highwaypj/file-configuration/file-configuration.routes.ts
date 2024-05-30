import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FileConfigurationComponent } from './list/file-configuration.component';
import { FileConfigurationDetailComponent } from './detail/file-configuration-detail.component';
import { FileConfigurationUpdateComponent } from './update/file-configuration-update.component';
import FileConfigurationResolve from './route/file-configuration-routing-resolve.service';

const fileConfigurationRoute: Routes = [
  {
    path: '',
    component: FileConfigurationComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FileConfigurationDetailComponent,
    resolve: {
      fileConfiguration: FileConfigurationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FileConfigurationUpdateComponent,
    resolve: {
      fileConfiguration: FileConfigurationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FileConfigurationUpdateComponent,
    resolve: {
      fileConfiguration: FileConfigurationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default fileConfigurationRoute;
