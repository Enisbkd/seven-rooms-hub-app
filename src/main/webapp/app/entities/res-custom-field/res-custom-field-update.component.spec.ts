/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ResCustomFieldUpdate from './res-custom-field-update.vue';
import ResCustomFieldService from './res-custom-field.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';

type ResCustomFieldUpdateComponentType = InstanceType<typeof ResCustomFieldUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resCustomFieldSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResCustomFieldUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ResCustomField Management Update Component', () => {
    let comp: ResCustomFieldUpdateComponentType;
    let resCustomFieldServiceStub: SinonStubbedInstance<ResCustomFieldService>;

    beforeEach(() => {
      route = {};
      resCustomFieldServiceStub = sinon.createStubInstance<ResCustomFieldService>(ResCustomFieldService);
      resCustomFieldServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          resCustomFieldService: () => resCustomFieldServiceStub,
          reservationService: () =>
            sinon.createStubInstance<ReservationService>(ReservationService, {
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
        const wrapper = shallowMount(ResCustomFieldUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(ResCustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resCustomField = resCustomFieldSample;
        resCustomFieldServiceStub.update.resolves(resCustomFieldSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resCustomFieldServiceStub.update.calledWith(resCustomFieldSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resCustomFieldServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResCustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resCustomField = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resCustomFieldServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resCustomFieldServiceStub.find.resolves(resCustomFieldSample);
        resCustomFieldServiceStub.retrieve.resolves([resCustomFieldSample]);

        // WHEN
        route = {
          params: {
            resCustomFieldId: '' + resCustomFieldSample.id,
          },
        };
        const wrapper = shallowMount(ResCustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resCustomField).toMatchObject(resCustomFieldSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resCustomFieldServiceStub.find.resolves(resCustomFieldSample);
        const wrapper = shallowMount(ResCustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
