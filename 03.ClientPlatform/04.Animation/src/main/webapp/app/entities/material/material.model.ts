import { IExtraInfo } from 'app/entities/extra-info/extra-info.model';
import { IWorkspace } from 'app/entities/workspace/workspace.model';

export interface IMaterial {
  id: number;
  title?: string | null;
  creator?: string | null;
  description?: string | null;
  copyright?: string | null;
  watchLink?: string | null;
  extraInfo?: IExtraInfo | null;
  workspace?: IWorkspace | null;
}

export type NewMaterial = Omit<IMaterial, 'id'> & { id: null };
