import { ICreator, NewCreator } from './creator.model';

export const sampleWithRequiredData: ICreator = {
  id: 29562,
  name: 'violate',
};

export const sampleWithPartialData: ICreator = {
  id: 29780,
  name: 'schtup ha',
};

export const sampleWithFullData: ICreator = {
  id: 19182,
  name: 'athwart sofa',
};

export const sampleWithNewData: NewCreator = {
  name: 'correctly',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
