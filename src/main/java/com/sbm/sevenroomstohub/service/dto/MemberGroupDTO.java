package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.MemberGroup} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MemberGroupDTO implements Serializable {

    private Long id;

    private ClientDTO client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberGroupDTO)) {
            return false;
        }

        MemberGroupDTO memberGroupDTO = (MemberGroupDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, memberGroupDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MemberGroupDTO{" +
            "id=" + getId() +
            ", client=" + getClient() +
            "}";
    }
}
