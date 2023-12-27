import { type IClient } from '@/shared/model/client.model';

export interface IClientVenueStats {
  id?: number;
  venueId?: string | null;
  avgRating?: number | null;
  bookedByNames?: string | null;
  lastVisitDate?: string | null;
  numRatings?: number | null;
  totalCancellations?: number | null;
  totalCovers?: number | null;
  totalNoShows?: number | null;
  totalSpend?: number | null;
  totalSpendLocal?: number | null;
  totalSpendLocalperCover?: number | null;
  totalSpendLocalPerVisit?: number | null;
  totalSpendperCover?: number | null;
  totalSpendPerVisit?: number | null;
  totalVisit?: number | null;
  venueMarketingOptin?: boolean | null;
  venueMarketingOptints?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  client?: IClient | null;
}

export class ClientVenueStats implements IClientVenueStats {
  constructor(
    public id?: number,
    public venueId?: string | null,
    public avgRating?: number | null,
    public bookedByNames?: string | null,
    public lastVisitDate?: string | null,
    public numRatings?: number | null,
    public totalCancellations?: number | null,
    public totalCovers?: number | null,
    public totalNoShows?: number | null,
    public totalSpend?: number | null,
    public totalSpendLocal?: number | null,
    public totalSpendLocalperCover?: number | null,
    public totalSpendLocalPerVisit?: number | null,
    public totalSpendperCover?: number | null,
    public totalSpendPerVisit?: number | null,
    public totalVisit?: number | null,
    public venueMarketingOptin?: boolean | null,
    public venueMarketingOptints?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public client?: IClient | null,
  ) {
    this.venueMarketingOptin = this.venueMarketingOptin ?? false;
  }
}
