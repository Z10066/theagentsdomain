import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { FootMenuComponent } from 'app/layouts/foot-menu/foot-menu.component';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-novel',
  standalone: true,
  imports: [SharedModule, RouterModule,FootMenuComponent,ReactiveFormsModule],
  templateUrl: './novel.component.html',
  styleUrl: './novel.component.scss'
})
export class NovelComponent {

}
