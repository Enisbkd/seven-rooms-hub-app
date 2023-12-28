import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TableService from './table.service';
import { type ITable } from '@/shared/model/table.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableDetails',
  setup() {
    const tableService = inject('tableService', () => new TableService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const table: Ref<ITable> = ref({});

    const retrieveTable = async tableId => {
      try {
        const res = await tableService().find(tableId);
        table.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.tableId) {
      retrieveTable(route.params.tableId);
    }

    return {
      alertService,
      table,

      previousState,
      t$: useI18n().t,
    };
  },
});
