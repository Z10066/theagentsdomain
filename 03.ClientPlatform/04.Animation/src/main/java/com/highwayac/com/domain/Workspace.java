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
 * A Workspace.
 */
@Table("workspace")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Workspace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("name")
    private String name;

    @NotNull(message = "must not be null")
    @Column("identifier")
    private String identifier;

    @Column("beta_features")
    private Boolean betaFeatures;

    @Column("collaboration_cursor")
    private Boolean collaborationCursor;

    @Column("default_export_visibility")
    private Boolean defaultExportVisibility;

    @Column("public_access")
    private Boolean publicAccess;

    @Transient
    @JsonIgnoreProperties(value = { "linkedAccounts", "workspace" }, allowSetters = true)
    private Set<Member> members = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "extraInfo", "workspace" }, allowSetters = true)
    private Set<VideoProduction> videoProductions = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "extraInfo", "workspace" }, allowSetters = true)
    private Set<Material> materials = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "extraInfo", "workspace" }, allowSetters = true)
    private Set<History> histories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Workspace id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Workspace name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Workspace identifier(String identifier) {
        this.setIdentifier(identifier);
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getBetaFeatures() {
        return this.betaFeatures;
    }

    public Workspace betaFeatures(Boolean betaFeatures) {
        this.setBetaFeatures(betaFeatures);
        return this;
    }

    public void setBetaFeatures(Boolean betaFeatures) {
        this.betaFeatures = betaFeatures;
    }

    public Boolean getCollaborationCursor() {
        return this.collaborationCursor;
    }

    public Workspace collaborationCursor(Boolean collaborationCursor) {
        this.setCollaborationCursor(collaborationCursor);
        return this;
    }

    public void setCollaborationCursor(Boolean collaborationCursor) {
        this.collaborationCursor = collaborationCursor;
    }

    public Boolean getDefaultExportVisibility() {
        return this.defaultExportVisibility;
    }

    public Workspace defaultExportVisibility(Boolean defaultExportVisibility) {
        this.setDefaultExportVisibility(defaultExportVisibility);
        return this;
    }

    public void setDefaultExportVisibility(Boolean defaultExportVisibility) {
        this.defaultExportVisibility = defaultExportVisibility;
    }

    public Boolean getPublicAccess() {
        return this.publicAccess;
    }

    public Workspace publicAccess(Boolean publicAccess) {
        this.setPublicAccess(publicAccess);
        return this;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public Set<Member> getMembers() {
        return this.members;
    }

    public void setMembers(Set<Member> members) {
        if (this.members != null) {
            this.members.forEach(i -> i.setWorkspace(null));
        }
        if (members != null) {
            members.forEach(i -> i.setWorkspace(this));
        }
        this.members = members;
    }

    public Workspace members(Set<Member> members) {
        this.setMembers(members);
        return this;
    }

    public Workspace addMember(Member member) {
        this.members.add(member);
        member.setWorkspace(this);
        return this;
    }

    public Workspace removeMember(Member member) {
        this.members.remove(member);
        member.setWorkspace(null);
        return this;
    }

    public Set<VideoProduction> getVideoProductions() {
        return this.videoProductions;
    }

    public void setVideoProductions(Set<VideoProduction> videoProductions) {
        if (this.videoProductions != null) {
            this.videoProductions.forEach(i -> i.setWorkspace(null));
        }
        if (videoProductions != null) {
            videoProductions.forEach(i -> i.setWorkspace(this));
        }
        this.videoProductions = videoProductions;
    }

    public Workspace videoProductions(Set<VideoProduction> videoProductions) {
        this.setVideoProductions(videoProductions);
        return this;
    }

    public Workspace addVideoProduction(VideoProduction videoProduction) {
        this.videoProductions.add(videoProduction);
        videoProduction.setWorkspace(this);
        return this;
    }

    public Workspace removeVideoProduction(VideoProduction videoProduction) {
        this.videoProductions.remove(videoProduction);
        videoProduction.setWorkspace(null);
        return this;
    }

    public Set<Material> getMaterials() {
        return this.materials;
    }

    public void setMaterials(Set<Material> materials) {
        if (this.materials != null) {
            this.materials.forEach(i -> i.setWorkspace(null));
        }
        if (materials != null) {
            materials.forEach(i -> i.setWorkspace(this));
        }
        this.materials = materials;
    }

    public Workspace materials(Set<Material> materials) {
        this.setMaterials(materials);
        return this;
    }

    public Workspace addMaterial(Material material) {
        this.materials.add(material);
        material.setWorkspace(this);
        return this;
    }

    public Workspace removeMaterial(Material material) {
        this.materials.remove(material);
        material.setWorkspace(null);
        return this;
    }

    public Set<History> getHistories() {
        return this.histories;
    }

    public void setHistories(Set<History> histories) {
        if (this.histories != null) {
            this.histories.forEach(i -> i.setWorkspace(null));
        }
        if (histories != null) {
            histories.forEach(i -> i.setWorkspace(this));
        }
        this.histories = histories;
    }

    public Workspace histories(Set<History> histories) {
        this.setHistories(histories);
        return this;
    }

    public Workspace addHistory(History history) {
        this.histories.add(history);
        history.setWorkspace(this);
        return this;
    }

    public Workspace removeHistory(History history) {
        this.histories.remove(history);
        history.setWorkspace(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workspace)) {
            return false;
        }
        return getId() != null && getId().equals(((Workspace) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Workspace{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", betaFeatures='" + getBetaFeatures() + "'" +
            ", collaborationCursor='" + getCollaborationCursor() + "'" +
            ", defaultExportVisibility='" + getDefaultExportVisibility() + "'" +
            ", publicAccess='" + getPublicAccess() + "'" +
            "}";
    }
}
