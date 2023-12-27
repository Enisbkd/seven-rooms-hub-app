package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ResPosTicket.
 */
@Entity
@Table(name = "res_pos_ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResPosTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "admin_fee")
    private Double adminFee;

    @Column(name = "code")
    private Integer code;

    @Column(name = "table_no")
    private String tableNo;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "business_id")
    private Integer businessId;

    @Column(name = "local_posticket_id")
    private String localPosticketId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "total")
    private Double total;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "service_charge")
    private Double serviceCharge;

    @Column(name = "endtime")
    private String endtime;

    @Column(name = "tech_lineage")
    private String techLineage;

    @Column(name = "tech_created_date")
    private ZonedDateTime techCreatedDate;

    @Column(name = "tech_updated_date")
    private ZonedDateTime techUpdatedDate;

    @Column(name = "tech_mapping")
    private String techMapping;

    @Column(name = "tech_comment")
    private String techComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "resTags", "resPosticketsItems", "resPosTickets", "resCustomFields", "client" }, allowSetters = true)
    private Reservation reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ResPosTicket id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public ResPosTicket status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAdminFee() {
        return this.adminFee;
    }

    public ResPosTicket adminFee(Double adminFee) {
        this.setAdminFee(adminFee);
        return this;
    }

    public void setAdminFee(Double adminFee) {
        this.adminFee = adminFee;
    }

    public Integer getCode() {
        return this.code;
    }

    public ResPosTicket code(Integer code) {
        this.setCode(code);
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTableNo() {
        return this.tableNo;
    }

    public ResPosTicket tableNo(String tableNo) {
        this.setTableNo(tableNo);
        return this;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Double getTax() {
        return this.tax;
    }

    public ResPosTicket tax(Double tax) {
        this.setTax(tax);
        return this;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Integer getBusinessId() {
        return this.businessId;
    }

    public ResPosTicket businessId(Integer businessId) {
        this.setBusinessId(businessId);
        return this;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getLocalPosticketId() {
        return this.localPosticketId;
    }

    public ResPosTicket localPosticketId(String localPosticketId) {
        this.setLocalPosticketId(localPosticketId);
        return this;
    }

    public void setLocalPosticketId(String localPosticketId) {
        this.localPosticketId = localPosticketId;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public ResPosTicket employeeName(String employeeName) {
        this.setEmployeeName(employeeName);
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getTotal() {
        return this.total;
    }

    public ResPosTicket total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubtotal() {
        return this.subtotal;
    }

    public ResPosTicket subtotal(Double subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public ResPosTicket startTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Double getServiceCharge() {
        return this.serviceCharge;
    }

    public ResPosTicket serviceCharge(Double serviceCharge) {
        this.setServiceCharge(serviceCharge);
        return this;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public ResPosTicket endtime(String endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTechLineage() {
        return this.techLineage;
    }

    public ResPosTicket techLineage(String techLineage) {
        this.setTechLineage(techLineage);
        return this;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return this.techCreatedDate;
    }

    public ResPosTicket techCreatedDate(ZonedDateTime techCreatedDate) {
        this.setTechCreatedDate(techCreatedDate);
        return this;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return this.techUpdatedDate;
    }

    public ResPosTicket techUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.setTechUpdatedDate(techUpdatedDate);
        return this;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return this.techMapping;
    }

    public ResPosTicket techMapping(String techMapping) {
        this.setTechMapping(techMapping);
        return this;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return this.techComment;
    }

    public ResPosTicket techComment(String techComment) {
        this.setTechComment(techComment);
        return this;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ResPosTicket reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResPosTicket)) {
            return false;
        }
        return getId() != null && getId().equals(((ResPosTicket) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResPosTicket{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", adminFee=" + getAdminFee() +
            ", code=" + getCode() +
            ", tableNo='" + getTableNo() + "'" +
            ", tax=" + getTax() +
            ", businessId=" + getBusinessId() +
            ", localPosticketId='" + getLocalPosticketId() + "'" +
            ", employeeName='" + getEmployeeName() + "'" +
            ", total=" + getTotal() +
            ", subtotal=" + getSubtotal() +
            ", startTime='" + getStartTime() + "'" +
            ", serviceCharge=" + getServiceCharge() +
            ", endtime='" + getEndtime() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            "}";
    }
}
