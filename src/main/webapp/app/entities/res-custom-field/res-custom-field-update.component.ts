import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResCustomFieldService from './res-custom-field.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IResCustomField, ResCustomField } from '@/shared/model/res-custom-field.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResCustomFieldUpdate',
  setup() {
    const resCustomFieldService = inject('resCustomFieldService', () => new ResCustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resCustomField: Ref<IResCustomField> = ref(new ResCustomField());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResCustomField = async resCustomFieldId => {
      try {
        const res = await resCustomFieldService().find(resCustomFieldId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        resCustomField.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resCustomFieldId) {
      retrieveResCustomField(route.params.resCustomFieldId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      systemName: {},
      displayOrder: {},
      name: {},
      value: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
    };
    const v$ = useVuelidate(validationRules, resCustomField as any);
    v$.value.$validate();

    return {
      resCustomFieldService,
      alertService,
      resCustomField,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: resCustomField }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resCustomField.id) {
        this.resCustomFieldService()
          .update(this.resCustomField)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.resCustomField.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resCustomFieldService()
          .create(this.resCustomField)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.resCustomField.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
