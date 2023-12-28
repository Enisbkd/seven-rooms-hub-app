<template>
  <div>
    <h2 id="page-heading" data-cy="ReservationHeading">
      <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.home.title')" id="reservation-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ReservationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-reservation"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && reservations && reservations.length === 0">
      <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="reservations && reservations.length > 0">
      <table class="table table-striped" aria-describedby="reservations">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('resvId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.resvId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'resvId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('created')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.created')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'created'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updated')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.updated')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updated'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('deleted')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.deleted')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deleted'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueGroupClientId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupClientId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueGroupClientId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueGroupId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueGroupId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('date')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.date')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'date'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('duration')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.duration')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'duration'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkNumbers')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.checkNumbers')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkNumbers'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shiftCategory')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftCategory')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shiftCategory'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shiftPersistentId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftPersistentId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shiftPersistentId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('maxGuests')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.maxGuests')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'maxGuests'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mfratioMale')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioMale')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mfratioMale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mfratioFemale')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioFemale')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mfratioFemale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.status')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('statusDisplay')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusDisplay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusDisplay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('statusSimple')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusSimple')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusSimple'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tableNumbers')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.tableNumbers')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tableNumbers'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('accessPersistentId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.accessPersistentId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'accessPersistentId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrivedGuests')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivedGuests')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrivedGuests'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isvip')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.isvip')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isvip'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bookedby')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.bookedby')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bookedby'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientReferenceCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientReferenceCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientReferenceCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastname')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.lastname')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastname'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('firstname')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.firstname')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'firstname'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumber')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.phoneNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address2')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.address2')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('city')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.city')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'city'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postalCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.postalCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postalCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('state')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.state')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'state'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('country')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.country')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'country'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loyaltyId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loyaltyId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loyaltyRank')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyRank')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loyaltyRank'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loyaltyTier')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyTier')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loyaltyTier'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrivalTime')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivalTime')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrivalTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('seatedTime')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.seatedTime')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seatedTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('leftTime')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.leftTime')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'leftTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientRequests')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientRequests')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientRequests'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('comps')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.comps')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'comps'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('compsPriceType')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.compsPriceType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'compsPriceType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('costOption')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.costOption')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'costOption'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('policy')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.policy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'policy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('minPrice')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.minPrice')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'minPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('prePayment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.prePayment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'prePayment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('onsitePayment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.onsitePayment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'onsitePayment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalPayment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.totalPayment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalPayment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paidBy')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.paidBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paidBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('servedBy')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.servedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'servedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('rating')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.rating')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rating'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('problems')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.problems')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'problems'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('autoAssignments')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.autoAssignments')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'autoAssignments'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('externalClientId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalClientId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'externalClientId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('externalId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'externalId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('externalReferenceCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalReferenceCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'externalReferenceCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('externalUserId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalUserId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'externalUserId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyReservationLink')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.modifyReservationLink')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyReservationLink'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('referenceCode')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.referenceCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'referenceCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reservationSmsOptin')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationSmsOptin')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reservationSmsOptin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reservationType')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reservationType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sendReminderEmail')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendReminderEmail')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sendReminderEmail'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sendreminderSms')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendreminderSms')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sendreminderSms'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sourceClientId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sourceClientId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sourceClientId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userId')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.userId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userName')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.userName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techLineage')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techLineage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techLineage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techCreatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techCreatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techCreatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techUpdatedDate')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techUpdatedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techUpdatedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techMapping')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techMapping')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techMapping'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techComment')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techComment')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techComment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('client.id')">
              <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.client')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="reservation in reservations" :key="reservation.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ReservationView', params: { reservationId: reservation.id } }">{{ reservation.id }}</router-link>
            </td>
            <td>{{ reservation.resvId }}</td>
            <td>{{ reservation.created }}</td>
            <td>{{ reservation.updated }}</td>
            <td>{{ reservation.deleted }}</td>
            <td>{{ reservation.venueGroupClientId }}</td>
            <td>{{ reservation.venueGroupId }}</td>
            <td>{{ reservation.venueId }}</td>
            <td>{{ reservation.date }}</td>
            <td>{{ reservation.duration }}</td>
            <td>{{ reservation.checkNumbers }}</td>
            <td>{{ reservation.shiftCategory }}</td>
            <td>{{ reservation.shiftPersistentId }}</td>
            <td>{{ reservation.maxGuests }}</td>
            <td>{{ reservation.mfratioMale }}</td>
            <td>{{ reservation.mfratioFemale }}</td>
            <td>{{ reservation.status }}</td>
            <td>{{ reservation.statusDisplay }}</td>
            <td>{{ reservation.statusSimple }}</td>
            <td>{{ reservation.tableNumbers }}</td>
            <td>{{ reservation.accessPersistentId }}</td>
            <td>{{ reservation.arrivedGuests }}</td>
            <td>{{ reservation.isvip }}</td>
            <td>{{ reservation.bookedby }}</td>
            <td>{{ reservation.clientReferenceCode }}</td>
            <td>{{ reservation.lastname }}</td>
            <td>{{ reservation.firstname }}</td>
            <td>{{ reservation.email }}</td>
            <td>{{ reservation.phoneNumber }}</td>
            <td>{{ reservation.address }}</td>
            <td>{{ reservation.address2 }}</td>
            <td>{{ reservation.city }}</td>
            <td>{{ reservation.postalCode }}</td>
            <td>{{ reservation.state }}</td>
            <td>{{ reservation.country }}</td>
            <td>{{ reservation.loyaltyId }}</td>
            <td>{{ reservation.loyaltyRank }}</td>
            <td>{{ reservation.loyaltyTier }}</td>
            <td>{{ reservation.notes }}</td>
            <td>{{ reservation.arrivalTime }}</td>
            <td>{{ reservation.seatedTime }}</td>
            <td>{{ reservation.leftTime }}</td>
            <td>{{ reservation.clientRequests }}</td>
            <td>{{ reservation.comps }}</td>
            <td>{{ reservation.compsPriceType }}</td>
            <td>{{ reservation.costOption }}</td>
            <td>{{ reservation.policy }}</td>
            <td>{{ reservation.minPrice }}</td>
            <td>{{ reservation.prePayment }}</td>
            <td>{{ reservation.onsitePayment }}</td>
            <td>{{ reservation.totalPayment }}</td>
            <td>{{ reservation.paidBy }}</td>
            <td>{{ reservation.servedBy }}</td>
            <td>{{ reservation.rating }}</td>
            <td>{{ reservation.problems }}</td>
            <td>{{ reservation.autoAssignments }}</td>
            <td>{{ reservation.externalClientId }}</td>
            <td>{{ reservation.externalId }}</td>
            <td>{{ reservation.externalReferenceCode }}</td>
            <td>{{ reservation.externalUserId }}</td>
            <td>{{ reservation.modifyReservationLink }}</td>
            <td>{{ reservation.referenceCode }}</td>
            <td>{{ reservation.reservationSmsOptin }}</td>
            <td>{{ reservation.reservationType }}</td>
            <td>{{ reservation.sendReminderEmail }}</td>
            <td>{{ reservation.sendreminderSms }}</td>
            <td>{{ reservation.sourceClientId }}</td>
            <td>{{ reservation.userId }}</td>
            <td>{{ reservation.userName }}</td>
            <td>{{ reservation.techLineage }}</td>
            <td>{{ formatDateShort(reservation.techCreatedDate) || '' }}</td>
            <td>{{ formatDateShort(reservation.techUpdatedDate) || '' }}</td>
            <td>{{ reservation.techMapping }}</td>
            <td>{{ reservation.techComment }}</td>
            <td>
              <div v-if="reservation.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: reservation.client.id } }">{{
                  reservation.client.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ReservationView', params: { reservationId: reservation.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ReservationEdit', params: { reservationId: reservation.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(reservation)"
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
          id="sevenRoomsToHubApplicationApp.reservation.delete.question"
          data-cy="reservationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-reservation-heading"
          v-text="t$('sevenRoomsToHubApplicationApp.reservation.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-reservation"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeReservation()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="reservations && reservations.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./reservation.component.ts"></script>
