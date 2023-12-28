<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.memberGroup.home.createOrEditLabel"
          data-cy="MemberGroupCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.memberGroup.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="memberGroup.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="memberGroup.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.memberGroup.client')"
              for="member-group-client"
            ></label>
            <select class="form-control" id="member-group-client" data-cy="client" name="client" v-model="memberGroup.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="memberGroup.client && clientOption.id === memberGroup.client.id ? memberGroup.client : clientOption"
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
<script lang="ts" src="./member-group-update.component.ts"></script>
