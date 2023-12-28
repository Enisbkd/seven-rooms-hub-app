<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="table">
        <h2 class="jh-entity-heading" data-cy="tableDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.table.detail.title')"></span> {{ table.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.table.tableNumber')"></span>
          </dt>
          <dd>
            <span>{{ table.tableNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.table.reservation')"></span>
          </dt>
          <dd>
            <div v-if="table.reservation">
              <router-link :to="{ name: 'ReservationView', params: { reservationId: table.reservation.id } }">{{
                table.reservation.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="table.id" :to="{ name: 'TableEdit', params: { tableId: table.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./table-details.component.ts"></script>
