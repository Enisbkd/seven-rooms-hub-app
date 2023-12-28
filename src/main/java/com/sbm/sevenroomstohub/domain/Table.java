package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Table.
 */
@Entity
@Table(name = "jhi_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "table_number")
    private Integer tableNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "resTags", "resPosTickets", "resCustomFields", "tables", "client" }, allowSetters = true)
    private Reservation reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Table id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableNumber() {
        return this.tableNumber;
    }

    public Table tableNumber(Integer tableNumber) {
        this.setTableNumber(tableNumber);
        return this;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Table reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Table)) {
            return false;
        }
        return getId() != null && getId().equals(((Table) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Table{" +
            "id=" + getId() +
            ", tableNumber=" + getTableNumber() +
            "}";
    }
}
