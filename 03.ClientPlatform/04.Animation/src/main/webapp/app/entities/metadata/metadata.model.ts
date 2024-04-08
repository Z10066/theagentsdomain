import { IVideo } from 'app/entities/video/video.model';

export interface IMetadata {
  id: number;
  audienceFeedback?: string | null;
  relatedVideos?: string | null;
  video?: Pick<IVideo, 'id'> | null;
}

export type NewMetadata = Omit<IMetadata, 'id'> & { id: null };
