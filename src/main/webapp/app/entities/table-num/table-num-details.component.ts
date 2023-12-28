import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TableNumService from './table-num.service';
import { type ITableNum } from '@/shared/model/table-num.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableNumDetails',
  setup() {
    const tableNumService = inject('tableNumService', () => new TableNumService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const tableNum: Ref<ITableNum> = ref({});

    const retrieveTableNum = async tableNumId => {
      try {
        const res = await tableNumService().find(tableNumId);
        tableNum.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.tableNumId) {
      retrieveTableNum(route.params.tableNumId);
    }

    return {
      alertService,
      tableNum,

      previousState,
      t$: useI18n().t,
    };
  },
});
