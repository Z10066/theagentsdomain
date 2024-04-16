import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-usage',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './renderingVideo.html',
  styleUrl: './renderingVideoStyles.scss'
})
export class RenderingVideoComponent {
  constructor(
    public router: Router
    ){
      
    }
  continue() {
    this.router.navigate(['/Download']);
  }

}
