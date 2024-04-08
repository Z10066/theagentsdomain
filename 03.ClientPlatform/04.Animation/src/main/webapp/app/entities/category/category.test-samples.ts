import { ICategory, NewCategory } from './category.model';

export const sampleWithRequiredData: ICategory = {
  id: 18063,
  name: 'respect',
};

export const sampleWithPartialData: ICategory = {
  id: 7685,
  name: 'tender help maul',
};

export const sampleWithFullData: ICategory = {
  id: 17781,
  name: 'relationship boo meanwhile',
};

export const sampleWithNewData: NewCategory = {
  name: 'incidentally court',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
