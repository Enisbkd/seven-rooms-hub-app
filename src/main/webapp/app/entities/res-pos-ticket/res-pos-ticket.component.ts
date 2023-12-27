import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ResPosTicketService from './res-pos-ticket.service';
import { type IResPosTicket } from '@/shared/model/res-pos-ticket.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResPosTicket',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const resPosTicketService = inject('resPosTicketService', () => new ResPosTicketService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resPosTickets: Ref<IResPosTicket[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveResPosTickets = async () => {
      isFetching.value = true;
      try {
        const res = await resPosTicketService().retrieve();
        resPosTickets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveResPosTickets();
    };

    onMounted(async () => {
      await retrieveResPosTickets();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IResPosTicket) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeResPosTicket = async () => {
      try {
        await resPosTicketService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.resPosTicket.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveResPosTickets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      resPosTickets,
      handleSyncList,
      isFetching,
      retrieveResPosTickets,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeResPosTicket,
      t$,
    };
  },
});
