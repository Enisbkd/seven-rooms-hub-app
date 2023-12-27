package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ResPosTicketTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResPosTicketTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResPosTicket.class);
        ResPosTicket resPosTicket1 = getResPosTicketSample1();
        ResPosTicket resPosTicket2 = new ResPosTicket();
        assertThat(resPosTicket1).isNotEqualTo(resPosTicket2);

        resPosTicket2.setId(resPosTicket1.getId());
        assertThat(resPosTicket1).isEqualTo(resPosTicket2);

        resPosTicket2 = getResPosTicketSample2();
        assertThat(resPosTicket1).isNotEqualTo(resPosTicket2);
    }

    @Test
    void reservationTest() throws Exception {
        ResPosTicket resPosTicket = getResPosTicketRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        resPosTicket.setReservation(reservationBack);
        assertThat(resPosTicket.getReservation()).isEqualTo(reservationBack);

        resPosTicket.reservation(null);
        assertThat(resPosTicket.getReservation()).isNull();
    }
}
