import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ClientVenueStatsService from './client-venue-stats.service';
import { useDateFormat } from '@/shared/composables';
import { type IClientVenueStats } from '@/shared/model/client-venue-stats.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientVenueStatsDetails',
  setup() {
    const dateFormat = useDateFormat();
    const clientVenueStatsService = inject('clientVenueStatsService', () => new ClientVenueStatsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const clientVenueStats: Ref<IClientVenueStats> = ref({});

    const retrieveClientVenueStats = async clientVenueStatsId => {
      try {
        const res = await clientVenueStatsService().find(clientVenueStatsId);
        clientVenueStats.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientVenueStatsId) {
      retrieveClientVenueStats(route.params.clientVenueStatsId);
    }

    return {
      ...dateFormat,
      alertService,
      clientVenueStats,

      previousState,
      t$: useI18n().t,
    };
  },
});
