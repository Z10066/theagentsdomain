import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFileConfiguration, NewFileConfiguration } from '../file-configuration.model';

export type PartialUpdateFileConfiguration = Partial<IFileConfiguration> & Pick<IFileConfiguration, 'id'>;

export type EntityResponseType = HttpResponse<IFileConfiguration>;
export type EntityArrayResponseType = HttpResponse<IFileConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class FileConfigurationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/file-configurations');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(fileConfiguration: NewFileConfiguration): Observable<EntityResponseType> {
    return this.http.post<IFileConfiguration>(this.resourceUrl, fileConfiguration, { observe: 'response' });
  }

  update(fileConfiguration: IFileConfiguration): Observable<EntityResponseType> {
    return this.http.put<IFileConfiguration>(
      `${this.resourceUrl}/${this.getFileConfigurationIdentifier(fileConfiguration)}`,
      fileConfiguration,
      { observe: 'response' },
    );
  }

  partialUpdate(fileConfiguration: PartialUpdateFileConfiguration): Observable<EntityResponseType> {
    return this.http.patch<IFileConfiguration>(
      `${this.resourceUrl}/${this.getFileConfigurationIdentifier(fileConfiguration)}`,
      fileConfiguration,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFileConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFileConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFileConfigurationIdentifier(fileConfiguration: Pick<IFileConfiguration, 'id'>): number {
    return fileConfiguration.id;
  }

  compareFileConfiguration(o1: Pick<IFileConfiguration, 'id'> | null, o2: Pick<IFileConfiguration, 'id'> | null): boolean {
    return o1 && o2 ? this.getFileConfigurationIdentifier(o1) === this.getFileConfigurationIdentifier(o2) : o1 === o2;
  }

  addFileConfigurationToCollectionIfMissing<Type extends Pick<IFileConfiguration, 'id'>>(
    fileConfigurationCollection: Type[],
    ...fileConfigurationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const fileConfigurations: Type[] = fileConfigurationsToCheck.filter(isPresent);
    if (fileConfigurations.length > 0) {
      const fileConfigurationCollectionIdentifiers = fileConfigurationCollection.map(
        fileConfigurationItem => this.getFileConfigurationIdentifier(fileConfigurationItem)!,
      );
      const fileConfigurationsToAdd = fileConfigurations.filter(fileConfigurationItem => {
        const fileConfigurationIdentifier = this.getFileConfigurationIdentifier(fileConfigurationItem);
        if (fileConfigurationCollectionIdentifiers.includes(fileConfigurationIdentifier)) {
          return false;
        }
        fileConfigurationCollectionIdentifiers.push(fileConfigurationIdentifier);
        return true;
      });
      return [...fileConfigurationsToAdd, ...fileConfigurationCollection];
    }
    return fileConfigurationCollection;
  }
}
