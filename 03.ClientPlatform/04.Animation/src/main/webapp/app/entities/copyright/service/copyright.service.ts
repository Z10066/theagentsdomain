import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICopyright, NewCopyright } from '../copyright.model';

export type PartialUpdateCopyright = Partial<ICopyright> & Pick<ICopyright, 'id'>;

export type EntityResponseType = HttpResponse<ICopyright>;
export type EntityArrayResponseType = HttpResponse<ICopyright[]>;

@Injectable({ providedIn: 'root' })
export class CopyrightService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/copyrights');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(copyright: NewCopyright): Observable<EntityResponseType> {
    return this.http.post<ICopyright>(this.resourceUrl, copyright, { observe: 'response' });
  }

  update(copyright: ICopyright): Observable<EntityResponseType> {
    return this.http.put<ICopyright>(`${this.resourceUrl}/${this.getCopyrightIdentifier(copyright)}`, copyright, { observe: 'response' });
  }

  partialUpdate(copyright: PartialUpdateCopyright): Observable<EntityResponseType> {
    return this.http.patch<ICopyright>(`${this.resourceUrl}/${this.getCopyrightIdentifier(copyright)}`, copyright, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICopyright>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICopyright[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCopyrightIdentifier(copyright: Pick<ICopyright, 'id'>): number {
    return copyright.id;
  }

  compareCopyright(o1: Pick<ICopyright, 'id'> | null, o2: Pick<ICopyright, 'id'> | null): boolean {
    return o1 && o2 ? this.getCopyrightIdentifier(o1) === this.getCopyrightIdentifier(o2) : o1 === o2;
  }

  addCopyrightToCollectionIfMissing<Type extends Pick<ICopyright, 'id'>>(
    copyrightCollection: Type[],
    ...copyrightsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const copyrights: Type[] = copyrightsToCheck.filter(isPresent);
    if (copyrights.length > 0) {
      const copyrightCollectionIdentifiers = copyrightCollection.map(copyrightItem => this.getCopyrightIdentifier(copyrightItem)!);
      const copyrightsToAdd = copyrights.filter(copyrightItem => {
        const copyrightIdentifier = this.getCopyrightIdentifier(copyrightItem);
        if (copyrightCollectionIdentifiers.includes(copyrightIdentifier)) {
          return false;
        }
        copyrightCollectionIdentifiers.push(copyrightIdentifier);
        return true;
      });
      return [...copyrightsToAdd, ...copyrightCollection];
    }
    return copyrightCollection;
  }
}
