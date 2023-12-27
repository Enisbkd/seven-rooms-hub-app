package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ResCustomFieldTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResCustomFieldTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResCustomField.class);
        ResCustomField resCustomField1 = getResCustomFieldSample1();
        ResCustomField resCustomField2 = new ResCustomField();
        assertThat(resCustomField1).isNotEqualTo(resCustomField2);

        resCustomField2.setId(resCustomField1.getId());
        assertThat(resCustomField1).isEqualTo(resCustomField2);

        resCustomField2 = getResCustomFieldSample2();
        assertThat(resCustomField1).isNotEqualTo(resCustomField2);
    }
}
