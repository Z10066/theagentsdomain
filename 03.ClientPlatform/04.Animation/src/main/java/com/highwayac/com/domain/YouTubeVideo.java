package com.highwayac.com.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A YouTubeVideo.
 */
@Table("you_tube_video")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class YouTubeVideo implements Serializable {

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

    @NotNull(message = "must not be null")
    @Column("theme")
    private String theme;

    @Column("content")
    private String content;

    @NotNull(message = "must not be null")
    @Column("background_music")
    private String backgroundMusic;

    @NotNull(message = "must not be null")
    @Column("video_time")
    private String videoTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public YouTubeVideo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkspace() {
        return this.workspace;
    }

    public YouTubeVideo workspace(String workspace) {
        this.setWorkspace(workspace);
        return this;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getCreator() {
        return this.creator;
    }

    public YouTubeVideo creator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTheme() {
        return this.theme;
    }

    public YouTubeVideo theme(String theme) {
        this.setTheme(theme);
        return this;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return this.content;
    }

    public YouTubeVideo content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBackgroundMusic() {
        return this.backgroundMusic;
    }

    public YouTubeVideo backgroundMusic(String backgroundMusic) {
        this.setBackgroundMusic(backgroundMusic);
        return this;
    }

    public void setBackgroundMusic(String backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public String getVideoTime() {
        return this.videoTime;
    }

    public YouTubeVideo videoTime(String videoTime) {
        this.setVideoTime(videoTime);
        return this;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof YouTubeVideo)) {
            return false;
        }
        return getId() != null && getId().equals(((YouTubeVideo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "YouTubeVideo{" +
            "id=" + getId() +
            ", workspace='" + getWorkspace() + "'" +
            ", creator='" + getCreator() + "'" +
            ", theme='" + getTheme() + "'" +
            ", content='" + getContent() + "'" +
            ", backgroundMusic='" + getBackgroundMusic() + "'" +
            ", videoTime='" + getVideoTime() + "'" +
            "}";
    }
}
