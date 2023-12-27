<template>
  <div>
    <h2 id="page-heading" data-cy="ClientPhotoHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.home.title')" id="client-photo-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ClientPhotoCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-client-photo"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clientPhotos && clientPhotos.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="clientPhotos && clientPhotos.length > 0">
      <table class="table table-striped" aria-describedby="clientPhotos">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.clientId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.large')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.largeHeight')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.largeWidth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.medium')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.mediumHeight')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.mediumWidth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.small')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.smallHeight')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.smallWidth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.raw')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.cropx')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.cropy')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.cropHeight')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.cropWidth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.techComment')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="clientPhoto in clientPhotos" :key="clientPhoto.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientPhotoView', params: { clientPhotoId: clientPhoto.id } }">{{ clientPhoto.id }}</router-link>
            </td>
            <td>{{ clientPhoto.clientId }}</td>
            <td>{{ clientPhoto.large }}</td>
            <td>{{ clientPhoto.largeHeight }}</td>
            <td>{{ clientPhoto.largeWidth }}</td>
            <td>{{ clientPhoto.medium }}</td>
            <td>{{ clientPhoto.mediumHeight }}</td>
            <td>{{ clientPhoto.mediumWidth }}</td>
            <td>{{ clientPhoto.small }}</td>
            <td>{{ clientPhoto.smallHeight }}</td>
            <td>{{ clientPhoto.smallWidth }}</td>
            <td>{{ clientPhoto.raw }}</td>
            <td>{{ clientPhoto.cropx }}</td>
            <td>{{ clientPhoto.cropy }}</td>
            <td>{{ clientPhoto.cropHeight }}</td>
            <td>{{ clientPhoto.cropWidth }}</td>
            <td>{{ clientPhoto.techLineage }}</td>
            <td>{{ formatDateShort(clientPhoto.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(clientPhoto.techUpdatedDate) || '' }}</td>
            <td>{{ clientPhoto.techMapping }}</td>
            <td>{{ clientPhoto.techComment }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientPhotoView', params: { clientPhotoId: clientPhoto.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClientPhotoEdit', params: { clientPhotoId: clientPhoto.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(clientPhoto)"
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
          id="sevenRoomsToHubApplicationApp.clientPhoto.delete.question"
          data-cy="clientPhotoDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-clientPhoto-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.clientPhoto.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-clientPhoto"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeClientPhoto()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./client-photo.component.ts"></script>
