import { INovel, NewNovel } from './novel.model';

export const sampleWithRequiredData: INovel = {
  id: 10076,
  noveltext: 'pish brr',
  novelname: 'vanish usually used',
};

export const sampleWithPartialData: INovel = {
  id: 10215,
  noveltext: 'ha floss which',
  novelname: 'gerbil endpoint',
};

export const sampleWithFullData: INovel = {
  id: 31947,
  noveltext: 'unlike request',
  novelname: 'absent though yearly',
};

export const sampleWithNewData: NewNovel = {
  noveltext: 'windy at',
  novelname: 'as compass',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
