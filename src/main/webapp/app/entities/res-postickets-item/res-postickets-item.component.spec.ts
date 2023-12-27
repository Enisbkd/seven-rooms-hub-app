/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ResPosticketsItem from './res-postickets-item.vue';
import ResPosticketsItemService from './res-postickets-item.service';
import AlertService from '@/shared/alert/alert.service';

type ResPosticketsItemComponentType = InstanceType<typeof ResPosticketsItem>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ResPosticketsItem Management Component', () => {
    let resPosticketsItemServiceStub: SinonStubbedInstance<ResPosticketsItemService>;
    let mountOptions: MountingOptions<ResPosticketsItemComponentType>['global'];

    beforeEach(() => {
      resPosticketsItemServiceStub = sinon.createStubInstance<ResPosticketsItemService>(ResPosticketsItemService);
      resPosticketsItemServiceStub.retrieve.resolves({ headers: {} });

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
          resPosticketsItemService: () => resPosticketsItemServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resPosticketsItemServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ResPosticketsItem, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resPosticketsItemServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resPosticketsItems[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ResPosticketsItemComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ResPosticketsItem, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resPosticketsItemServiceStub.retrieve.reset();
        resPosticketsItemServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resPosticketsItemServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeResPosticketsItem();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resPosticketsItemServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resPosticketsItemServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
