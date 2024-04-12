import { IMember, NewMember } from './member.model';

export const sampleWithRequiredData: IMember = {
  id: 20995,
  username: 'meanwhile given per',
  fullName: 'um crumb',
};

export const sampleWithPartialData: IMember = {
  id: 5650,
  username: 'intermix considering',
  fullName: 'meet',
  activeStatus: false,
};

export const sampleWithFullData: IMember = {
  id: 19008,
  username: 'musculature meh sick',
  fullName: 'derivative',
  role: 'unless counsel',
  activeStatus: false,
};

export const sampleWithNewData: NewMember = {
  username: 'candid shorts',
  fullName: 'when or ick',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
