import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MemberGroupService from './member-group.service';
import { type IMemberGroup } from '@/shared/model/member-group.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MemberGroupDetails',
  setup() {
    const memberGroupService = inject('memberGroupService', () => new MemberGroupService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const memberGroup: Ref<IMemberGroup> = ref({});

    const retrieveMemberGroup = async memberGroupId => {
      try {
        const res = await memberGroupService().find(memberGroupId);
        memberGroup.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.memberGroupId) {
      retrieveMemberGroup(route.params.memberGroupId);
    }

    return {
      alertService,
      memberGroup,

      previousState,
      t$: useI18n().t,
    };
  },
});
