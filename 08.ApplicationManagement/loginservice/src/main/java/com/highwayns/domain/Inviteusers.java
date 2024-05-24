package com.highwayns.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Inviteusers.
 */
@Table("inviteusers")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Inviteusers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("invitername")
    private String invitername;

    @NotNull(message = "must not be null")
    @Column("workspaces")
    private String workspaces;

    @NotNull(message = "must not be null")
    @Column("invitertime")
    private String invitertime;

    @NotNull(message = "must not be null")
    @Column("email")
    private String email;

    @NotNull(message = "must not be null")
    @Column("inviterid")
    private String inviterid;

    @NotNull(message = "must not be null")
    @Column("status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Inviteusers id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvitername() {
        return this.invitername;
    }

    public Inviteusers invitername(String invitername) {
        this.setInvitername(invitername);
        return this;
    }

    public void setInvitername(String invitername) {
        this.invitername = invitername;
    }

    public String getWorkspaces() {
        return this.workspaces;
    }

    public Inviteusers workspaces(String workspaces) {
        this.setWorkspaces(workspaces);
        return this;
    }

    public void setWorkspaces(String workspaces) {
        this.workspaces = workspaces;
    }

    public String getInvitertime() {
        return this.invitertime;
    }

    public Inviteusers invitertime(String invitertime) {
        this.setInvitertime(invitertime);
        return this;
    }

    public void setInvitertime(String invitertime) {
        this.invitertime = invitertime;
    }

    public String getEmail() {
        return this.email;
    }

    public Inviteusers email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInviterid() {
        return this.inviterid;
    }

    public Inviteusers inviterid(String inviterid) {
        this.setInviterid(inviterid);
        return this;
    }

    public void setInviterid(String inviterid) {
        this.inviterid = inviterid;
    }

    public String getStatus() {
        return this.status;
    }

    public Inviteusers status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inviteusers)) {
            return false;
        }
        return getId() != null && getId().equals(((Inviteusers) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inviteusers{" +
            "id=" + getId() +
            ", invitername='" + getInvitername() + "'" +
            ", workspaces='" + getWorkspaces() + "'" +
            ", invitertime='" + getInvitertime() + "'" +
            ", email='" + getEmail() + "'" +
            ", inviterid='" + getInviterid() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
