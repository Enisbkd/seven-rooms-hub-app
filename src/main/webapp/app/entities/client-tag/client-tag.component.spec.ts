/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ClientTag from './client-tag.vue';
import ClientTagService from './client-tag.service';
import AlertService from '@/shared/alert/alert.service';

type ClientTagComponentType = InstanceType<typeof ClientTag>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ClientTag Management Component', () => {
    let clientTagServiceStub: SinonStubbedInstance<ClientTagService>;
    let mountOptions: MountingOptions<ClientTagComponentType>['global'];

    beforeEach(() => {
      clientTagServiceStub = sinon.createStubInstance<ClientTagService>(ClientTagService);
      clientTagServiceStub.retrieve.resolves({ headers: {} });

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
          clientTagService: () => clientTagServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clientTagServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ClientTag, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(clientTagServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.clientTags[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ClientTagComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ClientTag, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        clientTagServiceStub.retrieve.reset();
        clientTagServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        clientTagServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeClientTag();
        await comp.$nextTick(); // clear components

        // THEN
        expect(clientTagServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(clientTagServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
