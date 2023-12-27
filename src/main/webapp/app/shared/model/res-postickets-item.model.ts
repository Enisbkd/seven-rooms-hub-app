import { type IReservation } from '@/shared/model/reservation.model';

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
  reservation?: IReservation | null;
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
    public reservation?: IReservation | null,
  ) {}
}
