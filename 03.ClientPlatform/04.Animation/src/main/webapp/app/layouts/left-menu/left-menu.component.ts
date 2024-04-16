import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-left-menu',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './left-menu.component.html',
  styleUrl: './left-menu.component.scss'
})
export class LeftMenuComponent {

  navItems = [
    { label: 'Workspaces', link: '/Workspaces', target: '_self' },
    { label: 'Createvideoprompt', link: '/Createvideoprompt', target: '_blank' },
    { label: 'Members', link: '/Members', target: '_self' },
    { label: 'Plan', link: '/Plan', target: '_self' },
    { label: 'Usage', link: '/Usage', target: '_self' },
    { label: 'VideoProduct', link: '/VideoProduct', target: '_self' },
    { label: 'Material', link: '/Material', target: '_self' },
    { label: 'History', link: '/History', target: '_self' },
    { label: 'Profile', link: '/Profile', target: '_self' },
  ];
}
