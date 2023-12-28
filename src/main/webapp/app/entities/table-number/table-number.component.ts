import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TableNumberService from './table-number.service';
import { type ITableNumber } from '@/shared/model/table-number.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableNumber',
  setup() {
    const { t: t$ } = useI18n();
    const tableNumberService = inject('tableNumberService', () => new TableNumberService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const tableNumbers: Ref<ITableNumber[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTableNumbers = async () => {
      isFetching.value = true;
      try {
        const res = await tableNumberService().retrieve();
        tableNumbers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTableNumbers();
    };

    onMounted(async () => {
      await retrieveTableNumbers();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITableNumber) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTableNumber = async () => {
      try {
        await tableNumberService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.tableNumber.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTableNumbers();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      tableNumbers,
      handleSyncList,
      isFetching,
      retrieveTableNumbers,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTableNumber,
      t$,
    };
  },
});
