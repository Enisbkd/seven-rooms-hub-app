package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClientPhoto.
 */
@Entity
@Table(name = "client_photo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientPhoto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "large")
    private String large;

    @Column(name = "large_height")
    private Integer largeHeight;

    @Column(name = "large_width")
    private Integer largeWidth;

    @Column(name = "medium")
    private String medium;

    @Column(name = "medium_height")
    private Integer mediumHeight;

    @Column(name = "medium_width")
    private Integer mediumWidth;

    @Column(name = "small")
    private String small;

    @Column(name = "small_height")
    private Integer smallHeight;

    @Column(name = "small_width")
    private Integer smallWidth;

    @Column(name = "jhi_raw")
    private String raw;

    @Column(name = "cropx")
    private Integer cropx;

    @Column(name = "cropy")
    private Integer cropy;

    @Column(name = "crop_height")
    private Double cropHeight;

    @Column(name = "crop_width")
    private Double cropWidth;

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

    @JsonIgnoreProperties(
        value = { "clientPhoto", "clientVenueStats", "customFields", "clientTags", "reservations", "memberGroups" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "clientPhoto")
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClientPhoto id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return this.clientId;
    }

    public ClientPhoto clientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLarge() {
        return this.large;
    }

    public ClientPhoto large(String large) {
        this.setLarge(large);
        return this;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public Integer getLargeHeight() {
        return this.largeHeight;
    }

    public ClientPhoto largeHeight(Integer largeHeight) {
        this.setLargeHeight(largeHeight);
        return this;
    }

    public void setLargeHeight(Integer largeHeight) {
        this.largeHeight = largeHeight;
    }

    public Integer getLargeWidth() {
        return this.largeWidth;
    }

    public ClientPhoto largeWidth(Integer largeWidth) {
        this.setLargeWidth(largeWidth);
        return this;
    }

    public void setLargeWidth(Integer largeWidth) {
        this.largeWidth = largeWidth;
    }

    public String getMedium() {
        return this.medium;
    }

    public ClientPhoto medium(String medium) {
        this.setMedium(medium);
        return this;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Integer getMediumHeight() {
        return this.mediumHeight;
    }

    public ClientPhoto mediumHeight(Integer mediumHeight) {
        this.setMediumHeight(mediumHeight);
        return this;
    }

    public void setMediumHeight(Integer mediumHeight) {
        this.mediumHeight = mediumHeight;
    }

    public Integer getMediumWidth() {
        return this.mediumWidth;
    }

    public ClientPhoto mediumWidth(Integer mediumWidth) {
        this.setMediumWidth(mediumWidth);
        return this;
    }

    public void setMediumWidth(Integer mediumWidth) {
        this.mediumWidth = mediumWidth;
    }

    public String getSmall() {
        return this.small;
    }

    public ClientPhoto small(String small) {
        this.setSmall(small);
        return this;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public Integer getSmallHeight() {
        return this.smallHeight;
    }

    public ClientPhoto smallHeight(Integer smallHeight) {
        this.setSmallHeight(smallHeight);
        return this;
    }

    public void setSmallHeight(Integer smallHeight) {
        this.smallHeight = smallHeight;
    }

    public Integer getSmallWidth() {
        return this.smallWidth;
    }

    public ClientPhoto smallWidth(Integer smallWidth) {
        this.setSmallWidth(smallWidth);
        return this;
    }

    public void setSmallWidth(Integer smallWidth) {
        this.smallWidth = smallWidth;
    }

    public String getRaw() {
        return this.raw;
    }

    public ClientPhoto raw(String raw) {
        this.setRaw(raw);
        return this;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Integer getCropx() {
        return this.cropx;
    }

    public ClientPhoto cropx(Integer cropx) {
        this.setCropx(cropx);
        return this;
    }

    public void setCropx(Integer cropx) {
        this.cropx = cropx;
    }

    public Integer getCropy() {
        return this.cropy;
    }

    public ClientPhoto cropy(Integer cropy) {
        this.setCropy(cropy);
        return this;
    }

    public void setCropy(Integer cropy) {
        this.cropy = cropy;
    }

    public Double getCropHeight() {
        return this.cropHeight;
    }

    public ClientPhoto cropHeight(Double cropHeight) {
        this.setCropHeight(cropHeight);
        return this;
    }

    public void setCropHeight(Double cropHeight) {
        this.cropHeight = cropHeight;
    }

    public Double getCropWidth() {
        return this.cropWidth;
    }

    public ClientPhoto cropWidth(Double cropWidth) {
        this.setCropWidth(cropWidth);
        return this;
    }

    public void setCropWidth(Double cropWidth) {
        this.cropWidth = cropWidth;
    }

    public String getTechLineage() {
        return this.techLineage;
    }

    public ClientPhoto techLineage(String techLineage) {
        this.setTechLineage(techLineage);
        return this;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return this.techCreatedDate;
    }

    public ClientPhoto techCreatedDate(ZonedDateTime techCreatedDate) {
        this.setTechCreatedDate(techCreatedDate);
        return this;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return this.techUpdatedDate;
    }

    public ClientPhoto techUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.setTechUpdatedDate(techUpdatedDate);
        return this;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return this.techMapping;
    }

    public ClientPhoto techMapping(String techMapping) {
        this.setTechMapping(techMapping);
        return this;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return this.techComment;
    }

    public ClientPhoto techComment(String techComment) {
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
        if (this.client != null) {
            this.client.setClientPhoto(null);
        }
        if (client != null) {
            client.setClientPhoto(this);
        }
        this.client = client;
    }

    public ClientPhoto client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientPhoto)) {
            return false;
        }
        return getId() != null && getId().equals(((ClientPhoto) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientPhoto{" +
            "id=" + getId() +
            ", clientId='" + getClientId() + "'" +
            ", large='" + getLarge() + "'" +
            ", largeHeight=" + getLargeHeight() +
            ", largeWidth=" + getLargeWidth() +
            ", medium='" + getMedium() + "'" +
            ", mediumHeight=" + getMediumHeight() +
            ", mediumWidth=" + getMediumWidth() +
            ", small='" + getSmall() + "'" +
            ", smallHeight=" + getSmallHeight() +
            ", smallWidth=" + getSmallWidth() +
            ", raw='" + getRaw() + "'" +
            ", cropx=" + getCropx() +
            ", cropy=" + getCropy() +
            ", cropHeight=" + getCropHeight() +
            ", cropWidth=" + getCropWidth() +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            "}";
    }
}
