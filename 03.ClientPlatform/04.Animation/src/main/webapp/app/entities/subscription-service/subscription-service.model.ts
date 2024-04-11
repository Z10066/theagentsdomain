import dayjs from 'dayjs/esm';
import { IUsage } from 'app/entities/usage/usage.model';

export interface ISubscriptionService {
  id: number;
  serviceLevel?: string | null;
  totalUsageTime?: number | null;
  startTime?: dayjs.Dayjs | null;
  endTime?: dayjs.Dayjs | null;
  usages?: IUsage[] | null;
}

export type NewSubscriptionService = Omit<ISubscriptionService, 'id'> & { id: null };
