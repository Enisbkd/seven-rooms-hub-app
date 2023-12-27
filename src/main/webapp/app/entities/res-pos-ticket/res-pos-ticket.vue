<template>
  <div>
    <h2 id="page-heading" data-cy="ResPosTicketHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.home.title')" id="res-pos-ticket-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResPosTicketCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-res-pos-ticket"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resPosTickets && resPosTickets.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resPosTickets && resPosTickets.length > 0">
      <table class="table table-striped" aria-describedby="resPosTickets">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.status')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('adminFee')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.adminFee')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'adminFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('code')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.code')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'code'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tableNo')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.tableNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tableNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tax')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.tax')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tax'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('businessId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.businessId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'businessId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('localPosticketId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.localPosticketId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'localPosticketId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('employeeName')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.employeeName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'employeeName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('total')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.total')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'total'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('subtotal')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.subtotal')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'subtotal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('startTime')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.startTime')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'startTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('serviceCharge')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.serviceCharge')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'serviceCharge'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('endtime')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.endtime')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'endtime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techLineage')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techLineage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techLineage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techCreatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techCreatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techCreatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techUpdatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techUpdatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techUpdatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techMapping')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techMapping')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techMapping'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techComment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techComment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techComment'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resPosTicket in resPosTickets" :key="resPosTicket.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResPosTicketView', params: { resPosTicketId: resPosTicket.id } }">{{
                resPosTicket.id
              }}</router-link>
            </td>
            <td>{{ resPosTicket.status }}</td>
            <td>{{ resPosTicket.adminFee }}</td>
            <td>{{ resPosTicket.code }}</td>
            <td>{{ resPosTicket.tableNo }}</td>
            <td>{{ resPosTicket.tax }}</td>
            <td>{{ resPosTicket.businessId }}</td>
            <td>{{ resPosTicket.localPosticketId }}</td>
            <td>{{ resPosTicket.employeeName }}</td>
            <td>{{ resPosTicket.total }}</td>
            <td>{{ resPosTicket.subtotal }}</td>
            <td>{{ resPosTicket.startTime }}</td>
            <td>{{ resPosTicket.serviceCharge }}</td>
            <td>{{ resPosTicket.endtime }}</td>
            <td>{{ resPosTicket.techLineage }}</td>
            <td>{{ formatDateShort(resPosTicket.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(resPosTicket.techUpdatedDate) || '' }}</td>
            <td>{{ resPosTicket.techMapping }}</td>
            <td>{{ resPosTicket.techComment }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ResPosTicketView', params: { resPosTicketId: resPosTicket.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ResPosTicketEdit', params: { resPosTicketId: resPosTicket.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resPosTicket)"
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
          id="sevenRoomsToHubApplicationApp.resPosTicket.delete.question"
          data-cy="resPosTicketDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-resPosTicket-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resPosTicket"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResPosTicket()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="resPosTickets && resPosTickets.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-pos-ticket.component.ts"></script>
