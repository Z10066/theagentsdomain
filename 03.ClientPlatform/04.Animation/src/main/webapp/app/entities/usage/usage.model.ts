import dayjs from 'dayjs/esm';
import { ISubscriptionService } from 'app/entities/subscription-service/subscription-service.model';

export interface IUsage {
  id: number;
  usageType?: string | null;
  usageTime?: number | null;
  startTime?: dayjs.Dayjs | null;
  endTime?: dayjs.Dayjs | null;
  subscriptionService?: ISubscriptionService | null;
}

export type NewUsage = Omit<IUsage, 'id'> & { id: null };
