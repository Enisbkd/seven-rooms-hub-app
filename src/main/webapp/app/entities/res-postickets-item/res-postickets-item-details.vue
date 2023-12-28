<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resPosticketsItem">
        <h2 class="jh-entity-heading" data-cy="resPosticketsItemDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.detail.title')"></span> {{ resPosticketsItem.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.price')"></span>
          </dt>
          <dd>
            <span>{{ resPosticketsItem.price }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.name')"></span>
          </dt>
          <dd>
            <span>{{ resPosticketsItem.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.quantity')"></span>
          </dt>
          <dd>
            <span>{{ resPosticketsItem.quantity }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ resPosticketsItem.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resPosticketsItem.techCreatedDate">{{ formatDateLong(resPosticketsItem.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resPosticketsItem.techUpdatedDate">{{ formatDateLong(resPosticketsItem.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ resPosticketsItem.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techComment')"></span>
          </dt>
          <dd>
            <span>{{ resPosticketsItem.techComment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.resPosTicket')"></span>
          </dt>
          <dd>
            <div v-if="resPosticketsItem.resPosTicket">
              <router-link :to="{ name: 'ResPosTicketView', params: { resPosTicketId: resPosticketsItem.resPosTicket.id } }">{{
                resPosticketsItem.resPosTicket.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="resPosticketsItem.id"
          :to="{ name: 'ResPosticketsItemEdit', params: { resPosticketsItemId: resPosticketsItem.id } }"
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

<script lang="ts" src="./res-postickets-item-details.component.ts"></script>
