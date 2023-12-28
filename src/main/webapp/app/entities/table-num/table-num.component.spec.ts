/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import TableNum from './table-num.vue';
import TableNumService from './table-num.service';
import AlertService from '@/shared/alert/alert.service';

type TableNumComponentType = InstanceType<typeof TableNum>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('TableNum Management Component', () => {
    let tableNumServiceStub: SinonStubbedInstance<TableNumService>;
    let mountOptions: MountingOptions<TableNumComponentType>['global'];

    beforeEach(() => {
      tableNumServiceStub = sinon.createStubInstance<TableNumService>(TableNumService);
      tableNumServiceStub.retrieve.resolves({ headers: {} });

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
          tableNumService: () => tableNumServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        tableNumServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(TableNum, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(tableNumServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.tableNums[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: TableNumComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(TableNum, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        tableNumServiceStub.retrieve.reset();
        tableNumServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        tableNumServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeTableNum();
        await comp.$nextTick(); // clear components

        // THEN
        expect(tableNumServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(tableNumServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
