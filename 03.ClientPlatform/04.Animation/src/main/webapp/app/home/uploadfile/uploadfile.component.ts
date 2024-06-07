import { Component } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { combineLatest, filter, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortDirective, SortByDirective } from 'app/shared/sort';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ItemCountComponent } from 'app/shared/pagination';
import { FormsModule } from '@angular/forms';

import { ITEMS_PER_PAGE, PAGE_HEADER, TOTAL_COUNT_RESPONSE_HEADER } from 'app/config/pagination.constants';
import { ASC, DESC, SORT, ITEM_DELETED_EVENT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import { IFileConfiguration } from 'app/entities/file-configuration/file-configuration.model';
import { EntityArrayResponseType, FileConfigurationService } from 'app/entities/file-configuration/service/file-configuration.service';
import { FileConfigurationDeleteDialogComponent } from 'app/entities/file-configuration/delete/file-configuration-delete-dialog.component';
import { LeftMenuComponent } from 'app/layouts/left-menu/left-menu.component';


@Component({
  selector: 'jhi-uploadfile',
  standalone: true,
  imports: [
    LeftMenuComponent,
    RouterModule,
    FormsModule,
    SharedModule,
    SortDirective,
    SortByDirective,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
    ItemCountComponent,
  ],
  templateUrl: './uploadfile.component.html',
  styleUrl: './uploadfile.component.scss'
})
export class UploadfileComponent {
    fileConfigurations?: IFileConfiguration[];
    isLoading = false;
  
    predicate = 'id';
    ascending = true;
  
    itemsPerPage = ITEMS_PER_PAGE;
    totalItems = 0;
    page = 1;
  
    constructor(
      protected fileConfigurationService: FileConfigurationService,
      protected activatedRoute: ActivatedRoute,
      public router: Router,
      protected modalService: NgbModal,
    ) {}
  
    trackId = (_index: number, item: IFileConfiguration): number => this.fileConfigurationService.getFileConfigurationIdentifier(item);
  
    ngOnInit(): void {
      this.load();
    }
  
    delete(fileConfiguration: IFileConfiguration): void {
      const modalRef = this.modalService.open(FileConfigurationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
      modalRef.componentInstance.fileConfiguration = fileConfiguration;
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
          this.onResponseSuccess(res);
        },
      });
    }
  
    navigateToWithComponentValues(): void {
      this.handleNavigation(this.page, this.predicate, this.ascending);
    }
  
    navigateToPage(page = this.page): void {
      this.handleNavigation(page, this.predicate, this.ascending);
    }
  
    protected loadFromBackendWithRouteInformations(): Observable<EntityArrayResponseType> {
      return combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data]).pipe(
        tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
        switchMap(() => this.queryBackend(this.page, this.predicate, this.ascending)),
      );
    }
  
    protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
      const page = params.get(PAGE_HEADER);
      this.page = +(page ?? 1);
      const sort = (params.get(SORT) ?? data[DEFAULT_SORT_DATA]).split(',');
      this.predicate = sort[0];
      this.ascending = sort[1] === ASC;
    }
  
    protected onResponseSuccess(response: EntityArrayResponseType): void {
      this.fillComponentAttributesFromResponseHeader(response.headers);
      const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
      this.fileConfigurations = dataFromBody;
    }
  
    protected fillComponentAttributesFromResponseBody(data: IFileConfiguration[] | null): IFileConfiguration[] {
      return data ?? [];
    }
  
    protected fillComponentAttributesFromResponseHeader(headers: HttpHeaders): void {
      this.totalItems = Number(headers.get(TOTAL_COUNT_RESPONSE_HEADER));
    }
  
    protected queryBackend(page?: number, predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
      this.isLoading = true;
      const pageToLoad: number = page ?? 1;
      const queryObject: any = {
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.getSortQueryParam(predicate, ascending),
      };
      return this.fileConfigurationService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
  
    protected handleNavigation(page = this.page, predicate?: string, ascending?: boolean): void {
      const queryParamsObj = {
        page,
        size: this.itemsPerPage,
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
