import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ResTagService from './res-tag.service';
import { type IResTag } from '@/shared/model/res-tag.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResTag',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const resTagService = inject('resTagService', () => new ResTagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resTags: Ref<IResTag[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveResTags = async () => {
      isFetching.value = true;
      try {
        const res = await resTagService().retrieve();
        resTags.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveResTags();
    };

    onMounted(async () => {
      await retrieveResTags();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IResTag) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeResTag = async () => {
      try {
        await resTagService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.resTag.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveResTags();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      resTags,
      handleSyncList,
      isFetching,
      retrieveResTags,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeResTag,
      t$,
    };
  },
});
