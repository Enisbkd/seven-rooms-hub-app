import { type IClient } from '@/shared/model/client.model';

export interface IMemberGroup {
  id?: number;
  client?: IClient | null;
}

export class MemberGroup implements IMemberGroup {
  constructor(
    public id?: number,
    public client?: IClient | null,
  ) {}
}
