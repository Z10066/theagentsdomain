import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-usage',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './renderingVideo.html',
  styleUrl: './renderingVideoStyles.scss'
})
export class RenderingVideoComponent {

}
