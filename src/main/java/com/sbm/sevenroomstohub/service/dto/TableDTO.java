package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.Table} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TableDTO implements Serializable {

    private Long id;

    private Integer tableNumber;

    private ReservationDTO reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableDTO)) {
            return false;
        }

        TableDTO tableDTO = (TableDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tableDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TableDTO{" +
            "id=" + getId() +
            ", tableNumber=" + getTableNumber() +
            ", reservation=" + getReservation() +
            "}";
    }
}
