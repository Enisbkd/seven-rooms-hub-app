import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TableService from './table.service';
import { type ITable } from '@/shared/model/table.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Table',
  setup() {
    const { t: t$ } = useI18n();
    const tableService = inject('tableService', () => new TableService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const tables: Ref<ITable[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTables = async () => {
      isFetching.value = true;
      try {
        const res = await tableService().retrieve();
        tables.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTables();
    };

    onMounted(async () => {
      await retrieveTables();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITable) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTable = async () => {
      try {
        await tableService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.table.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTables();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      tables,
      handleSyncList,
      isFetching,
      retrieveTables,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTable,
      t$,
    };
  },
});
