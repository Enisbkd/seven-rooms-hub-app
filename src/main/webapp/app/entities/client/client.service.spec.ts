/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ClientService from './client.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Client } from '@/shared/model/client.model';

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
  describe('Client Service', () => {
    let service: ClientService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ClientService();
      currentDate = new Date();
      elemDefault = new Client(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
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

      it('should create a Client', async () => {
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

      it('should not create a Client', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Client', async () => {
        const returnedFromService = Object.assign(
          {
            clientId: 'BBBBBB',
            createdDate: 'BBBBBB',
            updatedDate: 'BBBBBB',
            deletedDate: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            gender: 'BBBBBB',
            salutation: 'BBBBBB',
            title: 'BBBBBB',
            birthdayDay: 1,
            birthdayMonth: 1,
            birthdayAltMonth: 1,
            anniversaryDay: 1,
            anniversaryMonth: 1,
            company: 'BBBBBB',
            email: 'BBBBBB',
            emailAlt: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            phoneNumberlocale: 'BBBBBB',
            phoneNumberalt: 'BBBBBB',
            phoneNumberaltlocale: 'BBBBBB',
            address: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            isContactPrivate: true,
            isOnetimeGuest: true,
            status: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            marketingOptin: true,
            marketingOptints: 'BBBBBB',
            marketingOptOutts: 'BBBBBB',
            hasBillingProfile: true,
            notes: 'BBBBBB',
            privateNotes: 'BBBBBB',
            tags: 'BBBBBB',
            totalVisits: 1,
            totalCovers: 1,
            totalCancellations: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalSpendPerCover: 1,
            totalspendPerVisit: 1,
            avgRating: 1,
            referenceCode: 'BBBBBB',
            externalUserId: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            birthdayAltDay: 1,
            userId: 'BBBBBB',
            userName: 'BBBBBB',
            totalOrderCount: 1,
            preferredLanguageCode: 'BBBBBB',
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

      it('should not update a Client', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Client', async () => {
        const patchObject = Object.assign(
          {
            clientId: 'BBBBBB',
            createdDate: 'BBBBBB',
            firstname: 'BBBBBB',
            gender: 'BBBBBB',
            salutation: 'BBBBBB',
            title: 'BBBBBB',
            birthdayAltMonth: 1,
            anniversaryDay: 1,
            anniversaryMonth: 1,
            email: 'BBBBBB',
            emailAlt: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            phoneNumberalt: 'BBBBBB',
            phoneNumberaltlocale: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            status: 'BBBBBB',
            loyaltyTier: 'BBBBBB',
            marketingOptin: true,
            marketingOptOutts: 'BBBBBB',
            notes: 'BBBBBB',
            privateNotes: 'BBBBBB',
            totalVisits: 1,
            totalCovers: 1,
            totalCancellations: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalspendPerVisit: 1,
            avgRating: 1,
            externalUserId: 'BBBBBB',
            userName: 'BBBBBB',
            preferredLanguageCode: 'BBBBBB',
            techLineage: 'BBBBBB',
            techUpdatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techComment: 'BBBBBB',
          },
          new Client(),
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

      it('should not partial update a Client', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Client', async () => {
        const returnedFromService = Object.assign(
          {
            clientId: 'BBBBBB',
            createdDate: 'BBBBBB',
            updatedDate: 'BBBBBB',
            deletedDate: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            gender: 'BBBBBB',
            salutation: 'BBBBBB',
            title: 'BBBBBB',
            birthdayDay: 1,
            birthdayMonth: 1,
            birthdayAltMonth: 1,
            anniversaryDay: 1,
            anniversaryMonth: 1,
            company: 'BBBBBB',
            email: 'BBBBBB',
            emailAlt: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            phoneNumberlocale: 'BBBBBB',
            phoneNumberalt: 'BBBBBB',
            phoneNumberaltlocale: 'BBBBBB',
            address: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            isContactPrivate: true,
            isOnetimeGuest: true,
            status: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            marketingOptin: true,
            marketingOptints: 'BBBBBB',
            marketingOptOutts: 'BBBBBB',
            hasBillingProfile: true,
            notes: 'BBBBBB',
            privateNotes: 'BBBBBB',
            tags: 'BBBBBB',
            totalVisits: 1,
            totalCovers: 1,
            totalCancellations: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalSpendPerCover: 1,
            totalspendPerVisit: 1,
            avgRating: 1,
            referenceCode: 'BBBBBB',
            externalUserId: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            birthdayAltDay: 1,
            userId: 'BBBBBB',
            userName: 'BBBBBB',
            totalOrderCount: 1,
            preferredLanguageCode: 'BBBBBB',
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

      it('should not return a list of Client', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Client', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Client', async () => {
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
