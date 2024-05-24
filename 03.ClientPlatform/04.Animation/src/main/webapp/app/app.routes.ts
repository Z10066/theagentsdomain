import { Routes } from '@angular/router';

import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { errorRoute } from './layouts/error/error.route';

import HomeComponent from './home/home.component';
import NavbarComponent from './layouts/navbar/navbar.component';
import { loadEntityRoutes } from './core/microfrontend';
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
import { ASC } from './config/navigation.constants';
import { SignupComponent } from './home/signup/signup.component';
import { LoginComponent } from './home/login/login.component';
import { VideoProductionsComponent } from './home/video_production_page/video_production.component';
import { MaterialsComponent } from './home/material_page/material.component';
import YouTubeVideoResolve from './entities/you-tube-video/route/you-tube-video-routing-resolve.service';


import VideoHintResolve from './entities/video-hint/route/video-hint-routing-resolve.service';
import { InviteusersComponent } from './home/inviteusers/inviteusers.component';
import { InvitationLinkComponent } from './home/invitation-link/invitation-link.component';

const routes: Routes = [
  /*{
    path: '',
    component: HomeComponent,
    title: 'home.title',
  },*/
  {
    path: '',
    component: WelcomeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Workspaces',
    component: WprkspacesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Welcome',
    component: WelcomeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'WaitingScreen',
    component: WaitingScreenComponent,
  },
  {
    path: 'Usage',
    component: UsageComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'ThinkingScreen',
    component: ThinkingScreenComponent,
  },
  {
    path: 'RenderingVideo',
    component: RenderingVideoComponent,
  },
  {
    path: 'PublishVideo',
    component: PublishVideoComponent,
  },
  {
    path: 'Profile',
    component: ProfileComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Plan',
    component: PlanComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Members',
    component: MembersComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'History',
    component: HistoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'VideoProduct',
    component: VideoProductionsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Material',
    component: MaterialsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  
  {
    path: 'Download',
    component: DownloadComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'VideoCircle',
    component: VideoCircleComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Inviteusers',
    component: InviteusersComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'InvitationLink',
    component: InvitationLinkComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'CreatVideo',
    component: CreatVideoComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  
  {
    path: 'Createvideoprompt',
    component: CreatevideopromptComponent,
    resolve: {
      videoHint: VideoHintResolve,
    },
    canActivate: [UserRouteAccessService],
    data: {
      defaultSort: 'id,' + ASC,
    },

  },
  {
    path: 'CreateinstagramReelPrompt',
    component: CreateinstagramReelPromptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Createprompt',
    component: CreatepromptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'CreaterecentEventsPrompt',
    component: CreaterecentEventsPromptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'CreatetiktokVideoPrompt',
    component: CreatetiktokVideoPromptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'CreatexplainerPrompt',
    component: CreatexplainerPromptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    resolve: {
      youTubeVideo: YouTubeVideoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'Continue',
    component: ContinueComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: '',
    component: NavbarComponent,
    outlet: 'navbar',
  },
  {
    path: 'admin',
    data: {
      authorities: [Authority.ADMIN],
    },
    canActivate: [UserRouteAccessService],
    loadChildren: () => import('./admin/admin.routes'),
  },
  {
    path: '',
    loadChildren: () => import(`./entities/entity.routes`),
  },
  ...errorRoute,
];

export default routes;
