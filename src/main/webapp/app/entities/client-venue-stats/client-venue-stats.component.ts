import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ClientVenueStatsService from './client-venue-stats.service';
import { type IClientVenueStats } from '@/shared/model/client-venue-stats.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientVenueStats',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const clientVenueStatsService = inject('clientVenueStatsService', () => new ClientVenueStatsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientVenueStats: Ref<IClientVenueStats[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveClientVenueStatss = async () => {
      isFetching.value = true;
      try {
        const res = await clientVenueStatsService().retrieve();
        clientVenueStats.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveClientVenueStatss();
    };

    onMounted(async () => {
      await retrieveClientVenueStatss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IClientVenueStats) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeClientVenueStats = async () => {
      try {
        await clientVenueStatsService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.clientVenueStats.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveClientVenueStatss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      clientVenueStats,
      handleSyncList,
      isFetching,
      retrieveClientVenueStatss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeClientVenueStats,
      t$,
    };
  },
});
