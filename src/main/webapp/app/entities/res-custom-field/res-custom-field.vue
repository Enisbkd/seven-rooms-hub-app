<template>
  <div>
    <h2 id="page-heading" data-cy="ResCustomFieldHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.home.title')" id="res-custom-field-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResCustomFieldCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-res-custom-field"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resCustomFields && resCustomFields.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resCustomFields && resCustomFields.length > 0">
      <table class="table table-striped" aria-describedby="resCustomFields">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.systemName')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.displayOrder')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.name')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.value')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techComment')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resCustomField in resCustomFields" :key="resCustomField.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResCustomFieldView', params: { resCustomFieldId: resCustomField.id } }">{{
                resCustomField.id
              }}</router-link>
            </td>
            <td>{{ resCustomField.systemName }}</td>
            <td>{{ resCustomField.displayOrder }}</td>
            <td>{{ resCustomField.name }}</td>
            <td>{{ resCustomField.value }}</td>
            <td>{{ resCustomField.techLineage }}</td>
            <td>{{ formatDateShort(resCustomField.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(resCustomField.techUpdatedDate) || '' }}</td>
            <td>{{ resCustomField.techMapping }}</td>
            <td>{{ resCustomField.techComment }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ResCustomFieldView', params: { resCustomFieldId: resCustomField.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ResCustomFieldEdit', params: { resCustomFieldId: resCustomField.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resCustomField)"
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
          id="sevenRoomsToHubApplicationApp.resCustomField.delete.question"
          data-cy="resCustomFieldDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-resCustomField-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resCustomField"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResCustomField()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./res-custom-field.component.ts"></script>
