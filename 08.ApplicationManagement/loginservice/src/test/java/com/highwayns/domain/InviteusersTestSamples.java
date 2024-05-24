package com.highwayns.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InviteusersTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Inviteusers getInviteusersSample1() {
        return new Inviteusers()
            .id(1L)
            .invitername("invitername1")
            .workspaces("workspaces1")
            .invitertime("invitertime1")
            .email("email1")
            .inviterid("inviterid1")
            .status("status1");
    }

    public static Inviteusers getInviteusersSample2() {
        return new Inviteusers()
            .id(2L)
            .invitername("invitername2")
            .workspaces("workspaces2")
            .invitertime("invitertime2")
            .email("email2")
            .inviterid("inviterid2")
            .status("status2");
    }

    public static Inviteusers getInviteusersRandomSampleGenerator() {
        return new Inviteusers()
            .id(longCount.incrementAndGet())
            .invitername(UUID.randomUUID().toString())
            .workspaces(UUID.randomUUID().toString())
            .invitertime(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .inviterid(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
