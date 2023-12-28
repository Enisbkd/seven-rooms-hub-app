import { type IClientPhoto } from '@/shared/model/client-photo.model';
import { type IClientVenueStats } from '@/shared/model/client-venue-stats.model';
import { type ICustomField } from '@/shared/model/custom-field.model';
import { type IClientTag } from '@/shared/model/client-tag.model';
import { type IReservation } from '@/shared/model/reservation.model';
import { type IMemberGroup } from '@/shared/model/member-group.model';

export interface IClient {
  id?: number;
  clientId?: string | null;
  createdDate?: string | null;
  updatedDate?: string | null;
  deletedDate?: string | null;
  lastname?: string | null;
  firstname?: string | null;
  gender?: string | null;
  salutation?: string | null;
  title?: string | null;
  birthdayDay?: number | null;
  birthdayMonth?: number | null;
  birthdayAltMonth?: number | null;
  anniversaryDay?: number | null;
  anniversaryMonth?: number | null;
  company?: string | null;
  email?: string | null;
  emailAlt?: string | null;
  phoneNumber?: string | null;
  phoneNumberlocale?: string | null;
  phoneNumberalt?: string | null;
  phoneNumberaltlocale?: string | null;
  address?: string | null;
  address2?: string | null;
  city?: string | null;
  postalCode?: string | null;
  state?: string | null;
  country?: string | null;
  isContactPrivate?: boolean | null;
  isOnetimeGuest?: boolean | null;
  status?: string | null;
  loyaltyId?: string | null;
  loyaltyRank?: number | null;
  loyaltyTier?: string | null;
  marketingOptin?: boolean | null;
  marketingOptints?: string | null;
  marketingOptOutts?: string | null;
  hasBillingProfile?: boolean | null;
  notes?: string | null;
  privateNotes?: string | null;
  tags?: string | null;
  totalVisits?: number | null;
  totalCovers?: number | null;
  totalCancellations?: number | null;
  totalNoShows?: number | null;
  totalSpend?: number | null;
  totalSpendPerCover?: number | null;
  totalspendPerVisit?: number | null;
  avgRating?: number | null;
  referenceCode?: string | null;
  externalUserId?: string | null;
  venueGroupId?: string | null;
  birthdayAltDay?: number | null;
  userId?: string | null;
  userName?: string | null;
  totalOrderCount?: number | null;
  preferredLanguageCode?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  clientPhoto?: IClientPhoto | null;
  clientVenueStats?: IClientVenueStats | null;
  customFields?: ICustomField[] | null;
  clientTags?: IClientTag[] | null;
  reservations?: IReservation[] | null;
  memberGroups?: IMemberGroup[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public clientId?: string | null,
    public createdDate?: string | null,
    public updatedDate?: string | null,
    public deletedDate?: string | null,
    public lastname?: string | null,
    public firstname?: string | null,
    public gender?: string | null,
    public salutation?: string | null,
    public title?: string | null,
    public birthdayDay?: number | null,
    public birthdayMonth?: number | null,
    public birthdayAltMonth?: number | null,
    public anniversaryDay?: number | null,
    public anniversaryMonth?: number | null,
    public company?: string | null,
    public email?: string | null,
    public emailAlt?: string | null,
    public phoneNumber?: string | null,
    public phoneNumberlocale?: string | null,
    public phoneNumberalt?: string | null,
    public phoneNumberaltlocale?: string | null,
    public address?: string | null,
    public address2?: string | null,
    public city?: string | null,
    public postalCode?: string | null,
    public state?: string | null,
    public country?: string | null,
    public isContactPrivate?: boolean | null,
    public isOnetimeGuest?: boolean | null,
    public status?: string | null,
    public loyaltyId?: string | null,
    public loyaltyRank?: number | null,
    public loyaltyTier?: string | null,
    public marketingOptin?: boolean | null,
    public marketingOptints?: string | null,
    public marketingOptOutts?: string | null,
    public hasBillingProfile?: boolean | null,
    public notes?: string | null,
    public privateNotes?: string | null,
    public tags?: string | null,
    public totalVisits?: number | null,
    public totalCovers?: number | null,
    public totalCancellations?: number | null,
    public totalNoShows?: number | null,
    public totalSpend?: number | null,
    public totalSpendPerCover?: number | null,
    public totalspendPerVisit?: number | null,
    public avgRating?: number | null,
    public referenceCode?: string | null,
    public externalUserId?: string | null,
    public venueGroupId?: string | null,
    public birthdayAltDay?: number | null,
    public userId?: string | null,
    public userName?: string | null,
    public totalOrderCount?: number | null,
    public preferredLanguageCode?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public clientPhoto?: IClientPhoto | null,
    public clientVenueStats?: IClientVenueStats | null,
    public customFields?: ICustomField[] | null,
    public clientTags?: IClientTag[] | null,
    public reservations?: IReservation[] | null,
    public memberGroups?: IMemberGroup[] | null,
  ) {
    this.isContactPrivate = this.isContactPrivate ?? false;
    this.isOnetimeGuest = this.isOnetimeGuest ?? false;
    this.marketingOptin = this.marketingOptin ?? false;
    this.hasBillingProfile = this.hasBillingProfile ?? false;
  }
}
