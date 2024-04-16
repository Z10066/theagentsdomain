import { Component, Input, OnInit } from '@angular/core';
import { LoginService } from 'app/login/login.service';
import { FormsModule } from '@angular/forms'; // 引入 FormsModule

@Component({
  selector: 'jhi-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent implements OnInit{
  @Input() email: string="";
  @Input() password: string="";

  constructor(
    private loginService: LoginService,
  ) {}

  ngOnInit() {
  }

  signup(): void {
    this.loginService.signup(this.email, this.password);
  }
}
