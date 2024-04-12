package com.highwayac.com.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ExtraInfo.
 */
@Table("extra_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExtraInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("audience_feedback")
    private String audienceFeedback;

    @Column("related_videos")
    private String relatedVideos;

    @Transient
    private VideoProduction videoProduction;

    @Transient
    private Material material;

    @Transient
    private History history;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ExtraInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAudienceFeedback() {
        return this.audienceFeedback;
    }

    public ExtraInfo audienceFeedback(String audienceFeedback) {
        this.setAudienceFeedback(audienceFeedback);
        return this;
    }

    public void setAudienceFeedback(String audienceFeedback) {
        this.audienceFeedback = audienceFeedback;
    }

    public String getRelatedVideos() {
        return this.relatedVideos;
    }

    public ExtraInfo relatedVideos(String relatedVideos) {
        this.setRelatedVideos(relatedVideos);
        return this;
    }

    public void setRelatedVideos(String relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    public VideoProduction getVideoProduction() {
        return this.videoProduction;
    }

    public void setVideoProduction(VideoProduction videoProduction) {
        if (this.videoProduction != null) {
            this.videoProduction.setExtraInfo(null);
        }
        if (videoProduction != null) {
            videoProduction.setExtraInfo(this);
        }
        this.videoProduction = videoProduction;
    }

    public ExtraInfo videoProduction(VideoProduction videoProduction) {
        this.setVideoProduction(videoProduction);
        return this;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        if (this.material != null) {
            this.material.setExtraInfo(null);
        }
        if (material != null) {
            material.setExtraInfo(this);
        }
        this.material = material;
    }

    public ExtraInfo material(Material material) {
        this.setMaterial(material);
        return this;
    }

    public History getHistory() {
        return this.history;
    }

    public void setHistory(History history) {
        if (this.history != null) {
            this.history.setExtraInfo(null);
        }
        if (history != null) {
            history.setExtraInfo(this);
        }
        this.history = history;
    }

    public ExtraInfo history(History history) {
        this.setHistory(history);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtraInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((ExtraInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExtraInfo{" +
            "id=" + getId() +
            ", audienceFeedback='" + getAudienceFeedback() + "'" +
            ", relatedVideos='" + getRelatedVideos() + "'" +
            "}";
    }
}
