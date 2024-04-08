package com.highwayac.com.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Creator.
 */
@Table("creator")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Creator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("name")
    private String name;

    @Transient
    private Video video;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Creator id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Creator name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        if (this.video != null) {
            this.video.setCreator(null);
        }
        if (video != null) {
            video.setCreator(this);
        }
        this.video = video;
    }

    public Creator video(Video video) {
        this.setVideo(video);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Creator)) {
            return false;
        }
        return getId() != null && getId().equals(((Creator) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Creator{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
