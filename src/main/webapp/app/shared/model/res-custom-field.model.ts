import { type IReservation } from '@/shared/model/reservation.model';

export interface IResCustomField {
  id?: number;
  systemName?: string | null;
  displayOrder?: number | null;
  name?: string | null;
  value?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  reservation?: IReservation | null;
}

export class ResCustomField implements IResCustomField {
  constructor(
    public id?: number,
    public systemName?: string | null,
    public displayOrder?: number | null,
    public name?: string | null,
    public value?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public reservation?: IReservation | null,
  ) {}
}
