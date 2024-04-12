import { IExtraInfo, NewExtraInfo } from './extra-info.model';

export const sampleWithRequiredData: IExtraInfo = {
  id: 3612,
};

export const sampleWithPartialData: IExtraInfo = {
  id: 3609,
};

export const sampleWithFullData: IExtraInfo = {
  id: 15786,
  audienceFeedback: 'hm',
  relatedVideos: 'listen whereas',
};

export const sampleWithNewData: NewExtraInfo = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
