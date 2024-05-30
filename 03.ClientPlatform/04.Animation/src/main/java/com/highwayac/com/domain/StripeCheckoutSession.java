package com.highwayac.com.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A StripeCheckoutSession.
 */
@Table("stripe_checkout_session")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StripeCheckoutSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("created")
    private Long created;

    @NotNull(message = "must not be null")
    @Column("amount_subtotal")
    private Long amountSubtotal;

    @NotNull(message = "must not be null")
    @Column("amount_total")
    private Long amountTotal;

    @NotNull(message = "must not be null")
    @Column("email")
    private String email;

    @NotNull(message = "must not be null")
    @Column("name")
    private String name;

    @NotNull(message = "must not be null")
    @Column("client_reference_id")
    private String clientReferenceId;

    @NotNull(message = "must not be null")
    @Column("payment_intent")
    private String paymentIntent;

    @NotNull(message = "must not be null")
    @Column("payment_status")
    private String paymentStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public StripeCheckoutSession id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreated() {
        return this.created;
    }

    public StripeCheckoutSession created(Long created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getAmountSubtotal() {
        return this.amountSubtotal;
    }

    public StripeCheckoutSession amountSubtotal(Long amountSubtotal) {
        this.setAmountSubtotal(amountSubtotal);
        return this;
    }

    public void setAmountSubtotal(Long amountSubtotal) {
        this.amountSubtotal = amountSubtotal;
    }

    public Long getAmountTotal() {
        return this.amountTotal;
    }

    public StripeCheckoutSession amountTotal(Long amountTotal) {
        this.setAmountTotal(amountTotal);
        return this;
    }

    public void setAmountTotal(Long amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getEmail() {
        return this.email;
    }

    public StripeCheckoutSession email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public StripeCheckoutSession name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientReferenceId() {
        return this.clientReferenceId;
    }

    public StripeCheckoutSession clientReferenceId(String clientReferenceId) {
        this.setClientReferenceId(clientReferenceId);
        return this;
    }

    public void setClientReferenceId(String clientReferenceId) {
        this.clientReferenceId = clientReferenceId;
    }

    public String getPaymentIntent() {
        return this.paymentIntent;
    }

    public StripeCheckoutSession paymentIntent(String paymentIntent) {
        this.setPaymentIntent(paymentIntent);
        return this;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    public StripeCheckoutSession paymentStatus(String paymentStatus) {
        this.setPaymentStatus(paymentStatus);
        return this;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StripeCheckoutSession)) {
            return false;
        }
        return getId() != null && getId().equals(((StripeCheckoutSession) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StripeCheckoutSession{" +
            "id=" + getId() +
            ", created=" + getCreated() +
            ", amountSubtotal=" + getAmountSubtotal() +
            ", amountTotal=" + getAmountTotal() +
            ", email='" + getEmail() + "'" +
            ", name='" + getName() + "'" +
            ", clientReferenceId='" + getClientReferenceId() + "'" +
            ", paymentIntent='" + getPaymentIntent() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            "}";
    }
}
