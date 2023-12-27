import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResPosticketsItemService from './res-postickets-item.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IResPosticketsItem, ResPosticketsItem } from '@/shared/model/res-postickets-item.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResPosticketsItemUpdate',
  setup() {
    const resPosticketsItemService = inject('resPosticketsItemService', () => new ResPosticketsItemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resPosticketsItem: Ref<IResPosticketsItem> = ref(new ResPosticketsItem());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResPosticketsItem = async resPosticketsItemId => {
      try {
        const res = await resPosticketsItemService().find(resPosticketsItemId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        resPosticketsItem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resPosticketsItemId) {
      retrieveResPosticketsItem(route.params.resPosticketsItemId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      price: {},
      name: {},
      quantity: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
    };
    const v$ = useVuelidate(validationRules, resPosticketsItem as any);
    v$.value.$validate();

    return {
      resPosticketsItemService,
      alertService,
      resPosticketsItem,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: resPosticketsItem }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resPosticketsItem.id) {
        this.resPosticketsItemService()
          .update(this.resPosticketsItem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.resPosticketsItem.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resPosticketsItemService()
          .create(this.resPosticketsItem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToHubApplicationApp.resPosticketsItem.created', { param: param.id }).toString(),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
