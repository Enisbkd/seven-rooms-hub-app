import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ReservationService from './reservation.service';
import { useDateFormat } from '@/shared/composables';
import { type IReservation } from '@/shared/model/reservation.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ReservationDetails',
  setup() {
    const dateFormat = useDateFormat();
    const reservationService = inject('reservationService', () => new ReservationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const reservation: Ref<IReservation> = ref({});

    const retrieveReservation = async reservationId => {
      try {
        const res = await reservationService().find(reservationId);
        reservation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.reservationId) {
      retrieveReservation(route.params.reservationId);
    }

    return {
      ...dateFormat,
      alertService,
      reservation,

      previousState,
      t$: useI18n().t,
    };
  },
});
