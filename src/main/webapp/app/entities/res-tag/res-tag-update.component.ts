import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResTagService from './res-tag.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';
import { type IReservation } from '@/shared/model/reservation.model';
import { type IResTag, ResTag } from '@/shared/model/res-tag.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResTagUpdate',
  setup() {
    const resTagService = inject('resTagService', () => new ResTagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resTag: Ref<IResTag> = ref(new ResTag());

    const reservationService = inject('reservationService', () => new ReservationService());

    const reservations: Ref<IReservation[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResTag = async resTagId => {
      try {
        const res = await resTagService().find(resTagId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        resTag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resTagId) {
      retrieveResTag(route.params.resTagId);
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
      tag: {},
      tagDisplay: {},
      group: {},
      groupDisplay: {},
      color: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
      reservation: {},
    };
    const v$ = useVuelidate(validationRules, resTag as any);
    v$.value.$validate();

    return {
      resTagService,
      alertService,
      resTag,
      previousState,
      isSaving,
      currentLanguage,
      reservations,
      v$,
      ...useDateFormat({ entityRef: resTag }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resTag.id) {
        this.resTagService()
          .update(this.resTag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.resTag.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resTagService()
          .create(this.resTag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.resTag.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
