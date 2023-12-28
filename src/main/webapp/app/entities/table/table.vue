<template>
  <div>
    <h2 id="page-heading" data-cy="TableHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.table.home.title')" id="table-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.table.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TableCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-table"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.table.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tables && tables.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.table.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="tables && tables.length > 0">
      <table class="table table-striped" aria-describedby="tables">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.table.tableNumber')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.table.reservation')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="table in tables" :key="table.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TableView', params: { tableId: table.id } }">{{ table.id }}</router-link>
            </td>
            <td>{{ table.tableNumber }}</td>
            <td>
              <div v-if="table.reservation">
                <router-link :to="{ name: 'ReservationView', params: { reservationId: table.reservation.id } }">{{
                  table.reservation.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TableView', params: { tableId: table.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TableEdit', params: { tableId: table.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(table)"
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
          id="sevenRoomsToHubApplicationApp.table.delete.question"
          data-cy="tableDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-table-heading" v-text="t$('sevenRoomsToHubApplicationApp.table.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-table"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTable()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./table.component.ts"></script>
