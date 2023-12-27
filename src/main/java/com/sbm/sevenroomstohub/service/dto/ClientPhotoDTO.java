package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.ClientPhoto} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientPhotoDTO implements Serializable {

    private Long id;

    private String clientId;

    private String large;

    private Integer largeHeight;

    private Integer largeWidth;

    private String medium;

    private Integer mediumHeight;

    private Integer mediumWidth;

    private String small;

    private Integer smallHeight;

    private Integer smallWidth;

    private String raw;

    private Integer cropx;

    private Integer cropy;

    private Double cropHeight;

    private Double cropWidth;

    private String techLineage;

    private ZonedDateTime techCreatedDate;

    private ZonedDateTime techUpdatedDate;

    private String techMapping;

    private String techComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public Integer getLargeHeight() {
        return largeHeight;
    }

    public void setLargeHeight(Integer largeHeight) {
        this.largeHeight = largeHeight;
    }

    public Integer getLargeWidth() {
        return largeWidth;
    }

    public void setLargeWidth(Integer largeWidth) {
        this.largeWidth = largeWidth;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Integer getMediumHeight() {
        return mediumHeight;
    }

    public void setMediumHeight(Integer mediumHeight) {
        this.mediumHeight = mediumHeight;
    }

    public Integer getMediumWidth() {
        return mediumWidth;
    }

    public void setMediumWidth(Integer mediumWidth) {
        this.mediumWidth = mediumWidth;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public Integer getSmallHeight() {
        return smallHeight;
    }

    public void setSmallHeight(Integer smallHeight) {
        this.smallHeight = smallHeight;
    }

    public Integer getSmallWidth() {
        return smallWidth;
    }

    public void setSmallWidth(Integer smallWidth) {
        this.smallWidth = smallWidth;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Integer getCropx() {
        return cropx;
    }

    public void setCropx(Integer cropx) {
        this.cropx = cropx;
    }

    public Integer getCropy() {
        return cropy;
    }

    public void setCropy(Integer cropy) {
        this.cropy = cropy;
    }

    public Double getCropHeight() {
        return cropHeight;
    }

    public void setCropHeight(Double cropHeight) {
        this.cropHeight = cropHeight;
    }

    public Double getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(Double cropWidth) {
        this.cropWidth = cropWidth;
    }

    public String getTechLineage() {
        return techLineage;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return techCreatedDate;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return techUpdatedDate;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return techMapping;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return techComment;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientPhotoDTO)) {
            return false;
        }

        ClientPhotoDTO clientPhotoDTO = (ClientPhotoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientPhotoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientPhotoDTO{" +
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
