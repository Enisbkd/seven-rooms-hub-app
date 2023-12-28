import { type IResPosTicket } from '@/shared/model/res-pos-ticket.model';

export interface IResPosticketsItem {
  id?: number;
  price?: number | null;
  name?: string | null;
  quantity?: number | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  resPosTicket?: IResPosTicket | null;
}

export class ResPosticketsItem implements IResPosticketsItem {
  constructor(
    public id?: number,
    public price?: number | null,
    public name?: string | null,
    public quantity?: number | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public resPosTicket?: IResPosTicket | null,
  ) {}
}
