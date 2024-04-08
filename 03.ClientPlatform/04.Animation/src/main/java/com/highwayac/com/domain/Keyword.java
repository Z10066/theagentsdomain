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
 * A Keyword.
 */
@Table("keyword")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("word")
    private String word;

    @Transient
    @JsonIgnoreProperties(value = { "creator", "category", "copyright", "extraInfo", "keywords" }, allowSetters = true)
    private Set<Video> videos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Keyword id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }

    public Keyword word(String word) {
        this.setWord(word);
        return this;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<Video> getVideos() {
        return this.videos;
    }

    public void setVideos(Set<Video> videos) {
        if (this.videos != null) {
            this.videos.forEach(i -> i.removeKeyword(this));
        }
        if (videos != null) {
            videos.forEach(i -> i.addKeyword(this));
        }
        this.videos = videos;
    }

    public Keyword videos(Set<Video> videos) {
        this.setVideos(videos);
        return this;
    }

    public Keyword addVideo(Video video) {
        this.videos.add(video);
        video.getKeywords().add(this);
        return this;
    }

    public Keyword removeVideo(Video video) {
        this.videos.remove(video);
        video.getKeywords().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Keyword)) {
            return false;
        }
        return getId() != null && getId().equals(((Keyword) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Keyword{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            "}";
    }
}
