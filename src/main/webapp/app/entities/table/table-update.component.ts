import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TableService from './table.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';
import { type IReservation } from '@/shared/model/reservation.model';
import { type ITable, Table } from '@/shared/model/table.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TableUpdate',
  setup() {
    const tableService = inject('tableService', () => new TableService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const table: Ref<ITable> = ref(new Table());

    const reservationService = inject('reservationService', () => new ReservationService());

    const reservations: Ref<IReservation[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTable = async tableId => {
      try {
        const res = await tableService().find(tableId);
        table.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.tableId) {
      retrieveTable(route.params.tableId);
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
    const v$ = useVuelidate(validationRules, table as any);
    v$.value.$validate();

    return {
      tableService,
      alertService,
      table,
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
      if (this.table.id) {
        this.tableService()
          .update(this.table)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.table.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.tableService()
          .create(this.table)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.table.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
