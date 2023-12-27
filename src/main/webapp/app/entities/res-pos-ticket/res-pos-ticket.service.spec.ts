/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ResPosTicketService from './res-pos-ticket.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { ResPosTicket } from '@/shared/model/res-pos-ticket.model';

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
  describe('ResPosTicket Service', () => {
    let service: ResPosTicketService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ResPosTicketService();
      currentDate = new Date();
      elemDefault = new ResPosTicket(
        123,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
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

      it('should create a ResPosTicket', async () => {
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

      it('should not create a ResPosTicket', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ResPosTicket', async () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            adminFee: 1,
            code: 1,
            tableNo: 'BBBBBB',
            tax: 1,
            businessId: 1,
            localPosticketId: 'BBBBBB',
            employeeName: 'BBBBBB',
            total: 1,
            subtotal: 1,
            startTime: 'BBBBBB',
            serviceCharge: 1,
            endtime: 'BBBBBB',
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

      it('should not update a ResPosTicket', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ResPosTicket', async () => {
        const patchObject = Object.assign(
          {
            code: 1,
            tableNo: 'BBBBBB',
            tax: 1,
            businessId: 1,
            localPosticketId: 'BBBBBB',
            employeeName: 'BBBBBB',
            total: 1,
            startTime: 'BBBBBB',
            serviceCharge: 1,
            endtime: 'BBBBBB',
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techUpdatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          new ResPosTicket(),
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

      it('should not partial update a ResPosTicket', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ResPosTicket', async () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            adminFee: 1,
            code: 1,
            tableNo: 'BBBBBB',
            tax: 1,
            businessId: 1,
            localPosticketId: 'BBBBBB',
            employeeName: 'BBBBBB',
            total: 1,
            subtotal: 1,
            startTime: 'BBBBBB',
            serviceCharge: 1,
            endtime: 'BBBBBB',
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
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ResPosTicket', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ResPosTicket', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ResPosTicket', async () => {
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
