/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MemberGroupUpdate from './member-group-update.vue';
import MemberGroupService from './member-group.service';
import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';

type MemberGroupUpdateComponentType = InstanceType<typeof MemberGroupUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const memberGroupSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MemberGroupUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MemberGroup Management Update Component', () => {
    let comp: MemberGroupUpdateComponentType;
    let memberGroupServiceStub: SinonStubbedInstance<MemberGroupService>;

    beforeEach(() => {
      route = {};
      memberGroupServiceStub = sinon.createStubInstance<MemberGroupService>(MemberGroupService);
      memberGroupServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          memberGroupService: () => memberGroupServiceStub,
          clientService: () =>
            sinon.createStubInstance<ClientService>(ClientService, {
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
        const wrapper = shallowMount(MemberGroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.memberGroup = memberGroupSample;
        memberGroupServiceStub.update.resolves(memberGroupSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(memberGroupServiceStub.update.calledWith(memberGroupSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        memberGroupServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MemberGroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.memberGroup = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(memberGroupServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        memberGroupServiceStub.find.resolves(memberGroupSample);
        memberGroupServiceStub.retrieve.resolves([memberGroupSample]);

        // WHEN
        route = {
          params: {
            memberGroupId: '' + memberGroupSample.id,
          },
        };
        const wrapper = shallowMount(MemberGroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.memberGroup).toMatchObject(memberGroupSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        memberGroupServiceStub.find.resolves(memberGroupSample);
        const wrapper = shallowMount(MemberGroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
