package com.highwayac.com.domain;

import static com.highwayac.com.domain.LinkedAccountTestSamples.*;
import static com.highwayac.com.domain.MemberTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LinkedAccountTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinkedAccount.class);
        LinkedAccount linkedAccount1 = getLinkedAccountSample1();
        LinkedAccount linkedAccount2 = new LinkedAccount();
        assertThat(linkedAccount1).isNotEqualTo(linkedAccount2);

        linkedAccount2.setId(linkedAccount1.getId());
        assertThat(linkedAccount1).isEqualTo(linkedAccount2);

        linkedAccount2 = getLinkedAccountSample2();
        assertThat(linkedAccount1).isNotEqualTo(linkedAccount2);
    }

    @Test
    void memberTest() throws Exception {
        LinkedAccount linkedAccount = getLinkedAccountRandomSampleGenerator();
        Member memberBack = getMemberRandomSampleGenerator();

        linkedAccount.setMember(memberBack);
        assertThat(linkedAccount.getMember()).isEqualTo(memberBack);

        linkedAccount.member(null);
        assertThat(linkedAccount.getMember()).isNull();
    }
}
