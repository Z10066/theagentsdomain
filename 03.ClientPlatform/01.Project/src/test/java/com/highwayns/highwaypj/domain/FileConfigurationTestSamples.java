package com.highwayns.highwaypj.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FileConfigurationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FileConfiguration getFileConfigurationSample1() {
        return new FileConfiguration().id(1L).name("name1").description("description1").path("path1").types("types1");
    }

    public static FileConfiguration getFileConfigurationSample2() {
        return new FileConfiguration().id(2L).name("name2").description("description2").path("path2").types("types2");
    }

    public static FileConfiguration getFileConfigurationRandomSampleGenerator() {
        return new FileConfiguration()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .path(UUID.randomUUID().toString())
            .types(UUID.randomUUID().toString());
    }
}
