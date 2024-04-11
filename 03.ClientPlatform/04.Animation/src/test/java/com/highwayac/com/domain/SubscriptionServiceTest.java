package com.highwayac.com.domain;

import static com.highwayac.com.domain.SubscriptionServiceTestSamples.*;
import static com.highwayac.com.domain.UsageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SubscriptionServiceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptionService.class);
        SubscriptionService subscriptionService1 = getSubscriptionServiceSample1();
        SubscriptionService subscriptionService2 = new SubscriptionService();
        assertThat(subscriptionService1).isNotEqualTo(subscriptionService2);

        subscriptionService2.setId(subscriptionService1.getId());
        assertThat(subscriptionService1).isEqualTo(subscriptionService2);

        subscriptionService2 = getSubscriptionServiceSample2();
        assertThat(subscriptionService1).isNotEqualTo(subscriptionService2);
    }

    @Test
    void usageTest() throws Exception {
        SubscriptionService subscriptionService = getSubscriptionServiceRandomSampleGenerator();
        Usage usageBack = getUsageRandomSampleGenerator();

        subscriptionService.addUsage(usageBack);
        assertThat(subscriptionService.getUsages()).containsOnly(usageBack);
        assertThat(usageBack.getSubscriptionService()).isEqualTo(subscriptionService);

        subscriptionService.removeUsage(usageBack);
        assertThat(subscriptionService.getUsages()).doesNotContain(usageBack);
        assertThat(usageBack.getSubscriptionService()).isNull();

        subscriptionService.usages(new HashSet<>(Set.of(usageBack)));
        assertThat(subscriptionService.getUsages()).containsOnly(usageBack);
        assertThat(usageBack.getSubscriptionService()).isEqualTo(subscriptionService);

        subscriptionService.setUsages(new HashSet<>());
        assertThat(subscriptionService.getUsages()).doesNotContain(usageBack);
        assertThat(usageBack.getSubscriptionService()).isNull();
    }
}
