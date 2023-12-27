/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ClientPhotoService from './client-photo.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { ClientPhoto } from '@/shared/model/client-photo.model';

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
  describe('ClientPhoto Service', () => {
    let service: ClientPhotoService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ClientPhotoService();
      currentDate = new Date();
      elemDefault = new ClientPhoto(
        123,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
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

      it('should create a ClientPhoto', async () => {
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

      it('should not create a ClientPhoto', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ClientPhoto', async () => {
        const returnedFromService = Object.assign(
          {
            clientId: 'BBBBBB',
            large: 'BBBBBB',
            largeHeight: 1,
            largeWidth: 1,
            medium: 'BBBBBB',
            mediumHeight: 1,
            mediumWidth: 1,
            small: 'BBBBBB',
            smallHeight: 1,
            smallWidth: 1,
            raw: 'BBBBBB',
            cropx: 1,
            cropy: 1,
            cropHeight: 1,
            cropWidth: 1,
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

      it('should not update a ClientPhoto', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ClientPhoto', async () => {
        const patchObject = Object.assign(
          {
            large: 'BBBBBB',
            medium: 'BBBBBB',
            mediumHeight: 1,
            mediumWidth: 1,
            smallWidth: 1,
            raw: 'BBBBBB',
            cropx: 1,
            cropHeight: 1,
            cropWidth: 1,
            techLineage: 'BBBBBB',
            techCreatedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            techMapping: 'BBBBBB',
            techComment: 'BBBBBB',
          },
          new ClientPhoto(),
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

      it('should not partial update a ClientPhoto', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ClientPhoto', async () => {
        const returnedFromService = Object.assign(
          {
            clientId: 'BBBBBB',
            large: 'BBBBBB',
            largeHeight: 1,
            largeWidth: 1,
            medium: 'BBBBBB',
            mediumHeight: 1,
            mediumWidth: 1,
            small: 'BBBBBB',
            smallHeight: 1,
            smallWidth: 1,
            raw: 'BBBBBB',
            cropx: 1,
            cropy: 1,
            cropHeight: 1,
            cropWidth: 1,
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

      it('should not return a list of ClientPhoto', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ClientPhoto', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ClientPhoto', async () => {
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
