package com.highwayns.highwaypj.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A FileConfiguration.
 */
@Table("file_configuration")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FileConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @NotNull(message = "must not be null")
    @Column("path")
    private String path;

    @NotNull(message = "must not be null")
    @Column("types")
    private String types;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FileConfiguration id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public FileConfiguration name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public FileConfiguration description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return this.path;
    }

    public FileConfiguration path(String path) {
        this.setPath(path);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTypes() {
        return this.types;
    }

    public FileConfiguration types(String types) {
        this.setTypes(types);
        return this;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileConfiguration)) {
            return false;
        }
        return getId() != null && getId().equals(((FileConfiguration) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileConfiguration{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", path='" + getPath() + "'" +
            ", types='" + getTypes() + "'" +
            "}";
    }
}
