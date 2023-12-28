import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TableNumService from './table-num.service';
import { type ITableNum } from '@/shared/model/table-num.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableNum',
  setup() {
    const { t: t$ } = useI18n();
    const tableNumService = inject('tableNumService', () => new TableNumService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const tableNums: Ref<ITableNum[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTableNums = async () => {
      isFetching.value = true;
      try {
        const res = await tableNumService().retrieve();
        tableNums.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTableNums();
    };

    onMounted(async () => {
      await retrieveTableNums();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITableNum) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTableNum = async () => {
      try {
        await tableNumService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.tableNum.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTableNums();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      tableNums,
      handleSyncList,
      isFetching,
      retrieveTableNums,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTableNum,
      t$,
    };
  },
});
