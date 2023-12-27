import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CustomFieldService from './custom-field.service';
import { useDateFormat } from '@/shared/composables';
import { type ICustomField } from '@/shared/model/custom-field.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomFieldDetails',
  setup() {
    const dateFormat = useDateFormat();
    const customFieldService = inject('customFieldService', () => new CustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const customField: Ref<ICustomField> = ref({});

    const retrieveCustomField = async customFieldId => {
      try {
        const res = await customFieldService().find(customFieldId);
        customField.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customFieldId) {
      retrieveCustomField(route.params.customFieldId);
    }

    return {
      ...dateFormat,
      alertService,
      customField,

      previousState,
      t$: useI18n().t,
    };
  },
});
