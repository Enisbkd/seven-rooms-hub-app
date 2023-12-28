import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ClientTagService from './client-tag.service';
import { useDateFormat } from '@/shared/composables';
import { type IClientTag } from '@/shared/model/client-tag.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientTagDetails',
  setup() {
    const dateFormat = useDateFormat();
    const clientTagService = inject('clientTagService', () => new ClientTagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const clientTag: Ref<IClientTag> = ref({});

    const retrieveClientTag = async clientTagId => {
      try {
        const res = await clientTagService().find(clientTagId);
        clientTag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientTagId) {
      retrieveClientTag(route.params.clientTagId);
    }

    return {
      ...dateFormat,
      alertService,
      clientTag,

      previousState,
      t$: useI18n().t,
    };
  },
});
