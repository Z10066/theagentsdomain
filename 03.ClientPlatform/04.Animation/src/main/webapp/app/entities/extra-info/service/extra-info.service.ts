import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IExtraInfo, NewExtraInfo } from '../extra-info.model';

export type PartialUpdateExtraInfo = Partial<IExtraInfo> & Pick<IExtraInfo, 'id'>;

export type EntityResponseType = HttpResponse<IExtraInfo>;
export type EntityArrayResponseType = HttpResponse<IExtraInfo[]>;

@Injectable({ providedIn: 'root' })
export class ExtraInfoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/extra-infos');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(extraInfo: NewExtraInfo): Observable<EntityResponseType> {
    return this.http.post<IExtraInfo>(this.resourceUrl, extraInfo, { observe: 'response' });
  }

  update(extraInfo: IExtraInfo): Observable<EntityResponseType> {
    return this.http.put<IExtraInfo>(`${this.resourceUrl}/${this.getExtraInfoIdentifier(extraInfo)}`, extraInfo, { observe: 'response' });
  }

  partialUpdate(extraInfo: PartialUpdateExtraInfo): Observable<EntityResponseType> {
    return this.http.patch<IExtraInfo>(`${this.resourceUrl}/${this.getExtraInfoIdentifier(extraInfo)}`, extraInfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExtraInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExtraInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getExtraInfoIdentifier(extraInfo: Pick<IExtraInfo, 'id'>): number {
    return extraInfo.id;
  }

  compareExtraInfo(o1: Pick<IExtraInfo, 'id'> | null, o2: Pick<IExtraInfo, 'id'> | null): boolean {
    return o1 && o2 ? this.getExtraInfoIdentifier(o1) === this.getExtraInfoIdentifier(o2) : o1 === o2;
  }

  addExtraInfoToCollectionIfMissing<Type extends Pick<IExtraInfo, 'id'>>(
    extraInfoCollection: Type[],
    ...extraInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const extraInfos: Type[] = extraInfosToCheck.filter(isPresent);
    if (extraInfos.length > 0) {
      const extraInfoCollectionIdentifiers = extraInfoCollection.map(extraInfoItem => this.getExtraInfoIdentifier(extraInfoItem)!);
      const extraInfosToAdd = extraInfos.filter(extraInfoItem => {
        const extraInfoIdentifier = this.getExtraInfoIdentifier(extraInfoItem);
        if (extraInfoCollectionIdentifiers.includes(extraInfoIdentifier)) {
          return false;
        }
        extraInfoCollectionIdentifiers.push(extraInfoIdentifier);
        return true;
      });
      return [...extraInfosToAdd, ...extraInfoCollection];
    }
    return extraInfoCollection;
  }
}
