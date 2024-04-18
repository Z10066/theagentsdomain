import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { combineLatest, filter, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortDirective, SortByDirective } from 'app/shared/sort';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { FormsModule } from '@angular/forms';
import { ASC, DESC, SORT, ITEM_DELETED_EVENT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import { SortService } from 'app/shared/sort/sort.service';
import { LoginService } from 'app/login/login.service';
import { EntityArrayResponseType, SystemSettingService } from 'app/entities/system-setting/service/system-setting.service';
import { ISystemSetting } from 'app/entities/system-setting/system-setting.model';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { IWorkspace } from 'app/entities/workspace/workspace.model';
import { WorkspaceService } from 'app/entities/workspace/service/workspace.service';

@Component({
  selector: 'jhi-welcome',
  standalone: true,
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.scss',
  imports: [
    RouterModule,
    FormsModule,
    SharedModule,
    SortDirective,
    SortByDirective,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
  ]

})
export class WelcomeComponent implements OnInit {
  welcomeMessage: string="";  
  videoUrl: SafeResourceUrl={};
  systemSettings?: ISystemSetting[];
  isLoading = false;
  predicate = 'id';
  ascending = true;
  workspaces: IWorkspace[] = [];

  constructor(
    private loginService: LoginService,
    private systemSettingService:SystemSettingService,
    protected activatedRoute: ActivatedRoute,
    public router: Router,
    protected sortService: SortService,
    protected modalService: NgbModal,
    private sanitizer: DomSanitizer,
    private workspaceService: WorkspaceService
  ) {}

  ngOnInit() {
    const defaultUrl="https://www.youtube.com/embed/7ZzLL3mK-rc";
    this.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(defaultUrl);
    this.load()
    this.loadWorkspaces();
  }

  loadWorkspaces(): void {
    this.workspaceService.query().subscribe((response) => {
      this.workspaces = response.body ?? [];
      // 在这里处理工作区数据
    });
  }

  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      next: (res: EntityArrayResponseType) => {
        this.onResponseSuccess(res);
      },
    });
  }
  protected loadFromBackendWithRouteInformations(): Observable<EntityArrayResponseType> {
    return combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data]).pipe(
      tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
      switchMap(() => this.queryBackend(this.predicate, this.ascending)),
    );
  }
  protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
    const sort = (params.get(SORT) ?? data[DEFAULT_SORT_DATA]).split(',');
    this.predicate = sort[0];
    this.ascending = sort[1] === ASC;
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.systemSettings = this.refineData(dataFromBody);
    const welcomeMessages = this.systemSettings.filter(setting=>setting.name=="welcomeMessage");
    if(welcomeMessages.length>0)
      this.welcomeMessage = welcomeMessages[0].value??"";
    const youtubeUrls = this.systemSettings.filter(setting=>setting.name=="youtubeUrl");
    if(youtubeUrls.length>0){
      const youtubeUrl = youtubeUrls[0].value??"";
      this.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(youtubeUrl);
    }
  }
  protected fillComponentAttributesFromResponseBody(data: ISystemSetting[] | null): ISystemSetting[] {
    return data ?? [];
  }
  protected refineData(data: ISystemSetting[]): ISystemSetting[] {
    return data.sort(this.sortService.startSort(this.predicate, this.ascending ? 1 : -1));
  }

  protected queryBackend(predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const queryObject: any = {
      sort: this.getSortQueryParam(predicate, ascending),
    };
    return this.systemSettingService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
  }
  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }

  signup() {
    this.router.navigate(['/signup']);
  }
  
  login() {
    this.router.navigate(['/login']);
  }

  loginWithGoogle(): void {
    this.loginService.loginWithGoogle();
  }

  loginWithApple(): void {
    this.loginService.loginWithApple();
  }

  loginWithGPTPlus(): void {
    this.loginService.loginWithOpenaiPlus();
  }

}
