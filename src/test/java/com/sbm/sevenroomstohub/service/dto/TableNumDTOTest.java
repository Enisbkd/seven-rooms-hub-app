package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TableNumDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableNumDTO.class);
        TableNumDTO tableNumDTO1 = new TableNumDTO();
        tableNumDTO1.setId(1L);
        TableNumDTO tableNumDTO2 = new TableNumDTO();
        assertThat(tableNumDTO1).isNotEqualTo(tableNumDTO2);
        tableNumDTO2.setId(tableNumDTO1.getId());
        assertThat(tableNumDTO1).isEqualTo(tableNumDTO2);
        tableNumDTO2.setId(2L);
        assertThat(tableNumDTO1).isNotEqualTo(tableNumDTO2);
        tableNumDTO1.setId(null);
        assertThat(tableNumDTO1).isNotEqualTo(tableNumDTO2);
    }
}
