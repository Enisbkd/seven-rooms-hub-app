import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MemberGroupService from './member-group.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { type IClient } from '@/shared/model/client.model';
import { type IMemberGroup, MemberGroup } from '@/shared/model/member-group.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MemberGroupUpdate',
  setup() {
    const memberGroupService = inject('memberGroupService', () => new MemberGroupService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const memberGroup: Ref<IMemberGroup> = ref(new MemberGroup());

    const clientService = inject('clientService', () => new ClientService());

    const clients: Ref<IClient[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMemberGroup = async memberGroupId => {
      try {
        const res = await memberGroupService().find(memberGroupId);
        memberGroup.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.memberGroupId) {
      retrieveMemberGroup(route.params.memberGroupId);
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
      client: {},
    };
    const v$ = useVuelidate(validationRules, memberGroup as any);
    v$.value.$validate();

    return {
      memberGroupService,
      alertService,
      memberGroup,
      previousState,
      isSaving,
      currentLanguage,
      clients,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.memberGroup.id) {
        this.memberGroupService()
          .update(this.memberGroup)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.memberGroup.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.memberGroupService()
          .create(this.memberGroup)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToHubApplicationApp.memberGroup.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
