import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TableNumberService from './table-number.service';
import { type ITableNumber } from '@/shared/model/table-number.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableNumberDetails',
  setup() {
    const tableNumberService = inject('tableNumberService', () => new TableNumberService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const tableNumber: Ref<ITableNumber> = ref({});

    const retrieveTableNumber = async tableNumberId => {
      try {
        const res = await tableNumberService().find(tableNumberId);
        tableNumber.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.tableNumberId) {
      retrieveTableNumber(route.params.tableNumberId);
    }

    return {
      alertService,
      tableNumber,

      previousState,
      t$: useI18n().t,
    };
  },
});
