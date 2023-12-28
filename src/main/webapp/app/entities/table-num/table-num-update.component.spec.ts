/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TableNumUpdate from './table-num-update.vue';
import TableNumService from './table-num.service';
import AlertService from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';

type TableNumUpdateComponentType = InstanceType<typeof TableNumUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const tableNumSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TableNumUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('TableNum Management Update Component', () => {
    let comp: TableNumUpdateComponentType;
    let tableNumServiceStub: SinonStubbedInstance<TableNumService>;

    beforeEach(() => {
      route = {};
      tableNumServiceStub = sinon.createStubInstance<TableNumService>(TableNumService);
      tableNumServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          tableNumService: () => tableNumServiceStub,
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

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(TableNumUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.tableNum = tableNumSample;
        tableNumServiceStub.update.resolves(tableNumSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tableNumServiceStub.update.calledWith(tableNumSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        tableNumServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TableNumUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.tableNum = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tableNumServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        tableNumServiceStub.find.resolves(tableNumSample);
        tableNumServiceStub.retrieve.resolves([tableNumSample]);

        // WHEN
        route = {
          params: {
            tableNumId: '' + tableNumSample.id,
          },
        };
        const wrapper = shallowMount(TableNumUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.tableNum).toMatchObject(tableNumSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        tableNumServiceStub.find.resolves(tableNumSample);
        const wrapper = shallowMount(TableNumUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
