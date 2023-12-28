<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.reservation.home.createOrEditLabel"
          data-cy="ReservationCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.reservation.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="reservation.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="reservation.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.resvId')"
              for="reservation-resvId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="resvId"
              id="reservation-resvId"
              data-cy="resvId"
              :class="{ valid: !v$.resvId.$invalid, invalid: v$.resvId.$invalid }"
              v-model="v$.resvId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.created')"
              for="reservation-created"
            ></label>
            <input
              type="text"
              class="form-control"
              name="created"
              id="reservation-created"
              data-cy="created"
              :class="{ valid: !v$.created.$invalid, invalid: v$.created.$invalid }"
              v-model="v$.created.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.updated')"
              for="reservation-updated"
            ></label>
            <input
              type="text"
              class="form-control"
              name="updated"
              id="reservation-updated"
              data-cy="updated"
              :class="{ valid: !v$.updated.$invalid, invalid: v$.updated.$invalid }"
              v-model="v$.updated.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.deleted')"
              for="reservation-deleted"
            ></label>
            <input
              type="text"
              class="form-control"
              name="deleted"
              id="reservation-deleted"
              data-cy="deleted"
              :class="{ valid: !v$.deleted.$invalid, invalid: v$.deleted.$invalid }"
              v-model="v$.deleted.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupClientId')"
              for="reservation-venueGroupClientId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueGroupClientId"
              id="reservation-venueGroupClientId"
              data-cy="venueGroupClientId"
              :class="{ valid: !v$.venueGroupClientId.$invalid, invalid: v$.venueGroupClientId.$invalid }"
              v-model="v$.venueGroupClientId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueGroupId')"
              for="reservation-venueGroupId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueGroupId"
              id="reservation-venueGroupId"
              data-cy="venueGroupId"
              :class="{ valid: !v$.venueGroupId.$invalid, invalid: v$.venueGroupId.$invalid }"
              v-model="v$.venueGroupId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.venueId')"
              for="reservation-venueId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueId"
              id="reservation-venueId"
              data-cy="venueId"
              :class="{ valid: !v$.venueId.$invalid, invalid: v$.venueId.$invalid }"
              v-model="v$.venueId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.reservation.date')" for="reservation-date"></label>
            <input
              type="text"
              class="form-control"
              name="date"
              id="reservation-date"
              data-cy="date"
              :class="{ valid: !v$.date.$invalid, invalid: v$.date.$invalid }"
              v-model="v$.date.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.duration')"
              for="reservation-duration"
            ></label>
            <input
              type="number"
              class="form-control"
              name="duration"
              id="reservation-duration"
              data-cy="duration"
              :class="{ valid: !v$.duration.$invalid, invalid: v$.duration.$invalid }"
              v-model.number="v$.duration.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.checkNumbers')"
              for="reservation-checkNumbers"
            ></label>
            <input
              type="text"
              class="form-control"
              name="checkNumbers"
              id="reservation-checkNumbers"
              data-cy="checkNumbers"
              :class="{ valid: !v$.checkNumbers.$invalid, invalid: v$.checkNumbers.$invalid }"
              v-model="v$.checkNumbers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftCategory')"
              for="reservation-shiftCategory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="shiftCategory"
              id="reservation-shiftCategory"
              data-cy="shiftCategory"
              :class="{ valid: !v$.shiftCategory.$invalid, invalid: v$.shiftCategory.$invalid }"
              v-model="v$.shiftCategory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.shiftPersistentId')"
              for="reservation-shiftPersistentId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="shiftPersistentId"
              id="reservation-shiftPersistentId"
              data-cy="shiftPersistentId"
              :class="{ valid: !v$.shiftPersistentId.$invalid, invalid: v$.shiftPersistentId.$invalid }"
              v-model="v$.shiftPersistentId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.maxGuests')"
              for="reservation-maxGuests"
            ></label>
            <input
              type="number"
              class="form-control"
              name="maxGuests"
              id="reservation-maxGuests"
              data-cy="maxGuests"
              :class="{ valid: !v$.maxGuests.$invalid, invalid: v$.maxGuests.$invalid }"
              v-model.number="v$.maxGuests.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioMale')"
              for="reservation-mfratioMale"
            ></label>
            <input
              type="number"
              class="form-control"
              name="mfratioMale"
              id="reservation-mfratioMale"
              data-cy="mfratioMale"
              :class="{ valid: !v$.mfratioMale.$invalid, invalid: v$.mfratioMale.$invalid }"
              v-model.number="v$.mfratioMale.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.mfratioFemale')"
              for="reservation-mfratioFemale"
            ></label>
            <input
              type="number"
              class="form-control"
              name="mfratioFemale"
              id="reservation-mfratioFemale"
              data-cy="mfratioFemale"
              :class="{ valid: !v$.mfratioFemale.$invalid, invalid: v$.mfratioFemale.$invalid }"
              v-model.number="v$.mfratioFemale.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.status')"
              for="reservation-status"
            ></label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="reservation-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusDisplay')"
              for="reservation-statusDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="statusDisplay"
              id="reservation-statusDisplay"
              data-cy="statusDisplay"
              :class="{ valid: !v$.statusDisplay.$invalid, invalid: v$.statusDisplay.$invalid }"
              v-model="v$.statusDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.statusSimple')"
              for="reservation-statusSimple"
            ></label>
            <input
              type="text"
              class="form-control"
              name="statusSimple"
              id="reservation-statusSimple"
              data-cy="statusSimple"
              :class="{ valid: !v$.statusSimple.$invalid, invalid: v$.statusSimple.$invalid }"
              v-model="v$.statusSimple.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.tableNumbers')"
              for="reservation-tableNumbers"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tableNumbers"
              id="reservation-tableNumbers"
              data-cy="tableNumbers"
              :class="{ valid: !v$.tableNumbers.$invalid, invalid: v$.tableNumbers.$invalid }"
              v-model="v$.tableNumbers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.accessPersistentId')"
              for="reservation-accessPersistentId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="accessPersistentId"
              id="reservation-accessPersistentId"
              data-cy="accessPersistentId"
              :class="{ valid: !v$.accessPersistentId.$invalid, invalid: v$.accessPersistentId.$invalid }"
              v-model="v$.accessPersistentId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivedGuests')"
              for="reservation-arrivedGuests"
            ></label>
            <input
              type="number"
              class="form-control"
              name="arrivedGuests"
              id="reservation-arrivedGuests"
              data-cy="arrivedGuests"
              :class="{ valid: !v$.arrivedGuests.$invalid, invalid: v$.arrivedGuests.$invalid }"
              v-model.number="v$.arrivedGuests.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.isvip')"
              for="reservation-isvip"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isvip"
              id="reservation-isvip"
              data-cy="isvip"
              :class="{ valid: !v$.isvip.$invalid, invalid: v$.isvip.$invalid }"
              v-model="v$.isvip.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.bookedby')"
              for="reservation-bookedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bookedby"
              id="reservation-bookedby"
              data-cy="bookedby"
              :class="{ valid: !v$.bookedby.$invalid, invalid: v$.bookedby.$invalid }"
              v-model="v$.bookedby.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientReferenceCode')"
              for="reservation-clientReferenceCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="clientReferenceCode"
              id="reservation-clientReferenceCode"
              data-cy="clientReferenceCode"
              :class="{ valid: !v$.clientReferenceCode.$invalid, invalid: v$.clientReferenceCode.$invalid }"
              v-model="v$.clientReferenceCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.lastname')"
              for="reservation-lastname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastname"
              id="reservation-lastname"
              data-cy="lastname"
              :class="{ valid: !v$.lastname.$invalid, invalid: v$.lastname.$invalid }"
              v-model="v$.lastname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.firstname')"
              for="reservation-firstname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="firstname"
              id="reservation-firstname"
              data-cy="firstname"
              :class="{ valid: !v$.firstname.$invalid, invalid: v$.firstname.$invalid }"
              v-model="v$.firstname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.email')"
              for="reservation-email"
            ></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="reservation-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.phoneNumber')"
              for="reservation-phoneNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumber"
              id="reservation-phoneNumber"
              data-cy="phoneNumber"
              :class="{ valid: !v$.phoneNumber.$invalid, invalid: v$.phoneNumber.$invalid }"
              v-model="v$.phoneNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.address')"
              for="reservation-address"
            ></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="reservation-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.address2')"
              for="reservation-address2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="address2"
              id="reservation-address2"
              data-cy="address2"
              :class="{ valid: !v$.address2.$invalid, invalid: v$.address2.$invalid }"
              v-model="v$.address2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.reservation.city')" for="reservation-city"></label>
            <input
              type="text"
              class="form-control"
              name="city"
              id="reservation-city"
              data-cy="city"
              :class="{ valid: !v$.city.$invalid, invalid: v$.city.$invalid }"
              v-model="v$.city.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.postalCode')"
              for="reservation-postalCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="postalCode"
              id="reservation-postalCode"
              data-cy="postalCode"
              :class="{ valid: !v$.postalCode.$invalid, invalid: v$.postalCode.$invalid }"
              v-model="v$.postalCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.state')"
              for="reservation-state"
            ></label>
            <input
              type="text"
              class="form-control"
              name="state"
              id="reservation-state"
              data-cy="state"
              :class="{ valid: !v$.state.$invalid, invalid: v$.state.$invalid }"
              v-model="v$.state.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.country')"
              for="reservation-country"
            ></label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="reservation-country"
              data-cy="country"
              :class="{ valid: !v$.country.$invalid, invalid: v$.country.$invalid }"
              v-model="v$.country.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyId')"
              for="reservation-loyaltyId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="loyaltyId"
              id="reservation-loyaltyId"
              data-cy="loyaltyId"
              :class="{ valid: !v$.loyaltyId.$invalid, invalid: v$.loyaltyId.$invalid }"
              v-model="v$.loyaltyId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyRank')"
              for="reservation-loyaltyRank"
            ></label>
            <input
              type="number"
              class="form-control"
              name="loyaltyRank"
              id="reservation-loyaltyRank"
              data-cy="loyaltyRank"
              :class="{ valid: !v$.loyaltyRank.$invalid, invalid: v$.loyaltyRank.$invalid }"
              v-model.number="v$.loyaltyRank.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.loyaltyTier')"
              for="reservation-loyaltyTier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="loyaltyTier"
              id="reservation-loyaltyTier"
              data-cy="loyaltyTier"
              :class="{ valid: !v$.loyaltyTier.$invalid, invalid: v$.loyaltyTier.$invalid }"
              v-model="v$.loyaltyTier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.notes')"
              for="reservation-notes"
            ></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="reservation-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.arrivalTime')"
              for="reservation-arrivalTime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arrivalTime"
              id="reservation-arrivalTime"
              data-cy="arrivalTime"
              :class="{ valid: !v$.arrivalTime.$invalid, invalid: v$.arrivalTime.$invalid }"
              v-model="v$.arrivalTime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.seatedTime')"
              for="reservation-seatedTime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="seatedTime"
              id="reservation-seatedTime"
              data-cy="seatedTime"
              :class="{ valid: !v$.seatedTime.$invalid, invalid: v$.seatedTime.$invalid }"
              v-model="v$.seatedTime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.leftTime')"
              for="reservation-leftTime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="leftTime"
              id="reservation-leftTime"
              data-cy="leftTime"
              :class="{ valid: !v$.leftTime.$invalid, invalid: v$.leftTime.$invalid }"
              v-model="v$.leftTime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.clientRequests')"
              for="reservation-clientRequests"
            ></label>
            <input
              type="text"
              class="form-control"
              name="clientRequests"
              id="reservation-clientRequests"
              data-cy="clientRequests"
              :class="{ valid: !v$.clientRequests.$invalid, invalid: v$.clientRequests.$invalid }"
              v-model="v$.clientRequests.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.comps')"
              for="reservation-comps"
            ></label>
            <input
              type="number"
              class="form-control"
              name="comps"
              id="reservation-comps"
              data-cy="comps"
              :class="{ valid: !v$.comps.$invalid, invalid: v$.comps.$invalid }"
              v-model.number="v$.comps.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.compsPriceType')"
              for="reservation-compsPriceType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="compsPriceType"
              id="reservation-compsPriceType"
              data-cy="compsPriceType"
              :class="{ valid: !v$.compsPriceType.$invalid, invalid: v$.compsPriceType.$invalid }"
              v-model="v$.compsPriceType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.costOption')"
              for="reservation-costOption"
            ></label>
            <input
              type="number"
              class="form-control"
              name="costOption"
              id="reservation-costOption"
              data-cy="costOption"
              :class="{ valid: !v$.costOption.$invalid, invalid: v$.costOption.$invalid }"
              v-model.number="v$.costOption.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.policy')"
              for="reservation-policy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="policy"
              id="reservation-policy"
              data-cy="policy"
              :class="{ valid: !v$.policy.$invalid, invalid: v$.policy.$invalid }"
              v-model="v$.policy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.minPrice')"
              for="reservation-minPrice"
            ></label>
            <input
              type="number"
              class="form-control"
              name="minPrice"
              id="reservation-minPrice"
              data-cy="minPrice"
              :class="{ valid: !v$.minPrice.$invalid, invalid: v$.minPrice.$invalid }"
              v-model.number="v$.minPrice.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.prePayment')"
              for="reservation-prePayment"
            ></label>
            <input
              type="number"
              class="form-control"
              name="prePayment"
              id="reservation-prePayment"
              data-cy="prePayment"
              :class="{ valid: !v$.prePayment.$invalid, invalid: v$.prePayment.$invalid }"
              v-model.number="v$.prePayment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.onsitePayment')"
              for="reservation-onsitePayment"
            ></label>
            <input
              type="number"
              class="form-control"
              name="onsitePayment"
              id="reservation-onsitePayment"
              data-cy="onsitePayment"
              :class="{ valid: !v$.onsitePayment.$invalid, invalid: v$.onsitePayment.$invalid }"
              v-model.number="v$.onsitePayment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.totalPayment')"
              for="reservation-totalPayment"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalPayment"
              id="reservation-totalPayment"
              data-cy="totalPayment"
              :class="{ valid: !v$.totalPayment.$invalid, invalid: v$.totalPayment.$invalid }"
              v-model.number="v$.totalPayment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.paidBy')"
              for="reservation-paidBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="paidBy"
              id="reservation-paidBy"
              data-cy="paidBy"
              :class="{ valid: !v$.paidBy.$invalid, invalid: v$.paidBy.$invalid }"
              v-model="v$.paidBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.servedBy')"
              for="reservation-servedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="servedBy"
              id="reservation-servedBy"
              data-cy="servedBy"
              :class="{ valid: !v$.servedBy.$invalid, invalid: v$.servedBy.$invalid }"
              v-model="v$.servedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.rating')"
              for="reservation-rating"
            ></label>
            <input
              type="number"
              class="form-control"
              name="rating"
              id="reservation-rating"
              data-cy="rating"
              :class="{ valid: !v$.rating.$invalid, invalid: v$.rating.$invalid }"
              v-model.number="v$.rating.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.problems')"
              for="reservation-problems"
            ></label>
            <input
              type="text"
              class="form-control"
              name="problems"
              id="reservation-problems"
              data-cy="problems"
              :class="{ valid: !v$.problems.$invalid, invalid: v$.problems.$invalid }"
              v-model="v$.problems.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.autoAssignments')"
              for="reservation-autoAssignments"
            ></label>
            <input
              type="text"
              class="form-control"
              name="autoAssignments"
              id="reservation-autoAssignments"
              data-cy="autoAssignments"
              :class="{ valid: !v$.autoAssignments.$invalid, invalid: v$.autoAssignments.$invalid }"
              v-model="v$.autoAssignments.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalClientId')"
              for="reservation-externalClientId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalClientId"
              id="reservation-externalClientId"
              data-cy="externalClientId"
              :class="{ valid: !v$.externalClientId.$invalid, invalid: v$.externalClientId.$invalid }"
              v-model="v$.externalClientId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalId')"
              for="reservation-externalId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalId"
              id="reservation-externalId"
              data-cy="externalId"
              :class="{ valid: !v$.externalId.$invalid, invalid: v$.externalId.$invalid }"
              v-model="v$.externalId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalReferenceCode')"
              for="reservation-externalReferenceCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalReferenceCode"
              id="reservation-externalReferenceCode"
              data-cy="externalReferenceCode"
              :class="{ valid: !v$.externalReferenceCode.$invalid, invalid: v$.externalReferenceCode.$invalid }"
              v-model="v$.externalReferenceCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.externalUserId')"
              for="reservation-externalUserId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalUserId"
              id="reservation-externalUserId"
              data-cy="externalUserId"
              :class="{ valid: !v$.externalUserId.$invalid, invalid: v$.externalUserId.$invalid }"
              v-model="v$.externalUserId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.modifyReservationLink')"
              for="reservation-modifyReservationLink"
            ></label>
            <input
              type="text"
              class="form-control"
              name="modifyReservationLink"
              id="reservation-modifyReservationLink"
              data-cy="modifyReservationLink"
              :class="{ valid: !v$.modifyReservationLink.$invalid, invalid: v$.modifyReservationLink.$invalid }"
              v-model="v$.modifyReservationLink.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.referenceCode')"
              for="reservation-referenceCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="referenceCode"
              id="reservation-referenceCode"
              data-cy="referenceCode"
              :class="{ valid: !v$.referenceCode.$invalid, invalid: v$.referenceCode.$invalid }"
              v-model="v$.referenceCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationSmsOptin')"
              for="reservation-reservationSmsOptin"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="reservationSmsOptin"
              id="reservation-reservationSmsOptin"
              data-cy="reservationSmsOptin"
              :class="{ valid: !v$.reservationSmsOptin.$invalid, invalid: v$.reservationSmsOptin.$invalid }"
              v-model="v$.reservationSmsOptin.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.reservationType')"
              for="reservation-reservationType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="reservationType"
              id="reservation-reservationType"
              data-cy="reservationType"
              :class="{ valid: !v$.reservationType.$invalid, invalid: v$.reservationType.$invalid }"
              v-model="v$.reservationType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendReminderEmail')"
              for="reservation-sendReminderEmail"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="sendReminderEmail"
              id="reservation-sendReminderEmail"
              data-cy="sendReminderEmail"
              :class="{ valid: !v$.sendReminderEmail.$invalid, invalid: v$.sendReminderEmail.$invalid }"
              v-model="v$.sendReminderEmail.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.sendreminderSms')"
              for="reservation-sendreminderSms"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="sendreminderSms"
              id="reservation-sendreminderSms"
              data-cy="sendreminderSms"
              :class="{ valid: !v$.sendreminderSms.$invalid, invalid: v$.sendreminderSms.$invalid }"
              v-model="v$.sendreminderSms.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.sourceClientId')"
              for="reservation-sourceClientId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="sourceClientId"
              id="reservation-sourceClientId"
              data-cy="sourceClientId"
              :class="{ valid: !v$.sourceClientId.$invalid, invalid: v$.sourceClientId.$invalid }"
              v-model="v$.sourceClientId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.userId')"
              for="reservation-userId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="userId"
              id="reservation-userId"
              data-cy="userId"
              :class="{ valid: !v$.userId.$invalid, invalid: v$.userId.$invalid }"
              v-model="v$.userId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.userName')"
              for="reservation-userName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="userName"
              id="reservation-userName"
              data-cy="userName"
              :class="{ valid: !v$.userName.$invalid, invalid: v$.userName.$invalid }"
              v-model="v$.userName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.techLineage')"
              for="reservation-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="reservation-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.techCreatedDate')"
              for="reservation-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="reservation-techCreatedDate"
                data-cy="techCreatedDate"
                type="datetime-local"
                class="form-control"
                name="techCreatedDate"
                :class="{ valid: !v$.techCreatedDate.$invalid, invalid: v$.techCreatedDate.$invalid }"
                :value="convertDateTimeFromServer(v$.techCreatedDate.$model)"
                @change="updateZonedDateTimeField('techCreatedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.techUpdatedDate')"
              for="reservation-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="reservation-techUpdatedDate"
                data-cy="techUpdatedDate"
                type="datetime-local"
                class="form-control"
                name="techUpdatedDate"
                :class="{ valid: !v$.techUpdatedDate.$invalid, invalid: v$.techUpdatedDate.$invalid }"
                :value="convertDateTimeFromServer(v$.techUpdatedDate.$model)"
                @change="updateZonedDateTimeField('techUpdatedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.techMapping')"
              for="reservation-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="reservation-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.techComment')"
              for="reservation-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="reservation-techComment"
              data-cy="techComment"
              :class="{ valid: !v$.techComment.$invalid, invalid: v$.techComment.$invalid }"
              v-model="v$.techComment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.reservation.client')"
              for="reservation-client"
            ></label>
            <select class="form-control" id="reservation-client" data-cy="client" name="client" v-model="reservation.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="reservation.client && clientOption.id === reservation.client.id ? reservation.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./reservation-update.component.ts"></script>
