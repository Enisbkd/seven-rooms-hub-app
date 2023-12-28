<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.customField.home.createOrEditLabel"
          data-cy="CustomFieldCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.customField.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="customField.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="customField.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.systemName')"
              for="custom-field-systemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="systemName"
              id="custom-field-systemName"
              data-cy="systemName"
              :class="{ valid: !v$.systemName.$invalid, invalid: v$.systemName.$invalid }"
              v-model="v$.systemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.displayOrder')"
              for="custom-field-displayOrder"
            ></label>
            <input
              type="number"
              class="form-control"
              name="displayOrder"
              id="custom-field-displayOrder"
              data-cy="displayOrder"
              :class="{ valid: !v$.displayOrder.$invalid, invalid: v$.displayOrder.$invalid }"
              v-model.number="v$.displayOrder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.customField.name')" for="custom-field-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="custom-field-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.value')"
              for="custom-field-value"
            ></label>
            <input
              type="text"
              class="form-control"
              name="value"
              id="custom-field-value"
              data-cy="value"
              :class="{ valid: !v$.value.$invalid, invalid: v$.value.$invalid }"
              v-model="v$.value.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.techLineage')"
              for="custom-field-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="custom-field-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.techCreatedDate')"
              for="custom-field-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="custom-field-techCreatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.customField.techUpdatedDate')"
              for="custom-field-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="custom-field-techUpdatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.customField.techMapping')"
              for="custom-field-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="custom-field-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.techComment')"
              for="custom-field-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="custom-field-techComment"
              data-cy="techComment"
              :class="{ valid: !v$.techComment.$invalid, invalid: v$.techComment.$invalid }"
              v-model="v$.techComment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.customField.client')"
              for="custom-field-client"
            ></label>
            <select class="form-control" id="custom-field-client" data-cy="client" name="client" v-model="customField.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="customField.client && clientOption.id === customField.client.id ? customField.client : clientOption"
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
<script lang="ts" src="./custom-field-update.component.ts"></script>
