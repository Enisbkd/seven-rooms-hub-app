/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResPosticketsItemDetails from './res-postickets-item-details.vue';
import ResPosticketsItemService from './res-postickets-item.service';
import AlertService from '@/shared/alert/alert.service';

type ResPosticketsItemDetailsComponentType = InstanceType<typeof ResPosticketsItemDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resPosticketsItemSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ResPosticketsItem Management Detail Component', () => {
    let resPosticketsItemServiceStub: SinonStubbedInstance<ResPosticketsItemService>;
    let mountOptions: MountingOptions<ResPosticketsItemDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resPosticketsItemServiceStub = sinon.createStubInstance<ResPosticketsItemService>(ResPosticketsItemService);

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
          resPosticketsItemService: () => resPosticketsItemServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resPosticketsItemServiceStub.find.resolves(resPosticketsItemSample);
        route = {
          params: {
            resPosticketsItemId: '' + 123,
          },
        };
        const wrapper = shallowMount(ResPosticketsItemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resPosticketsItem).toMatchObject(resPosticketsItemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resPosticketsItemServiceStub.find.resolves(resPosticketsItemSample);
        const wrapper = shallowMount(ResPosticketsItemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
