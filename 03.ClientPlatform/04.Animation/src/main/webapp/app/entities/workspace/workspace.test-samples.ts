import { IWorkspace, NewWorkspace } from './workspace.model';

export const sampleWithRequiredData: IWorkspace = {
  id: 860,
  name: 'apud willfully',
  identifier: 'with',
};

export const sampleWithPartialData: IWorkspace = {
  id: 1292,
  name: 'woot during fender',
  identifier: 'illiterate maniac waken',
  betaFeatures: true,
  publicAccess: false,
};

export const sampleWithFullData: IWorkspace = {
  id: 12780,
  name: 'continually',
  identifier: 'screamer jaunty',
  betaFeatures: false,
  collaborationCursor: false,
  defaultExportVisibility: false,
  publicAccess: false,
};

export const sampleWithNewData: NewWorkspace = {
  name: 'kind crease second',
  identifier: 'interestingly intertwine spider',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
