import { IYouTubeVideo, NewYouTubeVideo } from './you-tube-video.model';

export const sampleWithRequiredData: IYouTubeVideo = {
  id: 16066,
  workspace: 'psst overlap overlie',
  creator: 'quaintly boastfully',
  theme: 'equal',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'before',
  videoTime: 'stale mmm oddly',
  gender: 'left weird',
  videolanguage: 'woot aw',
};

export const sampleWithPartialData: IYouTubeVideo = {
  id: 27371,
  workspace: 'pirouette',
  creator: 'offbeat',
  theme: 'appliance half',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'ack businessman whether',
  videoTime: 'analog cruelly upon',
  gender: 'where',
  videolanguage: 'furthermore',
};

export const sampleWithFullData: IYouTubeVideo = {
  id: 6381,
  workspace: 'why incorporate lawful',
  creator: 'hearty',
  theme: 'inasmuch',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'chatter clone boldly',
  videoTime: 'symptom ah',
  gender: 'over boohoo righteously',
  videolanguage: 'goof woot between',
};

export const sampleWithNewData: NewYouTubeVideo = {
  workspace: 'retouch',
  creator: 'competent',
  theme: 'lest favour',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'mortify',
  videoTime: 'insignificant hot ward',
  gender: 'mechanically inside belabour',
  videolanguage: 'save instead mess',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
