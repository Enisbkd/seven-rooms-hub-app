export interface ITableNumber {
  id?: number;
  tableNum?: number | null;
}

export class TableNumber implements ITableNumber {
  constructor(
    public id?: number,
    public tableNum?: number | null,
  ) {}
}
