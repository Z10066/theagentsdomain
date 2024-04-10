import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { LoginService } from 'app/login/login.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

@Component({
  standalone: true,
  selector: 'jhi-home',
  templateUrl: './entity.component.html',
  styleUrl: './entity.component.scss',
  imports: [SharedModule, RouterModule,],
})
export default class EntityComponent implements OnInit {
  account: Account | null = null;

  constructor(
    private accountService: AccountService,
    private loginService: LoginService,
  ) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => (this.account = account));
  }

  login(): void {
    this.loginService.login();
  }

  navItems2 = [
    { label: 'Go to Video Page', link: '/video', target: '_blank' },



    
  ];
  getNavItemWidth(): string {
    return `calc(100% / ${this.navItems2.length})`; // 动态计算每个项目的宽度
  }
}



