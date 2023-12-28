/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TableDetails from './table-details.vue';
import TableService from './table.service';
import AlertService from '@/shared/alert/alert.service';

type TableDetailsComponentType = InstanceType<typeof TableDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const tableSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Table Management Detail Component', () => {
    let tableServiceStub: SinonStubbedInstance<TableService>;
    let mountOptions: MountingOptions<TableDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      tableServiceStub = sinon.createStubInstance<TableService>(TableService);

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
          tableService: () => tableServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        tableServiceStub.find.resolves(tableSample);
        route = {
          params: {
            tableId: '' + 123,
          },
        };
        const wrapper = shallowMount(TableDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.table).toMatchObject(tableSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        tableServiceStub.find.resolves(tableSample);
        const wrapper = shallowMount(TableDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
