package com.highwayac.com.domain;

import static com.highwayac.com.domain.StripeCheckoutSessionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StripeCheckoutSessionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StripeCheckoutSession.class);
        StripeCheckoutSession stripeCheckoutSession1 = getStripeCheckoutSessionSample1();
        StripeCheckoutSession stripeCheckoutSession2 = new StripeCheckoutSession();
        assertThat(stripeCheckoutSession1).isNotEqualTo(stripeCheckoutSession2);

        stripeCheckoutSession2.setId(stripeCheckoutSession1.getId());
        assertThat(stripeCheckoutSession1).isEqualTo(stripeCheckoutSession2);

        stripeCheckoutSession2 = getStripeCheckoutSessionSample2();
        assertThat(stripeCheckoutSession1).isNotEqualTo(stripeCheckoutSession2);
    }
}
