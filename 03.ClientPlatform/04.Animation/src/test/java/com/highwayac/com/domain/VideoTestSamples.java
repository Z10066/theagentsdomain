package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VideoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Video getVideoSample1() {
        return new Video()
            .id(1L)
            .title("title1")
            .duration("duration1")
            .format("format1")
            .resolution("resolution1")
            .viewingLink("viewingLink1");
    }

    public static Video getVideoSample2() {
        return new Video()
            .id(2L)
            .title("title2")
            .duration("duration2")
            .format("format2")
            .resolution("resolution2")
            .viewingLink("viewingLink2");
    }

    public static Video getVideoRandomSampleGenerator() {
        return new Video()
            .id(longCount.incrementAndGet())
            .title(UUID.randomUUID().toString())
            .duration(UUID.randomUUID().toString())
            .format(UUID.randomUUID().toString())
            .resolution(UUID.randomUUID().toString())
            .viewingLink(UUID.randomUUID().toString());
    }
}
