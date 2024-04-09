import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { LoginService } from 'app/login/login.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

@Component({
  standalone: true,
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  imports: [SharedModule, RouterModule],
})
export default class HomeComponent implements OnInit {
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

  navItems = [
    { label: 'Workspaces', link: '/Workspaces', target: '_self' },
    { label: 'Welcome', link: '/Welcome', target: '_blank' },
    { label: 'WaitingScreen', link: '/WaitingScreen', target: '_blank' },
    { label: 'Usage', link: '/Usage', target: '_self' },
    { label: 'ThinkingScreen', link: '/ThinkingScreen', target: '_self' },
    { label: 'RenderingVideo', link: '/RenderingVideo', target: '_self' },
    { label: 'PublishVideo', link: '/PublishVideo', target: '_self' },
    { label: 'Profile', link: '/Profile', target: '_self' },
    { label: 'Plan', link: '/Plan', target: '_self' },
    { label: 'Members', link: '/Members', target: '_self' },
    { label: 'History', link: '/History', target: '_self' },
    { label: 'Download', link: '/Download', target: '_self' },
  ];

  navItems2 = [
    { label: 'CreatVideo', link: '/CreatVideo', target: '_self' },
    { label: 'Continue', link: '/Continue', target: '_blank' },
  ];
  getNavItemWidth(): string {
    return `calc(100% / ${this.navItems.length})`; // 动态计算每个项目的宽度
  }
}



