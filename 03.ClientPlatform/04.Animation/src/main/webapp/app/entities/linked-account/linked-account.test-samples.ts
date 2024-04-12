import { ILinkedAccount, NewLinkedAccount } from './linked-account.model';

export const sampleWithRequiredData: ILinkedAccount = {
  id: 19950,
  accountType: 'YOUTUBE',
  accountIdentifier: 'beautifully',
};

export const sampleWithPartialData: ILinkedAccount = {
  id: 7889,
  accountType: 'YOUTUBE',
  accountIdentifier: 'apud',
};

export const sampleWithFullData: ILinkedAccount = {
  id: 25723,
  accountType: 'FACEBOOK',
  accountIdentifier: 'hiring interview',
};

export const sampleWithNewData: NewLinkedAccount = {
  accountType: 'TWITTER',
  accountIdentifier: 'blissfully',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
