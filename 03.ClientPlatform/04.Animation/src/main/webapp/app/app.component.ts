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
import { CreatVideoComponent } from './home/createVideo/creat-video.component';
import { ContinueComponent } from './home/continue_page/continue.component';
import { CreatevideopromptComponent } from './home/create_video_prompt/createvideoprompt.component';
import { VideoCircleComponent } from './home/video-circle/video-circle.component';
import { CreatexplainerPromptComponent } from './home/create_explainer_prompt/createxplainer-prompt.component';
import { CreateinstagramReelPromptComponent } from './home/create_instagram_reel_prompt/createinstagram-reel-prompt.component';
import { CreatepromptComponent } from './home/create_prompt/createprompt.component';
import { CreaterecentEventsPromptComponent } from './home/create_recent_events_prompt/createrecent-events-prompt.component';
import { CreatetiktokVideoPromptComponent } from './home/create_tiktok_video_prompt/createtiktok-video-prompt.component';
import { SignupComponent } from './home/signup/signup.component';
import { LoginComponent } from './home/login/login.component';
import { LeftMenuComponent } from './layouts/left-menu/left-menu.component';
import { VideoProductionsComponent } from './home/video_production_page/video_production.component';
import { MaterialsComponent } from './home/material_page/material.component';
import { FootMenuComponent } from './layouts/foot-menu/foot-menu.component';
import { InviteusersComponent } from './home/inviteusers/inviteusers.component';
import { InvitationLinkComponent } from './home/invitation-link/invitation-link.component';
import { NovelComponent } from './home/novel/novel.component';
import { UploadfileComponent } from './home/uploadfile/uploadfile.component';



@Component({
  selector: 'jhi-app',
  standalone: true,
  template: '<jhi-main></jhi-main>',
  imports: [
    MainComponent,WelcomeComponent,WaitingScreenComponent,UsageComponent,ThinkingScreenComponent,RenderingVideoComponent,PublishVideoComponent,
    ProfileComponent,PlanComponent,MembersComponent,HistoryComponent,DownloadComponent,WprkspacesComponent,CreatVideoComponent,ContinueComponent,
    CreatevideopromptComponent,VideoCircleComponent,CreatexplainerPromptComponent,CreateinstagramReelPromptComponent,CreatepromptComponent,
    CreaterecentEventsPromptComponent,CreatetiktokVideoPromptComponent,SignupComponent,LoginComponent,LeftMenuComponent,VideoProductionsComponent,
    MaterialsComponent,FootMenuComponent,InviteusersComponent,InvitationLinkComponent,NovelComponent,UploadfileComponent
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
