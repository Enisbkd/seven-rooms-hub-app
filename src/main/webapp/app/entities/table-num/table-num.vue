<template>
  <div>
    <h2 id="page-heading" data-cy="TableNumHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.home.title')" id="table-num-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TableNumCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-table-num"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tableNums && tableNums.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="tableNums && tableNums.length > 0">
      <table class="table table-striped" aria-describedby="tableNums">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.tableNumber')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.reservation')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tableNum in tableNums" :key="tableNum.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TableNumView', params: { tableNumId: tableNum.id } }">{{ tableNum.id }}</router-link>
            </td>
            <td>{{ tableNum.tableNumber }}</td>
            <td>
              <div v-if="tableNum.reservation">
                <router-link :to="{ name: 'ReservationView', params: { reservationId: tableNum.reservation.id } }">{{
                  tableNum.reservation.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TableNumView', params: { tableNumId: tableNum.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TableNumEdit', params: { tableNumId: tableNum.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tableNum)"
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
          id="sevenRoomsToHubApplicationApp.tableNum.delete.question"
          data-cy="tableNumDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-tableNum-heading" v-text="t$('sevenRoomsToHubApplicationApp.tableNum.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-tableNum"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTableNum()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./table-num.component.ts"></script>
