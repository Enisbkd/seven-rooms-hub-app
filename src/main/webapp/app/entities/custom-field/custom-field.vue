<template>
  <div>
    <h2 id="page-heading" data-cy="CustomFieldHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.customField.home.title')" id="custom-field-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.customField.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CustomFieldCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-custom-field"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.customField.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && customFields && customFields.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.customField.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="customFields && customFields.length > 0">
      <table class="table table-striped" aria-describedby="customFields">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.systemName')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.displayOrder')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.name')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.value')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.techComment')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.customField.client')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="customField in customFields" :key="customField.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CustomFieldView', params: { customFieldId: customField.id } }">{{ customField.id }}</router-link>
            </td>
            <td>{{ customField.systemName }}</td>
            <td>{{ customField.displayOrder }}</td>
            <td>{{ customField.name }}</td>
            <td>{{ customField.value }}</td>
            <td>{{ customField.techLineage }}</td>
            <td>{{ formatDateShort(customField.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(customField.techUpdatedDate) || '' }}</td>
            <td>{{ customField.techMapping }}</td>
            <td>{{ customField.techComment }}</td>
            <td>
              <div v-if="customField.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: customField.client.id } }">{{
                  customField.client.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CustomFieldView', params: { customFieldId: customField.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CustomFieldEdit', params: { customFieldId: customField.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(customField)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="sevenRoomsToHubApplicationApp.customField.delete.question"
          data-cy="customFieldDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-customField-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.customField.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-customField"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCustomField()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./custom-field.component.ts"></script>
