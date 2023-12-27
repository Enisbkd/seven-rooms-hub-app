package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientVenueStatsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientVenueStatsDTO.class);
        ClientVenueStatsDTO clientVenueStatsDTO1 = new ClientVenueStatsDTO();
        clientVenueStatsDTO1.setId(1L);
        ClientVenueStatsDTO clientVenueStatsDTO2 = new ClientVenueStatsDTO();
        assertThat(clientVenueStatsDTO1).isNotEqualTo(clientVenueStatsDTO2);
        clientVenueStatsDTO2.setId(clientVenueStatsDTO1.getId());
        assertThat(clientVenueStatsDTO1).isEqualTo(clientVenueStatsDTO2);
        clientVenueStatsDTO2.setId(2L);
        assertThat(clientVenueStatsDTO1).isNotEqualTo(clientVenueStatsDTO2);
        clientVenueStatsDTO1.setId(null);
        assertThat(clientVenueStatsDTO1).isNotEqualTo(clientVenueStatsDTO2);
    }
}
