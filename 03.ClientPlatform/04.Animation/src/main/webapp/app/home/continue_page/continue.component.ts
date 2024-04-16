import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-continue',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './continue.html',
  styleUrl: './promptSelectionStyles.scss'
})
export class ContinueComponent {
  constructor(
    public router: Router
    ){
      
    }
  continue() {
    this.router.navigate(['/WaitingScreen']);
  }
  EditPrompt() {
    this.router.navigate(['/Createprompt']);
  }

}
