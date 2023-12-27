<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="clientTag">
        <h2 class="jh-entity-heading" data-cy="clientTagDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.detail.title')"></span> {{ clientTag.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.tag')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.tag }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.tagDisplay')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.tagDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.group')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.group }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.groupDisplay')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.groupDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.color')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.color }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="clientTag.techCreatedDate">{{ formatDateLong(clientTag.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="clientTag.techUpdatedDate">{{ formatDateLong(clientTag.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techComment')"></span>
          </dt>
          <dd>
            <span>{{ clientTag.techComment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.client')"></span>
          </dt>
          <dd>
            <div v-if="clientTag.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: clientTag.client.id } }">{{ clientTag.client.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="clientTag.id"
          :to="{ name: 'ClientTagEdit', params: { clientTagId: clientTag.id } }"
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

<script lang="ts" src="./client-tag-details.component.ts"></script>
