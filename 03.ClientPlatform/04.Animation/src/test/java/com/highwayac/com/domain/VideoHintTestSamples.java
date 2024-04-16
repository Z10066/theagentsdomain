package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VideoHintTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static VideoHint getVideoHintSample1() {
        return new VideoHint().id(1L).workspace("workspace1").creator("creator1").backgroundMusic("backgroundMusic1");
    }

    public static VideoHint getVideoHintSample2() {
        return new VideoHint().id(2L).workspace("workspace2").creator("creator2").backgroundMusic("backgroundMusic2");
    }

    public static VideoHint getVideoHintRandomSampleGenerator() {
        return new VideoHint()
            .id(longCount.incrementAndGet())
            .workspace(UUID.randomUUID().toString())
            .creator(UUID.randomUUID().toString())
            .backgroundMusic(UUID.randomUUID().toString());
    }
}
