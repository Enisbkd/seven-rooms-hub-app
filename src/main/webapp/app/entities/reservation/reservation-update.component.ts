import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ReservationService from './reservation.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { type IClient } from '@/shared/model/client.model';
import { type IReservation, Reservation } from '@/shared/model/reservation.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ReservationUpdate',
  setup() {
    const reservationService = inject('reservationService', () => new ReservationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const reservation: Ref<IReservation> = ref(new Reservation());

    const clientService = inject('clientService', () => new ClientService());

    const clients: Ref<IClient[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveReservation = async reservationId => {
      try {
        const res = await reservationService().find(reservationId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        reservation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.reservationId) {
      retrieveReservation(route.params.reservationId);
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
      resvId: {},
      created: {},
      updated: {},
      deleted: {},
      venueGroupClientId: {},
      venueGroupId: {},
      venueId: {},
      date: {},
      duration: {},
      checkNumbers: {},
      shiftCategory: {},
      shiftPersistentId: {},
      maxGuests: {},
      mfratioMale: {},
      mfratioFemale: {},
      status: {},
      statusDisplay: {},
      statusSimple: {},
      tableNumbers: {},
      accessPersistentId: {},
      arrivedGuests: {},
      isvip: {},
      bookedby: {},
      clientReferenceCode: {},
      lastname: {},
      firstname: {},
      email: {},
      phoneNumber: {},
      address: {},
      address2: {},
      city: {},
      postalCode: {},
      state: {},
      country: {},
      loyaltyId: {},
      loyaltyRank: {},
      loyaltyTier: {},
      notes: {},
      arrivalTime: {},
      seatedTime: {},
      leftTime: {},
      clientRequests: {},
      comps: {},
      compsPriceType: {},
      costOption: {},
      policy: {},
      minPrice: {},
      prePayment: {},
      onsitePayment: {},
      totalPayment: {},
      paidBy: {},
      servedBy: {},
      rating: {},
      problems: {},
      autoAssignments: {},
      externalClientId: {},
      externalId: {},
      externalReferenceCode: {},
      externalUserId: {},
      modifyReservationLink: {},
      referenceCode: {},
      reservationSmsOptin: {},
      reservationType: {},
      sendReminderEmail: {},
      sendreminderSms: {},
      sourceClientId: {},
      userId: {},
      userName: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
      resTags: {},
      resPosTickets: {},
      resCustomFields: {},
      tables: {},
      client: {},
    };
    const v$ = useVuelidate(validationRules, reservation as any);
    v$.value.$validate();

    return {
      reservationService,
      alertService,
      reservation,
      previousState,
      isSaving,
      currentLanguage,
      clients,
      v$,
      ...useDateFormat({ entityRef: reservation }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.reservation.id) {
        this.reservationService()
          .update(this.reservation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.reservation.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.reservationService()
          .create(this.reservation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.reservation.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
