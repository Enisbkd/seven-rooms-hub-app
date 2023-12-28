import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TableNumService from './table-num.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';
import { type IReservation } from '@/shared/model/reservation.model';
import { type ITableNum, TableNum } from '@/shared/model/table-num.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableNumUpdate',
  setup() {
    const tableNumService = inject('tableNumService', () => new TableNumService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const tableNum: Ref<ITableNum> = ref(new TableNum());

    const reservationService = inject('reservationService', () => new ReservationService());

    const reservations: Ref<IReservation[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const initRelationships = () => {
      reservationService()
        .retrieve()
        .then(res => {
          reservations.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      tableNumber: {},
      reservation: {},
    };
    const v$ = useVuelidate(validationRules, tableNum as any);
    v$.value.$validate();

    return {
      tableNumService,
      alertService,
      tableNum,
      previousState,
      isSaving,
      currentLanguage,
      reservations,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.tableNum.id) {
        this.tableNumService()
          .update(this.tableNum)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.tableNum.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.tableNumService()
          .create(this.tableNum)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.tableNum.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
