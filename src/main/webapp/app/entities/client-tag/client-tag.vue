<template>
  <div>
    <h2 id="page-heading" data-cy="ClientTagHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.home.title')" id="client-tag-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ClientTagCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-client-tag"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clientTags && clientTags.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="clientTags && clientTags.length > 0">
      <table class="table table-striped" aria-describedby="clientTags">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tag')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.tag')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tag'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tagDisplay')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.tagDisplay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tagDisplay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('group')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.group')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'group'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('groupDisplay')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.groupDisplay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'groupDisplay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('color')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.color')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'color'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techLineage')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techLineage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techLineage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techCreatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techCreatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techCreatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techUpdatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techUpdatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techUpdatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techMapping')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techMapping')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techMapping'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techComment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.techComment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techComment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('client.id')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientTag.client')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="clientTag in clientTags" :key="clientTag.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientTagView', params: { clientTagId: clientTag.id } }">{{ clientTag.id }}</router-link>
            </td>
            <td>{{ clientTag.tag }}</td>
            <td>{{ clientTag.tagDisplay }}</td>
            <td>{{ clientTag.group }}</td>
            <td>{{ clientTag.groupDisplay }}</td>
            <td>{{ clientTag.color }}</td>
            <td>{{ clientTag.techLineage }}</td>
            <td>{{ formatDateShort(clientTag.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(clientTag.techUpdatedDate) || '' }}</td>
            <td>{{ clientTag.techMapping }}</td>
            <td>{{ clientTag.techComment }}</td>
            <td>
              <div v-if="clientTag.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: clientTag.client.id } }">{{ clientTag.client.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientTagView', params: { clientTagId: clientTag.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClientTagEdit', params: { clientTagId: clientTag.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(clientTag)"
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
          id="sevenRoomsToHubApplicationApp.clientTag.delete.question"
          data-cy="clientTagDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-clientTag-heading" v-text="t$('sevenRoomsToHubApplicationApp.clientTag.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-clientTag"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeClientTag()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="clientTags && clientTags.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-tag.component.ts"></script>
