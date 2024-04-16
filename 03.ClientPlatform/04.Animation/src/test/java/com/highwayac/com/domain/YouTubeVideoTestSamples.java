package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class YouTubeVideoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static YouTubeVideo getYouTubeVideoSample1() {
        return new YouTubeVideo().id(1L).workspace("workspace1").creator("creator1").theme("theme1").backgroundMusic("backgroundMusic1");
    }

    public static YouTubeVideo getYouTubeVideoSample2() {
        return new YouTubeVideo().id(2L).workspace("workspace2").creator("creator2").theme("theme2").backgroundMusic("backgroundMusic2");
    }

    public static YouTubeVideo getYouTubeVideoRandomSampleGenerator() {
        return new YouTubeVideo()
            .id(longCount.incrementAndGet())
            .workspace(UUID.randomUUID().toString())
            .creator(UUID.randomUUID().toString())
            .theme(UUID.randomUUID().toString())
            .backgroundMusic(UUID.randomUUID().toString());
    }
}
