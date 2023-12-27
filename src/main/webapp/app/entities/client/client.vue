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
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.clientId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.updatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('deletedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.deletedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deletedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastname')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.lastname')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastname'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('firstname')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.firstname')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'firstname'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('gender')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.gender')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gender'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('salutation')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.salutation')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'salutation'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('title')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.title')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthdayDay')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayDay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthdayDay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthdayMonth')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayMonth')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthdayMonth'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthdayAltMonth')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltMonth')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthdayAltMonth'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('anniversaryDay')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryDay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'anniversaryDay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('anniversaryMonth')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryMonth')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'anniversaryMonth'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('company')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.company')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'company'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('emailAlt')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.emailAlt')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'emailAlt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumber')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumberlocale')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberlocale')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumberlocale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumberalt')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberalt')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumberalt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumberaltlocale')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberaltlocale')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumberaltlocale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address2')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.address2')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('city')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.city')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'city'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postalCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.postalCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postalCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('state')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.state')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'state'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('country')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.country')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'country'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isContactPrivate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.isContactPrivate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isContactPrivate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isOnetimeGuest')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.isOnetimeGuest')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isOnetimeGuest'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.status')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loyaltyId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loyaltyId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loyaltyRank')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyRank')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loyaltyRank'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loyaltyTier')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyTier')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loyaltyTier'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('marketingOptin')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptin')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'marketingOptin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('marketingOptints')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptints')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'marketingOptints'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('marketingOptOutts')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptOutts')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'marketingOptOutts'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('hasBillingProfile')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.hasBillingProfile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'hasBillingProfile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('privateNotes')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.privateNotes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'privateNotes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tags')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.tags')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tags'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalVisits')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalVisits')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalVisits'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalCovers')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalCovers')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalCovers'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalCancellations')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalCancellations')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalCancellations'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalNoShows')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalNoShows')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalNoShows'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpend')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpend')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalSpend'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalSpendPerCover')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpendPerCover')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalSpendPerCover'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalspendPerVisit')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalspendPerVisit')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalspendPerVisit'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('avgRating')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.avgRating')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'avgRating'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('referenceCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.referenceCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'referenceCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('externalUserId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.externalUserId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'externalUserId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueGroupId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.venueGroupId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueGroupId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthdayAltDay')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltDay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthdayAltDay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.userId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userName')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.userName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalOrderCount')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.totalOrderCount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalOrderCount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('preferredLanguageCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.preferredLanguageCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'preferredLanguageCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techLineage')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.techLineage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techLineage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techCreatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.techCreatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techCreatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techUpdatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.techUpdatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techUpdatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techMapping')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.techMapping')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techMapping'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techComment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.techComment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techComment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientPhoto.id')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.clientPhoto')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientPhoto.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientVenueStats.id')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.client.clientVenueStats')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientVenueStats.id'"></jhi-sort-indicator>
            </th>
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
    <div v-show="clients && clients.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client.component.ts"></script>
