package com.highwayac.com.web.rest;

import com.highwayac.com.domain.StripeCheckoutSession;
import com.highwayac.com.repository.StripeCheckoutSessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.checkout.Session;
import com.stripe.model.StripeObject;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.nio.charset.StandardCharsets;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StripeWebhookResource {

    private final Logger log = LoggerFactory.getLogger(StripeWebhookResource.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${stripe.api.key}")
    private String apiKey;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    private final StripeCheckoutSessionRepository stripeCheckoutSessionRepository;

    public StripeWebhookResource(StripeCheckoutSessionRepository stripeCheckoutSessionRepository) {
        this.stripeCheckoutSessionRepository = stripeCheckoutSessionRepository;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }

    @PostMapping("/stripe/webhook")
    public Mono<ResponseEntity<String>> handleStripeWebhook(ServerWebExchange exchange) {
        log.debug("Received Stripe Webhook");

        return exchange.getRequest().getBody().map(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        })
        .reduce((s1, s2) -> s1 + s2)
        .flatMap(payload  -> {
            String sigHeader = exchange.getRequest().getHeaders().getFirst("Stripe-Signature");
            // log.debug("Payload: {}", payload);

            Event event;
            try {
                event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
                log.debug("Verified event: {}", event);
                log.debug("getApiVersion:" + event.getApiVersion());
            } catch (SignatureVerificationException e) {
                log.error("Webhook error while validating signature", e);
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook error while validating signature"));
            } catch (Exception e) {
                log.error("Webhook error while parsing basic request", e);
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook error while parsing basic request"));
            }

            // Deserialize the nested object inside the event
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
                log.debug("Deserialized object: {}", stripeObject);
            } else {
                log.warn("Deserialization failed, possibly due to API version mismatch.");
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deserialization failed"));
            }

            switch (event.getType()) {
                case "payment_intent.succeeded":
                    PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                    if (paymentIntent != null) {
                        log.info("Payment for " + paymentIntent.getAmount() + " succeeded.");
                    }
                    break;
                case "payment_method.attached":
                    PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                    if (paymentMethod != null) {
                        log.info("Payment method attached.");
                    }
                    break;
                case "checkout.session.completed":
                    Session checkoutSession = (Session) stripeObject;
                    if (checkoutSession != null) {
                        log.info("ClientReferenceId : " + checkoutSession.getClientReferenceId());
                        // 创建StripeCheckoutSession实体
                        StripeCheckoutSession stripeCheckoutSession = new StripeCheckoutSession();
                        stripeCheckoutSession.setCreated(checkoutSession.getCreated());
                        stripeCheckoutSession.setAmountSubtotal(checkoutSession.getAmountSubtotal());
                        stripeCheckoutSession.setAmountTotal(checkoutSession.getAmountTotal());
                        stripeCheckoutSession.setEmail(checkoutSession.getCustomerDetails().getEmail());
                        stripeCheckoutSession.setName(checkoutSession.getCustomerDetails().getName());
                        stripeCheckoutSession.setClientReferenceId(checkoutSession.getClientReferenceId());
                        stripeCheckoutSession.setPaymentIntent(checkoutSession.getPaymentIntent());
                        stripeCheckoutSession.setPaymentStatus(checkoutSession.getPaymentStatus());

                        // 保存到数据库
                        return stripeCheckoutSessionRepository.save(stripeCheckoutSession)
                            .map(result -> {
                                log.info("Saved StripeCheckoutSession with ID: " + result.getId());
                                return ResponseEntity.ok("Event received");
                            });
                    }
                    break;
                default:
                    log.warn("Unhandled event type: " + event.getType());
                    break;
            }

            return Mono.just(ResponseEntity.ok("Event received"));
        });
    }
}
