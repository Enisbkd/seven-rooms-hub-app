<template>
  <div>
    <h2 id="page-heading" data-cy="ClientVenueStatsHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.home.title')" id="client-venue-stats-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ClientVenueStatsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-client-venue-stats"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clientVenueStats && clientVenueStats.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="clientVenueStats && clientVenueStats.length > 0">
      <table class="table table-striped" aria-describedby="clientVenueStats">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.avgRating')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.bookedByNames')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.lastVisitDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.numRatings')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCancellations')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCovers')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalNoShows')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpend')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocal')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalperCover')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalPerVisit')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendperCover')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendPerVisit')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalVisit')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptin')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptints')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techComment')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="clientVenueStats in clientVenueStats" :key="clientVenueStats.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: clientVenueStats.id } }">{{
                clientVenueStats.id
              }}</router-link>
            </td>
            <td>{{ clientVenueStats.venueId }}</td>
            <td>{{ clientVenueStats.avgRating }}</td>
            <td>{{ clientVenueStats.bookedByNames }}</td>
            <td>{{ clientVenueStats.lastVisitDate }}</td>
            <td>{{ clientVenueStats.numRatings }}</td>
            <td>{{ clientVenueStats.totalCancellations }}</td>
            <td>{{ clientVenueStats.totalCovers }}</td>
            <td>{{ clientVenueStats.totalNoShows }}</td>
            <td>{{ clientVenueStats.totalSpend }}</td>
            <td>{{ clientVenueStats.totalSpendLocal }}</td>
            <td>{{ clientVenueStats.totalSpendLocalperCover }}</td>
            <td>{{ clientVenueStats.totalSpendLocalPerVisit }}</td>
            <td>{{ clientVenueStats.totalSpendperCover }}</td>
            <td>{{ clientVenueStats.totalSpendPerVisit }}</td>
            <td>{{ clientVenueStats.totalVisit }}</td>
            <td>{{ clientVenueStats.venueMarketingOptin }}</td>
            <td>{{ clientVenueStats.venueMarketingOptints }}</td>
            <td>{{ clientVenueStats.techLineage }}</td>
            <td>{{ formatDateShort(clientVenueStats.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(clientVenueStats.techUpdatedDate) || '' }}</td>
            <td>{{ clientVenueStats.techMapping }}</td>
            <td>{{ clientVenueStats.techComment }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: clientVenueStats.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ClientVenueStatsEdit', params: { clientVenueStatsId: clientVenueStats.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(clientVenueStats)"
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
          id="sevenRoomsToHubApplicationApp.clientVenueStats.delete.question"
          data-cy="clientVenueStatsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-clientVenueStats-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-clientVenueStats"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeClientVenueStats()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./client-venue-stats.component.ts"></script>
