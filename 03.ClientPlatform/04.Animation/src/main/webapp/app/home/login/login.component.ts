import { Component } from '@angular/core';
import { LoginService } from 'app/login/login.service';
import { FormsModule } from '@angular/forms'; // 引入 FormsModule

@Component({
  selector: 'jhi-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email: string="";
  password: string="";

  constructor(
    private loginService: LoginService,
  ) {}

  ngOnInit() {
  }

  login(): void {
    this.loginService.loginWithUserInfo(this.email, this.password);
  }

}
