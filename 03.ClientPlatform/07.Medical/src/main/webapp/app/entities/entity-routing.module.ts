import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'file-configuration',
        data: { pageTitle: 'FileConfigurations' },
        loadChildren: () => import('./file-configuration/file-configuration.module').then(m => m.FileConfigurationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
