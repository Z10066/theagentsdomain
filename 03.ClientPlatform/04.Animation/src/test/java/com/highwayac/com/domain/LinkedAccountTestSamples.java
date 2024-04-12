package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LinkedAccountTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LinkedAccount getLinkedAccountSample1() {
        return new LinkedAccount().id(1L).accountIdentifier("accountIdentifier1");
    }

    public static LinkedAccount getLinkedAccountSample2() {
        return new LinkedAccount().id(2L).accountIdentifier("accountIdentifier2");
    }

    public static LinkedAccount getLinkedAccountRandomSampleGenerator() {
        return new LinkedAccount().id(longCount.incrementAndGet()).accountIdentifier(UUID.randomUUID().toString());
    }
}
