import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUsage, NewUsage } from '../usage.model';

export type PartialUpdateUsage = Partial<IUsage> & Pick<IUsage, 'id'>;

type RestOf<T extends IUsage | NewUsage> = Omit<T, 'startTime' | 'endTime'> & {
  startTime?: string | null;
  endTime?: string | null;
};

export type RestUsage = RestOf<IUsage>;

export type NewRestUsage = RestOf<NewUsage>;

export type PartialUpdateRestUsage = RestOf<PartialUpdateUsage>;

export type EntityResponseType = HttpResponse<IUsage>;
export type EntityArrayResponseType = HttpResponse<IUsage[]>;

@Injectable({ providedIn: 'root' })
export class UsageService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/usages');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(usage: NewUsage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usage);
    return this.http.post<RestUsage>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(usage: IUsage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usage);
    return this.http
      .put<RestUsage>(`${this.resourceUrl}/${this.getUsageIdentifier(usage)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(usage: PartialUpdateUsage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usage);
    return this.http
      .patch<RestUsage>(`${this.resourceUrl}/${this.getUsageIdentifier(usage)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestUsage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestUsage[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getUsageIdentifier(usage: Pick<IUsage, 'id'>): number {
    return usage.id;
  }

  compareUsage(o1: Pick<IUsage, 'id'> | null, o2: Pick<IUsage, 'id'> | null): boolean {
    return o1 && o2 ? this.getUsageIdentifier(o1) === this.getUsageIdentifier(o2) : o1 === o2;
  }

  addUsageToCollectionIfMissing<Type extends Pick<IUsage, 'id'>>(
    usageCollection: Type[],
    ...usagesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const usages: Type[] = usagesToCheck.filter(isPresent);
    if (usages.length > 0) {
      const usageCollectionIdentifiers = usageCollection.map(usageItem => this.getUsageIdentifier(usageItem)!);
      const usagesToAdd = usages.filter(usageItem => {
        const usageIdentifier = this.getUsageIdentifier(usageItem);
        if (usageCollectionIdentifiers.includes(usageIdentifier)) {
          return false;
        }
        usageCollectionIdentifiers.push(usageIdentifier);
        return true;
      });
      return [...usagesToAdd, ...usageCollection];
    }
    return usageCollection;
  }

  protected convertDateFromClient<T extends IUsage | NewUsage | PartialUpdateUsage>(usage: T): RestOf<T> {
    return {
      ...usage,
      startTime: usage.startTime?.toJSON() ?? null,
      endTime: usage.endTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restUsage: RestUsage): IUsage {
    return {
      ...restUsage,
      startTime: restUsage.startTime ? dayjs(restUsage.startTime) : undefined,
      endTime: restUsage.endTime ? dayjs(restUsage.endTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestUsage>): HttpResponse<IUsage> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestUsage[]>): HttpResponse<IUsage[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
