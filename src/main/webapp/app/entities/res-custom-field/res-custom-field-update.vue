<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.resCustomField.home.createOrEditLabel"
          data-cy="ResCustomFieldCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resCustomField.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resCustomField.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.systemName')"
              for="res-custom-field-systemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="systemName"
              id="res-custom-field-systemName"
              data-cy="systemName"
              :class="{ valid: !v$.systemName.$invalid, invalid: v$.systemName.$invalid }"
              v-model="v$.systemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.displayOrder')"
              for="res-custom-field-displayOrder"
            ></label>
            <input
              type="number"
              class="form-control"
              name="displayOrder"
              id="res-custom-field-displayOrder"
              data-cy="displayOrder"
              :class="{ valid: !v$.displayOrder.$invalid, invalid: v$.displayOrder.$invalid }"
              v-model.number="v$.displayOrder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.name')"
              for="res-custom-field-name"
            ></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="res-custom-field-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.value')"
              for="res-custom-field-value"
            ></label>
            <input
              type="text"
              class="form-control"
              name="value"
              id="res-custom-field-value"
              data-cy="value"
              :class="{ valid: !v$.value.$invalid, invalid: v$.value.$invalid }"
              v-model="v$.value.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techLineage')"
              for="res-custom-field-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="res-custom-field-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techCreatedDate')"
              for="res-custom-field-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="res-custom-field-techCreatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techUpdatedDate')"
              for="res-custom-field-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="res-custom-field-techUpdatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techMapping')"
              for="res-custom-field-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="res-custom-field-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resCustomField.techComment')"
              for="res-custom-field-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="res-custom-field-techComment"
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
<script lang="ts" src="./res-custom-field-update.component.ts"></script>
