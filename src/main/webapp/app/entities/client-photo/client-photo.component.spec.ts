/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ClientPhoto from './client-photo.vue';
import ClientPhotoService from './client-photo.service';
import AlertService from '@/shared/alert/alert.service';

type ClientPhotoComponentType = InstanceType<typeof ClientPhoto>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ClientPhoto Management Component', () => {
    let clientPhotoServiceStub: SinonStubbedInstance<ClientPhotoService>;
    let mountOptions: MountingOptions<ClientPhotoComponentType>['global'];

    beforeEach(() => {
      clientPhotoServiceStub = sinon.createStubInstance<ClientPhotoService>(ClientPhotoService);
      clientPhotoServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          clientPhotoService: () => clientPhotoServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clientPhotoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ClientPhoto, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(clientPhotoServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.clientPhotos[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ClientPhotoComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ClientPhoto, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        clientPhotoServiceStub.retrieve.reset();
        clientPhotoServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        clientPhotoServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeClientPhoto();
        await comp.$nextTick(); // clear components

        // THEN
        expect(clientPhotoServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(clientPhotoServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
