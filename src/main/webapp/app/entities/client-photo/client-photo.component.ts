import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ClientPhotoService from './client-photo.service';
import { type IClientPhoto } from '@/shared/model/client-photo.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientPhoto',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const clientPhotoService = inject('clientPhotoService', () => new ClientPhotoService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientPhotos: Ref<IClientPhoto[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveClientPhotos = async () => {
      isFetching.value = true;
      try {
        const res = await clientPhotoService().retrieve();
        clientPhotos.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveClientPhotos();
    };

    onMounted(async () => {
      await retrieveClientPhotos();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IClientPhoto) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeClientPhoto = async () => {
      try {
        await clientPhotoService().delete(removeId.value);
        const message = t$('sevenRoomsToHubApplicationApp.clientPhoto.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveClientPhotos();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      clientPhotos,
      handleSyncList,
      isFetching,
      retrieveClientPhotos,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeClientPhoto,
      t$,
    };
  },
});
