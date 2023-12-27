export interface IResPosTicket {
  id?: number;
  status?: string | null;
  adminFee?: number | null;
  code?: number | null;
  tableNo?: string | null;
  tax?: number | null;
  businessId?: number | null;
  localPosticketId?: string | null;
  employeeName?: string | null;
  total?: number | null;
  subtotal?: number | null;
  startTime?: string | null;
  serviceCharge?: number | null;
  endtime?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
}

export class ResPosTicket implements IResPosTicket {
  constructor(
    public id?: number,
    public status?: string | null,
    public adminFee?: number | null,
    public code?: number | null,
    public tableNo?: string | null,
    public tax?: number | null,
    public businessId?: number | null,
    public localPosticketId?: string | null,
    public employeeName?: string | null,
    public total?: number | null,
    public subtotal?: number | null,
    public startTime?: string | null,
    public serviceCharge?: number | null,
    public endtime?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
  ) {}
}
