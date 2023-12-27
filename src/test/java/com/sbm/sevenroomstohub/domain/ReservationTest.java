package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ClientTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReservationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reservation.class);
        Reservation reservation1 = getReservationSample1();
        Reservation reservation2 = new Reservation();
        assertThat(reservation1).isNotEqualTo(reservation2);

        reservation2.setId(reservation1.getId());
        assertThat(reservation1).isEqualTo(reservation2);

        reservation2 = getReservationSample2();
        assertThat(reservation1).isNotEqualTo(reservation2);
    }

    @Test
    void clientTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        reservation.setClient(clientBack);
        assertThat(reservation.getClient()).isEqualTo(clientBack);

        reservation.client(null);
        assertThat(reservation.getClient()).isNull();
    }
}
