import { type IReservation } from '@/shared/model/reservation.model';

export interface ITableNum {
  id?: number;
  tableNumber?: number | null;
  reservation?: IReservation | null;
}

export class TableNum implements ITableNum {
  constructor(
    public id?: number,
    public tableNumber?: number | null,
    public reservation?: IReservation | null,
  ) {}
}
