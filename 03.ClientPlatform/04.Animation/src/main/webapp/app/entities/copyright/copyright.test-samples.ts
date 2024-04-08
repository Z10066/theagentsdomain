import { ICopyright, NewCopyright } from './copyright.model';

export const sampleWithRequiredData: ICopyright = {
  id: 24838,
  details: '../fake-data/blob/hipster.txt',
};

export const sampleWithPartialData: ICopyright = {
  id: 1187,
  details: '../fake-data/blob/hipster.txt',
};

export const sampleWithFullData: ICopyright = {
  id: 22409,
  details: '../fake-data/blob/hipster.txt',
};

export const sampleWithNewData: NewCopyright = {
  details: '../fake-data/blob/hipster.txt',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
