import { type IClient } from '@/shared/model/client.model';

export interface IClientPhoto {
  id?: number;
  clientId?: string | null;
  large?: string | null;
  largeHeight?: number | null;
  largeWidth?: number | null;
  medium?: string | null;
  mediumHeight?: number | null;
  mediumWidth?: number | null;
  small?: string | null;
  smallHeight?: number | null;
  smallWidth?: number | null;
  raw?: string | null;
  cropx?: number | null;
  cropy?: number | null;
  cropHeight?: number | null;
  cropWidth?: number | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  client?: IClient | null;
}

export class ClientPhoto implements IClientPhoto {
  constructor(
    public id?: number,
    public clientId?: string | null,
    public large?: string | null,
    public largeHeight?: number | null,
    public largeWidth?: number | null,
    public medium?: string | null,
    public mediumHeight?: number | null,
    public mediumWidth?: number | null,
    public small?: string | null,
    public smallHeight?: number | null,
    public smallWidth?: number | null,
    public raw?: string | null,
    public cropx?: number | null,
    public cropy?: number | null,
    public cropHeight?: number | null,
    public cropWidth?: number | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public client?: IClient | null,
  ) {}
}
