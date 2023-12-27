/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ClientTagUpdate from './client-tag-update.vue';
import ClientTagService from './client-tag.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';

type ClientTagUpdateComponentType = InstanceType<typeof ClientTagUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clientTagSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ClientTagUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ClientTag Management Update Component', () => {
    let comp: ClientTagUpdateComponentType;
    let clientTagServiceStub: SinonStubbedInstance<ClientTagService>;

    beforeEach(() => {
      route = {};
      clientTagServiceStub = sinon.createStubInstance<ClientTagService>(ClientTagService);
      clientTagServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          clientTagService: () => clientTagServiceStub,
          clientService: () =>
            sinon.createStubInstance<ClientService>(ClientService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(ClientTagUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(ClientTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clientTag = clientTagSample;
        clientTagServiceStub.update.resolves(clientTagSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clientTagServiceStub.update.calledWith(clientTagSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        clientTagServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ClientTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clientTag = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clientTagServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        clientTagServiceStub.find.resolves(clientTagSample);
        clientTagServiceStub.retrieve.resolves([clientTagSample]);

        // WHEN
        route = {
          params: {
            clientTagId: '' + clientTagSample.id,
          },
        };
        const wrapper = shallowMount(ClientTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.clientTag).toMatchObject(clientTagSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clientTagServiceStub.find.resolves(clientTagSample);
        const wrapper = shallowMount(ClientTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
