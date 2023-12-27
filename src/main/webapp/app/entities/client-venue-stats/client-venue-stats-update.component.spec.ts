/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ClientVenueStatsUpdate from './client-venue-stats-update.vue';
import ClientVenueStatsService from './client-venue-stats.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type ClientVenueStatsUpdateComponentType = InstanceType<typeof ClientVenueStatsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clientVenueStatsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ClientVenueStatsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ClientVenueStats Management Update Component', () => {
    let comp: ClientVenueStatsUpdateComponentType;
    let clientVenueStatsServiceStub: SinonStubbedInstance<ClientVenueStatsService>;

    beforeEach(() => {
      route = {};
      clientVenueStatsServiceStub = sinon.createStubInstance<ClientVenueStatsService>(ClientVenueStatsService);
      clientVenueStatsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          clientVenueStatsService: () => clientVenueStatsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(ClientVenueStatsUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(ClientVenueStatsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clientVenueStats = clientVenueStatsSample;
        clientVenueStatsServiceStub.update.resolves(clientVenueStatsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clientVenueStatsServiceStub.update.calledWith(clientVenueStatsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        clientVenueStatsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ClientVenueStatsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clientVenueStats = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clientVenueStatsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        clientVenueStatsServiceStub.find.resolves(clientVenueStatsSample);
        clientVenueStatsServiceStub.retrieve.resolves([clientVenueStatsSample]);

        // WHEN
        route = {
          params: {
            clientVenueStatsId: '' + clientVenueStatsSample.id,
          },
        };
        const wrapper = shallowMount(ClientVenueStatsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.clientVenueStats).toMatchObject(clientVenueStatsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clientVenueStatsServiceStub.find.resolves(clientVenueStatsSample);
        const wrapper = shallowMount(ClientVenueStatsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
