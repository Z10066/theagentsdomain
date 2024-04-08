import { Component } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import locale from '@angular/common/locales/en';
import dayjs from 'dayjs/esm';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
// jhipster-needle-angular-add-module-import JHipster will add new module here

import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { fontAwesomeIcons } from './config/font-awesome-icons';
import MainComponent from './layouts/main/main.component';
import { WelcomeComponent } from './home/welcome/welcome.component';
import { WaitingScreenComponent } from './home/waiting_screen_page/waiting-screen.component';
import { UsageComponent } from './home/usage_page/usage.component';
import { ThinkingScreenComponent } from './home/thinking_screen_page/thinking_screen.component';
import { RenderingVideoComponent } from './home/rendering_video_page/rendering_video.component';
import { PublishVideoComponent } from './home/publish_video_page/publish_video.component';
import { ProfileComponent } from './home/profile_page/profile.component';
import { PlanComponent } from './home/plan_page/plan.component';
import { MembersComponent } from './home/members_page/members.component';
import { HistoryComponent } from './home/history_log/history.component';
import { DownloadComponent } from './home/download_page/download.component';
import { WprkspacesComponent } from './home/workspaces_page/workspaces.component';


@Component({
  selector: 'jhi-app',
  standalone: true,
  template: '<jhi-main></jhi-main>',
  imports: [
    MainComponent,WelcomeComponent,WaitingScreenComponent,UsageComponent,ThinkingScreenComponent,RenderingVideoComponent,PublishVideoComponent,
    ProfileComponent,PlanComponent,MembersComponent,HistoryComponent,DownloadComponent,WprkspacesComponent
    // jhipster-needle-angular-add-module JHipster will add new module here
  ],
})
export default class AppComponent {
  constructor(applicationConfigService: ApplicationConfigService, iconLibrary: FaIconLibrary, dpConfig: NgbDatepickerConfig) {
    applicationConfigService.setEndpointPrefix(SERVER_API_URL);
    registerLocaleData(locale);
    iconLibrary.addIcons(...fontAwesomeIcons);
    dpConfig.minDate = { year: dayjs().subtract(100, 'year').year(), month: 1, day: 1 };
  }
}
