<template>
  <div>
    <h2 id="page-heading" data-cy="TableNumberHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.tableNumber.home.title')" id="table-number-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.tableNumber.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TableNumberCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-table-number"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.tableNumber.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tableNumbers && tableNumbers.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.tableNumber.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="tableNumbers && tableNumbers.length > 0">
      <table class="table table-striped" aria-describedby="tableNumbers">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.tableNumber.tableNum')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tableNumber in tableNumbers" :key="tableNumber.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TableNumberView', params: { tableNumberId: tableNumber.id } }">{{ tableNumber.id }}</router-link>
            </td>
            <td>{{ tableNumber.tableNum }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TableNumberView', params: { tableNumberId: tableNumber.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TableNumberEdit', params: { tableNumberId: tableNumber.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tableNumber)"
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
          id="sevenRoomsToHubApplicationApp.tableNumber.delete.question"
          data-cy="tableNumberDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-tableNumber-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.tableNumber.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-tableNumber"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTableNumber()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./table-number.component.ts"></script>
