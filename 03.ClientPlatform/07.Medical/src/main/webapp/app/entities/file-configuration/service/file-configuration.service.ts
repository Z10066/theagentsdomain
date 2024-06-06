import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFileConfiguration, getFileConfigurationIdentifier } from '../file-configuration.model';

export type EntityResponseType = HttpResponse<IFileConfiguration>;
export type EntityArrayResponseType = HttpResponse<IFileConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class FileConfigurationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/file-configurations');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(fileConfiguration: IFileConfiguration): Observable<EntityResponseType> {
    return this.http.post<IFileConfiguration>(this.resourceUrl, fileConfiguration, { observe: 'response' });
  }

  update(fileConfiguration: IFileConfiguration): Observable<EntityResponseType> {
    return this.http.put<IFileConfiguration>(
      `${this.resourceUrl}/${getFileConfigurationIdentifier(fileConfiguration) as number}`,
      fileConfiguration,
      { observe: 'response' }
    );
  }

  partialUpdate(fileConfiguration: IFileConfiguration): Observable<EntityResponseType> {
    return this.http.patch<IFileConfiguration>(
      `${this.resourceUrl}/${getFileConfigurationIdentifier(fileConfiguration) as number}`,
      fileConfiguration,
      { observe: 'response' }
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

  addFileConfigurationToCollectionIfMissing(
    fileConfigurationCollection: IFileConfiguration[],
    ...fileConfigurationsToCheck: (IFileConfiguration | null | undefined)[]
  ): IFileConfiguration[] {
    const fileConfigurations: IFileConfiguration[] = fileConfigurationsToCheck.filter(isPresent);
    if (fileConfigurations.length > 0) {
      const fileConfigurationCollectionIdentifiers = fileConfigurationCollection.map(
        fileConfigurationItem => getFileConfigurationIdentifier(fileConfigurationItem)!
      );
      const fileConfigurationsToAdd = fileConfigurations.filter(fileConfigurationItem => {
        const fileConfigurationIdentifier = getFileConfigurationIdentifier(fileConfigurationItem);
        if (fileConfigurationIdentifier == null || fileConfigurationCollectionIdentifiers.includes(fileConfigurationIdentifier)) {
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
