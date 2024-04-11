package com.highwayac.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A History.
 */
@Table("history")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("title")
    private String title;

    @NotNull(message = "must not be null")
    @Column("creator")
    private String creator;

    @Column("description")
    private String description;

    @Column("copyright")
    private String copyright;

    @Column("watch_link")
    private String watchLink;

    @Transient
    private ExtraInfo extraInfo;

    @Transient
    @JsonIgnoreProperties(value = { "members", "videoProductions", "materials", "histories" }, allowSetters = true)
    private Workspace workspace;

    @Column("extra_info_id")
    private Long extraInfoId;

    @Column("workspace_id")
    private Long workspaceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public History id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public History title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return this.creator;
    }

    public History creator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return this.description;
    }

    public History description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public History copyright(String copyright) {
        this.setCopyright(copyright);
        return this;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getWatchLink() {
        return this.watchLink;
    }

    public History watchLink(String watchLink) {
        this.setWatchLink(watchLink);
        return this;
    }

    public void setWatchLink(String watchLink) {
        this.watchLink = watchLink;
    }

    public ExtraInfo getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
        this.extraInfoId = extraInfo != null ? extraInfo.getId() : null;
    }

    public History extraInfo(ExtraInfo extraInfo) {
        this.setExtraInfo(extraInfo);
        return this;
    }

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        this.workspaceId = workspace != null ? workspace.getId() : null;
    }

    public History workspace(Workspace workspace) {
        this.setWorkspace(workspace);
        return this;
    }

    public Long getExtraInfoId() {
        return this.extraInfoId;
    }

    public void setExtraInfoId(Long extraInfo) {
        this.extraInfoId = extraInfo;
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
        if (!(o instanceof History)) {
            return false;
        }
        return getId() != null && getId().equals(((History) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "History{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", creator='" + getCreator() + "'" +
            ", description='" + getDescription() + "'" +
            ", copyright='" + getCopyright() + "'" +
            ", watchLink='" + getWatchLink() + "'" +
            "}";
    }
}
