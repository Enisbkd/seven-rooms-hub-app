import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import MemberGroupService from './member-group.service';
import { type IMemberGroup } from '@/shared/model/member-group.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MemberGroup',
  setup() {
    const { t: t$ } = useI18n();
    const memberGroupService = inject('memberGroupService', () => new MemberGroupService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const memberGroups: Ref<IMemberGroup[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMemberGroups = async () => {
      isFetching.value = true;
      try {
        const res = await memberGroupService().retrieve();
        memberGroups.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMemberGroups();
    };

    onMounted(async () => {
      await retrieveMemberGroups();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMemberGroup) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMemberGroup = async () => {
      try {
        await memberGroupService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.memberGroup.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMemberGroups();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      memberGroups,
      handleSyncList,
      isFetching,
      retrieveMemberGroups,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMemberGroup,
      t$,
    };
  },
});
