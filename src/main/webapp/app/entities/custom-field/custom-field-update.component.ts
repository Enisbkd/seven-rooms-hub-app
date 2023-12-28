import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CustomFieldService from './custom-field.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { type IClient } from '@/shared/model/client.model';
import { type ICustomField, CustomField } from '@/shared/model/custom-field.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomFieldUpdate',
  setup() {
    const customFieldService = inject('customFieldService', () => new CustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customField: Ref<ICustomField> = ref(new CustomField());

    const clientService = inject('clientService', () => new ClientService());

    const clients: Ref<IClient[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCustomField = async customFieldId => {
      try {
        const res = await customFieldService().find(customFieldId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        customField.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customFieldId) {
      retrieveCustomField(route.params.customFieldId);
    }

    const initRelationships = () => {
      clientService()
        .retrieve()
        .then(res => {
          clients.value = res.data;
        });
    };

    initRelationships();

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
      client: {},
    };
    const v$ = useVuelidate(validationRules, customField as any);
    v$.value.$validate();

    return {
      customFieldService,
      alertService,
      customField,
      previousState,
      isSaving,
      currentLanguage,
      clients,
      v$,
      ...useDateFormat({ entityRef: customField }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.customField.id) {
        this.customFieldService()
          .update(this.customField)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.customField.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.customFieldService()
          .create(this.customField)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.customField.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
