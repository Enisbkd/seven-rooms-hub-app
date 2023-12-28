/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TableNumDetails from './table-num-details.vue';
import TableNumService from './table-num.service';
import AlertService from '@/shared/alert/alert.service';

type TableNumDetailsComponentType = InstanceType<typeof TableNumDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const tableNumSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('TableNum Management Detail Component', () => {
    let tableNumServiceStub: SinonStubbedInstance<TableNumService>;
    let mountOptions: MountingOptions<TableNumDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      tableNumServiceStub = sinon.createStubInstance<TableNumService>(TableNumService);

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
          tableNumService: () => tableNumServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        tableNumServiceStub.find.resolves(tableNumSample);
        route = {
          params: {
            tableNumId: '' + 123,
          },
        };
        const wrapper = shallowMount(TableNumDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.tableNum).toMatchObject(tableNumSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        tableNumServiceStub.find.resolves(tableNumSample);
        const wrapper = shallowMount(TableNumDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
