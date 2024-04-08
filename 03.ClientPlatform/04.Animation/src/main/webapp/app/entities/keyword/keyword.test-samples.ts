import { IKeyword, NewKeyword } from './keyword.model';

export const sampleWithRequiredData: IKeyword = {
  id: 9876,
  word: 'apologise hm patio',
};

export const sampleWithPartialData: IKeyword = {
  id: 25267,
  word: 'oof afterwards',
};

export const sampleWithFullData: IKeyword = {
  id: 4276,
  word: 'truthfully atop',
};

export const sampleWithNewData: NewKeyword = {
  word: 'stealthily ack',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
