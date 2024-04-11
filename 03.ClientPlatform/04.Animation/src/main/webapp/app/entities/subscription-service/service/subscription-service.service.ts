import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISubscriptionService, NewSubscriptionService } from '../subscription-service.model';

export type PartialUpdateSubscriptionService = Partial<ISubscriptionService> & Pick<ISubscriptionService, 'id'>;

type RestOf<T extends ISubscriptionService | NewSubscriptionService> = Omit<T, 'startTime' | 'endTime'> & {
  startTime?: string | null;
  endTime?: string | null;
};

export type RestSubscriptionService = RestOf<ISubscriptionService>;

export type NewRestSubscriptionService = RestOf<NewSubscriptionService>;

export type PartialUpdateRestSubscriptionService = RestOf<PartialUpdateSubscriptionService>;

export type EntityResponseType = HttpResponse<ISubscriptionService>;
export type EntityArrayResponseType = HttpResponse<ISubscriptionService[]>;

@Injectable({ providedIn: 'root' })
export class SubscriptionServiceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/subscription-services');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(subscriptionService: NewSubscriptionService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionService);
    return this.http
      .post<RestSubscriptionService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(subscriptionService: ISubscriptionService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionService);
    return this.http
      .put<RestSubscriptionService>(`${this.resourceUrl}/${this.getSubscriptionServiceIdentifier(subscriptionService)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(subscriptionService: PartialUpdateSubscriptionService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionService);
    return this.http
      .patch<RestSubscriptionService>(`${this.resourceUrl}/${this.getSubscriptionServiceIdentifier(subscriptionService)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSubscriptionService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSubscriptionService[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSubscriptionServiceIdentifier(subscriptionService: Pick<ISubscriptionService, 'id'>): number {
    return subscriptionService.id;
  }

  compareSubscriptionService(o1: Pick<ISubscriptionService, 'id'> | null, o2: Pick<ISubscriptionService, 'id'> | null): boolean {
    return o1 && o2 ? this.getSubscriptionServiceIdentifier(o1) === this.getSubscriptionServiceIdentifier(o2) : o1 === o2;
  }

  addSubscriptionServiceToCollectionIfMissing<Type extends Pick<ISubscriptionService, 'id'>>(
    subscriptionServiceCollection: Type[],
    ...subscriptionServicesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const subscriptionServices: Type[] = subscriptionServicesToCheck.filter(isPresent);
    if (subscriptionServices.length > 0) {
      const subscriptionServiceCollectionIdentifiers = subscriptionServiceCollection.map(
        subscriptionServiceItem => this.getSubscriptionServiceIdentifier(subscriptionServiceItem)!,
      );
      const subscriptionServicesToAdd = subscriptionServices.filter(subscriptionServiceItem => {
        const subscriptionServiceIdentifier = this.getSubscriptionServiceIdentifier(subscriptionServiceItem);
        if (subscriptionServiceCollectionIdentifiers.includes(subscriptionServiceIdentifier)) {
          return false;
        }
        subscriptionServiceCollectionIdentifiers.push(subscriptionServiceIdentifier);
        return true;
      });
      return [...subscriptionServicesToAdd, ...subscriptionServiceCollection];
    }
    return subscriptionServiceCollection;
  }

  protected convertDateFromClient<T extends ISubscriptionService | NewSubscriptionService | PartialUpdateSubscriptionService>(
    subscriptionService: T,
  ): RestOf<T> {
    return {
      ...subscriptionService,
      startTime: subscriptionService.startTime?.toJSON() ?? null,
      endTime: subscriptionService.endTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restSubscriptionService: RestSubscriptionService): ISubscriptionService {
    return {
      ...restSubscriptionService,
      startTime: restSubscriptionService.startTime ? dayjs(restSubscriptionService.startTime) : undefined,
      endTime: restSubscriptionService.endTime ? dayjs(restSubscriptionService.endTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSubscriptionService>): HttpResponse<ISubscriptionService> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSubscriptionService[]>): HttpResponse<ISubscriptionService[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
