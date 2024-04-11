import { ILinkedAccount } from 'app/entities/linked-account/linked-account.model';
import { IWorkspace } from 'app/entities/workspace/workspace.model';

export interface IMember {
  id: number;
  username?: string | null;
  fullName?: string | null;
  role?: string | null;
  activeStatus?: boolean | null;
  linkedAccounts?: ILinkedAccount[] | null;
  workspace?: IWorkspace | null;
}

export type NewMember = Omit<IMember, 'id'> & { id: null };
