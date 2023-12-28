<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="client">
        <h2 class="jh-entity-heading" data-cy="clientDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.client.detail.title')"></span> {{ client.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.clientId')"></span>
          </dt>
          <dd>
            <span>{{ client.clientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.createdDate')"></span>
          </dt>
          <dd>
            <span>{{ client.createdDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.updatedDate')"></span>
          </dt>
          <dd>
            <span>{{ client.updatedDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.deletedDate')"></span>
          </dt>
          <dd>
            <span>{{ client.deletedDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.lastname')"></span>
          </dt>
          <dd>
            <span>{{ client.lastname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.firstname')"></span>
          </dt>
          <dd>
            <span>{{ client.firstname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.gender')"></span>
          </dt>
          <dd>
            <span>{{ client.gender }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.salutation')"></span>
          </dt>
          <dd>
            <span>{{ client.salutation }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.title')"></span>
          </dt>
          <dd>
            <span>{{ client.title }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayDay')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayDay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayMonth')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayMonth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltMonth')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayAltMonth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryDay')"></span>
          </dt>
          <dd>
            <span>{{ client.anniversaryDay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryMonth')"></span>
          </dt>
          <dd>
            <span>{{ client.anniversaryMonth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.company')"></span>
          </dt>
          <dd>
            <span>{{ client.company }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.email')"></span>
          </dt>
          <dd>
            <span>{{ client.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.emailAlt')"></span>
          </dt>
          <dd>
            <span>{{ client.emailAlt }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberlocale')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumberlocale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberalt')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumberalt }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberaltlocale')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumberaltlocale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.address')"></span>
          </dt>
          <dd>
            <span>{{ client.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.address2')"></span>
          </dt>
          <dd>
            <span>{{ client.address2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.city')"></span>
          </dt>
          <dd>
            <span>{{ client.city }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.postalCode')"></span>
          </dt>
          <dd>
            <span>{{ client.postalCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.state')"></span>
          </dt>
          <dd>
            <span>{{ client.state }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.country')"></span>
          </dt>
          <dd>
            <span>{{ client.country }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.isContactPrivate')"></span>
          </dt>
          <dd>
            <span>{{ client.isContactPrivate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.isOnetimeGuest')"></span>
          </dt>
          <dd>
            <span>{{ client.isOnetimeGuest }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.status')"></span>
          </dt>
          <dd>
            <span>{{ client.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyId')"></span>
          </dt>
          <dd>
            <span>{{ client.loyaltyId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyRank')"></span>
          </dt>
          <dd>
            <span>{{ client.loyaltyRank }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyTier')"></span>
          </dt>
          <dd>
            <span>{{ client.loyaltyTier }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptin')"></span>
          </dt>
          <dd>
            <span>{{ client.marketingOptin }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptints')"></span>
          </dt>
          <dd>
            <span>{{ client.marketingOptints }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptOutts')"></span>
          </dt>
          <dd>
            <span>{{ client.marketingOptOutts }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.hasBillingProfile')"></span>
          </dt>
          <dd>
            <span>{{ client.hasBillingProfile }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.notes')"></span>
          </dt>
          <dd>
            <span>{{ client.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.privateNotes')"></span>
          </dt>
          <dd>
            <span>{{ client.privateNotes }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.tags')"></span>
          </dt>
          <dd>
            <span>{{ client.tags }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalVisits')"></span>
          </dt>
          <dd>
            <span>{{ client.totalVisits }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalCovers')"></span>
          </dt>
          <dd>
            <span>{{ client.totalCovers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalCancellations')"></span>
          </dt>
          <dd>
            <span>{{ client.totalCancellations }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalNoShows')"></span>
          </dt>
          <dd>
            <span>{{ client.totalNoShows }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpend')"></span>
          </dt>
          <dd>
            <span>{{ client.totalSpend }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpendPerCover')"></span>
          </dt>
          <dd>
            <span>{{ client.totalSpendPerCover }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalspendPerVisit')"></span>
          </dt>
          <dd>
            <span>{{ client.totalspendPerVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.avgRating')"></span>
          </dt>
          <dd>
            <span>{{ client.avgRating }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.referenceCode')"></span>
          </dt>
          <dd>
            <span>{{ client.referenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.externalUserId')"></span>
          </dt>
          <dd>
            <span>{{ client.externalUserId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.venueGroupId')"></span>
          </dt>
          <dd>
            <span>{{ client.venueGroupId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltDay')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayAltDay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.userId')"></span>
          </dt>
          <dd>
            <span>{{ client.userId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.userName')"></span>
          </dt>
          <dd>
            <span>{{ client.userName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalOrderCount')"></span>
          </dt>
          <dd>
            <span>{{ client.totalOrderCount }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.preferredLanguageCode')"></span>
          </dt>
          <dd>
            <span>{{ client.preferredLanguageCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ client.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="client.techCreatedDate">{{ formatDateLong(client.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="client.techUpdatedDate">{{ formatDateLong(client.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ client.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.techComment')"></span>
          </dt>
          <dd>
            <span>{{ client.techComment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.clientPhoto')"></span>
          </dt>
          <dd>
            <div v-if="client.clientPhoto">
              <router-link :to="{ name: 'ClientPhotoView', params: { clientPhotoId: client.clientPhoto.id } }">{{
                client.clientPhoto.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.client.clientVenueStats')"></span>
          </dt>
          <dd>
            <div v-if="client.clientVenueStats">
              <router-link :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: client.clientVenueStats.id } }">{{
                client.clientVenueStats.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="client.id" :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-details.component.ts"></script>
