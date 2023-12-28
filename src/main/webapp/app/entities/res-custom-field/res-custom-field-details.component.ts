import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResCustomFieldService from './res-custom-field.service';
import { useDateFormat } from '@/shared/composables';
import { type IResCustomField } from '@/shared/model/res-custom-field.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResCustomFieldDetails',
  setup() {
    const dateFormat = useDateFormat();
    const resCustomFieldService = inject('resCustomFieldService', () => new ResCustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resCustomField: Ref<IResCustomField> = ref({});

    const retrieveResCustomField = async resCustomFieldId => {
      try {
        const res = await resCustomFieldService().find(resCustomFieldId);
        resCustomField.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resCustomFieldId) {
      retrieveResCustomField(route.params.resCustomFieldId);
    }

    return {
      ...dateFormat,
      alertService,
      resCustomField,

      previousState,
      t$: useI18n().t,
    };
  },
});
