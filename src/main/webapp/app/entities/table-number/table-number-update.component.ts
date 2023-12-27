import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TableNumberService from './table-number.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ITableNumber, TableNumber } from '@/shared/model/table-number.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableNumberUpdate',
  setup() {
    const tableNumberService = inject('tableNumberService', () => new TableNumberService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const tableNumber: Ref<ITableNumber> = ref(new TableNumber());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      tableNum: {},
    };
    const v$ = useVuelidate(validationRules, tableNumber as any);
    v$.value.$validate();

    return {
      tableNumberService,
      alertService,
      tableNumber,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.tableNumber.id) {
        this.tableNumberService()
          .update(this.tableNumber)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.tableNumber.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.tableNumberService()
          .create(this.tableNumber)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.tableNumber.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
