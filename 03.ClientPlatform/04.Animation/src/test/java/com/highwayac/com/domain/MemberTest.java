package com.highwayac.com.domain;

import static com.highwayac.com.domain.LinkedAccountTestSamples.*;
import static com.highwayac.com.domain.MemberTestSamples.*;
import static com.highwayac.com.domain.WorkspaceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Member.class);
        Member member1 = getMemberSample1();
        Member member2 = new Member();
        assertThat(member1).isNotEqualTo(member2);

        member2.setId(member1.getId());
        assertThat(member1).isEqualTo(member2);

        member2 = getMemberSample2();
        assertThat(member1).isNotEqualTo(member2);
    }

    @Test
    void linkedAccountTest() throws Exception {
        Member member = getMemberRandomSampleGenerator();
        LinkedAccount linkedAccountBack = getLinkedAccountRandomSampleGenerator();

        member.addLinkedAccount(linkedAccountBack);
        assertThat(member.getLinkedAccounts()).containsOnly(linkedAccountBack);
        assertThat(linkedAccountBack.getMember()).isEqualTo(member);

        member.removeLinkedAccount(linkedAccountBack);
        assertThat(member.getLinkedAccounts()).doesNotContain(linkedAccountBack);
        assertThat(linkedAccountBack.getMember()).isNull();

        member.linkedAccounts(new HashSet<>(Set.of(linkedAccountBack)));
        assertThat(member.getLinkedAccounts()).containsOnly(linkedAccountBack);
        assertThat(linkedAccountBack.getMember()).isEqualTo(member);

        member.setLinkedAccounts(new HashSet<>());
        assertThat(member.getLinkedAccounts()).doesNotContain(linkedAccountBack);
        assertThat(linkedAccountBack.getMember()).isNull();
    }

    @Test
    void workspaceTest() throws Exception {
        Member member = getMemberRandomSampleGenerator();
        Workspace workspaceBack = getWorkspaceRandomSampleGenerator();

        member.setWorkspace(workspaceBack);
        assertThat(member.getWorkspace()).isEqualTo(workspaceBack);

        member.workspace(null);
        assertThat(member.getWorkspace()).isNull();
    }
}
