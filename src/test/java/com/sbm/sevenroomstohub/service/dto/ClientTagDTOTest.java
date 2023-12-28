package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientTagDTO.class);
        ClientTagDTO clientTagDTO1 = new ClientTagDTO();
        clientTagDTO1.setId(1L);
        ClientTagDTO clientTagDTO2 = new ClientTagDTO();
        assertThat(clientTagDTO1).isNotEqualTo(clientTagDTO2);
        clientTagDTO2.setId(clientTagDTO1.getId());
        assertThat(clientTagDTO1).isEqualTo(clientTagDTO2);
        clientTagDTO2.setId(2L);
        assertThat(clientTagDTO1).isNotEqualTo(clientTagDTO2);
        clientTagDTO1.setId(null);
        assertThat(clientTagDTO1).isNotEqualTo(clientTagDTO2);
    }
}
