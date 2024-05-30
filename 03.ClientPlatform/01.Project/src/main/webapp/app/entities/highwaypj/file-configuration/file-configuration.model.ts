export interface IFileConfiguration {
  id: number;
  name?: string | null;
  description?: string | null;
  path?: string | null;
  types?: string | null;
}

export type NewFileConfiguration = Omit<IFileConfiguration, 'id'> & { id: null };
