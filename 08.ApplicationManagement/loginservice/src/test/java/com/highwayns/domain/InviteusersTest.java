package com.highwayns.domain;

import static com.highwayns.domain.InviteusersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayns.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InviteusersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inviteusers.class);
        Inviteusers inviteusers1 = getInviteusersSample1();
        Inviteusers inviteusers2 = new Inviteusers();
        assertThat(inviteusers1).isNotEqualTo(inviteusers2);

        inviteusers2.setId(inviteusers1.getId());
        assertThat(inviteusers1).isEqualTo(inviteusers2);

        inviteusers2 = getInviteusersSample2();
        assertThat(inviteusers1).isNotEqualTo(inviteusers2);
    }
}
