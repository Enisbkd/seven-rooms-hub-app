/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ClientVenueStatsDetails from './client-venue-stats-details.vue';
import ClientVenueStatsService from './client-venue-stats.service';
import AlertService from '@/shared/alert/alert.service';

type ClientVenueStatsDetailsComponentType = InstanceType<typeof ClientVenueStatsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clientVenueStatsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ClientVenueStats Management Detail Component', () => {
    let clientVenueStatsServiceStub: SinonStubbedInstance<ClientVenueStatsService>;
    let mountOptions: MountingOptions<ClientVenueStatsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      clientVenueStatsServiceStub = sinon.createStubInstance<ClientVenueStatsService>(ClientVenueStatsService);

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
          clientVenueStatsService: () => clientVenueStatsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clientVenueStatsServiceStub.find.resolves(clientVenueStatsSample);
        route = {
          params: {
            clientVenueStatsId: '' + 123,
          },
        };
        const wrapper = shallowMount(ClientVenueStatsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.clientVenueStats).toMatchObject(clientVenueStatsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clientVenueStatsServiceStub.find.resolves(clientVenueStatsSample);
        const wrapper = shallowMount(ClientVenueStatsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
