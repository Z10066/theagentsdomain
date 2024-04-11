import { IHistory, NewHistory } from './history.model';

export const sampleWithRequiredData: IHistory = {
  id: 23485,
  title: 'scarcely fertilise save',
  creator: 'annually neatly resale',
};

export const sampleWithPartialData: IHistory = {
  id: 18602,
  title: 'against equalize',
  creator: 'superficial',
  description: 'amongst against',
  watchLink: 'blessing confine',
};

export const sampleWithFullData: IHistory = {
  id: 3246,
  title: 'kid hopeful yuck',
  creator: 'cheerfully',
  description: 'perfect than',
  copyright: 'meaningfully well-lit',
  watchLink: 'clam lightly',
};

export const sampleWithNewData: NewHistory = {
  title: 'tankful century cornflakes',
  creator: 'apud natural',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
