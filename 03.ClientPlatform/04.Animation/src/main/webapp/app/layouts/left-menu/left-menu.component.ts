import { Component, Input, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-left-menu',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './left-menu.component.html',
  styleUrl: './left-menu.component.scss'
})
export class LeftMenuComponent implements OnInit {
@Input()  currentpage:string = ""
  navItems = [
    { label: 'Workspaces', link: '/Workspaces', target: '_self', active: false },
    { label: 'Createvideoprompt', link: '/Createvideoprompt', target: '_blank', active: false },
    { label: 'Members', link: '/Members', target: '_self', active: false },
    { label: 'Plan', link: '/Plan', target: '_self', active: false },
    { label: 'Usage', link: '/Usage', target: '_self', active: false },
    { label: 'VideoProduct', link: '/VideoProduct', target: '_self', active: false },
    { label: 'Material', link: '/Material', target: '_self', active: false },
    { label: 'History', link: '/History', target: '_self', active: false },
    { label: 'Profile', link: '/Profile', target: '_self', active: false },
  ];
  ngOnInit() {
    this.init();
  }

  init() {
    this.navItems.forEach(navItem => {
      if ( navItem.label == this.currentpage )
        navItem.active = true; 
      else navItem.active = false;
    });
  }
}
