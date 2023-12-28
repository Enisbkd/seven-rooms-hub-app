package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ReservationTestSamples.*;
import static com.sbm.sevenroomstohub.domain.TableTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Table.class);
        Table table1 = getTableSample1();
        Table table2 = new Table();
        assertThat(table1).isNotEqualTo(table2);

        table2.setId(table1.getId());
        assertThat(table1).isEqualTo(table2);

        table2 = getTableSample2();
        assertThat(table1).isNotEqualTo(table2);
    }

    @Test
    void reservationTest() throws Exception {
        Table table = getTableRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        table.setReservation(reservationBack);
        assertThat(table.getReservation()).isEqualTo(reservationBack);

        table.reservation(null);
        assertThat(table.getReservation()).isNull();
    }
}
