/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ClientVenueStatsService from './client-venue-stats.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { ClientVenueStats } from '@/shared/model/client-venue-stats.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('ClientVenueStats Service', () => {
    let service: ClientVenueStatsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ClientVenueStatsService();
      currentDate = new Date();
      elemDefault = new ClientVenueStats(
        123,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techUpdatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a ClientVenueStats', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techUpdatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            techCreatedDate: currentDate,
            techUpdatedDate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ClientVenueStats', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ClientVenueStats', async () => {
        const returnedFromService = Object.assign(
          {
            venueId: 'BBBBBB',
            avgRating: 1,
            bookedByNames: 'BBBBBB',
            lastVisitDate: 'BBBBBB',
            numRatings: 1,
            totalCancellations: 1,
            totalCovers: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalSpendLocal: 1,
            totalSpendLocalperCover: 1,
            totalSpendLocalPerVisit: 1,
            totalSpendperCover: 1,
            totalSpendPerVisit: 1,
            totalVisit: 1,
            venueMarketingOptin: true,
            venueMarketingOptints: 'BBBBBB',
            techLineage: 'BBBBBB',
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techUpdatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techMapping: 'BBBBBB',
            techComment: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            techCreatedDate: currentDate,
            techUpdatedDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ClientVenueStats', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ClientVenueStats', async () => {
        const patchObject = Object.assign(
          {
            venueId: 'BBBBBB',
            avgRating: 1,
            bookedByNames: 'BBBBBB',
            lastVisitDate: 'BBBBBB',
            totalCancellations: 1,
            totalCovers: 1,
            totalNoShows: 1,
            totalSpendLocal: 1,
            totalVisit: 1,
            venueMarketingOptin: true,
            venueMarketingOptints: 'BBBBBB',
            techLineage: 'BBBBBB',
            techMapping: 'BBBBBB',
            techComment: 'BBBBBB',
          },
          new ClientVenueStats(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            techCreatedDate: currentDate,
            techUpdatedDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ClientVenueStats', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ClientVenueStats', async () => {
        const returnedFromService = Object.assign(
          {
            venueId: 'BBBBBB',
            avgRating: 1,
            bookedByNames: 'BBBBBB',
            lastVisitDate: 'BBBBBB',
            numRatings: 1,
            totalCancellations: 1,
            totalCovers: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalSpendLocal: 1,
            totalSpendLocalperCover: 1,
            totalSpendLocalPerVisit: 1,
            totalSpendperCover: 1,
            totalSpendPerVisit: 1,
            totalVisit: 1,
            venueMarketingOptin: true,
            venueMarketingOptints: 'BBBBBB',
            techLineage: 'BBBBBB',
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techUpdatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techMapping: 'BBBBBB',
            techComment: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            techCreatedDate: currentDate,
            techUpdatedDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ClientVenueStats', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ClientVenueStats', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ClientVenueStats', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
