import { IFileConfiguration, NewFileConfiguration } from './file-configuration.model';

export const sampleWithRequiredData: IFileConfiguration = {
  id: 13952,
  name: 'gee over kettledrum',
  path: 'circle fooey parlay',
  types: 'why loathsome',
};

export const sampleWithPartialData: IFileConfiguration = {
  id: 25118,
  name: 'authorized',
  description: 'inasmuch as',
  path: 'far',
  types: 'majestically drat appertain',
};

export const sampleWithFullData: IFileConfiguration = {
  id: 22488,
  name: 'raffle stucco',
  description: 'above for',
  path: 'troubled united armament',
  types: 'aboard millet optimal',
};

export const sampleWithNewData: NewFileConfiguration = {
  name: 'valentine poorly',
  path: 'which yuck serpentine',
  types: 'oddly ouch duplexer',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
