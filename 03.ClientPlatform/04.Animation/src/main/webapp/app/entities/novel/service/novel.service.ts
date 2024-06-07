import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INovel, NewNovel } from '../novel.model';

export type PartialUpdateNovel = Partial<INovel> & Pick<INovel, 'id'>;

export type EntityResponseType = HttpResponse<INovel>;
export type EntityArrayResponseType = HttpResponse<INovel[]>;

@Injectable({ providedIn: 'root' })
export class NovelService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/novels');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(novel: NewNovel): Observable<EntityResponseType> {
    return this.http.post<INovel>(this.resourceUrl, novel, { observe: 'response' });
  }

  update(novel: INovel): Observable<EntityResponseType> {
    return this.http.put<INovel>(`${this.resourceUrl}/${this.getNovelIdentifier(novel)}`, novel, { observe: 'response' });
  }

  partialUpdate(novel: PartialUpdateNovel): Observable<EntityResponseType> {
    return this.http.patch<INovel>(`${this.resourceUrl}/${this.getNovelIdentifier(novel)}`, novel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INovel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INovel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getNovelIdentifier(novel: Pick<INovel, 'id'>): number {
    return novel.id;
  }

  compareNovel(o1: Pick<INovel, 'id'> | null, o2: Pick<INovel, 'id'> | null): boolean {
    return o1 && o2 ? this.getNovelIdentifier(o1) === this.getNovelIdentifier(o2) : o1 === o2;
  }

  addNovelToCollectionIfMissing<Type extends Pick<INovel, 'id'>>(
    novelCollection: Type[],
    ...novelsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const novels: Type[] = novelsToCheck.filter(isPresent);
    if (novels.length > 0) {
      const novelCollectionIdentifiers = novelCollection.map(novelItem => this.getNovelIdentifier(novelItem)!);
      const novelsToAdd = novels.filter(novelItem => {
        const novelIdentifier = this.getNovelIdentifier(novelItem);
        if (novelCollectionIdentifiers.includes(novelIdentifier)) {
          return false;
        }
        novelCollectionIdentifiers.push(novelIdentifier);
        return true;
      });
      return [...novelsToAdd, ...novelCollection];
    }
    return novelCollection;
  }
}
