import { type IClient } from '@/shared/model/client.model';

export interface ICustomField {
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
  client?: IClient | null;
}

export class CustomField implements ICustomField {
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
    public client?: IClient | null,
  ) {}
}
