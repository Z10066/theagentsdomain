import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMetadata, NewMetadata } from '../metadata.model';

export type PartialUpdateMetadata = Partial<IMetadata> & Pick<IMetadata, 'id'>;

export type EntityResponseType = HttpResponse<IMetadata>;
export type EntityArrayResponseType = HttpResponse<IMetadata[]>;

@Injectable({ providedIn: 'root' })
export class MetadataService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/metadata');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(metadata: NewMetadata): Observable<EntityResponseType> {
    return this.http.post<IMetadata>(this.resourceUrl, metadata, { observe: 'response' });
  }

  update(metadata: IMetadata): Observable<EntityResponseType> {
    return this.http.put<IMetadata>(`${this.resourceUrl}/${this.getMetadataIdentifier(metadata)}`, metadata, { observe: 'response' });
  }

  partialUpdate(metadata: PartialUpdateMetadata): Observable<EntityResponseType> {
    return this.http.patch<IMetadata>(`${this.resourceUrl}/${this.getMetadataIdentifier(metadata)}`, metadata, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMetadata>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMetadata[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMetadataIdentifier(metadata: Pick<IMetadata, 'id'>): number {
    return metadata.id;
  }

  compareMetadata(o1: Pick<IMetadata, 'id'> | null, o2: Pick<IMetadata, 'id'> | null): boolean {
    return o1 && o2 ? this.getMetadataIdentifier(o1) === this.getMetadataIdentifier(o2) : o1 === o2;
  }

  addMetadataToCollectionIfMissing<Type extends Pick<IMetadata, 'id'>>(
    metadataCollection: Type[],
    ...metadataToCheck: (Type | null | undefined)[]
  ): Type[] {
    const metadata: Type[] = metadataToCheck.filter(isPresent);
    if (metadata.length > 0) {
      const metadataCollectionIdentifiers = metadataCollection.map(metadataItem => this.getMetadataIdentifier(metadataItem)!);
      const metadataToAdd = metadata.filter(metadataItem => {
        const metadataIdentifier = this.getMetadataIdentifier(metadataItem);
        if (metadataCollectionIdentifiers.includes(metadataIdentifier)) {
          return false;
        }
        metadataCollectionIdentifiers.push(metadataIdentifier);
        return true;
      });
      return [...metadataToAdd, ...metadataCollection];
    }
    return metadataCollection;
  }
}
