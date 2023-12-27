package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CustomField.
 */
@Entity
@Table(name = "custom_field")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomField implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_value")
    private String value;

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
    @JsonIgnoreProperties(
        value = { "clientPhoto", "clientVenueStats", "customFields", "clientTags", "reservations", "memberGroups" },
        allowSetters = true
    )
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CustomField id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public CustomField systemName(String systemName) {
        this.setSystemName(systemName);
        return this;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public CustomField displayOrder(Integer displayOrder) {
        this.setDisplayOrder(displayOrder);
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getName() {
        return this.name;
    }

    public CustomField name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public CustomField value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTechLineage() {
        return this.techLineage;
    }

    public CustomField techLineage(String techLineage) {
        this.setTechLineage(techLineage);
        return this;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return this.techCreatedDate;
    }

    public CustomField techCreatedDate(ZonedDateTime techCreatedDate) {
        this.setTechCreatedDate(techCreatedDate);
        return this;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return this.techUpdatedDate;
    }

    public CustomField techUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.setTechUpdatedDate(techUpdatedDate);
        return this;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return this.techMapping;
    }

    public CustomField techMapping(String techMapping) {
        this.setTechMapping(techMapping);
        return this;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return this.techComment;
    }

    public CustomField techComment(String techComment) {
        this.setTechComment(techComment);
        return this;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CustomField client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomField)) {
            return false;
        }
        return getId() != null && getId().equals(((CustomField) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomField{" +
            "id=" + getId() +
            ", systemName='" + getSystemName() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            "}";
    }
}
