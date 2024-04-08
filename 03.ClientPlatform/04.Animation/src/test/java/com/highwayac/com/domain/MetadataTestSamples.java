package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MetadataTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Metadata getMetadataSample1() {
        return new Metadata().id(1L).relatedVideos("relatedVideos1");
    }

    public static Metadata getMetadataSample2() {
        return new Metadata().id(2L).relatedVideos("relatedVideos2");
    }

    public static Metadata getMetadataRandomSampleGenerator() {
        return new Metadata().id(longCount.incrementAndGet()).relatedVideos(UUID.randomUUID().toString());
    }
}
