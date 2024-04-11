import { IMaterial, NewMaterial } from './material.model';

export const sampleWithRequiredData: IMaterial = {
  id: 28057,
  title: 'abaft',
  creator: 'gosh yet ick',
};

export const sampleWithPartialData: IMaterial = {
  id: 28624,
  title: 'gloat',
  creator: 'wearily',
  copyright: 'mockingly although among',
};

export const sampleWithFullData: IMaterial = {
  id: 31336,
  title: 'vacantly definitive',
  creator: 'enormously',
  description: 'so',
  copyright: 'what',
  watchLink: 'correctly bah recall',
};

export const sampleWithNewData: NewMaterial = {
  title: 'ultimately',
  creator: 'until once but',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
