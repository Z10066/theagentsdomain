export interface IVideoHint {
  id: number;
  workspace?: string | null;
  creator?: string | null;
  creationContent?: string | null;
  backgroundMusic?: string | null;
}

export type NewVideoHint = Omit<IVideoHint, 'id'> & { id: null };
