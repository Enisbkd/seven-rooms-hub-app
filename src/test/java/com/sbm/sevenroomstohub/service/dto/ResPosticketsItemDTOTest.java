package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResPosticketsItemDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResPosticketsItemDTO.class);
        ResPosticketsItemDTO resPosticketsItemDTO1 = new ResPosticketsItemDTO();
        resPosticketsItemDTO1.setId(1L);
        ResPosticketsItemDTO resPosticketsItemDTO2 = new ResPosticketsItemDTO();
        assertThat(resPosticketsItemDTO1).isNotEqualTo(resPosticketsItemDTO2);
        resPosticketsItemDTO2.setId(resPosticketsItemDTO1.getId());
        assertThat(resPosticketsItemDTO1).isEqualTo(resPosticketsItemDTO2);
        resPosticketsItemDTO2.setId(2L);
        assertThat(resPosticketsItemDTO1).isNotEqualTo(resPosticketsItemDTO2);
        resPosticketsItemDTO1.setId(null);
        assertThat(resPosticketsItemDTO1).isNotEqualTo(resPosticketsItemDTO2);
    }
}
