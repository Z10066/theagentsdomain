package com.highwayac.com.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A VideoHint.
 */
@Table("video_hint")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VideoHint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("workspace")
    private String workspace;

    @NotNull(message = "must not be null")
    @Column("creator")
    private String creator;

    @Column("creation_content")
    private String creationContent;

    @NotNull(message = "must not be null")
    @Column("background_music")
    private String backgroundMusic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VideoHint id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkspace() {
        return this.workspace;
    }

    public VideoHint workspace(String workspace) {
        this.setWorkspace(workspace);
        return this;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getCreator() {
        return this.creator;
    }

    public VideoHint creator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreationContent() {
        return this.creationContent;
    }

    public VideoHint creationContent(String creationContent) {
        this.setCreationContent(creationContent);
        return this;
    }

    public void setCreationContent(String creationContent) {
        this.creationContent = creationContent;
    }

    public String getBackgroundMusic() {
        return this.backgroundMusic;
    }

    public VideoHint backgroundMusic(String backgroundMusic) {
        this.setBackgroundMusic(backgroundMusic);
        return this;
    }

    public void setBackgroundMusic(String backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoHint)) {
            return false;
        }
        return getId() != null && getId().equals(((VideoHint) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoHint{" +
            "id=" + getId() +
            ", workspace='" + getWorkspace() + "'" +
            ", creator='" + getCreator() + "'" +
            ", creationContent='" + getCreationContent() + "'" +
            ", backgroundMusic='" + getBackgroundMusic() + "'" +
            "}";
    }
}
