import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import SharedModule from 'app/shared/shared.module';


@Component({
  selector: 'jhi-workspaces',
  standalone: true,
  imports: [SharedModule, RouterModule],
  templateUrl: './workspaces.html',
  styleUrl: './workspacesStyles.scss'
})
export class WprkspacesComponent {

}
