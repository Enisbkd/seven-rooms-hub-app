import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResPosticketsItemService from './res-postickets-item.service';
import { useDateFormat } from '@/shared/composables';
import { type IResPosticketsItem } from '@/shared/model/res-postickets-item.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResPosticketsItemDetails',
  setup() {
    const dateFormat = useDateFormat();
    const resPosticketsItemService = inject('resPosticketsItemService', () => new ResPosticketsItemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resPosticketsItem: Ref<IResPosticketsItem> = ref({});

    const retrieveResPosticketsItem = async resPosticketsItemId => {
      try {
        const res = await resPosticketsItemService().find(resPosticketsItemId);
        resPosticketsItem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resPosticketsItemId) {
      retrieveResPosticketsItem(route.params.resPosticketsItemId);
    }

    return {
      ...dateFormat,
      alertService,
      resPosticketsItem,

      previousState,
      t$: useI18n().t,
    };
  },
});
