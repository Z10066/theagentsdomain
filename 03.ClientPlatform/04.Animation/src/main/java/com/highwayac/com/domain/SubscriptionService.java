package com.highwayac.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SubscriptionService.
 */
@Table("subscription_service")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SubscriptionService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("service_level")
    private String serviceLevel;

    @Column("total_usage_time")
    private Integer totalUsageTime;

    @Column("start_time")
    private Instant startTime;

    @Column("end_time")
    private Instant endTime;

    @Transient
    @JsonIgnoreProperties(value = { "subscriptionService" }, allowSetters = true)
    private Set<Usage> usages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SubscriptionService id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceLevel() {
        return this.serviceLevel;
    }

    public SubscriptionService serviceLevel(String serviceLevel) {
        this.setServiceLevel(serviceLevel);
        return this;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Integer getTotalUsageTime() {
        return this.totalUsageTime;
    }

    public SubscriptionService totalUsageTime(Integer totalUsageTime) {
        this.setTotalUsageTime(totalUsageTime);
        return this;
    }

    public void setTotalUsageTime(Integer totalUsageTime) {
        this.totalUsageTime = totalUsageTime;
    }

    public Instant getStartTime() {
        return this.startTime;
    }

    public SubscriptionService startTime(Instant startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return this.endTime;
    }

    public SubscriptionService endTime(Instant endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Set<Usage> getUsages() {
        return this.usages;
    }

    public void setUsages(Set<Usage> usages) {
        if (this.usages != null) {
            this.usages.forEach(i -> i.setSubscriptionService(null));
        }
        if (usages != null) {
            usages.forEach(i -> i.setSubscriptionService(this));
        }
        this.usages = usages;
    }

    public SubscriptionService usages(Set<Usage> usages) {
        this.setUsages(usages);
        return this;
    }

    public SubscriptionService addUsage(Usage usage) {
        this.usages.add(usage);
        usage.setSubscriptionService(this);
        return this;
    }

    public SubscriptionService removeUsage(Usage usage) {
        this.usages.remove(usage);
        usage.setSubscriptionService(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionService)) {
            return false;
        }
        return getId() != null && getId().equals(((SubscriptionService) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubscriptionService{" +
            "id=" + getId() +
            ", serviceLevel='" + getServiceLevel() + "'" +
            ", totalUsageTime=" + getTotalUsageTime() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }
}
