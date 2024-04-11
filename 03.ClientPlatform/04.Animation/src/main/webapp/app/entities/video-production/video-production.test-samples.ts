import { IVideoProduction, NewVideoProduction } from './video-production.model';

export const sampleWithRequiredData: IVideoProduction = {
  id: 7604,
  title: 'yearningly reap',
  creator: 'out standing chargesheet',
};

export const sampleWithPartialData: IVideoProduction = {
  id: 17180,
  title: 'once emphasise geez',
  creator: 'hence',
};

export const sampleWithFullData: IVideoProduction = {
  id: 2501,
  title: 'indeed abaft',
  creator: 'obvious the',
  description: 'bestow apropos underneath',
  copyright: 'however',
  watchLink: 'helplessly around',
};

export const sampleWithNewData: NewVideoProduction = {
  title: 'instead after meh',
  creator: 'yowza',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
