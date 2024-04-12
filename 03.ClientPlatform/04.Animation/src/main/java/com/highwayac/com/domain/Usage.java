package com.highwayac.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Usage.
 */
@Table("usage")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Usage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("usage_type")
    private String usageType;

    @Column("usage_time")
    private Integer usageTime;

    @Column("start_time")
    private Instant startTime;

    @Column("end_time")
    private Instant endTime;

    @Transient
    @JsonIgnoreProperties(value = { "usages" }, allowSetters = true)
    private SubscriptionService subscriptionService;

    @Column("subscription_service_id")
    private Long subscriptionServiceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Usage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsageType() {
        return this.usageType;
    }

    public Usage usageType(String usageType) {
        this.setUsageType(usageType);
        return this;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public Integer getUsageTime() {
        return this.usageTime;
    }

    public Usage usageTime(Integer usageTime) {
        this.setUsageTime(usageTime);
        return this;
    }

    public void setUsageTime(Integer usageTime) {
        this.usageTime = usageTime;
    }

    public Instant getStartTime() {
        return this.startTime;
    }

    public Usage startTime(Instant startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return this.endTime;
    }

    public Usage endTime(Instant endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public SubscriptionService getSubscriptionService() {
        return this.subscriptionService;
    }

    public void setSubscriptionService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
        this.subscriptionServiceId = subscriptionService != null ? subscriptionService.getId() : null;
    }

    public Usage subscriptionService(SubscriptionService subscriptionService) {
        this.setSubscriptionService(subscriptionService);
        return this;
    }

    public Long getSubscriptionServiceId() {
        return this.subscriptionServiceId;
    }

    public void setSubscriptionServiceId(Long subscriptionService) {
        this.subscriptionServiceId = subscriptionService;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usage)) {
            return false;
        }
        return getId() != null && getId().equals(((Usage) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Usage{" +
            "id=" + getId() +
            ", usageType='" + getUsageType() + "'" +
            ", usageTime=" + getUsageTime() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }
}
