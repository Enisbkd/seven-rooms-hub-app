/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ResPosTicket from './res-pos-ticket.vue';
import ResPosTicketService from './res-pos-ticket.service';
import AlertService from '@/shared/alert/alert.service';

type ResPosTicketComponentType = InstanceType<typeof ResPosTicket>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ResPosTicket Management Component', () => {
    let resPosTicketServiceStub: SinonStubbedInstance<ResPosTicketService>;
    let mountOptions: MountingOptions<ResPosTicketComponentType>['global'];

    beforeEach(() => {
      resPosTicketServiceStub = sinon.createStubInstance<ResPosTicketService>(ResPosTicketService);
      resPosTicketServiceStub.retrieve.resolves({ headers: {} });

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
          resPosTicketService: () => resPosTicketServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resPosTicketServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ResPosTicket, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resPosTicketServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resPosTickets[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ResPosTicketComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ResPosTicket, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resPosTicketServiceStub.retrieve.reset();
        resPosTicketServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resPosTicketServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeResPosTicket();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resPosTicketServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resPosTicketServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
