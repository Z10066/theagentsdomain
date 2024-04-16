import { IVideoHint, NewVideoHint } from './video-hint.model';

export const sampleWithRequiredData: IVideoHint = {
  id: 30240,
  workspace: 'wherever',
  creator: 'saunter woefully',
  creationContent: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'greedy',
};

export const sampleWithPartialData: IVideoHint = {
  id: 32333,
  workspace: 'poster likewise',
  creator: 'unacceptable',
  creationContent: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'floodlight unlike',
};

export const sampleWithFullData: IVideoHint = {
  id: 21785,
  workspace: 'schoolhouse fav hence',
  creator: 'frequent',
  creationContent: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'outsmart phew zowie',
};

export const sampleWithNewData: NewVideoHint = {
  workspace: 'almost',
  creator: 'crust once ugh',
  creationContent: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'orient',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
