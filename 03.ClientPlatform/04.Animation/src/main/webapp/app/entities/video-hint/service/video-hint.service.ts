import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVideoHint, NewVideoHint } from '../video-hint.model';

export type PartialUpdateVideoHint = Partial<IVideoHint> & Pick<IVideoHint, 'id'>;

export type EntityResponseType = HttpResponse<IVideoHint>;
export type EntityArrayResponseType = HttpResponse<IVideoHint[]>;

@Injectable({ providedIn: 'root' })
export class VideoHintService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/video-hints');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(videoHint: NewVideoHint): Observable<EntityResponseType> {
    return this.http.post<IVideoHint>(this.resourceUrl, videoHint, { observe: 'response' });
  }

  update(videoHint: IVideoHint): Observable<EntityResponseType> {
    return this.http.put<IVideoHint>(`${this.resourceUrl}/${this.getVideoHintIdentifier(videoHint)}`, videoHint, { observe: 'response' });
  }

  partialUpdate(videoHint: PartialUpdateVideoHint): Observable<EntityResponseType> {
    return this.http.patch<IVideoHint>(`${this.resourceUrl}/${this.getVideoHintIdentifier(videoHint)}`, videoHint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVideoHint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVideoHint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVideoHintIdentifier(videoHint: Pick<IVideoHint, 'id'>): number {
    return videoHint.id;
  }

  compareVideoHint(o1: Pick<IVideoHint, 'id'> | null, o2: Pick<IVideoHint, 'id'> | null): boolean {
    return o1 && o2 ? this.getVideoHintIdentifier(o1) === this.getVideoHintIdentifier(o2) : o1 === o2;
  }

  addVideoHintToCollectionIfMissing<Type extends Pick<IVideoHint, 'id'>>(
    videoHintCollection: Type[],
    ...videoHintsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const videoHints: Type[] = videoHintsToCheck.filter(isPresent);
    if (videoHints.length > 0) {
      const videoHintCollectionIdentifiers = videoHintCollection.map(videoHintItem => this.getVideoHintIdentifier(videoHintItem)!);
      const videoHintsToAdd = videoHints.filter(videoHintItem => {
        const videoHintIdentifier = this.getVideoHintIdentifier(videoHintItem);
        if (videoHintCollectionIdentifiers.includes(videoHintIdentifier)) {
          return false;
        }
        videoHintCollectionIdentifiers.push(videoHintIdentifier);
        return true;
      });
      return [...videoHintsToAdd, ...videoHintCollection];
    }
    return videoHintCollection;
  }
}
