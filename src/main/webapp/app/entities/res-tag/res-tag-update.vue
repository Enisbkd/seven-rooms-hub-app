<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.resTag.home.createOrEditLabel"
          data-cy="ResTagCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.resTag.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resTag.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resTag.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.resTag.tag')" for="res-tag-tag"></label>
            <input
              type="text"
              class="form-control"
              name="tag"
              id="res-tag-tag"
              data-cy="tag"
              :class="{ valid: !v$.tag.$invalid, invalid: v$.tag.$invalid }"
              v-model="v$.tag.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.tagDisplay')"
              for="res-tag-tagDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tagDisplay"
              id="res-tag-tagDisplay"
              data-cy="tagDisplay"
              :class="{ valid: !v$.tagDisplay.$invalid, invalid: v$.tagDisplay.$invalid }"
              v-model="v$.tagDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.resTag.group')" for="res-tag-group"></label>
            <input
              type="text"
              class="form-control"
              name="group"
              id="res-tag-group"
              data-cy="group"
              :class="{ valid: !v$.group.$invalid, invalid: v$.group.$invalid }"
              v-model="v$.group.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.groupDisplay')"
              for="res-tag-groupDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="groupDisplay"
              id="res-tag-groupDisplay"
              data-cy="groupDisplay"
              :class="{ valid: !v$.groupDisplay.$invalid, invalid: v$.groupDisplay.$invalid }"
              v-model="v$.groupDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToHubApplicationApp.resTag.color')" for="res-tag-color"></label>
            <input
              type="text"
              class="form-control"
              name="color"
              id="res-tag-color"
              data-cy="color"
              :class="{ valid: !v$.color.$invalid, invalid: v$.color.$invalid }"
              v-model="v$.color.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.techLineage')"
              for="res-tag-techLineage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techLineage"
              id="res-tag-techLineage"
              data-cy="techLineage"
              :class="{ valid: !v$.techLineage.$invalid, invalid: v$.techLineage.$invalid }"
              v-model="v$.techLineage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.techCreatedDate')"
              for="res-tag-techCreatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="res-tag-techCreatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.techUpdatedDate')"
              for="res-tag-techUpdatedDate"
            ></label>
            <div class="d-flex">
              <input
                id="res-tag-techUpdatedDate"
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
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.techMapping')"
              for="res-tag-techMapping"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techMapping"
              id="res-tag-techMapping"
              data-cy="techMapping"
              :class="{ valid: !v$.techMapping.$invalid, invalid: v$.techMapping.$invalid }"
              v-model="v$.techMapping.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.resTag.techComment')"
              for="res-tag-techComment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="techComment"
              id="res-tag-techComment"
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
<script lang="ts" src="./res-tag-update.component.ts"></script>
