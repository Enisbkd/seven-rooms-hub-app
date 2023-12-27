package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.TableNumberTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TableNumberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableNumber.class);
        TableNumber tableNumber1 = getTableNumberSample1();
        TableNumber tableNumber2 = new TableNumber();
        assertThat(tableNumber1).isNotEqualTo(tableNumber2);

        tableNumber2.setId(tableNumber1.getId());
        assertThat(tableNumber1).isEqualTo(tableNumber2);

        tableNumber2 = getTableNumberSample2();
        assertThat(tableNumber1).isNotEqualTo(tableNumber2);
    }
}
