import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ClientService from './client.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClientPhotoService from '@/entities/client-photo/client-photo.service';
import { type IClientPhoto } from '@/shared/model/client-photo.model';
import ClientVenueStatsService from '@/entities/client-venue-stats/client-venue-stats.service';
import { type IClientVenueStats } from '@/shared/model/client-venue-stats.model';
import { type IClient, Client } from '@/shared/model/client.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientUpdate',
  setup() {
    const clientService = inject('clientService', () => new ClientService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const client: Ref<IClient> = ref(new Client());

    const clientPhotoService = inject('clientPhotoService', () => new ClientPhotoService());

    const clientPhotos: Ref<IClientPhoto[]> = ref([]);

    const clientVenueStatsService = inject('clientVenueStatsService', () => new ClientVenueStatsService());

    const clientVenueStats: Ref<IClientVenueStats[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveClient = async clientId => {
      try {
        const res = await clientService().find(clientId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        client.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientId) {
      retrieveClient(route.params.clientId);
    }

    const initRelationships = () => {
      clientPhotoService()
        .retrieve()
        .then(res => {
          clientPhotos.value = res.data;
        });
      clientVenueStatsService()
        .retrieve()
        .then(res => {
          clientVenueStats.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      clientId: {},
      createdDate: {},
      updatedDate: {},
      deletedDate: {},
      lastname: {},
      firstname: {},
      gender: {},
      salutation: {},
      title: {},
      birthdayDay: {},
      birthdayMonth: {},
      birthdayAltMonth: {},
      anniversaryDay: {},
      anniversaryMonth: {},
      company: {},
      email: {},
      emailAlt: {},
      phoneNumber: {},
      phoneNumberlocale: {},
      phoneNumberalt: {},
      phoneNumberaltlocale: {},
      address: {},
      address2: {},
      city: {},
      postalCode: {},
      state: {},
      country: {},
      isContactPrivate: {},
      isOnetimeGuest: {},
      status: {},
      loyaltyId: {},
      loyaltyRank: {},
      loyaltyTier: {},
      marketingOptin: {},
      marketingOptints: {},
      marketingOptOutts: {},
      hasBillingProfile: {},
      notes: {},
      privateNotes: {},
      tags: {},
      totalVisits: {},
      totalCovers: {},
      totalCancellations: {},
      totalNoShows: {},
      totalSpend: {},
      totalSpendPerCover: {},
      totalspendPerVisit: {},
      avgRating: {},
      referenceCode: {},
      externalUserId: {},
      venueGroupId: {},
      birthdayAltDay: {},
      userId: {},
      userName: {},
      totalOrderCount: {},
      preferredLanguageCode: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
      clientPhoto: {},
      clientVenueStats: {},
      customFields: {},
      clientTags: {},
      reservations: {},
      memberGroups: {},
    };
    const v$ = useVuelidate(validationRules, client as any);
    v$.value.$validate();

    return {
      clientService,
      alertService,
      client,
      previousState,
      isSaving,
      currentLanguage,
      clientPhotos,
      clientVenueStats,
      v$,
      ...useDateFormat({ entityRef: client }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.client.id) {
        this.clientService()
          .update(this.client)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.client.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.clientService()
          .create(this.client)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.client.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
