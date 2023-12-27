<template>
  <div>
    <h2 id="page-heading" data-cy="ClientHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.client.home.title')" id="client-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.client.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ClientCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-client"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clients && clients.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.client.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="clients && clients.length > 0">
      <table class="table table-striped" aria-describedby="clients">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.clientId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.createdDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.updatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.deletedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.lastname')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.firstname')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.gender')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.salutation')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.title')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayDay')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayMonth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltMonth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryDay')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryMonth')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.company')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.email')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.emailAlt')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumber')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberlocale')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberalt')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberaltlocale')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.address')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.address2')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.city')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.postalCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.state')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.country')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.isContactPrivate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.isOnetimeGuest')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.status')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyRank')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyTier')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptin')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptints')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptOutts')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.hasBillingProfile')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.notes')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.privateNotes')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.tags')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalVisits')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalCovers')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalCancellations')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalNoShows')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpend')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpendPerCover')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalspendPerVisit')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.avgRating')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.referenceCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.externalUserId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.venueGroupId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltDay')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.userId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.userName')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.totalOrderCount')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.preferredLanguageCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.techComment')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.clientPhoto')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.client.clientVenueStats')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="client in clients" :key="client.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientView', params: { clientId: client.id } }">{{ client.id }}</router-link>
            </td>
            <td>{{ client.clientId }}</td>
            <td>{{ client.createdDate }}</td>
            <td>{{ client.updatedDate }}</td>
            <td>{{ client.deletedDate }}</td>
            <td>{{ client.lastname }}</td>
            <td>{{ client.firstname }}</td>
            <td>{{ client.gender }}</td>
            <td>{{ client.salutation }}</td>
            <td>{{ client.title }}</td>
            <td>{{ client.birthdayDay }}</td>
            <td>{{ client.birthdayMonth }}</td>
            <td>{{ client.birthdayAltMonth }}</td>
            <td>{{ client.anniversaryDay }}</td>
            <td>{{ client.anniversaryMonth }}</td>
            <td>{{ client.company }}</td>
            <td>{{ client.email }}</td>
            <td>{{ client.emailAlt }}</td>
            <td>{{ client.phoneNumber }}</td>
            <td>{{ client.phoneNumberlocale }}</td>
            <td>{{ client.phoneNumberalt }}</td>
            <td>{{ client.phoneNumberaltlocale }}</td>
            <td>{{ client.address }}</td>
            <td>{{ client.address2 }}</td>
            <td>{{ client.city }}</td>
            <td>{{ client.postalCode }}</td>
            <td>{{ client.state }}</td>
            <td>{{ client.country }}</td>
            <td>{{ client.isContactPrivate }}</td>
            <td>{{ client.isOnetimeGuest }}</td>
            <td>{{ client.status }}</td>
            <td>{{ client.loyaltyId }}</td>
            <td>{{ client.loyaltyRank }}</td>
            <td>{{ client.loyaltyTier }}</td>
            <td>{{ client.marketingOptin }}</td>
            <td>{{ client.marketingOptints }}</td>
            <td>{{ client.marketingOptOutts }}</td>
            <td>{{ client.hasBillingProfile }}</td>
            <td>{{ client.notes }}</td>
            <td>{{ client.privateNotes }}</td>
            <td>{{ client.tags }}</td>
            <td>{{ client.totalVisits }}</td>
            <td>{{ client.totalCovers }}</td>
            <td>{{ client.totalCancellations }}</td>
            <td>{{ client.totalNoShows }}</td>
            <td>{{ client.totalSpend }}</td>
            <td>{{ client.totalSpendPerCover }}</td>
            <td>{{ client.totalspendPerVisit }}</td>
            <td>{{ client.avgRating }}</td>
            <td>{{ client.referenceCode }}</td>
            <td>{{ client.externalUserId }}</td>
            <td>{{ client.venueGroupId }}</td>
            <td>{{ client.birthdayAltDay }}</td>
            <td>{{ client.userId }}</td>
            <td>{{ client.userName }}</td>
            <td>{{ client.totalOrderCount }}</td>
            <td>{{ client.preferredLanguageCode }}</td>
            <td>{{ client.techLineage }}</td>
            <td>{{ formatDateShort(client.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(client.techUpdatedDate) || '' }}</td>
            <td>{{ client.techMapping }}</td>
            <td>{{ client.techComment }}</td>
            <td>
              <div v-if="client.clientPhoto">
                <router-link :to="{ name: 'ClientPhotoView', params: { clientPhotoId: client.clientPhoto.id } }">{{
                  client.clientPhoto.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="client.clientVenueStats">
                <router-link :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: client.clientVenueStats.id } }">{{
                  client.clientVenueStats.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientView', params: { clientId: client.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(client)"
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
          id="sevenRoomsToHubApplicationApp.client.delete.question"
          data-cy="clientDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-client-heading" v-text="t$('sevenRoomsToHubApplicationApp.client.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-client"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeClient()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./client.component.ts"></script>
