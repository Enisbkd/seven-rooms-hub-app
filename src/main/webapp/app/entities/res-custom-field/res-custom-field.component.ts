import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ResCustomFieldService from './res-custom-field.service';
import { type IResCustomField } from '@/shared/model/res-custom-field.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResCustomField',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const resCustomFieldService = inject('resCustomFieldService', () => new ResCustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resCustomFields: Ref<IResCustomField[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveResCustomFields = async () => {
      isFetching.value = true;
      try {
        const res = await resCustomFieldService().retrieve();
        resCustomFields.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveResCustomFields();
    };

    onMounted(async () => {
      await retrieveResCustomFields();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IResCustomField) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeResCustomField = async () => {
      try {
        await resCustomFieldService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.resCustomField.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveResCustomFields();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      resCustomFields,
      handleSyncList,
      isFetching,
      retrieveResCustomFields,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeResCustomField,
      t$,
    };
  },
});
