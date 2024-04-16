import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-foot-menu',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './foot-menu.component.html',
  styleUrl: './foot-menu.component.scss'
})
export class FootMenuComponent {

  navItems = [
    { label: 'CreatexplainerPrompt', link: '/CreatexplainerPrompt', target: '_self' },
    { label: 'Createvideoprompt', link: '/Createvideoprompt', target: '_self' },
    { label: 'CreaterecentEventsPrompt', link: '/CreaterecentEventsPrompt', target: '_self' },
    { label: 'CreatetiktokVideoPrompt', link: '/CreatetiktokVideoPrompt', target: '_self' },
    { label: 'CreateinstagramReelPrompt', link: '/CreateinstagramReelPrompt', target: '_self' },
    { label: 'CreatVideo', link: '/CreatVideo', target: '_self' },
    { label: 'Createprompt', link: '/Createprompt', target: '_self' },
    
  ];

}
