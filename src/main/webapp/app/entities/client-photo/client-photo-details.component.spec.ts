/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ClientPhotoDetails from './client-photo-details.vue';
import ClientPhotoService from './client-photo.service';
import AlertService from '@/shared/alert/alert.service';

type ClientPhotoDetailsComponentType = InstanceType<typeof ClientPhotoDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clientPhotoSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ClientPhoto Management Detail Component', () => {
    let clientPhotoServiceStub: SinonStubbedInstance<ClientPhotoService>;
    let mountOptions: MountingOptions<ClientPhotoDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      clientPhotoServiceStub = sinon.createStubInstance<ClientPhotoService>(ClientPhotoService);

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
          clientPhotoService: () => clientPhotoServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clientPhotoServiceStub.find.resolves(clientPhotoSample);
        route = {
          params: {
            clientPhotoId: '' + 123,
          },
        };
        const wrapper = shallowMount(ClientPhotoDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.clientPhoto).toMatchObject(clientPhotoSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clientPhotoServiceStub.find.resolves(clientPhotoSample);
        const wrapper = shallowMount(ClientPhotoDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
