/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ClientPhotoUpdate from './client-photo-update.vue';
import ClientPhotoService from './client-photo.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type ClientPhotoUpdateComponentType = InstanceType<typeof ClientPhotoUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clientPhotoSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ClientPhotoUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ClientPhoto Management Update Component', () => {
    let comp: ClientPhotoUpdateComponentType;
    let clientPhotoServiceStub: SinonStubbedInstance<ClientPhotoService>;

    beforeEach(() => {
      route = {};
      clientPhotoServiceStub = sinon.createStubInstance<ClientPhotoService>(ClientPhotoService);
      clientPhotoServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          clientPhotoService: () => clientPhotoServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(ClientPhotoUpdate, { global: mountOptions });
        comp = wrapper.vm;
      });
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ClientPhotoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clientPhoto = clientPhotoSample;
        clientPhotoServiceStub.update.resolves(clientPhotoSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clientPhotoServiceStub.update.calledWith(clientPhotoSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        clientPhotoServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ClientPhotoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clientPhoto = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clientPhotoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        clientPhotoServiceStub.find.resolves(clientPhotoSample);
        clientPhotoServiceStub.retrieve.resolves([clientPhotoSample]);

        // WHEN
        route = {
          params: {
            clientPhotoId: '' + clientPhotoSample.id,
          },
        };
        const wrapper = shallowMount(ClientPhotoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.clientPhoto).toMatchObject(clientPhotoSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clientPhotoServiceStub.find.resolves(clientPhotoSample);
        const wrapper = shallowMount(ClientPhotoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
