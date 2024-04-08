package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class KeywordTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Keyword getKeywordSample1() {
        return new Keyword().id(1L).word("word1");
    }

    public static Keyword getKeywordSample2() {
        return new Keyword().id(2L).word("word2");
    }

    public static Keyword getKeywordRandomSampleGenerator() {
        return new Keyword().id(longCount.incrementAndGet()).word(UUID.randomUUID().toString());
    }
}
