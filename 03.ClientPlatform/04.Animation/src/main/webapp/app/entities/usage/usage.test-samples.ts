import dayjs from 'dayjs/esm';

import { IUsage, NewUsage } from './usage.model';

export const sampleWithRequiredData: IUsage = {
  id: 28738,
  usageType: 'fence yap',
};

export const sampleWithPartialData: IUsage = {
  id: 9613,
  usageType: 'recklessly blue',
  usageTime: 17558,
  startTime: dayjs('2024-03-16T11:03'),
};

export const sampleWithFullData: IUsage = {
  id: 20003,
  usageType: 'aside sausage trunk',
  usageTime: 10464,
  startTime: dayjs('2024-03-15T18:42'),
  endTime: dayjs('2024-03-15T16:08'),
};

export const sampleWithNewData: NewUsage = {
  usageType: 'than',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
