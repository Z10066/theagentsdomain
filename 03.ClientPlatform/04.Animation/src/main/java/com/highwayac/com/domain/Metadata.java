package com.highwayac.com.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Metadata.
 */
@Table("metadata")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Metadata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("audience_feedback")
    private String audienceFeedback;

    @Column("related_videos")
    private String relatedVideos;

    @Transient
    private Video video;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Metadata id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAudienceFeedback() {
        return this.audienceFeedback;
    }

    public Metadata audienceFeedback(String audienceFeedback) {
        this.setAudienceFeedback(audienceFeedback);
        return this;
    }

    public void setAudienceFeedback(String audienceFeedback) {
        this.audienceFeedback = audienceFeedback;
    }

    public String getRelatedVideos() {
        return this.relatedVideos;
    }

    public Metadata relatedVideos(String relatedVideos) {
        this.setRelatedVideos(relatedVideos);
        return this;
    }

    public void setRelatedVideos(String relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        if (this.video != null) {
            this.video.setExtraInfo(null);
        }
        if (video != null) {
            video.setExtraInfo(this);
        }
        this.video = video;
    }

    public Metadata video(Video video) {
        this.setVideo(video);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Metadata)) {
            return false;
        }
        return getId() != null && getId().equals(((Metadata) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Metadata{" +
            "id=" + getId() +
            ", audienceFeedback='" + getAudienceFeedback() + "'" +
            ", relatedVideos='" + getRelatedVideos() + "'" +
            "}";
    }
}
