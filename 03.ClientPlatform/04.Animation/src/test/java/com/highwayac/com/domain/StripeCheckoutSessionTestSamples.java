package com.highwayac.com.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StripeCheckoutSessionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static StripeCheckoutSession getStripeCheckoutSessionSample1() {
        return new StripeCheckoutSession()
            .id(1L)
            .created(1L)
            .amountSubtotal(1L)
            .amountTotal(1L)
            .email("email1")
            .name("name1")
            .clientReferenceId("clientReferenceId1")
            .paymentIntent("paymentIntent1")
            .paymentStatus("paymentStatus1");
    }

    public static StripeCheckoutSession getStripeCheckoutSessionSample2() {
        return new StripeCheckoutSession()
            .id(2L)
            .created(2L)
            .amountSubtotal(2L)
            .amountTotal(2L)
            .email("email2")
            .name("name2")
            .clientReferenceId("clientReferenceId2")
            .paymentIntent("paymentIntent2")
            .paymentStatus("paymentStatus2");
    }

    public static StripeCheckoutSession getStripeCheckoutSessionRandomSampleGenerator() {
        return new StripeCheckoutSession()
            .id(longCount.incrementAndGet())
            .created(longCount.incrementAndGet())
            .amountSubtotal(longCount.incrementAndGet())
            .amountTotal(longCount.incrementAndGet())
            .email(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .clientReferenceId(UUID.randomUUID().toString())
            .paymentIntent(UUID.randomUUID().toString())
            .paymentStatus(UUID.randomUUID().toString());
    }
}
