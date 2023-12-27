/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MemberGroupDetails from './member-group-details.vue';
import MemberGroupService from './member-group.service';
import AlertService from '@/shared/alert/alert.service';

type MemberGroupDetailsComponentType = InstanceType<typeof MemberGroupDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const memberGroupSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MemberGroup Management Detail Component', () => {
    let memberGroupServiceStub: SinonStubbedInstance<MemberGroupService>;
    let mountOptions: MountingOptions<MemberGroupDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      memberGroupServiceStub = sinon.createStubInstance<MemberGroupService>(MemberGroupService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          memberGroupService: () => memberGroupServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        memberGroupServiceStub.find.resolves(memberGroupSample);
        route = {
          params: {
            memberGroupId: '' + 123,
          },
        };
        const wrapper = shallowMount(MemberGroupDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.memberGroup).toMatchObject(memberGroupSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        memberGroupServiceStub.find.resolves(memberGroupSample);
        const wrapper = shallowMount(MemberGroupDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
