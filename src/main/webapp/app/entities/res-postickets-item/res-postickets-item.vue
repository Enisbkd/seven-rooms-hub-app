<template>
  <div>
    <h2 id="page-heading" data-cy="ResPosticketsItemHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.home.title')" id="res-postickets-item-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResPosticketsItemCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-res-postickets-item"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resPosticketsItems && resPosticketsItems.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resPosticketsItems && resPosticketsItems.length > 0">
      <table class="table table-striped" aria-describedby="resPosticketsItems">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('price')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.price')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'price'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.name')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('quantity')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.quantity')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'quantity'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techLineage')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techLineage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techLineage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techCreatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techCreatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techCreatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techUpdatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techUpdatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techUpdatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techMapping')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techMapping')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techMapping'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techComment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techComment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techComment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('resPosTicket.id')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.resPosTicket')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'resPosTicket.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resPosticketsItem in resPosticketsItems" :key="resPosticketsItem.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResPosticketsItemView', params: { resPosticketsItemId: resPosticketsItem.id } }">{{
                resPosticketsItem.id
              }}</router-link>
            </td>
            <td>{{ resPosticketsItem.price }}</td>
            <td>{{ resPosticketsItem.name }}</td>
            <td>{{ resPosticketsItem.quantity }}</td>
            <td>{{ resPosticketsItem.techLineage }}</td>
            <td>{{ formatDateShort(resPosticketsItem.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(resPosticketsItem.techUpdatedDate) || '' }}</td>
            <td>{{ resPosticketsItem.techMapping }}</td>
            <td>{{ resPosticketsItem.techComment }}</td>
            <td>
              <div v-if="resPosticketsItem.resPosTicket">
                <router-link :to="{ name: 'ResPosTicketView', params: { resPosTicketId: resPosticketsItem.resPosTicket.id } }">{{
                  resPosticketsItem.resPosTicket.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ResPosticketsItemView', params: { resPosticketsItemId: resPosticketsItem.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ResPosticketsItemEdit', params: { resPosticketsItemId: resPosticketsItem.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resPosticketsItem)"
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
          id="sevenRoomsToHubApplicationApp.resPosticketsItem.delete.question"
          data-cy="resPosticketsItemDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-resPosticketsItem-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resPosticketsItem"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResPosticketsItem()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="resPosticketsItems && resPosticketsItems.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-postickets-item.component.ts"></script>
