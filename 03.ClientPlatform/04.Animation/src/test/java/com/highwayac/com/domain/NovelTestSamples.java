package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class NovelTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Novel getNovelSample1() {
        return new Novel().id(1L).noveltext("noveltext1").novelname("novelname1").noveltype("noveltype1");
    }

    public static Novel getNovelSample2() {
        return new Novel().id(2L).noveltext("noveltext2").novelname("novelname2").noveltype("noveltype2");
    }

    public static Novel getNovelRandomSampleGenerator() {
        return new Novel()
            .id(longCount.incrementAndGet())
            .noveltext(UUID.randomUUID().toString())
            .novelname(UUID.randomUUID().toString())
            .noveltype(UUID.randomUUID().toString());
    }
}
