package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class HistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static History getHistorySample1() {
        return new History()
            .id(1L)
            .title("title1")
            .creator("creator1")
            .description("description1")
            .copyright("copyright1")
            .watchLink("watchLink1");
    }

    public static History getHistorySample2() {
        return new History()
            .id(2L)
            .title("title2")
            .creator("creator2")
            .description("description2")
            .copyright("copyright2")
            .watchLink("watchLink2");
    }

    public static History getHistoryRandomSampleGenerator() {
        return new History()
            .id(longCount.incrementAndGet())
            .title(UUID.randomUUID().toString())
            .creator(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .copyright(UUID.randomUUID().toString())
            .watchLink(UUID.randomUUID().toString());
    }
}
