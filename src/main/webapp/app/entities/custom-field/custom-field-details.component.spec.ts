/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CustomFieldDetails from './custom-field-details.vue';
import CustomFieldService from './custom-field.service';
import AlertService from '@/shared/alert/alert.service';

type CustomFieldDetailsComponentType = InstanceType<typeof CustomFieldDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const customFieldSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CustomField Management Detail Component', () => {
    let customFieldServiceStub: SinonStubbedInstance<CustomFieldService>;
    let mountOptions: MountingOptions<CustomFieldDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      customFieldServiceStub = sinon.createStubInstance<CustomFieldService>(CustomFieldService);

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
          customFieldService: () => customFieldServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        customFieldServiceStub.find.resolves(customFieldSample);
        route = {
          params: {
            customFieldId: '' + 123,
          },
        };
        const wrapper = shallowMount(CustomFieldDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.customField).toMatchObject(customFieldSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        customFieldServiceStub.find.resolves(customFieldSample);
        const wrapper = shallowMount(CustomFieldDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
