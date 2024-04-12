package com.highwayac.com.domain;

import static com.highwayac.com.domain.SubscriptionServiceTestSamples.*;
import static com.highwayac.com.domain.UsageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UsageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usage.class);
        Usage usage1 = getUsageSample1();
        Usage usage2 = new Usage();
        assertThat(usage1).isNotEqualTo(usage2);

        usage2.setId(usage1.getId());
        assertThat(usage1).isEqualTo(usage2);

        usage2 = getUsageSample2();
        assertThat(usage1).isNotEqualTo(usage2);
    }

    @Test
    void subscriptionServiceTest() throws Exception {
        Usage usage = getUsageRandomSampleGenerator();
        SubscriptionService subscriptionServiceBack = getSubscriptionServiceRandomSampleGenerator();

        usage.setSubscriptionService(subscriptionServiceBack);
        assertThat(usage.getSubscriptionService()).isEqualTo(subscriptionServiceBack);

        usage.subscriptionService(null);
        assertThat(usage.getSubscriptionService()).isNull();
    }
}
