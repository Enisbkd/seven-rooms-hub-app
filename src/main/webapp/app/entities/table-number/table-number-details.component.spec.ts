/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TableNumberDetails from './table-number-details.vue';
import TableNumberService from './table-number.service';
import AlertService from '@/shared/alert/alert.service';

type TableNumberDetailsComponentType = InstanceType<typeof TableNumberDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const tableNumberSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('TableNumber Management Detail Component', () => {
    let tableNumberServiceStub: SinonStubbedInstance<TableNumberService>;
    let mountOptions: MountingOptions<TableNumberDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      tableNumberServiceStub = sinon.createStubInstance<TableNumberService>(TableNumberService);

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
          tableNumberService: () => tableNumberServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        tableNumberServiceStub.find.resolves(tableNumberSample);
        route = {
          params: {
            tableNumberId: '' + 123,
          },
        };
        const wrapper = shallowMount(TableNumberDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.tableNumber).toMatchObject(tableNumberSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        tableNumberServiceStub.find.resolves(tableNumberSample);
        const wrapper = shallowMount(TableNumberDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
