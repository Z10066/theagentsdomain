import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IKeyword, NewKeyword } from '../keyword.model';

export type PartialUpdateKeyword = Partial<IKeyword> & Pick<IKeyword, 'id'>;

export type EntityResponseType = HttpResponse<IKeyword>;
export type EntityArrayResponseType = HttpResponse<IKeyword[]>;

@Injectable({ providedIn: 'root' })
export class KeywordService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/keywords');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(keyword: NewKeyword): Observable<EntityResponseType> {
    return this.http.post<IKeyword>(this.resourceUrl, keyword, { observe: 'response' });
  }

  update(keyword: IKeyword): Observable<EntityResponseType> {
    return this.http.put<IKeyword>(`${this.resourceUrl}/${this.getKeywordIdentifier(keyword)}`, keyword, { observe: 'response' });
  }

  partialUpdate(keyword: PartialUpdateKeyword): Observable<EntityResponseType> {
    return this.http.patch<IKeyword>(`${this.resourceUrl}/${this.getKeywordIdentifier(keyword)}`, keyword, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IKeyword>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IKeyword[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getKeywordIdentifier(keyword: Pick<IKeyword, 'id'>): number {
    return keyword.id;
  }

  compareKeyword(o1: Pick<IKeyword, 'id'> | null, o2: Pick<IKeyword, 'id'> | null): boolean {
    return o1 && o2 ? this.getKeywordIdentifier(o1) === this.getKeywordIdentifier(o2) : o1 === o2;
  }

  addKeywordToCollectionIfMissing<Type extends Pick<IKeyword, 'id'>>(
    keywordCollection: Type[],
    ...keywordsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const keywords: Type[] = keywordsToCheck.filter(isPresent);
    if (keywords.length > 0) {
      const keywordCollectionIdentifiers = keywordCollection.map(keywordItem => this.getKeywordIdentifier(keywordItem)!);
      const keywordsToAdd = keywords.filter(keywordItem => {
        const keywordIdentifier = this.getKeywordIdentifier(keywordItem);
        if (keywordCollectionIdentifiers.includes(keywordIdentifier)) {
          return false;
        }
        keywordCollectionIdentifiers.push(keywordIdentifier);
        return true;
      });
      return [...keywordsToAdd, ...keywordCollection];
    }
    return keywordCollection;
  }
}
