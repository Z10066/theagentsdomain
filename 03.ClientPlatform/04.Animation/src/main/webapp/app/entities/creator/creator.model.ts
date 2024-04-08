import { IVideo } from 'app/entities/video/video.model';

export interface ICreator {
  id: number;
  name?: string | null;
  video?: Pick<IVideo, 'id'> | null;
}

export type NewCreator = Omit<ICreator, 'id'> & { id: null };
