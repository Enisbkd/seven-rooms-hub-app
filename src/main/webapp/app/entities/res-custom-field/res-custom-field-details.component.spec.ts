/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResCustomFieldDetails from './res-custom-field-details.vue';
import ResCustomFieldService from './res-custom-field.service';
import AlertService from '@/shared/alert/alert.service';

type ResCustomFieldDetailsComponentType = InstanceType<typeof ResCustomFieldDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resCustomFieldSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ResCustomField Management Detail Component', () => {
    let resCustomFieldServiceStub: SinonStubbedInstance<ResCustomFieldService>;
    let mountOptions: MountingOptions<ResCustomFieldDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resCustomFieldServiceStub = sinon.createStubInstance<ResCustomFieldService>(ResCustomFieldService);

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
          resCustomFieldService: () => resCustomFieldServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resCustomFieldServiceStub.find.resolves(resCustomFieldSample);
        route = {
          params: {
            resCustomFieldId: '' + 123,
          },
        };
        const wrapper = shallowMount(ResCustomFieldDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resCustomField).toMatchObject(resCustomFieldSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resCustomFieldServiceStub.find.resolves(resCustomFieldSample);
        const wrapper = shallowMount(ResCustomFieldDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
