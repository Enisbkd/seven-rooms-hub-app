/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import TableNumber from './table-number.vue';
import TableNumberService from './table-number.service';
import AlertService from '@/shared/alert/alert.service';

type TableNumberComponentType = InstanceType<typeof TableNumber>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('TableNumber Management Component', () => {
    let tableNumberServiceStub: SinonStubbedInstance<TableNumberService>;
    let mountOptions: MountingOptions<TableNumberComponentType>['global'];

    beforeEach(() => {
      tableNumberServiceStub = sinon.createStubInstance<TableNumberService>(TableNumberService);
      tableNumberServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          tableNumberService: () => tableNumberServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        tableNumberServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(TableNumber, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(tableNumberServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.tableNumbers[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: TableNumberComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(TableNumber, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        tableNumberServiceStub.retrieve.reset();
        tableNumberServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        tableNumberServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeTableNumber();
        await comp.$nextTick(); // clear components

        // THEN
        expect(tableNumberServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(tableNumberServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
