package com.highwayac.com.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.highwayac.com.domain.Metadata} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MetadataDTO implements Serializable {

    private Long id;

    @Lob
    private String audienceFeedback;

    private String relatedVideos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAudienceFeedback() {
        return audienceFeedback;
    }

    public void setAudienceFeedback(String audienceFeedback) {
        this.audienceFeedback = audienceFeedback;
    }

    public String getRelatedVideos() {
        return relatedVideos;
    }

    public void setRelatedVideos(String relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MetadataDTO)) {
            return false;
        }

        MetadataDTO metadataDTO = (MetadataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, metadataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MetadataDTO{" +
            "id=" + getId() +
            ", audienceFeedback='" + getAudienceFeedback() + "'" +
            ", relatedVideos='" + getRelatedVideos() + "'" +
            "}";
    }
}
