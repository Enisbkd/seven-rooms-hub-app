/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResTagDetails from './res-tag-details.vue';
import ResTagService from './res-tag.service';
import AlertService from '@/shared/alert/alert.service';

type ResTagDetailsComponentType = InstanceType<typeof ResTagDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resTagSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ResTag Management Detail Component', () => {
    let resTagServiceStub: SinonStubbedInstance<ResTagService>;
    let mountOptions: MountingOptions<ResTagDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resTagServiceStub = sinon.createStubInstance<ResTagService>(ResTagService);

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
          resTagService: () => resTagServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resTagServiceStub.find.resolves(resTagSample);
        route = {
          params: {
            resTagId: '' + 123,
          },
        };
        const wrapper = shallowMount(ResTagDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resTag).toMatchObject(resTagSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resTagServiceStub.find.resolves(resTagSample);
        const wrapper = shallowMount(ResTagDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
