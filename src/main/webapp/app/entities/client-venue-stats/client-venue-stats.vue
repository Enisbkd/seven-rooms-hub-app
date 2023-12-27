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
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('avgRating')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.avgRating')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'avgRating'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bookedByNames')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.bookedByNames')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bookedByNames'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastVisitDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.lastVisitDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastVisitDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('numRatings')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.numRatings')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numRatings'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalCancellations')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCancellations')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalCancellations'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalCovers')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCovers')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalCovers'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalNoShows')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalNoShows')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalNoShows'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpend')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpend')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalSpend'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpendLocal')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocal')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalSpendLocal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpendLocalperCover')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalperCover')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'totalSpendLocalperCover'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpendLocalPerVisit')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalPerVisit')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'totalSpendLocalPerVisit'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpendperCover')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendperCover')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalSpendperCover'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpendPerVisit')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendPerVisit')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalSpendPerVisit'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalVisit')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalVisit')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalVisit'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueMarketingOptin')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptin')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueMarketingOptin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueMarketingOptints')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptints')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueMarketingOptints'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techLineage')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techLineage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techLineage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techCreatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techCreatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techCreatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techUpdatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techUpdatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techUpdatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techMapping')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techMapping')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techMapping'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techComment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techComment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techComment'"></jhi-sort-indicator>
            </th>
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
    <div v-show="clientVenueStats && clientVenueStats.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-venue-stats.component.ts"></script>
