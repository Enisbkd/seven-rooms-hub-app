/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ClientTagService from './client-tag.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { ClientTag } from '@/shared/model/client-tag.model';

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
  describe('ClientTag Service', () => {
    let service: ClientTagService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ClientTagService();
      currentDate = new Date();
      elemDefault = new ClientTag(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a ClientTag', async () => {
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

      it('should not create a ClientTag', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ClientTag', async () => {
        const returnedFromService = Object.assign(
          {
            tag: 'BBBBBB',
            tagDisplay: 'BBBBBB',
            group: 'BBBBBB',
            groupDisplay: 'BBBBBB',
            color: 'BBBBBB',
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

      it('should not update a ClientTag', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ClientTag', async () => {
        const patchObject = Object.assign(
          {
            group: 'BBBBBB',
            color: 'BBBBBB',
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techMapping: 'BBBBBB',
          },
          new ClientTag(),
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

      it('should not partial update a ClientTag', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ClientTag', async () => {
        const returnedFromService = Object.assign(
          {
            tag: 'BBBBBB',
            tagDisplay: 'BBBBBB',
            group: 'BBBBBB',
            groupDisplay: 'BBBBBB',
            color: 'BBBBBB',
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

      it('should not return a list of ClientTag', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ClientTag', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ClientTag', async () => {
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
