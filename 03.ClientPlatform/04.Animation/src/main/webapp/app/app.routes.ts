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
import EntityComponent from './entities/entity.component';
import { VideoComponent } from './entities/video/list/video.component';
import { CreatevideopromptComponent } from './home/create_video_prompt/createvideoprompt.component';
import { VideoCircleComponent } from './home/video-circle/video-circle.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'home.title',
  },
  {
    path: 'Workspaces',
    component: WprkspacesComponent,
  },
  {
    path: 'Welcome',
    component: WelcomeComponent,
  },
  {
    path: 'WaitingScreen',
    component: WaitingScreenComponent,
  },
  {
    path: 'Usage',
    component: UsageComponent,
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
  },
  {
    path: 'Plan',
    component: PlanComponent,
  },
  {
    path: 'Members',
    component: MembersComponent,
  },
  {
    path: 'History',
    component: HistoryComponent,
  },
  {
    path: 'Download',
    component: DownloadComponent,
  },
  {
    path: 'VideoCircle',
    component: VideoCircleComponent,
  },
  {
    path: 'CreatVideo',
    component: CreatVideoComponent,
  },
  
  {
    path: 'Createvideoprompt',
    component: CreatevideopromptComponent,
  },
  {
    path: 'Continue',
    component: ContinueComponent,
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
