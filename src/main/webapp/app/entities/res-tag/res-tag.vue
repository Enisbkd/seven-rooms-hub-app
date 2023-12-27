<template>
  <div>
    <h2 id="page-heading" data-cy="ResTagHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.home.title')" id="res-tag-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResTagCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-res-tag"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resTags && resTags.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.resTag.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resTags && resTags.length > 0">
      <table class="table table-striped" aria-describedby="resTags">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.tag')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.tagDisplay')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.group')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.groupDisplay')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.color')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.resTag.techComment')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resTag in resTags" :key="resTag.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResTagView', params: { resTagId: resTag.id } }">{{ resTag.id }}</router-link>
            </td>
            <td>{{ resTag.tag }}</td>
            <td>{{ resTag.tagDisplay }}</td>
            <td>{{ resTag.group }}</td>
            <td>{{ resTag.groupDisplay }}</td>
            <td>{{ resTag.color }}</td>
            <td>{{ resTag.techLineage }}</td>
            <td>{{ formatDateShort(resTag.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(resTag.techUpdatedDate) || '' }}</td>
            <td>{{ resTag.techMapping }}</td>
            <td>{{ resTag.techComment }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ResTagView', params: { resTagId: resTag.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ResTagEdit', params: { resTagId: resTag.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resTag)"
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
          id="sevenRoomsToHubApplicationApp.resTag.delete.question"
          data-cy="resTagDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-resTag-heading" v-text="t$('sevenRoomsToHubApplicationApp.resTag.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resTag"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResTag()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./res-tag.component.ts"></script>
