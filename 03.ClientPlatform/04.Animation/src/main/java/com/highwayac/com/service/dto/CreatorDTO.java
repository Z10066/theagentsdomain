package com.highwayac.com.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.highwayac.com.domain.Creator} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreatorDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreatorDTO)) {
            return false;
        }

        CreatorDTO creatorDTO = (CreatorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, creatorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreatorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
