package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MaterialTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Material getMaterialSample1() {
        return new Material()
            .id(1L)
            .title("title1")
            .creator("creator1")
            .description("description1")
            .copyright("copyright1")
            .watchLink("watchLink1");
    }

    public static Material getMaterialSample2() {
        return new Material()
            .id(2L)
            .title("title2")
            .creator("creator2")
            .description("description2")
            .copyright("copyright2")
            .watchLink("watchLink2");
    }

    public static Material getMaterialRandomSampleGenerator() {
        return new Material()
            .id(longCount.incrementAndGet())
            .title(UUID.randomUUID().toString())
            .creator(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .copyright(UUID.randomUUID().toString())
            .watchLink(UUID.randomUUID().toString());
    }
}
