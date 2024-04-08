import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreator, NewCreator } from '../creator.model';

export type PartialUpdateCreator = Partial<ICreator> & Pick<ICreator, 'id'>;

export type EntityResponseType = HttpResponse<ICreator>;
export type EntityArrayResponseType = HttpResponse<ICreator[]>;

@Injectable({ providedIn: 'root' })
export class CreatorService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/creators');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(creator: NewCreator): Observable<EntityResponseType> {
    return this.http.post<ICreator>(this.resourceUrl, creator, { observe: 'response' });
  }

  update(creator: ICreator): Observable<EntityResponseType> {
    return this.http.put<ICreator>(`${this.resourceUrl}/${this.getCreatorIdentifier(creator)}`, creator, { observe: 'response' });
  }

  partialUpdate(creator: PartialUpdateCreator): Observable<EntityResponseType> {
    return this.http.patch<ICreator>(`${this.resourceUrl}/${this.getCreatorIdentifier(creator)}`, creator, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreator>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreator[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreatorIdentifier(creator: Pick<ICreator, 'id'>): number {
    return creator.id;
  }

  compareCreator(o1: Pick<ICreator, 'id'> | null, o2: Pick<ICreator, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreatorIdentifier(o1) === this.getCreatorIdentifier(o2) : o1 === o2;
  }

  addCreatorToCollectionIfMissing<Type extends Pick<ICreator, 'id'>>(
    creatorCollection: Type[],
    ...creatorsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const creators: Type[] = creatorsToCheck.filter(isPresent);
    if (creators.length > 0) {
      const creatorCollectionIdentifiers = creatorCollection.map(creatorItem => this.getCreatorIdentifier(creatorItem)!);
      const creatorsToAdd = creators.filter(creatorItem => {
        const creatorIdentifier = this.getCreatorIdentifier(creatorItem);
        if (creatorCollectionIdentifiers.includes(creatorIdentifier)) {
          return false;
        }
        creatorCollectionIdentifiers.push(creatorIdentifier);
        return true;
      });
      return [...creatorsToAdd, ...creatorCollection];
    }
    return creatorCollection;
  }
}
