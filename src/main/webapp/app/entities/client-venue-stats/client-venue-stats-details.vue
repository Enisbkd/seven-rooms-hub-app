<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="clientVenueStats">
        <h2 class="jh-entity-heading" data-cy="clientVenueStatsDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.detail.title')"></span> {{ clientVenueStats.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueId')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.venueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.avgRating')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.avgRating }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.bookedByNames')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.bookedByNames }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.lastVisitDate')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.lastVisitDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.numRatings')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.numRatings }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCancellations')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalCancellations }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCovers')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalCovers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalNoShows')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalNoShows }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpend')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpend }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocal')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocal }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalperCover')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocalperCover }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalPerVisit')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocalPerVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendperCover')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendperCover }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendPerVisit')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendPerVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalVisit')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptin')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.venueMarketingOptin }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptints')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.venueMarketingOptints }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="clientVenueStats.techCreatedDate">{{ formatDateLong(clientVenueStats.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="clientVenueStats.techUpdatedDate">{{ formatDateLong(clientVenueStats.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techComment')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.techComment }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="clientVenueStats.id"
          :to="{ name: 'ClientVenueStatsEdit', params: { clientVenueStatsId: clientVenueStats.id } }"
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

<script lang="ts" src="./client-venue-stats-details.component.ts"></script>
