/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ClientTagDetails from './client-tag-details.vue';
import ClientTagService from './client-tag.service';
import AlertService from '@/shared/alert/alert.service';

type ClientTagDetailsComponentType = InstanceType<typeof ClientTagDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clientTagSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ClientTag Management Detail Component', () => {
    let clientTagServiceStub: SinonStubbedInstance<ClientTagService>;
    let mountOptions: MountingOptions<ClientTagDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      clientTagServiceStub = sinon.createStubInstance<ClientTagService>(ClientTagService);

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
          clientTagService: () => clientTagServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clientTagServiceStub.find.resolves(clientTagSample);
        route = {
          params: {
            clientTagId: '' + 123,
          },
        };
        const wrapper = shallowMount(ClientTagDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.clientTag).toMatchObject(clientTagSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clientTagServiceStub.find.resolves(clientTagSample);
        const wrapper = shallowMount(ClientTagDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
