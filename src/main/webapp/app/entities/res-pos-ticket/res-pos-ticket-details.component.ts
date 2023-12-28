import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResPosTicketService from './res-pos-ticket.service';
import { useDateFormat } from '@/shared/composables';
import { type IResPosTicket } from '@/shared/model/res-pos-ticket.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResPosTicketDetails',
  setup() {
    const dateFormat = useDateFormat();
    const resPosTicketService = inject('resPosTicketService', () => new ResPosTicketService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resPosTicket: Ref<IResPosTicket> = ref({});

    const retrieveResPosTicket = async resPosTicketId => {
      try {
        const res = await resPosTicketService().find(resPosTicketId);
        resPosTicket.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resPosTicketId) {
      retrieveResPosTicket(route.params.resPosTicketId);
    }

    return {
      ...dateFormat,
      alertService,
      resPosTicket,

      previousState,
      t$: useI18n().t,
    };
  },
});
