import { IVideoProduction } from 'app/entities/video-production/video-production.model';
import { IMaterial } from 'app/entities/material/material.model';
import { IHistory } from 'app/entities/history/history.model';

export interface IExtraInfo {
  id: number;
  audienceFeedback?: string | null;
  relatedVideos?: string | null;
  videoProduction?: IVideoProduction | null;
  material?: IMaterial | null;
  history?: IHistory | null;
}

export type NewExtraInfo = Omit<IExtraInfo, 'id'> & { id: null };
