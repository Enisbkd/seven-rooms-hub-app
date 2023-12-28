/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResPosTicketDetails from './res-pos-ticket-details.vue';
import ResPosTicketService from './res-pos-ticket.service';
import AlertService from '@/shared/alert/alert.service';

type ResPosTicketDetailsComponentType = InstanceType<typeof ResPosTicketDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resPosTicketSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ResPosTicket Management Detail Component', () => {
    let resPosTicketServiceStub: SinonStubbedInstance<ResPosTicketService>;
    let mountOptions: MountingOptions<ResPosTicketDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resPosTicketServiceStub = sinon.createStubInstance<ResPosTicketService>(ResPosTicketService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          resPosTicketService: () => resPosTicketServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resPosTicketServiceStub.find.resolves(resPosTicketSample);
        route = {
          params: {
            resPosTicketId: '' + 123,
          },
        };
        const wrapper = shallowMount(ResPosTicketDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resPosTicket).toMatchObject(resPosTicketSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resPosTicketServiceStub.find.resolves(resPosTicketSample);
        const wrapper = shallowMount(ResPosTicketDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
