<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tableNum">
        <h2 class="jh-entity-heading" data-cy="tableNumDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.detail.title')"></span> {{ tableNum.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.tableNumber')"></span>
          </dt>
          <dd>
            <span>{{ tableNum.tableNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.tableNum.reservation')"></span>
          </dt>
          <dd>
            <div v-if="tableNum.reservation">
              <router-link :to="{ name: 'ReservationView', params: { reservationId: tableNum.reservation.id } }">{{
                tableNum.reservation.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="tableNum.id" :to="{ name: 'TableNumEdit', params: { tableNumId: tableNum.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./table-num-details.component.ts"></script>
