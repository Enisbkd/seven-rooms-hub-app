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
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.resvId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.created')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.updated')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.deleted')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupClientId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.date')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.duration')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.checkNumbers')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftCategory')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftPersistentId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.maxGuests')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioMale')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioFemale')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.status')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusDisplay')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusSimple')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.tableNumbers')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.accessPersistentId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivedGuests')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.isvip')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.bookedby')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientReferenceCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.lastname')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.firstname')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.email')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.phoneNumber')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.address')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.address2')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.city')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.postalCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.state')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.country')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyRank')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyTier')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.notes')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivalTime')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.seatedTime')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.leftTime')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientRequests')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.comps')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.compsPriceType')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.costOption')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.policy')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.minPrice')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.prePayment')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.onsitePayment')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.totalPayment')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.paidBy')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.servedBy')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.rating')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.problems')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.autoAssignments')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalClientId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalReferenceCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalUserId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.modifyReservationLink')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.referenceCode')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationSmsOptin')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationType')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendReminderEmail')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendreminderSms')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.sourceClientId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.userId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.userName')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techLineage')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techCreatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techUpdatedDate')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techMapping')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.techComment')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToHubApplicationApp.reservation.client')"></span></th>
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
  </div>
</template>

<script lang="ts" src="./reservation.component.ts"></script>
