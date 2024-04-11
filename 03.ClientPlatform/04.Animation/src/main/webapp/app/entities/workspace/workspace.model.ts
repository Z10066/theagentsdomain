import { IMember } from 'app/entities/member/member.model';
import { IVideoProduction } from 'app/entities/video-production/video-production.model';
import { IMaterial } from 'app/entities/material/material.model';
import { IHistory } from 'app/entities/history/history.model';

export interface IWorkspace {
  id: number;
  name?: string | null;
  identifier?: string | null;
  betaFeatures?: boolean | null;
  collaborationCursor?: boolean | null;
  defaultExportVisibility?: boolean | null;
  publicAccess?: boolean | null;
  members?: IMember[] | null;
  videoProductions?: IVideoProduction[] | null;
  materials?: IMaterial[] | null;
  histories?: IHistory[] | null;
}

export type NewWorkspace = Omit<IWorkspace, 'id'> & { id: null };
