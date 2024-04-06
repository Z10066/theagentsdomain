package com.highwayac.com.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class CopyrightTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Copyright getCopyrightSample1() {
        return new Copyright().id(1L);
    }

    public static Copyright getCopyrightSample2() {
        return new Copyright().id(2L);
    }

    public static Copyright getCopyrightRandomSampleGenerator() {
        return new Copyright().id(longCount.incrementAndGet());
    }
}
