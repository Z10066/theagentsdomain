import { IVideo } from 'app/entities/video/video.model';

export interface IKeyword {
  id: number;
  word?: string | null;
  videos?: Pick<IVideo, 'id'>[] | null;
}

export type NewKeyword = Omit<IKeyword, 'id'> & { id: null };
