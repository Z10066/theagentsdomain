package com.highwayac.com.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.highwayac.com.domain.Copyright} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CopyrightDTO implements Serializable {

    private Long id;

    @Lob
    private String details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CopyrightDTO)) {
            return false;
        }

        CopyrightDTO copyrightDTO = (CopyrightDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, copyrightDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CopyrightDTO{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            "}";
    }
}
