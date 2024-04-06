package com.highwayac.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Video.
 */
@Table("video")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("title")
    private String title;

    @Column("duration")
    private String duration;

    @Column("format")
    private String format;

    @Column("resolution")
    private String resolution;

    @Column("published_date")
    private LocalDate publishedDate;

    @Column("description")
    private String description;

    @Column("viewing_link")
    private String viewingLink;

    @Transient
    private Creator creator;

    @Transient
    private Category category;

    @Transient
    private Copyright copyright;

    @Transient
    private Metadata extraInfo;

    @Transient
    @JsonIgnoreProperties(value = { "videos" }, allowSetters = true)
    private Set<Keyword> keywords = new HashSet<>();

    @Column("creator_id")
    private Long creatorId;

    @Column("category_id")
    private Long categoryId;

    @Column("copyright_id")
    private Long copyrightId;

    @Column("extra_info_id")
    private Long extraInfoId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Video id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Video title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return this.duration;
    }

    public Video duration(String duration) {
        this.setDuration(duration);
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return this.format;
    }

    public Video format(String format) {
        this.setFormat(format);
        return this;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getResolution() {
        return this.resolution;
    }

    public Video resolution(String resolution) {
        this.setResolution(resolution);
        return this;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public LocalDate getPublishedDate() {
        return this.publishedDate;
    }

    public Video publishedDate(LocalDate publishedDate) {
        this.setPublishedDate(publishedDate);
        return this;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return this.description;
    }

    public Video description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewingLink() {
        return this.viewingLink;
    }

    public Video viewingLink(String viewingLink) {
        this.setViewingLink(viewingLink);
        return this;
    }

    public void setViewingLink(String viewingLink) {
        this.viewingLink = viewingLink;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
        this.creatorId = creator != null ? creator.getId() : null;
    }

    public Video creator(Creator creator) {
        this.setCreator(creator);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.categoryId = category != null ? category.getId() : null;
    }

    public Video category(Category category) {
        this.setCategory(category);
        return this;
    }

    public Copyright getCopyright() {
        return this.copyright;
    }

    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
        this.copyrightId = copyright != null ? copyright.getId() : null;
    }

    public Video copyright(Copyright copyright) {
        this.setCopyright(copyright);
        return this;
    }

    public Metadata getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(Metadata metadata) {
        this.extraInfo = metadata;
        this.extraInfoId = metadata != null ? metadata.getId() : null;
    }

    public Video extraInfo(Metadata metadata) {
        this.setExtraInfo(metadata);
        return this;
    }

    public Set<Keyword> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Video keywords(Set<Keyword> keywords) {
        this.setKeywords(keywords);
        return this;
    }

    public Video addKeyword(Keyword keyword) {
        this.keywords.add(keyword);
        return this;
    }

    public Video removeKeyword(Keyword keyword) {
        this.keywords.remove(keyword);
        return this;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Long creator) {
        this.creatorId = creator;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long category) {
        this.categoryId = category;
    }

    public Long getCopyrightId() {
        return this.copyrightId;
    }

    public void setCopyrightId(Long copyright) {
        this.copyrightId = copyright;
    }

    public Long getExtraInfoId() {
        return this.extraInfoId;
    }

    public void setExtraInfoId(Long metadata) {
        this.extraInfoId = metadata;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Video)) {
            return false;
        }
        return getId() != null && getId().equals(((Video) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Video{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", duration='" + getDuration() + "'" +
            ", format='" + getFormat() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", publishedDate='" + getPublishedDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", viewingLink='" + getViewingLink() + "'" +
            "}";
    }
}
