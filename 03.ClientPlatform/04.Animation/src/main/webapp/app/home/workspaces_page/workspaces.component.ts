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
import { LeftMenuComponent } from 'app/layouts/left-menu/left-menu.component';
import { IWorkspace, NewWorkspace } from 'app/entities/workspace/workspace.model';
import { EntityArrayResponseType, WorkspaceService } from 'app/entities/workspace/service/workspace.service';
import { WorkspaceDeleteDialogComponent } from 'app/entities/workspace/delete/workspace-delete-dialog.component';
import { AccountService } from 'app/core/auth/account.service';


@Component({
  selector: 'jhi-workspaces',
  standalone: true,
  imports: [SharedModule, RouterModule,LeftMenuComponent,
    FormsModule,
    SortDirective,
    SortByDirective,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
],
  templateUrl: './workspaces.html',
  styleUrl: './workspacesStyles.scss'
})
export class WprkspacesComponent {
  workspaces?: IWorkspace[];
  isLoading = false;

  predicate = 'id';
  ascending = true;

  constructor(
    protected workspaceService: WorkspaceService,
    protected activatedRoute: ActivatedRoute,
    public router: Router,
    protected sortService: SortService,
    protected modalService: NgbModal,
    private accountService: AccountService
  ) {}

  trackId = (_index: number, item: IWorkspace): number => this.workspaceService.getWorkspaceIdentifier(item);

  useid:string = "";

  ngOnInit(): void {

    this.accountService.getAuthenticationState().subscribe((item)=>{
      this.useid = item?.login??""
      this.load();
      // console.log(item?.login);
    });

  }

  delete(workspace: IWorkspace): void {
    const modalRef = this.modalService.open(WorkspaceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.workspace = workspace;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed
      .pipe(
        filter(reason => reason === ITEM_DELETED_EVENT),
        switchMap(() => this.loadFromBackendWithRouteInformations()),
      )
      .subscribe({
        next: (res: EntityArrayResponseType) => {
          this.onResponseSuccess(res);
        },
      });
  }

  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      next: (res: EntityArrayResponseType) => {
        if( res.body && res.body?.length > 0){
          this.onResponseSuccess(res);
          res.body[0].id;
          console.log(res.body[0])
          res.body.forEach(
            (item) => {
              if(item.betaFeatures){
                console.log("name: " + item.name)
              }
            }
          );
        }
        else{
          const workspace : NewWorkspace = {
            id: null,
            name: this.useid + "workspaces",
            identifier: this.useid,
            betaFeatures: true,
            collaborationCursor: false,
            defaultExportVisibility: false,
            publicAccess: false,
            members: [],
            videoProductions: [],
            materials: [],
            histories: [],
          };
          this.workspaceService.create(workspace).subscribe(
            ()=>{
              this.loadFromBackendWithRouteInformations().subscribe({
                next: (res: EntityArrayResponseType) => {
                    if( res.body && res.body?.length > 0){
                      this.onResponseSuccess(res);
                    }
                  }
                })
              });
        }
      },
    });
  }

  navigateToWithComponentValues(): void {
    this.handleNavigation(this.predicate, this.ascending);
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
    this.workspaces = this.refineData(dataFromBody);
  }

  protected refineData(data: IWorkspace[]): IWorkspace[] {
    console.log(data);
    //return data.sort(this.sortService.startSort(this.predicate, this.ascending ? 1 : -1));
    return data.sort(this.sortService.startSort(this.predicate, this.ascending ? 1 : -1));
  }

  protected fillComponentAttributesFromResponseBody(data: IWorkspace[] | null): IWorkspace[] {
    return data ?? [];
  }

  protected queryBackend(predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const queryObject: any = {
      sort: this.getSortQueryParam(predicate, ascending),
    };
    // return this.workspaceService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
    return this.workspaceService.findByIdentifier(queryObject, this.useid).pipe(tap(() => (this.isLoading = false)));
  }

  protected handleNavigation(predicate?: string, ascending?: boolean): void {
    const queryParamsObj = {
      sort: this.getSortQueryParam(predicate, ascending),
    };

    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute,
      queryParams: queryParamsObj,
    });
  }

  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }
}
