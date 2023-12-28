import { type IResTag } from '@/shared/model/res-tag.model';
import { type IResPosTicket } from '@/shared/model/res-pos-ticket.model';
import { type IResCustomField } from '@/shared/model/res-custom-field.model';
import { type IClient } from '@/shared/model/client.model';

export interface IReservation {
  id?: number;
  resvId?: string | null;
  created?: string | null;
  updated?: string | null;
  deleted?: string | null;
  venueGroupClientId?: string | null;
  venueGroupId?: string | null;
  venueId?: string | null;
  date?: string | null;
  duration?: number | null;
  checkNumbers?: string | null;
  shiftCategory?: string | null;
  shiftPersistentId?: string | null;
  maxGuests?: number | null;
  mfratioMale?: number | null;
  mfratioFemale?: number | null;
  status?: string | null;
  statusDisplay?: string | null;
  statusSimple?: string | null;
  tableNumbers?: string | null;
  accessPersistentId?: string | null;
  arrivedGuests?: number | null;
  isvip?: boolean | null;
  bookedby?: string | null;
  clientReferenceCode?: string | null;
  lastname?: string | null;
  firstname?: string | null;
  email?: string | null;
  phoneNumber?: string | null;
  address?: string | null;
  address2?: string | null;
  city?: string | null;
  postalCode?: string | null;
  state?: string | null;
  country?: string | null;
  loyaltyId?: string | null;
  loyaltyRank?: number | null;
  loyaltyTier?: string | null;
  notes?: string | null;
  arrivalTime?: string | null;
  seatedTime?: string | null;
  leftTime?: string | null;
  clientRequests?: string | null;
  comps?: number | null;
  compsPriceType?: string | null;
  costOption?: number | null;
  policy?: string | null;
  minPrice?: number | null;
  prePayment?: number | null;
  onsitePayment?: number | null;
  totalPayment?: number | null;
  paidBy?: string | null;
  servedBy?: string | null;
  rating?: number | null;
  problems?: string | null;
  autoAssignments?: string | null;
  externalClientId?: string | null;
  externalId?: string | null;
  externalReferenceCode?: string | null;
  externalUserId?: string | null;
  modifyReservationLink?: string | null;
  referenceCode?: string | null;
  reservationSmsOptin?: boolean | null;
  reservationType?: string | null;
  sendReminderEmail?: boolean | null;
  sendreminderSms?: boolean | null;
  sourceClientId?: string | null;
  userId?: string | null;
  userName?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  resTags?: IResTag[] | null;
  resPosTickets?: IResPosTicket[] | null;
  resCustomFields?: IResCustomField[] | null;
  client?: IClient | null;
}

export class Reservation implements IReservation {
  constructor(
    public id?: number,
    public resvId?: string | null,
    public created?: string | null,
    public updated?: string | null,
    public deleted?: string | null,
    public venueGroupClientId?: string | null,
    public venueGroupId?: string | null,
    public venueId?: string | null,
    public date?: string | null,
    public duration?: number | null,
    public checkNumbers?: string | null,
    public shiftCategory?: string | null,
    public shiftPersistentId?: string | null,
    public maxGuests?: number | null,
    public mfratioMale?: number | null,
    public mfratioFemale?: number | null,
    public status?: string | null,
    public statusDisplay?: string | null,
    public statusSimple?: string | null,
    public tableNumbers?: string | null,
    public accessPersistentId?: string | null,
    public arrivedGuests?: number | null,
    public isvip?: boolean | null,
    public bookedby?: string | null,
    public clientReferenceCode?: string | null,
    public lastname?: string | null,
    public firstname?: string | null,
    public email?: string | null,
    public phoneNumber?: string | null,
    public address?: string | null,
    public address2?: string | null,
    public city?: string | null,
    public postalCode?: string | null,
    public state?: string | null,
    public country?: string | null,
    public loyaltyId?: string | null,
    public loyaltyRank?: number | null,
    public loyaltyTier?: string | null,
    public notes?: string | null,
    public arrivalTime?: string | null,
    public seatedTime?: string | null,
    public leftTime?: string | null,
    public clientRequests?: string | null,
    public comps?: number | null,
    public compsPriceType?: string | null,
    public costOption?: number | null,
    public policy?: string | null,
    public minPrice?: number | null,
    public prePayment?: number | null,
    public onsitePayment?: number | null,
    public totalPayment?: number | null,
    public paidBy?: string | null,
    public servedBy?: string | null,
    public rating?: number | null,
    public problems?: string | null,
    public autoAssignments?: string | null,
    public externalClientId?: string | null,
    public externalId?: string | null,
    public externalReferenceCode?: string | null,
    public externalUserId?: string | null,
    public modifyReservationLink?: string | null,
    public referenceCode?: string | null,
    public reservationSmsOptin?: boolean | null,
    public reservationType?: string | null,
    public sendReminderEmail?: boolean | null,
    public sendreminderSms?: boolean | null,
    public sourceClientId?: string | null,
    public userId?: string | null,
    public userName?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public resTags?: IResTag[] | null,
    public resPosTickets?: IResPosTicket[] | null,
    public resCustomFields?: IResCustomField[] | null,
    public client?: IClient | null,
  ) {
    this.isvip = this.isvip ?? false;
    this.reservationSmsOptin = this.reservationSmsOptin ?? false;
    this.sendReminderEmail = this.sendReminderEmail ?? false;
    this.sendreminderSms = this.sendreminderSms ?? false;
  }
}
