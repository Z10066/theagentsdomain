package com.highwayac.com.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Copyright.
 */
@Table("copyright")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Copyright implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("details")
    private String details;

    @Transient
    private Video video;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Copyright id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return this.details;
    }

    public Copyright details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        if (this.video != null) {
            this.video.setCopyright(null);
        }
        if (video != null) {
            video.setCopyright(this);
        }
        this.video = video;
    }

    public Copyright video(Video video) {
        this.setVideo(video);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Copyright)) {
            return false;
        }
        return getId() != null && getId().equals(((Copyright) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Copyright{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            "}";
    }
}
