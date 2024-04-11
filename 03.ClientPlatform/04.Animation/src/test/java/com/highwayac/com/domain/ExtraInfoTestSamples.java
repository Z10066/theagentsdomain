package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ExtraInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ExtraInfo getExtraInfoSample1() {
        return new ExtraInfo().id(1L).audienceFeedback("audienceFeedback1").relatedVideos("relatedVideos1");
    }

    public static ExtraInfo getExtraInfoSample2() {
        return new ExtraInfo().id(2L).audienceFeedback("audienceFeedback2").relatedVideos("relatedVideos2");
    }

    public static ExtraInfo getExtraInfoRandomSampleGenerator() {
        return new ExtraInfo()
            .id(longCount.incrementAndGet())
            .audienceFeedback(UUID.randomUUID().toString())
            .relatedVideos(UUID.randomUUID().toString());
    }
}
