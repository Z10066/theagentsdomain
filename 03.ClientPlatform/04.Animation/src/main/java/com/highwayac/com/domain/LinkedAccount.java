package com.highwayac.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.highwayac.com.domain.enumeration.AccountType;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A LinkedAccount.
 */
@Table("linked_account")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LinkedAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("account_type")
    private AccountType accountType;

    @NotNull(message = "must not be null")
    @Column("account_identifier")
    private String accountIdentifier;

    @Transient
    @JsonIgnoreProperties(value = { "linkedAccounts", "workspace" }, allowSetters = true)
    private Member member;

    @Column("member_id")
    private Long memberId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LinkedAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public LinkedAccount accountType(AccountType accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountIdentifier() {
        return this.accountIdentifier;
    }

    public LinkedAccount accountIdentifier(String accountIdentifier) {
        this.setAccountIdentifier(accountIdentifier);
        return this;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
        this.memberId = member != null ? member.getId() : null;
    }

    public LinkedAccount member(Member member) {
        this.setMember(member);
        return this;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long member) {
        this.memberId = member;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedAccount)) {
            return false;
        }
        return getId() != null && getId().equals(((LinkedAccount) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LinkedAccount{" +
            "id=" + getId() +
            ", accountType='" + getAccountType() + "'" +
            ", accountIdentifier='" + getAccountIdentifier() + "'" +
            "}";
    }
}
