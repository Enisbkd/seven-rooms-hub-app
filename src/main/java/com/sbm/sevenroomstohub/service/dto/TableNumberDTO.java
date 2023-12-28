package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.TableNumber} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TableNumberDTO implements Serializable {

    private Long id;

    private Integer tableNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableNumberDTO)) {
            return false;
        }

        TableNumberDTO tableNumberDTO = (TableNumberDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tableNumberDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TableNumberDTO{" +
            "id=" + getId() +
            ", tableNum=" + getTableNum() +
            "}";
    }
}
