import { IYouTubeVideo, NewYouTubeVideo } from './you-tube-video.model';

export const sampleWithRequiredData: IYouTubeVideo = {
  id: 32642,
  workspace: 'elegantly antique duh',
  creator: 'quarter',
  theme: 'swimming notwithstanding disconcert',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'puzzled',
  videoTime: 'velvety',
};

export const sampleWithPartialData: IYouTubeVideo = {
  id: 22060,
  workspace: 'phew per',
  creator: 'incidentally pish wonderfully',
  theme: 'wretched ensconce than',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'tremor',
  videoTime: 'finally reshuffle',
};

export const sampleWithFullData: IYouTubeVideo = {
  id: 26061,
  workspace: 'amusing merrily sharply',
  creator: 'and format liner',
  theme: 'extroverted TV likely',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'that intensely because',
  videoTime: 'around fear for',
};

export const sampleWithNewData: NewYouTubeVideo = {
  workspace: 'mismatch instantly furthermore',
  creator: 'within eventually kite',
  theme: 'lashes',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'ultimately who',
  videoTime: 'plus vice since',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
