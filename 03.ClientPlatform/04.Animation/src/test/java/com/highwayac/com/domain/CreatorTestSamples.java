package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CreatorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Creator getCreatorSample1() {
        return new Creator().id(1L).name("name1");
    }

    public static Creator getCreatorSample2() {
        return new Creator().id(2L).name("name2");
    }

    public static Creator getCreatorRandomSampleGenerator() {
        return new Creator().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
