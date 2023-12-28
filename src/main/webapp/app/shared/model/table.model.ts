import { type IReservation } from '@/shared/model/reservation.model';

export interface ITable {
  id?: number;
  tableNumber?: number | null;
  reservation?: IReservation | null;
}

export class Table implements ITable {
  constructor(
    public id?: number,
    public tableNumber?: number | null,
    public reservation?: IReservation | null,
  ) {}
}
