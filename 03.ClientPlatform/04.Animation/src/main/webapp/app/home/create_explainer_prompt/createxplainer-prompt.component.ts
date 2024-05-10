import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EventManager } from 'app/core/util/event-manager.service';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { EventWithContent } from 'app/core/util/event-manager.service';
import { YouTubeVideoService } from 'app/entities/you-tube-video/service/you-tube-video.service';
import { YouTubeVideoFormGroup, YouTubeVideoFormService } from 'app/entities/you-tube-video/update/you-tube-video-form.service';
import { IYouTubeVideo } from 'app/entities/you-tube-video/you-tube-video.model';
import { FootMenuComponent } from 'app/layouts/foot-menu/foot-menu.component';
import SharedModule from 'app/shared/shared.module';
import { Observable, finalize } from 'rxjs';
import { IWorkspace } from 'app/entities/workspace/workspace.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SortService } from 'app/shared/sort/sort.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EntityArrayResponseType, WorkspaceService } from 'app/entities/workspace/service/workspace.service';
import { AccountService } from 'app/core/auth/account.service';
import { ASC, DEFAULT_SORT_DATA, DESC, SORT } from 'app/config/navigation.constants';
import { combineLatest, switchMap, tap } from 'rxjs';

@Component({
  selector: 'jhi-createxplainer-prompt',
  standalone: true,
  imports: [SharedModule, RouterModule,FootMenuComponent,FormsModule, ReactiveFormsModule],
  templateUrl: './createExplainerPrompt.html',
  styleUrl: './createExplainerPromptStyles.scss'
})
export class CreatexplainerPromptComponent implements OnInit {
  isSaving = false;
  youTubeVideo: IYouTubeVideo | null = null;


  editForm: YouTubeVideoFormGroup = this.youTubeVideoFormService.createYouTubeVideoFormGroup();

  workspaces?: IWorkspace[];
  predicate = 'id';
  ascending = true;
  isLoading = false;
  useid:string = "";
  namesList: string[] = ['15 seconds', '30 seconds', '1 minute','2 minutes','3 minutes'];
  gendername: string[] = ['Use any', 'a male', 'a female'];
  


  constructor(
    public router: Router,
    protected youTubeVideoService: YouTubeVideoService,
    protected activatedRoute: ActivatedRoute,
    protected sortService: SortService,
    protected dataUtils: DataUtils,
    protected modalService: NgbModal,
    protected eventManager: EventManager,
    protected youTubeVideoFormService: YouTubeVideoFormService,
    private workspaceService: WorkspaceService,
    private accountService: AccountService,

    ){
      
    }
  continue() {
    this.router.navigate(['/ThinkingScreen']);
  }
  ngOnInit(): void {

    this.activatedRoute.data.subscribe(({ youTubeVideo }) => {
      this.youTubeVideo = youTubeVideo;
      if (youTubeVideo) {
        this.updateForm(youTubeVideo);
      }
    });

    this.accountService.getAuthenticationState().subscribe((item)=>{
      if(item){
        this.useid = item.login
        this.load();
      }
      
    });

  }
  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      next: (res: EntityArrayResponseType) => {
        if (res.body && res.body.length > 0){
            res.body.forEach(
              (item) => {
                if(item.betaFeatures){
                  this.editForm.get('workspace')?.setValue(item.id.toString())
                  this.editForm.get('creator')?.setValue(this.useid)
                }
              }
            );
        } else {
          this.router.navigate(['/Workspaces']);
        }
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

  protected queryBackend(predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const queryObject: any = {
      sort: this.getSortQueryParam(predicate, ascending),
    };
    // return this.workspaceService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
    return this.workspaceService.findByIdentifier(queryObject, this.useid).pipe(tap(() => (this.isLoading = false)));
  }

  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }


  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('highwayacApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    console.log("save")
    this.isSaving = true;
    const youTubeVideo = this.youTubeVideoFormService.getYouTubeVideo(this.editForm);
    if (youTubeVideo.id !== null) {
      this.subscribeToSaveResponse(this.youTubeVideoService.update(youTubeVideo));
    } else {
      this.subscribeToSaveResponse(this.youTubeVideoService.create(youTubeVideo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IYouTubeVideo>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(youTubeVideo: IYouTubeVideo): void {
    this.youTubeVideo = youTubeVideo;
    this.youTubeVideoFormService.resetForm(this.editForm, youTubeVideo);
  }
}
