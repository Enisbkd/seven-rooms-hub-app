package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ClientTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ResCustomFieldTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ResPosTicketTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ResTagTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ReservationTestSamples.*;
import static com.sbm.sevenroomstohub.domain.TableTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
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
    void resTagTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResTag resTagBack = getResTagRandomSampleGenerator();

        reservation.addResTag(resTagBack);
        assertThat(reservation.getResTags()).containsOnly(resTagBack);
        assertThat(resTagBack.getReservation()).isEqualTo(reservation);

        reservation.removeResTag(resTagBack);
        assertThat(reservation.getResTags()).doesNotContain(resTagBack);
        assertThat(resTagBack.getReservation()).isNull();

        reservation.resTags(new HashSet<>(Set.of(resTagBack)));
        assertThat(reservation.getResTags()).containsOnly(resTagBack);
        assertThat(resTagBack.getReservation()).isEqualTo(reservation);

        reservation.setResTags(new HashSet<>());
        assertThat(reservation.getResTags()).doesNotContain(resTagBack);
        assertThat(resTagBack.getReservation()).isNull();
    }

    @Test
    void resPosTicketTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResPosTicket resPosTicketBack = getResPosTicketRandomSampleGenerator();

        reservation.addResPosTicket(resPosTicketBack);
        assertThat(reservation.getResPosTickets()).containsOnly(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isEqualTo(reservation);

        reservation.removeResPosTicket(resPosTicketBack);
        assertThat(reservation.getResPosTickets()).doesNotContain(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isNull();

        reservation.resPosTickets(new HashSet<>(Set.of(resPosTicketBack)));
        assertThat(reservation.getResPosTickets()).containsOnly(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isEqualTo(reservation);

        reservation.setResPosTickets(new HashSet<>());
        assertThat(reservation.getResPosTickets()).doesNotContain(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isNull();
    }

    @Test
    void resCustomFieldTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResCustomField resCustomFieldBack = getResCustomFieldRandomSampleGenerator();

        reservation.addResCustomField(resCustomFieldBack);
        assertThat(reservation.getResCustomFields()).containsOnly(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isEqualTo(reservation);

        reservation.removeResCustomField(resCustomFieldBack);
        assertThat(reservation.getResCustomFields()).doesNotContain(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isNull();

        reservation.resCustomFields(new HashSet<>(Set.of(resCustomFieldBack)));
        assertThat(reservation.getResCustomFields()).containsOnly(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isEqualTo(reservation);

        reservation.setResCustomFields(new HashSet<>());
        assertThat(reservation.getResCustomFields()).doesNotContain(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isNull();
    }

    @Test
    void tableTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Table tableBack = getTableRandomSampleGenerator();

        reservation.addTable(tableBack);
        assertThat(reservation.getTables()).containsOnly(tableBack);
        assertThat(tableBack.getReservation()).isEqualTo(reservation);

        reservation.removeTable(tableBack);
        assertThat(reservation.getTables()).doesNotContain(tableBack);
        assertThat(tableBack.getReservation()).isNull();

        reservation.tables(new HashSet<>(Set.of(tableBack)));
        assertThat(reservation.getTables()).containsOnly(tableBack);
        assertThat(tableBack.getReservation()).isEqualTo(reservation);

        reservation.setTables(new HashSet<>());
        assertThat(reservation.getTables()).doesNotContain(tableBack);
        assertThat(tableBack.getReservation()).isNull();
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
