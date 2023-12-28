package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ReservationTestSamples.*;
import static com.sbm.sevenroomstohub.domain.TableNumTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TableNumTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableNum.class);
        TableNum tableNum1 = getTableNumSample1();
        TableNum tableNum2 = new TableNum();
        assertThat(tableNum1).isNotEqualTo(tableNum2);

        tableNum2.setId(tableNum1.getId());
        assertThat(tableNum1).isEqualTo(tableNum2);

        tableNum2 = getTableNumSample2();
        assertThat(tableNum1).isNotEqualTo(tableNum2);
    }

    @Test
    void reservationTest() throws Exception {
        TableNum tableNum = getTableNumRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        tableNum.setReservation(reservationBack);
        assertThat(tableNum.getReservation()).isEqualTo(reservationBack);

        tableNum.reservation(null);
        assertThat(tableNum.getReservation()).isNull();
    }
}
