import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { SystemSettingComponent } from './list/system-setting.component';
import { SystemSettingDetailComponent } from './detail/system-setting-detail.component';
import { SystemSettingUpdateComponent } from './update/system-setting-update.component';
import SystemSettingResolve from './route/system-setting-routing-resolve.service';

const systemSettingRoute: Routes = [
  {
    path: '',
    component: SystemSettingComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SystemSettingDetailComponent,
    resolve: {
      systemSetting: SystemSettingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SystemSettingUpdateComponent,
    resolve: {
      systemSetting: SystemSettingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SystemSettingUpdateComponent,
    resolve: {
      systemSetting: SystemSettingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default systemSettingRoute;
