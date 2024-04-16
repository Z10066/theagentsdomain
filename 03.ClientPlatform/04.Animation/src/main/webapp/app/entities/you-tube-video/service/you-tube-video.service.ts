import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IYouTubeVideo, NewYouTubeVideo } from '../you-tube-video.model';

export type PartialUpdateYouTubeVideo = Partial<IYouTubeVideo> & Pick<IYouTubeVideo, 'id'>;

export type EntityResponseType = HttpResponse<IYouTubeVideo>;
export type EntityArrayResponseType = HttpResponse<IYouTubeVideo[]>;

@Injectable({ providedIn: 'root' })
export class YouTubeVideoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/you-tube-videos');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(youTubeVideo: NewYouTubeVideo): Observable<EntityResponseType> {
    return this.http.post<IYouTubeVideo>(this.resourceUrl, youTubeVideo, { observe: 'response' });
  }

  update(youTubeVideo: IYouTubeVideo): Observable<EntityResponseType> {
    return this.http.put<IYouTubeVideo>(`${this.resourceUrl}/${this.getYouTubeVideoIdentifier(youTubeVideo)}`, youTubeVideo, {
      observe: 'response',
    });
  }

  partialUpdate(youTubeVideo: PartialUpdateYouTubeVideo): Observable<EntityResponseType> {
    return this.http.patch<IYouTubeVideo>(`${this.resourceUrl}/${this.getYouTubeVideoIdentifier(youTubeVideo)}`, youTubeVideo, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IYouTubeVideo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IYouTubeVideo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getYouTubeVideoIdentifier(youTubeVideo: Pick<IYouTubeVideo, 'id'>): number {
    return youTubeVideo.id;
  }

  compareYouTubeVideo(o1: Pick<IYouTubeVideo, 'id'> | null, o2: Pick<IYouTubeVideo, 'id'> | null): boolean {
    return o1 && o2 ? this.getYouTubeVideoIdentifier(o1) === this.getYouTubeVideoIdentifier(o2) : o1 === o2;
  }

  addYouTubeVideoToCollectionIfMissing<Type extends Pick<IYouTubeVideo, 'id'>>(
    youTubeVideoCollection: Type[],
    ...youTubeVideosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const youTubeVideos: Type[] = youTubeVideosToCheck.filter(isPresent);
    if (youTubeVideos.length > 0) {
      const youTubeVideoCollectionIdentifiers = youTubeVideoCollection.map(
        youTubeVideoItem => this.getYouTubeVideoIdentifier(youTubeVideoItem)!,
      );
      const youTubeVideosToAdd = youTubeVideos.filter(youTubeVideoItem => {
        const youTubeVideoIdentifier = this.getYouTubeVideoIdentifier(youTubeVideoItem);
        if (youTubeVideoCollectionIdentifiers.includes(youTubeVideoIdentifier)) {
          return false;
        }
        youTubeVideoCollectionIdentifiers.push(youTubeVideoIdentifier);
        return true;
      });
      return [...youTubeVideosToAdd, ...youTubeVideoCollection];
    }
    return youTubeVideoCollection;
  }
}
