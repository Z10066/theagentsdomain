package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SystemSettingTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SystemSetting getSystemSettingSample1() {
        return new SystemSetting().id(1L).name("name1").value("value1");
    }

    public static SystemSetting getSystemSettingSample2() {
        return new SystemSetting().id(2L).name("name2").value("value2");
    }

    public static SystemSetting getSystemSettingRandomSampleGenerator() {
        return new SystemSetting().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString()).value(UUID.randomUUID().toString());
    }
}
