import dayjs from 'dayjs/esm';

import { ISubscriptionService, NewSubscriptionService } from './subscription-service.model';

export const sampleWithRequiredData: ISubscriptionService = {
  id: 10578,
  serviceLevel: 'boo',
};

export const sampleWithPartialData: ISubscriptionService = {
  id: 15929,
  serviceLevel: 'psst',
};

export const sampleWithFullData: ISubscriptionService = {
  id: 9421,
  serviceLevel: 'geyser',
  totalUsageTime: 30508,
  startTime: dayjs('2024-03-15T21:52'),
  endTime: dayjs('2024-03-16T00:53'),
};

export const sampleWithNewData: NewSubscriptionService = {
  serviceLevel: 'why rudely',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
