import { IMetadata, NewMetadata } from './metadata.model';

export const sampleWithRequiredData: IMetadata = {
  id: 15248,
};

export const sampleWithPartialData: IMetadata = {
  id: 9091,
  audienceFeedback: '../fake-data/blob/hipster.txt',
  relatedVideos: 'snuffle',
};

export const sampleWithFullData: IMetadata = {
  id: 24528,
  audienceFeedback: '../fake-data/blob/hipster.txt',
  relatedVideos: 'full than',
};

export const sampleWithNewData: NewMetadata = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
