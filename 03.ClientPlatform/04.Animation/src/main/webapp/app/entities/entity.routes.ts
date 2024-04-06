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
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
