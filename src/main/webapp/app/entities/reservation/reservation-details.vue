<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="reservation">
        <h2 class="jh-entity-heading" data-cy="reservationDetailsHeading">
          <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.detail.title')"></span> {{ reservation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.resvId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.resvId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.created')"></span>
          </dt>
          <dd>
            <span>{{ reservation.created }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.updated')"></span>
          </dt>
          <dd>
            <span>{{ reservation.updated }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.deleted')"></span>
          </dt>
          <dd>
            <span>{{ reservation.deleted }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupClientId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.venueGroupClientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.venueGroupId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.venueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.date')"></span>
          </dt>
          <dd>
            <span>{{ reservation.date }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.duration')"></span>
          </dt>
          <dd>
            <span>{{ reservation.duration }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.checkNumbers')"></span>
          </dt>
          <dd>
            <span>{{ reservation.checkNumbers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftCategory')"></span>
          </dt>
          <dd>
            <span>{{ reservation.shiftCategory }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftPersistentId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.shiftPersistentId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.maxGuests')"></span>
          </dt>
          <dd>
            <span>{{ reservation.maxGuests }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioMale')"></span>
          </dt>
          <dd>
            <span>{{ reservation.mfratioMale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioFemale')"></span>
          </dt>
          <dd>
            <span>{{ reservation.mfratioFemale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.status')"></span>
          </dt>
          <dd>
            <span>{{ reservation.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusDisplay')"></span>
          </dt>
          <dd>
            <span>{{ reservation.statusDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusSimple')"></span>
          </dt>
          <dd>
            <span>{{ reservation.statusSimple }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.tableNumbers')"></span>
          </dt>
          <dd>
            <span>{{ reservation.tableNumbers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.accessPersistentId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.accessPersistentId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivedGuests')"></span>
          </dt>
          <dd>
            <span>{{ reservation.arrivedGuests }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.isvip')"></span>
          </dt>
          <dd>
            <span>{{ reservation.isvip }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.bookedby')"></span>
          </dt>
          <dd>
            <span>{{ reservation.bookedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientReferenceCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.clientReferenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.lastname')"></span>
          </dt>
          <dd>
            <span>{{ reservation.lastname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.firstname')"></span>
          </dt>
          <dd>
            <span>{{ reservation.firstname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.email')"></span>
          </dt>
          <dd>
            <span>{{ reservation.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.phoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ reservation.phoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.address')"></span>
          </dt>
          <dd>
            <span>{{ reservation.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.address2')"></span>
          </dt>
          <dd>
            <span>{{ reservation.address2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.city')"></span>
          </dt>
          <dd>
            <span>{{ reservation.city }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.postalCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.postalCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.state')"></span>
          </dt>
          <dd>
            <span>{{ reservation.state }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.country')"></span>
          </dt>
          <dd>
            <span>{{ reservation.country }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.loyaltyId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyRank')"></span>
          </dt>
          <dd>
            <span>{{ reservation.loyaltyRank }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyTier')"></span>
          </dt>
          <dd>
            <span>{{ reservation.loyaltyTier }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.notes')"></span>
          </dt>
          <dd>
            <span>{{ reservation.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivalTime')"></span>
          </dt>
          <dd>
            <span>{{ reservation.arrivalTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.seatedTime')"></span>
          </dt>
          <dd>
            <span>{{ reservation.seatedTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.leftTime')"></span>
          </dt>
          <dd>
            <span>{{ reservation.leftTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientRequests')"></span>
          </dt>
          <dd>
            <span>{{ reservation.clientRequests }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.comps')"></span>
          </dt>
          <dd>
            <span>{{ reservation.comps }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.compsPriceType')"></span>
          </dt>
          <dd>
            <span>{{ reservation.compsPriceType }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.costOption')"></span>
          </dt>
          <dd>
            <span>{{ reservation.costOption }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.policy')"></span>
          </dt>
          <dd>
            <span>{{ reservation.policy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.minPrice')"></span>
          </dt>
          <dd>
            <span>{{ reservation.minPrice }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.prePayment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.prePayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.onsitePayment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.onsitePayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.totalPayment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.totalPayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.paidBy')"></span>
          </dt>
          <dd>
            <span>{{ reservation.paidBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.servedBy')"></span>
          </dt>
          <dd>
            <span>{{ reservation.servedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.rating')"></span>
          </dt>
          <dd>
            <span>{{ reservation.rating }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.problems')"></span>
          </dt>
          <dd>
            <span>{{ reservation.problems }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.autoAssignments')"></span>
          </dt>
          <dd>
            <span>{{ reservation.autoAssignments }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalClientId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalClientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalReferenceCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalReferenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalUserId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalUserId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.modifyReservationLink')"></span>
          </dt>
          <dd>
            <span>{{ reservation.modifyReservationLink }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.referenceCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.referenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationSmsOptin')"></span>
          </dt>
          <dd>
            <span>{{ reservation.reservationSmsOptin }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationType')"></span>
          </dt>
          <dd>
            <span>{{ reservation.reservationType }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendReminderEmail')"></span>
          </dt>
          <dd>
            <span>{{ reservation.sendReminderEmail }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendreminderSms')"></span>
          </dt>
          <dd>
            <span>{{ reservation.sendreminderSms }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sourceClientId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.sourceClientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.userId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.userId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.userName')"></span>
          </dt>
          <dd>
            <span>{{ reservation.userName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techLineage')"></span>
          </dt>
          <dd>
            <span>{{ reservation.techLineage }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techCreatedDate')"></span>
          </dt>
          <dd>
            <span v-if="reservation.techCreatedDate">{{ formatDateLong(reservation.techCreatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techUpdatedDate')"></span>
          </dt>
          <dd>
            <span v-if="reservation.techUpdatedDate">{{ formatDateLong(reservation.techUpdatedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techMapping')"></span>
          </dt>
          <dd>
            <span>{{ reservation.techMapping }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techComment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.techComment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToHubApplicationApp.reservation.client')"></span>
          </dt>
          <dd>
            <div v-if="reservation.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: reservation.client.id } }">{{
                reservation.client.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="reservation.id"
          :to="{ name: 'ReservationEdit', params: { reservationId: reservation.id } }"
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

<script lang="ts" src="./reservation-details.component.ts"></script>
