package com.highwayac.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Member.
 */
@Table("member")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("username")
    private String username;

    @NotNull(message = "must not be null")
    @Column("full_name")
    private String fullName;

    @Column("role")
    private String role;

    @Column("active_status")
    private Boolean activeStatus;

    @Transient
    @JsonIgnoreProperties(value = { "member" }, allowSetters = true)
    private Set<LinkedAccount> linkedAccounts = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "members", "videoProductions", "materials", "histories" }, allowSetters = true)
    private Workspace workspace;

    @Column("workspace_id")
    private Long workspaceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Member id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public Member username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Member fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return this.role;
    }

    public Member role(String role) {
        this.setRole(role);
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActiveStatus() {
        return this.activeStatus;
    }

    public Member activeStatus(Boolean activeStatus) {
        this.setActiveStatus(activeStatus);
        return this;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Set<LinkedAccount> getLinkedAccounts() {
        return this.linkedAccounts;
    }

    public void setLinkedAccounts(Set<LinkedAccount> linkedAccounts) {
        if (this.linkedAccounts != null) {
            this.linkedAccounts.forEach(i -> i.setMember(null));
        }
        if (linkedAccounts != null) {
            linkedAccounts.forEach(i -> i.setMember(this));
        }
        this.linkedAccounts = linkedAccounts;
    }

    public Member linkedAccounts(Set<LinkedAccount> linkedAccounts) {
        this.setLinkedAccounts(linkedAccounts);
        return this;
    }

    public Member addLinkedAccount(LinkedAccount linkedAccount) {
        this.linkedAccounts.add(linkedAccount);
        linkedAccount.setMember(this);
        return this;
    }

    public Member removeLinkedAccount(LinkedAccount linkedAccount) {
        this.linkedAccounts.remove(linkedAccount);
        linkedAccount.setMember(null);
        return this;
    }

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        this.workspaceId = workspace != null ? workspace.getId() : null;
    }

    public Member workspace(Workspace workspace) {
        this.setWorkspace(workspace);
        return this;
    }

    public Long getWorkspaceId() {
        return this.workspaceId;
    }

    public void setWorkspaceId(Long workspace) {
        this.workspaceId = workspace;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }
        return getId() != null && getId().equals(((Member) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Member{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", role='" + getRole() + "'" +
            ", activeStatus='" + getActiveStatus() + "'" +
            "}";
    }
}
