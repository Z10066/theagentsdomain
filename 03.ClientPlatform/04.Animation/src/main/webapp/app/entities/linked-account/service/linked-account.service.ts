import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILinkedAccount, NewLinkedAccount } from '../linked-account.model';

export type PartialUpdateLinkedAccount = Partial<ILinkedAccount> & Pick<ILinkedAccount, 'id'>;

export type EntityResponseType = HttpResponse<ILinkedAccount>;
export type EntityArrayResponseType = HttpResponse<ILinkedAccount[]>;

@Injectable({ providedIn: 'root' })
export class LinkedAccountService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/linked-accounts');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(linkedAccount: NewLinkedAccount): Observable<EntityResponseType> {
    return this.http.post<ILinkedAccount>(this.resourceUrl, linkedAccount, { observe: 'response' });
  }

  update(linkedAccount: ILinkedAccount): Observable<EntityResponseType> {
    return this.http.put<ILinkedAccount>(`${this.resourceUrl}/${this.getLinkedAccountIdentifier(linkedAccount)}`, linkedAccount, {
      observe: 'response',
    });
  }

  partialUpdate(linkedAccount: PartialUpdateLinkedAccount): Observable<EntityResponseType> {
    return this.http.patch<ILinkedAccount>(`${this.resourceUrl}/${this.getLinkedAccountIdentifier(linkedAccount)}`, linkedAccount, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILinkedAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILinkedAccount[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLinkedAccountIdentifier(linkedAccount: Pick<ILinkedAccount, 'id'>): number {
    return linkedAccount.id;
  }

  compareLinkedAccount(o1: Pick<ILinkedAccount, 'id'> | null, o2: Pick<ILinkedAccount, 'id'> | null): boolean {
    return o1 && o2 ? this.getLinkedAccountIdentifier(o1) === this.getLinkedAccountIdentifier(o2) : o1 === o2;
  }

  addLinkedAccountToCollectionIfMissing<Type extends Pick<ILinkedAccount, 'id'>>(
    linkedAccountCollection: Type[],
    ...linkedAccountsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const linkedAccounts: Type[] = linkedAccountsToCheck.filter(isPresent);
    if (linkedAccounts.length > 0) {
      const linkedAccountCollectionIdentifiers = linkedAccountCollection.map(
        linkedAccountItem => this.getLinkedAccountIdentifier(linkedAccountItem)!,
      );
      const linkedAccountsToAdd = linkedAccounts.filter(linkedAccountItem => {
        const linkedAccountIdentifier = this.getLinkedAccountIdentifier(linkedAccountItem);
        if (linkedAccountCollectionIdentifiers.includes(linkedAccountIdentifier)) {
          return false;
        }
        linkedAccountCollectionIdentifiers.push(linkedAccountIdentifier);
        return true;
      });
      return [...linkedAccountsToAdd, ...linkedAccountCollection];
    }
    return linkedAccountCollection;
  }
}
