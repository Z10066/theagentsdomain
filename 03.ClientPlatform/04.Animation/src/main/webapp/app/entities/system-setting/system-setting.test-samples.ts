import { ISystemSetting, NewSystemSetting } from './system-setting.model';

export const sampleWithRequiredData: ISystemSetting = {
  id: 3726,
  name: 'bus hamstring cell',
  value: 'loud cooking',
};

export const sampleWithPartialData: ISystemSetting = {
  id: 5327,
  name: 'roommate',
  value: 'whenever excepting gloss',
};

export const sampleWithFullData: ISystemSetting = {
  id: 5688,
  name: 'awaken ferret',
  value: 'to publicise',
};

export const sampleWithNewData: NewSystemSetting = {
  name: 'drat bah',
  value: 'whoever',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
