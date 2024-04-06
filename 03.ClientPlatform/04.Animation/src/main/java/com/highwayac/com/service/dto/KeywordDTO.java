package com.highwayac.com.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.highwayac.com.domain.Keyword} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class KeywordDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String word;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KeywordDTO)) {
            return false;
        }

        KeywordDTO keywordDTO = (KeywordDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, keywordDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KeywordDTO{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            "}";
    }
}
