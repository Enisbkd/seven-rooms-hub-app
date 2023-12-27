import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CustomFieldService from './custom-field.service';
import { type ICustomField } from '@/shared/model/custom-field.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomField',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const customFieldService = inject('customFieldService', () => new CustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customFields: Ref<ICustomField[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCustomFields = async () => {
      isFetching.value = true;
      try {
        const res = await customFieldService().retrieve();
        customFields.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCustomFields();
    };

    onMounted(async () => {
      await retrieveCustomFields();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICustomField) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCustomField = async () => {
      try {
        await customFieldService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.customField.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCustomFields();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      customFields,
      handleSyncList,
      isFetching,
      retrieveCustomFields,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCustomField,
      t$,
    };
  },
});
