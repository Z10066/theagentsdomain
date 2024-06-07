export interface INovel {
  id: number;
  noveltext?: string | null;
  novelname?: string | null;
  noveltype?: string | null;
}

export type NewNovel = Omit<INovel, 'id'> & { id: null };
