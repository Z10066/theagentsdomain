import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVideoProduction, NewVideoProduction } from '../video-production.model';

export type PartialUpdateVideoProduction = Partial<IVideoProduction> & Pick<IVideoProduction, 'id'>;

export type EntityResponseType = HttpResponse<IVideoProduction>;
export type EntityArrayResponseType = HttpResponse<IVideoProduction[]>;

@Injectable({ providedIn: 'root' })
export class VideoProductionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/video-productions');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(videoProduction: NewVideoProduction): Observable<EntityResponseType> {
    return this.http.post<IVideoProduction>(this.resourceUrl, videoProduction, { observe: 'response' });
  }

  update(videoProduction: IVideoProduction): Observable<EntityResponseType> {
    return this.http.put<IVideoProduction>(`${this.resourceUrl}/${this.getVideoProductionIdentifier(videoProduction)}`, videoProduction, {
      observe: 'response',
    });
  }

  partialUpdate(videoProduction: PartialUpdateVideoProduction): Observable<EntityResponseType> {
    return this.http.patch<IVideoProduction>(`${this.resourceUrl}/${this.getVideoProductionIdentifier(videoProduction)}`, videoProduction, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVideoProduction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVideoProduction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVideoProductionIdentifier(videoProduction: Pick<IVideoProduction, 'id'>): number {
    return videoProduction.id;
  }

  compareVideoProduction(o1: Pick<IVideoProduction, 'id'> | null, o2: Pick<IVideoProduction, 'id'> | null): boolean {
    return o1 && o2 ? this.getVideoProductionIdentifier(o1) === this.getVideoProductionIdentifier(o2) : o1 === o2;
  }

  addVideoProductionToCollectionIfMissing<Type extends Pick<IVideoProduction, 'id'>>(
    videoProductionCollection: Type[],
    ...videoProductionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const videoProductions: Type[] = videoProductionsToCheck.filter(isPresent);
    if (videoProductions.length > 0) {
      const videoProductionCollectionIdentifiers = videoProductionCollection.map(
        videoProductionItem => this.getVideoProductionIdentifier(videoProductionItem)!,
      );
      const videoProductionsToAdd = videoProductions.filter(videoProductionItem => {
        const videoProductionIdentifier = this.getVideoProductionIdentifier(videoProductionItem);
        if (videoProductionCollectionIdentifiers.includes(videoProductionIdentifier)) {
          return false;
        }
        videoProductionCollectionIdentifiers.push(videoProductionIdentifier);
        return true;
      });
      return [...videoProductionsToAdd, ...videoProductionCollection];
    }
    return videoProductionCollection;
  }
}
