<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.client.home.createOrEditLabel"
          data-cy="ClientCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.client.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.clientId')" for="client-clientId"></label>
            <input
              type="text"
              class="form-control"
              name="clientId"
              id="client-clientId"
              data-cy="clientId"
              :class="{ valid: !v$.clientId.$invalid, invalid: v$.clientId.$invalid }"
              v-model="v$.clientId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.createdDate')"
              for="client-createdDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="createdDate"
              id="client-createdDate"
              data-cy="createdDate"
              :class="{ valid: !v$.createdDate.$invalid, invalid: v$.createdDate.$invalid }"
              v-model="v$.createdDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.updatedDate')"
              for="client-updatedDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="updatedDate"
              id="client-updatedDate"
              data-cy="updatedDate"
              :class="{ valid: !v$.updatedDate.$invalid, invalid: v$.updatedDate.$invalid }"
              v-model="v$.updatedDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.deletedDate')"
              for="client-deletedDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="deletedDate"
              id="client-deletedDate"
              data-cy="deletedDate"
              :class="{ valid: !v$.deletedDate.$invalid, invalid: v$.deletedDate.$invalid }"
              v-model="v$.deletedDate.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.lastname')" for="client-lastname"></label>
            <input
              type="text"
              class="form-control"
              name="lastname"
              id="client-lastname"
              data-cy="lastname"
              :class="{ valid: !v$.lastname.$invalid, invalid: v$.lastname.$invalid }"
              v-model="v$.lastname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.firstname')" for="client-firstname"></label>
            <input
              type="text"
              class="form-control"
              name="firstname"
              id="client-firstname"
              data-cy="firstname"
              :class="{ valid: !v$.firstname.$invalid, invalid: v$.firstname.$invalid }"
              v-model="v$.firstname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.gender')" for="client-gender"></label>
            <input
              type="text"
              class="form-control"
              name="gender"
              id="client-gender"
              data-cy="gender"
              :class="{ valid: !v$.gender.$invalid, invalid: v$.gender.$invalid }"
              v-model="v$.gender.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.salutation')"
              for="client-salutation"
            ></label>
            <input
              type="text"
              class="form-control"
              name="salutation"
              id="client-salutation"
              data-cy="salutation"
              :class="{ valid: !v$.salutation.$invalid, invalid: v$.salutation.$invalid }"
              v-model="v$.salutation.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.title')" for="client-title"></label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="client-title"
              data-cy="title"
              :class="{ valid: !v$.title.$invalid, invalid: v$.title.$invalid }"
              v-model="v$.title.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayDay')"
              for="client-birthdayDay"
            ></label>
            <input
              type="number"
              class="form-control"
              name="birthdayDay"
              id="client-birthdayDay"
              data-cy="birthdayDay"
              :class="{ valid: !v$.birthdayDay.$invalid, invalid: v$.birthdayDay.$invalid }"
              v-model.number="v$.birthdayDay.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayMonth')"
              for="client-birthdayMonth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="birthdayMonth"
              id="client-birthdayMonth"
              data-cy="birthdayMonth"
              :class="{ valid: !v$.birthdayMonth.$invalid, invalid: v$.birthdayMonth.$invalid }"
              v-model.number="v$.birthdayMonth.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltMonth')"
              for="client-birthdayAltMonth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="birthdayAltMonth"
              id="client-birthdayAltMonth"
              data-cy="birthdayAltMonth"
              :class="{ valid: !v$.birthdayAltMonth.$invalid, invalid: v$.birthdayAltMonth.$invalid }"
              v-model.number="v$.birthdayAltMonth.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryDay')"
              for="client-anniversaryDay"
            ></label>
            <input
              type="number"
              class="form-control"
              name="anniversaryDay"
              id="client-anniversaryDay"
              data-cy="anniversaryDay"
              :class="{ valid: !v$.anniversaryDay.$invalid, invalid: v$.anniversaryDay.$invalid }"
              v-model.number="v$.anniversaryDay.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.anniversaryMonth')"
              for="client-anniversaryMonth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="anniversaryMonth"
              id="client-anniversaryMonth"
              data-cy="anniversaryMonth"
              :class="{ valid: !v$.anniversaryMonth.$invalid, invalid: v$.anniversaryMonth.$invalid }"
              v-model.number="v$.anniversaryMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.company')" for="client-company"></label>
            <input
              type="text"
              class="form-control"
              name="company"
              id="client-company"
              data-cy="company"
              :class="{ valid: !v$.company.$invalid, invalid: v$.company.$invalid }"
              v-model="v$.company.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.email')" for="client-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="client-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.emailAlt')" for="client-emailAlt"></label>
            <input
              type="text"
              class="form-control"
              name="emailAlt"
              id="client-emailAlt"
              data-cy="emailAlt"
              :class="{ valid: !v$.emailAlt.$invalid, invalid: v$.emailAlt.$invalid }"
              v-model="v$.emailAlt.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumber')"
              for="client-phoneNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumber"
              id="client-phoneNumber"
              data-cy="phoneNumber"
              :class="{ valid: !v$.phoneNumber.$invalid, invalid: v$.phoneNumber.$invalid }"
              v-model="v$.phoneNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberlocale')"
              for="client-phoneNumberlocale"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumberlocale"
              id="client-phoneNumberlocale"
              data-cy="phoneNumberlocale"
              :class="{ valid: !v$.phoneNumberlocale.$invalid, invalid: v$.phoneNumberlocale.$invalid }"
              v-model="v$.phoneNumberlocale.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberalt')"
              for="client-phoneNumberalt"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumberalt"
              id="client-phoneNumberalt"
              data-cy="phoneNumberalt"
              :class="{ valid: !v$.phoneNumberalt.$invalid, invalid: v$.phoneNumberalt.$invalid }"
              v-model="v$.phoneNumberalt.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.phoneNumberaltlocale')"
              for="client-phoneNumberaltlocale"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumberaltlocale"
              id="client-phoneNumberaltlocale"
              data-cy="phoneNumberaltlocale"
              :class="{ valid: !v$.phoneNumberaltlocale.$invalid, invalid: v$.phoneNumberaltlocale.$invalid }"
              v-model="v$.phoneNumberaltlocale.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.address')" for="client-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="client-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.address2')" for="client-address2"></label>
            <input
              type="text"
              class="form-control"
              name="address2"
              id="client-address2"
              data-cy="address2"
              :class="{ valid: !v$.address2.$invalid, invalid: v$.address2.$invalid }"
              v-model="v$.address2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.city')" for="client-city"></label>
            <input
              type="text"
              class="form-control"
              name="city"
              id="client-city"
              data-cy="city"
              :class="{ valid: !v$.city.$invalid, invalid: v$.city.$invalid }"
              v-model="v$.city.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.postalCode')"
              for="client-postalCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="postalCode"
              id="client-postalCode"
              data-cy="postalCode"
              :class="{ valid: !v$.postalCode.$invalid, invalid: v$.postalCode.$invalid }"
              v-model="v$.postalCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.state')" for="client-state"></label>
            <input
              type="text"
              class="form-control"
              name="state"
              id="client-state"
              data-cy="state"
              :class="{ valid: !v$.state.$invalid, invalid: v$.state.$invalid }"
              v-model="v$.state.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.country')" for="client-country"></label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="client-country"
              data-cy="country"
              :class="{ valid: !v$.country.$invalid, invalid: v$.country.$invalid }"
              v-model="v$.country.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.isContactPrivate')"
              for="client-isContactPrivate"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isContactPrivate"
              id="client-isContactPrivate"
              data-cy="isContactPrivate"
              :class="{ valid: !v$.isContactPrivate.$invalid, invalid: v$.isContactPrivate.$invalid }"
              v-model="v$.isContactPrivate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.isOnetimeGuest')"
              for="client-isOnetimeGuest"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isOnetimeGuest"
              id="client-isOnetimeGuest"
              data-cy="isOnetimeGuest"
              :class="{ valid: !v$.isOnetimeGuest.$invalid, invalid: v$.isOnetimeGuest.$invalid }"
              v-model="v$.isOnetimeGuest.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.status')" for="client-status"></label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="client-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyId')" for="client-loyaltyId"></label>
            <input
              type="text"
              class="form-control"
              name="loyaltyId"
              id="client-loyaltyId"
              data-cy="loyaltyId"
              :class="{ valid: !v$.loyaltyId.$invalid, invalid: v$.loyaltyId.$invalid }"
              v-model="v$.loyaltyId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyRank')"
              for="client-loyaltyRank"
            ></label>
            <input
              type="number"
              class="form-control"
              name="loyaltyRank"
              id="client-loyaltyRank"
              data-cy="loyaltyRank"
              :class="{ valid: !v$.loyaltyRank.$invalid, invalid: v$.loyaltyRank.$invalid }"
              v-model.number="v$.loyaltyRank.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.loyaltyTier')"
              for="client-loyaltyTier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="loyaltyTier"
              id="client-loyaltyTier"
              data-cy="loyaltyTier"
              :class="{ valid: !v$.loyaltyTier.$invalid, invalid: v$.loyaltyTier.$invalid }"
              v-model="v$.loyaltyTier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptin')"
              for="client-marketingOptin"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="marketingOptin"
              id="client-marketingOptin"
              data-cy="marketingOptin"
              :class="{ valid: !v$.marketingOptin.$invalid, invalid: v$.marketingOptin.$invalid }"
              v-model="v$.marketingOptin.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptints')"
              for="client-marketingOptints"
            ></label>
            <input
              type="text"
              class="form-control"
              name="marketingOptints"
              id="client-marketingOptints"
              data-cy="marketingOptints"
              :class="{ valid: !v$.marketingOptints.$invalid, invalid: v$.marketingOptints.$invalid }"
              v-model="v$.marketingOptints.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.marketingOptOutts')"
              for="client-marketingOptOutts"
            ></label>
            <input
              type="text"
              class="form-control"
              name="marketingOptOutts"
              id="client-marketingOptOutts"
              data-cy="marketingOptOutts"
              :class="{ valid: !v$.marketingOptOutts.$invalid, invalid: v$.marketingOptOutts.$invalid }"
              v-model="v$.marketingOptOutts.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.hasBillingProfile')"
              for="client-hasBillingProfile"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="hasBillingProfile"
              id="client-hasBillingProfile"
              data-cy="hasBillingProfile"
              :class="{ valid: !v$.hasBillingProfile.$invalid, invalid: v$.hasBillingProfile.$invalid }"
              v-model="v$.hasBillingProfile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.notes')" for="client-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="client-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.privateNotes')"
              for="client-privateNotes"
            ></label>
            <input
              type="text"
              class="form-control"
              name="privateNotes"
              id="client-privateNotes"
              data-cy="privateNotes"
              :class="{ valid: !v$.privateNotes.$invalid, invalid: v$.privateNotes.$invalid }"
              v-model="v$.privateNotes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.tags')" for="client-tags"></label>
            <input
              type="text"
              class="form-control"
              name="tags"
              id="client-tags"
              data-cy="tags"
              :class="{ valid: !v$.tags.$invalid, invalid: v$.tags.$invalid }"
              v-model="v$.tags.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalVisits')"
              for="client-totalVisits"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalVisits"
              id="client-totalVisits"
              data-cy="totalVisits"
              :class="{ valid: !v$.totalVisits.$invalid, invalid: v$.totalVisits.$invalid }"
              v-model.number="v$.totalVisits.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalCovers')"
              for="client-totalCovers"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalCovers"
              id="client-totalCovers"
              data-cy="totalCovers"
              :class="{ valid: !v$.totalCovers.$invalid, invalid: v$.totalCovers.$invalid }"
              v-model.number="v$.totalCovers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalCancellations')"
              for="client-totalCancellations"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalCancellations"
              id="client-totalCancellations"
              data-cy="totalCancellations"
              :class="{ valid: !v$.totalCancellations.$invalid, invalid: v$.totalCancellations.$invalid }"
              v-model.number="v$.totalCancellations.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalNoShows')"
              for="client-totalNoShows"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalNoShows"
              id="client-totalNoShows"
              data-cy="totalNoShows"
              :class="{ valid: !v$.totalNoShows.$invalid, invalid: v$.totalNoShows.$invalid }"
              v-model.number="v$.totalNoShows.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpend')"
              for="client-totalSpend"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpend"
              id="client-totalSpend"
              data-cy="totalSpend"
              :class="{ valid: !v$.totalSpend.$invalid, invalid: v$.totalSpend.$invalid }"
              v-model.number="v$.totalSpend.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalSpendPerCover')"
              for="client-totalSpendPerCover"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendPerCover"
              id="client-totalSpendPerCover"
              data-cy="totalSpendPerCover"
              :class="{ valid: !v$.totalSpendPerCover.$invalid, invalid: v$.totalSpendPerCover.$invalid }"
              v-model.number="v$.totalSpendPerCover.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalspendPerVisit')"
              for="client-totalspendPerVisit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalspendPerVisit"
              id="client-totalspendPerVisit"
              data-cy="totalspendPerVisit"
              :class="{ valid: !v$.totalspendPerVisit.$invalid, invalid: v$.totalspendPerVisit.$invalid }"
              v-model.number="v$.totalspendPerVisit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.avgRating')" for="client-avgRating"></label>
            <input
              type="number"
              class="form-control"
              name="avgRating"
              id="client-avgRating"
              data-cy="avgRating"
              :class="{ valid: !v$.avgRating.$invalid, invalid: v$.avgRating.$invalid }"
              v-model.number="v$.avgRating.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.referenceCode')"
              for="client-referenceCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="referenceCode"
              id="client-referenceCode"
              data-cy="referenceCode"
              :class="{ valid: !v$.referenceCode.$invalid, invalid: v$.referenceCode.$invalid }"
              v-model="v$.referenceCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.externalUserId')"
              for="client-externalUserId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalUserId"
              id="client-externalUserId"
              data-cy="externalUserId"
              :class="{ valid: !v$.externalUserId.$invalid, invalid: v$.externalUserId.$invalid }"
              v-model="v$.externalUserId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.venueGroupId')"
              for="client-venueGroupId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueGroupId"
              id="client-venueGroupId"
              data-cy="venueGroupId"
              :class="{ valid: !v$.venueGroupId.$invalid, invalid: v$.venueGroupId.$invalid }"
              v-model="v$.venueGroupId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.birthdayAltDay')"
              for="client-birthdayAltDay"
            ></label>
            <input
              type="number"
              class="form-control"
              name="birthdayAltDay"
              id="client-birthdayAltDay"
              data-cy="birthdayAltDay"
              :class="{ valid: !v$.birthdayAltDay.$invalid, invalid: v$.birthdayAltDay.$invalid }"
              v-model.number="v$.birthdayAltDay.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.userId')" for="client-userId"></label>
            <input
              type="text"
              class="form-control"
              name="userId"
              id="client-userId"
              data-cy="userId"
              :class="{ valid: !v$.userId.$invalid, invalid: v$.userId.$invalid }"
              v-model="v$.userId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.client.userName')" for="client-userName"></label>
            <input
              type="text"
              class="form-control"
              name="userName"
              id="client-userName"
              data-cy="userName"
              :class="{ valid: !v$.userName.$invalid, invalid: v$.userName.$invalid }"
              v-model="v$.userName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.totalOrderCount')"
              for="client-totalOrderCount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalOrderCount"
              id="client-totalOrderCount"
              data-cy="totalOrderCount"
              :class="{ valid: !v$.totalOrderCount.$invalid, invalid: v$.totalOrderCount.$invalid }"
              v-model.number="v$.totalOrderCount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.preferredLanguageCode')"
              for="client-preferredLanguageCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="preferredLanguageCode"
              id="client-preferredLanguageCode"
              data-cy="preferredLanguageCode"
              :class="{ valid: !v$.preferredLanguageCode.$invalid, invalid: v$.preferredLanguageCode.$invalid }"
              v-model="v$.preferredLanguageCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.techLineage')"
              for="client-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="client-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.techCreatedDate')"
              for="client-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="client-techCreatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.client.techUpdatedDate')"
              for="client-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="client-techUpdatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.client.techMapping')"
              for="client-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="client-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.techComment')"
              for="client-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="client-techComment"
              data-cy="techComment"
              :class="{ valid: !v$.techComment.$invalid, invalid: v$.techComment.$invalid }"
              v-model="v$.techComment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.clientPhoto')"
              for="client-clientPhoto"
            ></label>
            <select class="form-control" id="client-clientPhoto" data-cy="clientPhoto" name="clientPhoto" v-model="client.clientPhoto">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="client.clientPhoto && clientPhotoOption.id === client.clientPhoto.id ? client.clientPhoto : clientPhotoOption"
                v-for="clientPhotoOption in clientPhotos"
                :key="clientPhotoOption.id"
              >
                {{ clientPhotoOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.client.clientVenueStats')"
              for="client-clientVenueStats"
            ></label>
            <select
              class="form-control"
              id="client-clientVenueStats"
              data-cy="clientVenueStats"
              name="clientVenueStats"
              v-model="client.clientVenueStats"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  client.clientVenueStats && clientVenueStatsOption.id === client.clientVenueStats.id
                    ? client.clientVenueStats
                    : clientVenueStatsOption
                "
                v-for="clientVenueStatsOption in clientVenueStats"
                :key="clientVenueStatsOption.id"
              >
                {{ clientVenueStatsOption.id }}
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
<script lang="ts" src="./client-update.component.ts"></script>
