/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Table from './table.vue';
import TableService from './table.service';
import AlertService from '@/shared/alert/alert.service';

type TableComponentType = InstanceType<typeof Table>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Table Management Component', () => {
    let tableServiceStub: SinonStubbedInstance<TableService>;
    let mountOptions: MountingOptions<TableComponentType>['global'];

    beforeEach(() => {
      tableServiceStub = sinon.createStubInstance<TableService>(TableService);
      tableServiceStub.retrieve.resolves({ headers: {} });

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
          tableService: () => tableServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        tableServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Table, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(tableServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.tables[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: TableComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Table, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        tableServiceStub.retrieve.reset();
        tableServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        tableServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeTable();
        await comp.$nextTick(); // clear components

        // THEN
        expect(tableServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(tableServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
