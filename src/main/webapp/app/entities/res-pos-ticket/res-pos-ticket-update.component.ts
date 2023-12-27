import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResPosTicketService from './res-pos-ticket.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IResPosTicket, ResPosTicket } from '@/shared/model/res-pos-ticket.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResPosTicketUpdate',
  setup() {
    const resPosTicketService = inject('resPosTicketService', () => new ResPosTicketService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resPosTicket: Ref<IResPosTicket> = ref(new ResPosTicket());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResPosTicket = async resPosTicketId => {
      try {
        const res = await resPosTicketService().find(resPosTicketId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        resPosTicket.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resPosTicketId) {
      retrieveResPosTicket(route.params.resPosTicketId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      status: {},
      adminFee: {},
      code: {},
      tableNo: {},
      tax: {},
      businessId: {},
      localPosticketId: {},
      employeeName: {},
      total: {},
      subtotal: {},
      startTime: {},
      serviceCharge: {},
      endtime: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
    };
    const v$ = useVuelidate(validationRules, resPosTicket as any);
    v$.value.$validate();

    return {
      resPosTicketService,
      alertService,
      resPosTicket,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: resPosTicket }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resPosTicket.id) {
        this.resPosTicketService()
          .update(this.resPosTicket)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.resPosTicket.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resPosTicketService()
          .create(this.resPosTicket)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.resPosTicket.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
