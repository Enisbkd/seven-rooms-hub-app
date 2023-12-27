import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ClientTagService from './client-tag.service';
import { type IClientTag } from '@/shared/model/client-tag.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientTag',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const clientTagService = inject('clientTagService', () => new ClientTagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientTags: Ref<IClientTag[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveClientTags = async () => {
      isFetching.value = true;
      try {
        const res = await clientTagService().retrieve();
        clientTags.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveClientTags();
    };

    onMounted(async () => {
      await retrieveClientTags();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IClientTag) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeClientTag = async () => {
      try {
        await clientTagService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.clientTag.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveClientTags();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      clientTags,
      handleSyncList,
      isFetching,
      retrieveClientTags,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeClientTag,
      t$,
    };
  },
});
