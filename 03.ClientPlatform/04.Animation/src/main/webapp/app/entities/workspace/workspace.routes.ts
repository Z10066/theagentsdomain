import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { WorkspaceComponent } from './list/workspace.component';
import { WorkspaceDetailComponent } from './detail/workspace-detail.component';
import { WorkspaceUpdateComponent } from './update/workspace-update.component';
import WorkspaceResolve from './route/workspace-routing-resolve.service';

const workspaceRoute: Routes = [
  {
    path: '',
    component: WorkspaceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WorkspaceDetailComponent,
    resolve: {
      workspace: WorkspaceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WorkspaceUpdateComponent,
    resolve: {
      workspace: WorkspaceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WorkspaceUpdateComponent,
    resolve: {
      workspace: WorkspaceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default workspaceRoute;
