<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resPosTicket">
        <h2 class="jh-entity-heading" data-cy="resPosTicketDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.detail.title')"></span> {{ resPosTicket.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.status')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.adminFee')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.adminFee }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.code')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.code }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.tableNo')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.tableNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.tax')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.tax }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.businessId')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.businessId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.localPosticketId')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.localPosticketId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.employeeName')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.employeeName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.total')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.total }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.subtotal')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.subtotal }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.startTime')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.startTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.serviceCharge')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.serviceCharge }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.endtime')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resPosTicket.techCreatedDate">{{ formatDateLong(resPosTicket.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resPosTicket.techUpdatedDate">{{ formatDateLong(resPosTicket.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosTicket.techComment')"></span>
          </dt>
          <dd>
            <span>{{ resPosTicket.techComment }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="resPosTicket.id"
          :to="{ name: 'ResPosTicketEdit', params: { resPosTicketId: resPosTicket.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-pos-ticket-details.component.ts"></script>
