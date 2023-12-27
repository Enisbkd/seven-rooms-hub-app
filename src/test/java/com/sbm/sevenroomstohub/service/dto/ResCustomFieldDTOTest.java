package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResCustomFieldDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResCustomFieldDTO.class);
        ResCustomFieldDTO resCustomFieldDTO1 = new ResCustomFieldDTO();
        resCustomFieldDTO1.setId(1L);
        ResCustomFieldDTO resCustomFieldDTO2 = new ResCustomFieldDTO();
        assertThat(resCustomFieldDTO1).isNotEqualTo(resCustomFieldDTO2);
        resCustomFieldDTO2.setId(resCustomFieldDTO1.getId());
        assertThat(resCustomFieldDTO1).isEqualTo(resCustomFieldDTO2);
        resCustomFieldDTO2.setId(2L);
        assertThat(resCustomFieldDTO1).isNotEqualTo(resCustomFieldDTO2);
        resCustomFieldDTO1.setId(null);
        assertThat(resCustomFieldDTO1).isNotEqualTo(resCustomFieldDTO2);
    }
}
