import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FootMenuComponent } from 'app/layouts/foot-menu/foot-menu.component';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-createinstagram-reel-prompt',
  standalone: true,
  imports: [SharedModule, RouterModule,FootMenuComponent],
  templateUrl: './createInstagramReelPrompt.html',
  styleUrl: './createInstagramReelPromptStyles.scss'
})
export class CreateinstagramReelPromptComponent {
  constructor(
    public router: Router
    ){
      
    }
  continue() {
    this.router.navigate(['/ThinkingScreen']);
  }

}
