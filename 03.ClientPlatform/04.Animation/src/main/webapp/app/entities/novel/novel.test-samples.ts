import { INovel, NewNovel } from './novel.model';

export const sampleWithRequiredData: INovel = {
  id: 13877,
  noveltext: 'boohoo',
  novelname: 'mmm',
};

export const sampleWithPartialData: INovel = {
  id: 7035,
  noveltext: 'withhold soulful yahoo',
  novelname: 'yowza incidentally angry',
};

export const sampleWithFullData: INovel = {
  id: 13257,
  noveltext: 'adventurous open thankfully',
  novelname: 'polished',
  noveltype: 'counseling total over',
};

export const sampleWithNewData: NewNovel = {
  noveltext: 'wildly colony or',
  novelname: 'autosave whimsical',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
