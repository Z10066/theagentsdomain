import { IVideo } from 'app/entities/video/video.model';

export interface ICopyright {
  id: number;
  details?: string | null;
  video?: Pick<IVideo, 'id'> | null;
}

export type NewCopyright = Omit<ICopyright, 'id'> & { id: null };
