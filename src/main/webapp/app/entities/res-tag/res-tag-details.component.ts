import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResTagService from './res-tag.service';
import { useDateFormat } from '@/shared/composables';
import { type IResTag } from '@/shared/model/res-tag.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResTagDetails',
  setup() {
    const dateFormat = useDateFormat();
    const resTagService = inject('resTagService', () => new ResTagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resTag: Ref<IResTag> = ref({});

    const retrieveResTag = async resTagId => {
      try {
        const res = await resTagService().find(resTagId);
        resTag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resTagId) {
      retrieveResTag(route.params.resTagId);
    }

    return {
      ...dateFormat,
      alertService,
      resTag,

      previousState,
      t$: useI18n().t,
    };
  },
});
