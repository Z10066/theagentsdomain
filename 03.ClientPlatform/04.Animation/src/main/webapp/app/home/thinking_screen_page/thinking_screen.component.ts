import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-usage',
  standalone: true,
  imports: [RouterModule,SharedModule],
  templateUrl: './ThinkingScreen.html',
  styleUrl: './statusScreenStyles.scss'
})
export class ThinkingScreenComponent {

}
