<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resTag">
        <h2 class="jh-entity-heading" data-cy="resTagDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.detail.title')"></span> {{ resTag.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.tag')"></span>
          </dt>
          <dd>
            <span>{{ resTag.tag }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.tagDisplay')"></span>
          </dt>
          <dd>
            <span>{{ resTag.tagDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.group')"></span>
          </dt>
          <dd>
            <span>{{ resTag.group }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.groupDisplay')"></span>
          </dt>
          <dd>
            <span>{{ resTag.groupDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.color')"></span>
          </dt>
          <dd>
            <span>{{ resTag.color }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ resTag.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resTag.techCreatedDate">{{ formatDateLong(resTag.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="resTag.techUpdatedDate">{{ formatDateLong(resTag.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ resTag.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techComment')"></span>
          </dt>
          <dd>
            <span>{{ resTag.techComment }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="resTag.id" :to="{ name: 'ResTagEdit', params: { resTagId: resTag.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-tag-details.component.ts"></script>
