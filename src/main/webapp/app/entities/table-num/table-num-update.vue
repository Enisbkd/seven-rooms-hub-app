<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToHubApplicationApp.tableNum.home.createOrEditLabel"
          data-cy="TableNumCreateUpdateHeading"
          v-text="t$('sevenRoomsToHubApplicationApp.tableNum.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="tableNum.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="tableNum.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.tableNum.tableNumber')"
              for="table-num-tableNumber"
            ></label>
            <input
              type="number"
              class="form-control"
              name="tableNumber"
              id="table-num-tableNumber"
              data-cy="tableNumber"
              :class="{ valid: !v$.tableNumber.$invalid, invalid: v$.tableNumber.$invalid }"
              v-model.number="v$.tableNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToHubApplicationApp.tableNum.reservation')"
              for="table-num-reservation"
            ></label>
            <select class="form-control" id="table-num-reservation" data-cy="reservation" name="reservation" v-model="tableNum.reservation">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  tableNum.reservation && reservationOption.id === tableNum.reservation.id ? tableNum.reservation : reservationOption
                "
                v-for="reservationOption in reservations"
                :key="reservationOption.id"
              >
                {{ reservationOption.id }}
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
<script lang="ts" src="./table-num-update.component.ts"></script>
