package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientPhotoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientPhotoDTO.class);
        ClientPhotoDTO clientPhotoDTO1 = new ClientPhotoDTO();
        clientPhotoDTO1.setId(1L);
        ClientPhotoDTO clientPhotoDTO2 = new ClientPhotoDTO();
        assertThat(clientPhotoDTO1).isNotEqualTo(clientPhotoDTO2);
        clientPhotoDTO2.setId(clientPhotoDTO1.getId());
        assertThat(clientPhotoDTO1).isEqualTo(clientPhotoDTO2);
        clientPhotoDTO2.setId(2L);
        assertThat(clientPhotoDTO1).isNotEqualTo(clientPhotoDTO2);
        clientPhotoDTO1.setId(null);
        assertThat(clientPhotoDTO1).isNotEqualTo(clientPhotoDTO2);
    }
}
