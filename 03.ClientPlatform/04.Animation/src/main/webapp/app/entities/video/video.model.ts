import dayjs from 'dayjs/esm';
import { ICreator } from 'app/entities/creator/creator.model';
import { ICategory } from 'app/entities/category/category.model';
import { ICopyright } from 'app/entities/copyright/copyright.model';
import { IMetadata } from 'app/entities/metadata/metadata.model';
import { IKeyword } from 'app/entities/keyword/keyword.model';

export interface IVideo {
  id: number;
  title?: string | null;
  duration?: string | null;
  format?: string | null;
  resolution?: string | null;
  publishedDate?: dayjs.Dayjs | null;
  description?: string | null;
  viewingLink?: string | null;
  creator?: Pick<ICreator, 'id'> | null;
  category?: Pick<ICategory, 'id'> | null;
  copyright?: Pick<ICopyright, 'id'> | null;
  extraInfo?: Pick<IMetadata, 'id'> | null;
  keywords?: Pick<IKeyword, 'id'>[] | null;
}

export type NewVideo = Omit<IVideo, 'id'> & { id: null };
