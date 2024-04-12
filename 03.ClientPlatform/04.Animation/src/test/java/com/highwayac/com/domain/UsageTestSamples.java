package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UsageTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Usage getUsageSample1() {
        return new Usage().id(1L).usageType("usageType1").usageTime(1);
    }

    public static Usage getUsageSample2() {
        return new Usage().id(2L).usageType("usageType2").usageTime(2);
    }

    public static Usage getUsageRandomSampleGenerator() {
        return new Usage().id(longCount.incrementAndGet()).usageType(UUID.randomUUID().toString()).usageTime(intCount.incrementAndGet());
    }
}
