import { IFileConfiguration, NewFileConfiguration } from './file-configuration.model';

export const sampleWithRequiredData: IFileConfiguration = {
  id: 3726,
  name: 'think',
  path: 'tidy',
  types: 'wrongly dapper meanwhile',
};

export const sampleWithPartialData: IFileConfiguration = {
  id: 12531,
  name: 'zoologist valiantly',
  description: 'giddy',
  path: 'jaded ack semicolon',
  types: 'a cue',
};

export const sampleWithFullData: IFileConfiguration = {
  id: 5553,
  name: 'scrutinise',
  description: 'repeatedly but',
  path: 'whose astride arrogantly',
  types: 'dune trachoma',
};

export const sampleWithNewData: NewFileConfiguration = {
  name: 'till',
  path: 'spin behind aware',
  types: 'incidentally furthermore brick',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
