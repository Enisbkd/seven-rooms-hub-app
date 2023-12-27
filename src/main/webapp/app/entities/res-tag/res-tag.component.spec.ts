/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ResTag from './res-tag.vue';
import ResTagService from './res-tag.service';
import AlertService from '@/shared/alert/alert.service';

type ResTagComponentType = InstanceType<typeof ResTag>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ResTag Management Component', () => {
    let resTagServiceStub: SinonStubbedInstance<ResTagService>;
    let mountOptions: MountingOptions<ResTagComponentType>['global'];

    beforeEach(() => {
      resTagServiceStub = sinon.createStubInstance<ResTagService>(ResTagService);
      resTagServiceStub.retrieve.resolves({ headers: {} });

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
          resTagService: () => resTagServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resTagServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ResTag, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resTagServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resTags[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ResTagComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ResTag, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resTagServiceStub.retrieve.reset();
        resTagServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resTagServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeResTag();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resTagServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resTagServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
