/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ReservationDetails from './reservation-details.vue';
import ReservationService from './reservation.service';
import AlertService from '@/shared/alert/alert.service';

type ReservationDetailsComponentType = InstanceType<typeof ReservationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const reservationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Reservation Management Detail Component', () => {
    let reservationServiceStub: SinonStubbedInstance<ReservationService>;
    let mountOptions: MountingOptions<ReservationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      reservationServiceStub = sinon.createStubInstance<ReservationService>(ReservationService);

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
          reservationService: () => reservationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        reservationServiceStub.find.resolves(reservationSample);
        route = {
          params: {
            reservationId: '' + 123,
          },
        };
        const wrapper = shallowMount(ReservationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.reservation).toMatchObject(reservationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        reservationServiceStub.find.resolves(reservationSample);
        const wrapper = shallowMount(ReservationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
