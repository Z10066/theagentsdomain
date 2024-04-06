import dayjs from 'dayjs/esm';

import { IVideo, NewVideo } from './video.model';

export const sampleWithRequiredData: IVideo = {
  id: 11213,
  title: 'bitterly out sweep',
};

export const sampleWithPartialData: IVideo = {
  id: 28942,
  title: 'duh',
  format: 'peppery closed',
  resolution: 'conflict',
};

export const sampleWithFullData: IVideo = {
  id: 28664,
  title: 'brand',
  duration: 'aching priority',
  format: 'wherever playful restfully',
  resolution: 'flat quirk',
  publishedDate: dayjs('2024-03-15'),
  description: '../fake-data/blob/hipster.txt',
  viewingLink: 'refit race',
};

export const sampleWithNewData: NewVideo = {
  title: 'gosh unless',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
