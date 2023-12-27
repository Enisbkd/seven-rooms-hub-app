/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MemberGroup from './member-group.vue';
import MemberGroupService from './member-group.service';
import AlertService from '@/shared/alert/alert.service';

type MemberGroupComponentType = InstanceType<typeof MemberGroup>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MemberGroup Management Component', () => {
    let memberGroupServiceStub: SinonStubbedInstance<MemberGroupService>;
    let mountOptions: MountingOptions<MemberGroupComponentType>['global'];

    beforeEach(() => {
      memberGroupServiceStub = sinon.createStubInstance<MemberGroupService>(MemberGroupService);
      memberGroupServiceStub.retrieve.resolves({ headers: {} });

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
          memberGroupService: () => memberGroupServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        memberGroupServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MemberGroup, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(memberGroupServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.memberGroups[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: MemberGroupComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MemberGroup, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        memberGroupServiceStub.retrieve.reset();
        memberGroupServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        memberGroupServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMemberGroup();
        await comp.$nextTick(); // clear components

        // THEN
        expect(memberGroupServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(memberGroupServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
