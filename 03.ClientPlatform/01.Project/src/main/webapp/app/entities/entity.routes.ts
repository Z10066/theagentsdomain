import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'file-configuration',
    data: { pageTitle: 'highwaypjApp.highwaypjFileConfiguration.home.title' },
    loadChildren: () => import('./highwaypj/file-configuration/file-configuration.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
