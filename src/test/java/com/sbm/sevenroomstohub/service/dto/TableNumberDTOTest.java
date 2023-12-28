package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TableNumberDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableNumberDTO.class);
        TableNumberDTO tableNumberDTO1 = new TableNumberDTO();
        tableNumberDTO1.setId(1L);
        TableNumberDTO tableNumberDTO2 = new TableNumberDTO();
        assertThat(tableNumberDTO1).isNotEqualTo(tableNumberDTO2);
        tableNumberDTO2.setId(tableNumberDTO1.getId());
        assertThat(tableNumberDTO1).isEqualTo(tableNumberDTO2);
        tableNumberDTO2.setId(2L);
        assertThat(tableNumberDTO1).isNotEqualTo(tableNumberDTO2);
        tableNumberDTO1.setId(null);
        assertThat(tableNumberDTO1).isNotEqualTo(tableNumberDTO2);
    }
}
