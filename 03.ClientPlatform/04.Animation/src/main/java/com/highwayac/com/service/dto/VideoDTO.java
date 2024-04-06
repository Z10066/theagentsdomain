package com.highwayac.com.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.highwayac.com.domain.Video} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VideoDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String title;

    private String duration;

    private String format;

    private String resolution;

    private LocalDate publishedDate;

    @Lob
    private String description;

    private String viewingLink;

    private CreatorDTO creator;

    private CategoryDTO category;

    private CopyrightDTO copyright;

    private MetadataDTO extraInfo;

    private Set<KeywordDTO> keywords = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewingLink() {
        return viewingLink;
    }

    public void setViewingLink(String viewingLink) {
        this.viewingLink = viewingLink;
    }

    public CreatorDTO getCreator() {
        return creator;
    }

    public void setCreator(CreatorDTO creator) {
        this.creator = creator;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public CopyrightDTO getCopyright() {
        return copyright;
    }

    public void setCopyright(CopyrightDTO copyright) {
        this.copyright = copyright;
    }

    public MetadataDTO getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(MetadataDTO extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Set<KeywordDTO> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<KeywordDTO> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoDTO)) {
            return false;
        }

        VideoDTO videoDTO = (VideoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, videoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", duration='" + getDuration() + "'" +
            ", format='" + getFormat() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", publishedDate='" + getPublishedDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", viewingLink='" + getViewingLink() + "'" +
            ", creator=" + getCreator() +
            ", category=" + getCategory() +
            ", copyright=" + getCopyright() +
            ", extraInfo=" + getExtraInfo() +
            ", keywords=" + getKeywords() +
            "}";
    }
}
