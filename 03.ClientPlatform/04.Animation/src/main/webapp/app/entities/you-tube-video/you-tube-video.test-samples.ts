import { IYouTubeVideo, NewYouTubeVideo } from './you-tube-video.model';

export const sampleWithRequiredData: IYouTubeVideo = {
  id: 13130,
  workspace: 'gadzooks verbally beside',
  creator: 'the splinter',
  theme: 'aw format',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'qualified',
};

export const sampleWithPartialData: IYouTubeVideo = {
  id: 11272,
  workspace: 'sympathetically daily',
  creator: 'commonly intersperse',
  theme: 'on',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'if locker',
};

export const sampleWithFullData: IYouTubeVideo = {
  id: 2573,
  workspace: 'oof trooper',
  creator: 'aside',
  theme: 'whoever regular wonderfully',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'cautiously skid',
};

export const sampleWithNewData: NewYouTubeVideo = {
  workspace: 'dice electric',
  creator: 'inasmuch',
  theme: 'father-in-law tabletop',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'magnificent phew',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
