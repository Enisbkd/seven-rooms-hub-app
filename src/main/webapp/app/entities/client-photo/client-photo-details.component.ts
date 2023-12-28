import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ClientPhotoService from './client-photo.service';
import { useDateFormat } from '@/shared/composables';
import { type IClientPhoto } from '@/shared/model/client-photo.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientPhotoDetails',
  setup() {
    const dateFormat = useDateFormat();
    const clientPhotoService = inject('clientPhotoService', () => new ClientPhotoService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const clientPhoto: Ref<IClientPhoto> = ref({});

    const retrieveClientPhoto = async clientPhotoId => {
      try {
        const res = await clientPhotoService().find(clientPhotoId);
        clientPhoto.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientPhotoId) {
      retrieveClientPhoto(route.params.clientPhotoId);
    }

    return {
      ...dateFormat,
      alertService,
      clientPhoto,

      previousState,
      t$: useI18n().t,
    };
  },
});
