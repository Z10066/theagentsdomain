package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SubscriptionServiceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static SubscriptionService getSubscriptionServiceSample1() {
        return new SubscriptionService().id(1L).serviceLevel("serviceLevel1").totalUsageTime(1);
    }

    public static SubscriptionService getSubscriptionServiceSample2() {
        return new SubscriptionService().id(2L).serviceLevel("serviceLevel2").totalUsageTime(2);
    }

    public static SubscriptionService getSubscriptionServiceRandomSampleGenerator() {
        return new SubscriptionService()
            .id(longCount.incrementAndGet())
            .serviceLevel(UUID.randomUUID().toString())
            .totalUsageTime(intCount.incrementAndGet());
    }
}
