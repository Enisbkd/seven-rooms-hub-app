<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.clientVenueStats.home.createOrEditLabel"
          data-cy="ClientVenueStatsCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="clientVenueStats.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="clientVenueStats.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueId')"
              for="client-venue-stats-venueId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueId"
              id="client-venue-stats-venueId"
              data-cy="venueId"
              :class="{ valid: !v$.venueId.$invalid, invalid: v$.venueId.$invalid }"
              v-model="v$.venueId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.avgRating')"
              for="client-venue-stats-avgRating"
            ></label>
            <input
              type="number"
              class="form-control"
              name="avgRating"
              id="client-venue-stats-avgRating"
              data-cy="avgRating"
              :class="{ valid: !v$.avgRating.$invalid, invalid: v$.avgRating.$invalid }"
              v-model.number="v$.avgRating.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.bookedByNames')"
              for="client-venue-stats-bookedByNames"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bookedByNames"
              id="client-venue-stats-bookedByNames"
              data-cy="bookedByNames"
              :class="{ valid: !v$.bookedByNames.$invalid, invalid: v$.bookedByNames.$invalid }"
              v-model="v$.bookedByNames.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.lastVisitDate')"
              for="client-venue-stats-lastVisitDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastVisitDate"
              id="client-venue-stats-lastVisitDate"
              data-cy="lastVisitDate"
              :class="{ valid: !v$.lastVisitDate.$invalid, invalid: v$.lastVisitDate.$invalid }"
              v-model="v$.lastVisitDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.numRatings')"
              for="client-venue-stats-numRatings"
            ></label>
            <input
              type="number"
              class="form-control"
              name="numRatings"
              id="client-venue-stats-numRatings"
              data-cy="numRatings"
              :class="{ valid: !v$.numRatings.$invalid, invalid: v$.numRatings.$invalid }"
              v-model.number="v$.numRatings.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCancellations')"
              for="client-venue-stats-totalCancellations"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalCancellations"
              id="client-venue-stats-totalCancellations"
              data-cy="totalCancellations"
              :class="{ valid: !v$.totalCancellations.$invalid, invalid: v$.totalCancellations.$invalid }"
              v-model.number="v$.totalCancellations.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalCovers')"
              for="client-venue-stats-totalCovers"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalCovers"
              id="client-venue-stats-totalCovers"
              data-cy="totalCovers"
              :class="{ valid: !v$.totalCovers.$invalid, invalid: v$.totalCovers.$invalid }"
              v-model.number="v$.totalCovers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalNoShows')"
              for="client-venue-stats-totalNoShows"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalNoShows"
              id="client-venue-stats-totalNoShows"
              data-cy="totalNoShows"
              :class="{ valid: !v$.totalNoShows.$invalid, invalid: v$.totalNoShows.$invalid }"
              v-model.number="v$.totalNoShows.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpend')"
              for="client-venue-stats-totalSpend"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpend"
              id="client-venue-stats-totalSpend"
              data-cy="totalSpend"
              :class="{ valid: !v$.totalSpend.$invalid, invalid: v$.totalSpend.$invalid }"
              v-model.number="v$.totalSpend.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocal')"
              for="client-venue-stats-totalSpendLocal"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocal"
              id="client-venue-stats-totalSpendLocal"
              data-cy="totalSpendLocal"
              :class="{ valid: !v$.totalSpendLocal.$invalid, invalid: v$.totalSpendLocal.$invalid }"
              v-model.number="v$.totalSpendLocal.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalperCover')"
              for="client-venue-stats-totalSpendLocalperCover"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocalperCover"
              id="client-venue-stats-totalSpendLocalperCover"
              data-cy="totalSpendLocalperCover"
              :class="{ valid: !v$.totalSpendLocalperCover.$invalid, invalid: v$.totalSpendLocalperCover.$invalid }"
              v-model.number="v$.totalSpendLocalperCover.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendLocalPerVisit')"
              for="client-venue-stats-totalSpendLocalPerVisit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocalPerVisit"
              id="client-venue-stats-totalSpendLocalPerVisit"
              data-cy="totalSpendLocalPerVisit"
              :class="{ valid: !v$.totalSpendLocalPerVisit.$invalid, invalid: v$.totalSpendLocalPerVisit.$invalid }"
              v-model.number="v$.totalSpendLocalPerVisit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendperCover')"
              for="client-venue-stats-totalSpendperCover"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendperCover"
              id="client-venue-stats-totalSpendperCover"
              data-cy="totalSpendperCover"
              :class="{ valid: !v$.totalSpendperCover.$invalid, invalid: v$.totalSpendperCover.$invalid }"
              v-model.number="v$.totalSpendperCover.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalSpendPerVisit')"
              for="client-venue-stats-totalSpendPerVisit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendPerVisit"
              id="client-venue-stats-totalSpendPerVisit"
              data-cy="totalSpendPerVisit"
              :class="{ valid: !v$.totalSpendPerVisit.$invalid, invalid: v$.totalSpendPerVisit.$invalid }"
              v-model.number="v$.totalSpendPerVisit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.totalVisit')"
              for="client-venue-stats-totalVisit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalVisit"
              id="client-venue-stats-totalVisit"
              data-cy="totalVisit"
              :class="{ valid: !v$.totalVisit.$invalid, invalid: v$.totalVisit.$invalid }"
              v-model.number="v$.totalVisit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptin')"
              for="client-venue-stats-venueMarketingOptin"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="venueMarketingOptin"
              id="client-venue-stats-venueMarketingOptin"
              data-cy="venueMarketingOptin"
              :class="{ valid: !v$.venueMarketingOptin.$invalid, invalid: v$.venueMarketingOptin.$invalid }"
              v-model="v$.venueMarketingOptin.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.venueMarketingOptints')"
              for="client-venue-stats-venueMarketingOptints"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueMarketingOptints"
              id="client-venue-stats-venueMarketingOptints"
              data-cy="venueMarketingOptints"
              :class="{ valid: !v$.venueMarketingOptints.$invalid, invalid: v$.venueMarketingOptints.$invalid }"
              v-model="v$.venueMarketingOptints.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techLineage')"
              for="client-venue-stats-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="client-venue-stats-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techCreatedDate')"
              for="client-venue-stats-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="client-venue-stats-techCreatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techUpdatedDate')"
              for="client-venue-stats-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="client-venue-stats-techUpdatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techMapping')"
              for="client-venue-stats-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="client-venue-stats-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.clientVenueStats.techComment')"
              for="client-venue-stats-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="client-venue-stats-techComment"
              data-cy="techComment"
              :class="{ valid: !v$.techComment.$invalid, invalid: v$.techComment.$invalid }"
              v-model="v$.techComment.$model"
            />
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
<script lang="ts" src="./client-venue-stats-update.component.ts"></script>
