import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FileConfigurationComponent } from '../list/file-configuration.component';
import { FileConfigurationDetailComponent } from '../detail/file-configuration-detail.component';
import { FileConfigurationUpdateComponent } from '../update/file-configuration-update.component';
import { FileConfigurationRoutingResolveService } from './file-configuration-routing-resolve.service';

const fileConfigurationRoute: Routes = [
  {
    path: '',
    component: FileConfigurationComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FileConfigurationDetailComponent,
    resolve: {
      fileConfiguration: FileConfigurationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FileConfigurationUpdateComponent,
    resolve: {
      fileConfiguration: FileConfigurationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FileConfigurationUpdateComponent,
    resolve: {
      fileConfiguration: FileConfigurationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(fileConfigurationRoute)],
  exports: [RouterModule],
})
export class FileConfigurationRoutingModule {}
