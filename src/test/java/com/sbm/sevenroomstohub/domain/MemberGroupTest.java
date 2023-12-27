package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ClientTestSamples.*;
import static com.sbm.sevenroomstohub.domain.MemberGroupTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberGroupTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberGroup.class);
        MemberGroup memberGroup1 = getMemberGroupSample1();
        MemberGroup memberGroup2 = new MemberGroup();
        assertThat(memberGroup1).isNotEqualTo(memberGroup2);

        memberGroup2.setId(memberGroup1.getId());
        assertThat(memberGroup1).isEqualTo(memberGroup2);

        memberGroup2 = getMemberGroupSample2();
        assertThat(memberGroup1).isNotEqualTo(memberGroup2);
    }

    @Test
    void clientTest() throws Exception {
        MemberGroup memberGroup = getMemberGroupRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        memberGroup.setClient(clientBack);
        assertThat(memberGroup.getClient()).isEqualTo(clientBack);

        memberGroup.client(null);
        assertThat(memberGroup.getClient()).isNull();
    }
}
