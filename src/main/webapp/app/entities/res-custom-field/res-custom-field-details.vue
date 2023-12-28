<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resCustomField">
        <h2 class="jh-entity-heading" data-cy="resCustomFieldDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.detail.title')"></span> {{ resCustomField.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.systemName')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.systemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.displayOrder')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.displayOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.name')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.value')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.value }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resCustomField.techCreatedDate">{{ formatDateLong(resCustomField.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resCustomField.techUpdatedDate">{{ formatDateLong(resCustomField.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techComment')"></span>
          </dt>
          <dd>
            <span>{{ resCustomField.techComment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.reservation')"></span>
          </dt>
          <dd>
            <div v-if="resCustomField.reservation">
              <router-link :to="{ name: 'ReservationView', params: { reservationId: resCustomField.reservation.id } }">{{
                resCustomField.reservation.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="resCustomField.id"
          :to="{ name: 'ResCustomFieldEdit', params: { resCustomFieldId: resCustomField.id } }"
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

<script lang="ts" src="./res-custom-field-details.component.ts"></script>
