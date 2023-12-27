/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CustomField from './custom-field.vue';
import CustomFieldService from './custom-field.service';
import AlertService from '@/shared/alert/alert.service';

type CustomFieldComponentType = InstanceType<typeof CustomField>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CustomField Management Component', () => {
    let customFieldServiceStub: SinonStubbedInstance<CustomFieldService>;
    let mountOptions: MountingOptions<CustomFieldComponentType>['global'];

    beforeEach(() => {
      customFieldServiceStub = sinon.createStubInstance<CustomFieldService>(CustomFieldService);
      customFieldServiceStub.retrieve.resolves({ headers: {} });

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
          customFieldService: () => customFieldServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        customFieldServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CustomField, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(customFieldServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.customFields[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CustomFieldComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CustomField, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        customFieldServiceStub.retrieve.reset();
        customFieldServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        customFieldServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCustomField();
        await comp.$nextTick(); // clear components

        // THEN
        expect(customFieldServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(customFieldServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
