/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TableUpdate from './table-update.vue';
import TableService from './table.service';
import AlertService from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';

type TableUpdateComponentType = InstanceType<typeof TableUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const tableSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TableUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Table Management Update Component', () => {
    let comp: TableUpdateComponentType;
    let tableServiceStub: SinonStubbedInstance<TableService>;

    beforeEach(() => {
      route = {};
      tableServiceStub = sinon.createStubInstance<TableService>(TableService);
      tableServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          tableService: () => tableServiceStub,
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
        const wrapper = shallowMount(TableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.table = tableSample;
        tableServiceStub.update.resolves(tableSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tableServiceStub.update.calledWith(tableSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        tableServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.table = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tableServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        tableServiceStub.find.resolves(tableSample);
        tableServiceStub.retrieve.resolves([tableSample]);

        // WHEN
        route = {
          params: {
            tableId: '' + tableSample.id,
          },
        };
        const wrapper = shallowMount(TableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.table).toMatchObject(tableSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        tableServiceStub.find.resolves(tableSample);
        const wrapper = shallowMount(TableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
