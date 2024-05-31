import { Component, Input, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-foot-menu',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './foot-menu.component.html',
  styleUrl: './foot-menu.component.scss'
})
export class FootMenuComponent implements OnInit {
@Input()  currentpage:string = ""
  navItems = [
    { label: 'CreatexplainerPrompt', link: '/CreatexplainerPrompt', target: '_self', active: false },
    { label: 'Createvideoprompt', link: '/Createvideoprompt', target: '_self', active: false},
    { label: 'CreaterecentEventsPrompt', link: '/CreaterecentEventsPrompt', target: '_self', active: false },
    { label: 'CreatetiktokVideoPrompt', link: '/CreatetiktokVideoPrompt', target: '_self', active: false },
    { label: 'CreateinstagramReelPrompt', link: '/CreateinstagramReelPrompt', target: '_self' , active: false},
    { label: 'CreatVideo', link: '/CreatVideo', target: '_self', active: false },
    { label: 'Createprompt', link: '/Createprompt', target: '_self', active: false },
    { label: 'Novel', link: '/Novel', target: '_self', active: false },     
  ];

  ngOnInit() {
    this.init()
  }
  
  init() {
    this.navItems.forEach(navItem => {
      if ( navItem.label == this.currentpage)
        navItem.active = true;
      else navItem.active = false;
    })
  }
}
