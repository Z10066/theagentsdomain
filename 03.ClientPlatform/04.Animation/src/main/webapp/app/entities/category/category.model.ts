import { IVideo } from 'app/entities/video/video.model';

export interface ICategory {
  id: number;
  name?: string | null;
  video?: Pick<IVideo, 'id'> | null;
}

export type NewCategory = Omit<ICategory, 'id'> & { id: null };
