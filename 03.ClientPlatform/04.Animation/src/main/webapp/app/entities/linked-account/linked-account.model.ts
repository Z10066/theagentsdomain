import { IMember } from 'app/entities/member/member.model';
import { AccountType } from 'app/entities/enumerations/account-type.model';

export interface ILinkedAccount {
  id: number;
  accountType?: keyof typeof AccountType | null;
  accountIdentifier?: string | null;
  member?: IMember | null;
}

export type NewLinkedAccount = Omit<ILinkedAccount, 'id'> & { id: null };
