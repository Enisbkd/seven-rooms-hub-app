package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ClientTestSamples.*;
import static com.sbm.sevenroomstohub.domain.CustomFieldTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomFieldTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomField.class);
        CustomField customField1 = getCustomFieldSample1();
        CustomField customField2 = new CustomField();
        assertThat(customField1).isNotEqualTo(customField2);

        customField2.setId(customField1.getId());
        assertThat(customField1).isEqualTo(customField2);

        customField2 = getCustomFieldSample2();
        assertThat(customField1).isNotEqualTo(customField2);
    }

    @Test
    void clientTest() throws Exception {
        CustomField customField = getCustomFieldRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        customField.setClient(clientBack);
        assertThat(customField.getClient()).isEqualTo(clientBack);

        customField.client(null);
        assertThat(customField.getClient()).isNull();
    }
}
