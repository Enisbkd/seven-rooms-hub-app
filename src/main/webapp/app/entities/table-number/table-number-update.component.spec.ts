/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TableNumberUpdate from './table-number-update.vue';
import TableNumberService from './table-number.service';
import AlertService from '@/shared/alert/alert.service';

type TableNumberUpdateComponentType = InstanceType<typeof TableNumberUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const tableNumberSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TableNumberUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('TableNumber Management Update Component', () => {
    let comp: TableNumberUpdateComponentType;
    let tableNumberServiceStub: SinonStubbedInstance<TableNumberService>;

    beforeEach(() => {
      route = {};
      tableNumberServiceStub = sinon.createStubInstance<TableNumberService>(TableNumberService);
      tableNumberServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          tableNumberService: () => tableNumberServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(TableNumberUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.tableNumber = tableNumberSample;
        tableNumberServiceStub.update.resolves(tableNumberSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tableNumberServiceStub.update.calledWith(tableNumberSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        tableNumberServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TableNumberUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.tableNumber = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tableNumberServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        tableNumberServiceStub.find.resolves(tableNumberSample);
        tableNumberServiceStub.retrieve.resolves([tableNumberSample]);

        // WHEN
        route = {
          params: {
            tableNumberId: '' + tableNumberSample.id,
          },
        };
        const wrapper = shallowMount(TableNumberUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.tableNumber).toMatchObject(tableNumberSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        tableNumberServiceStub.find.resolves(tableNumberSample);
        const wrapper = shallowMount(TableNumberUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
