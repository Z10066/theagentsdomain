import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'video',
    data: { pageTitle: 'highwayacApp.video.home.title' },
    loadChildren: () => import('./video/video.routes'),
  },
  {
    path: 'creator',
    data: { pageTitle: 'highwayacApp.creator.home.title' },
    loadChildren: () => import('./creator/creator.routes'),
  },
  {
    path: 'category',
    data: { pageTitle: 'highwayacApp.category.home.title' },
    loadChildren: () => import('./category/category.routes'),
  },
  {
    path: 'copyright',
    data: { pageTitle: 'highwayacApp.copyright.home.title' },
    loadChildren: () => import('./copyright/copyright.routes'),
  },
  {
    path: 'metadata',
    data: { pageTitle: 'highwayacApp.metadata.home.title' },
    loadChildren: () => import('./metadata/metadata.routes'),
  },
  {
    path: 'keyword',
    data: { pageTitle: 'highwayacApp.keyword.home.title' },
    loadChildren: () => import('./keyword/keyword.routes'),
  },
  {
    path: 'workspace',
    data: { pageTitle: 'highwayacApp.workspace.home.title' },
    loadChildren: () => import('./workspace/workspace.routes'),
  },
  {
    path: 'member',
    data: { pageTitle: 'highwayacApp.member.home.title' },
    loadChildren: () => import('./member/member.routes'),
  },
  {
    path: 'linked-account',
    data: { pageTitle: 'highwayacApp.linkedAccount.home.title' },
    loadChildren: () => import('./linked-account/linked-account.routes'),
  },
  {
    path: 'subscription-service',
    data: { pageTitle: 'highwayacApp.subscriptionService.home.title' },
    loadChildren: () => import('./subscription-service/subscription-service.routes'),
  },
  {
    path: 'usage',
    data: { pageTitle: 'highwayacApp.usage.home.title' },
    loadChildren: () => import('./usage/usage.routes'),
  },
  {
    path: 'video-production',
    data: { pageTitle: 'highwayacApp.videoProduction.home.title' },
    loadChildren: () => import('./video-production/video-production.routes'),
  },
  {
    path: 'extra-info',
    data: { pageTitle: 'highwayacApp.extraInfo.home.title' },
    loadChildren: () => import('./extra-info/extra-info.routes'),
  },
  {
    path: 'material',
    data: { pageTitle: 'highwayacApp.material.home.title' },
    loadChildren: () => import('./material/material.routes'),
  },
  {
    path: 'history',
    data: { pageTitle: 'highwayacApp.history.home.title' },
    loadChildren: () => import('./history/history.routes'),
  },
  {
    path: 'system-setting',
    data: { pageTitle: 'highwayacApp.systemSetting.home.title' },
    loadChildren: () => import('./system-setting/system-setting.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
