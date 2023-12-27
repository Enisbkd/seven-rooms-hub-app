<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.resPosticketsItem.home.createOrEditLabel"
          data-cy="ResPosticketsItemCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resPosticketsItem.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resPosticketsItem.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.price')"
              for="res-postickets-item-price"
            ></label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="res-postickets-item-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.name')"
              for="res-postickets-item-name"
            ></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="res-postickets-item-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.quantity')"
              for="res-postickets-item-quantity"
            ></label>
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="res-postickets-item-quantity"
              data-cy="quantity"
              :class="{ valid: !v$.quantity.$invalid, invalid: v$.quantity.$invalid }"
              v-model.number="v$.quantity.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techLineage')"
              for="res-postickets-item-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="res-postickets-item-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techCreatedDate')"
              for="res-postickets-item-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="res-postickets-item-techCreatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techUpdatedDate')"
              for="res-postickets-item-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="res-postickets-item-techUpdatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techMapping')"
              for="res-postickets-item-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="res-postickets-item-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resPosticketsItem.techComment')"
              for="res-postickets-item-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="res-postickets-item-techComment"
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
<script lang="ts" src="./res-postickets-item-update.component.ts"></script>
