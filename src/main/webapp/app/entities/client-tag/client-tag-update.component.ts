import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ClientTagService from './client-tag.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { type IClient } from '@/shared/model/client.model';
import { type IClientTag, ClientTag } from '@/shared/model/client-tag.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientTagUpdate',
  setup() {
    const clientTagService = inject('clientTagService', () => new ClientTagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientTag: Ref<IClientTag> = ref(new ClientTag());

    const clientService = inject('clientService', () => new ClientService());

    const clients: Ref<IClient[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveClientTag = async clientTagId => {
      try {
        const res = await clientTagService().find(clientTagId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        clientTag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientTagId) {
      retrieveClientTag(route.params.clientTagId);
    }

    const initRelationships = () => {
      clientService()
        .retrieve()
        .then(res => {
          clients.value = res.data;
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
      client: {},
    };
    const v$ = useVuelidate(validationRules, clientTag as any);
    v$.value.$validate();

    return {
      clientTagService,
      alertService,
      clientTag,
      previousState,
      isSaving,
      currentLanguage,
      clients,
      v$,
      ...useDateFormat({ entityRef: clientTag }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.clientTag.id) {
        this.clientTagService()
          .update(this.clientTag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.clientTag.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.clientTagService()
          .create(this.clientTag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.clientTag.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
