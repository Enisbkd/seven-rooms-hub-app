package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResTagDTO.class);
        ResTagDTO resTagDTO1 = new ResTagDTO();
        resTagDTO1.setId(1L);
        ResTagDTO resTagDTO2 = new ResTagDTO();
        assertThat(resTagDTO1).isNotEqualTo(resTagDTO2);
        resTagDTO2.setId(resTagDTO1.getId());
        assertThat(resTagDTO1).isEqualTo(resTagDTO2);
        resTagDTO2.setId(2L);
        assertThat(resTagDTO1).isNotEqualTo(resTagDTO2);
        resTagDTO1.setId(null);
        assertThat(resTagDTO1).isNotEqualTo(resTagDTO2);
    }
}
