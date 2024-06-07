package com.highwayac.com.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Novel.
 */
@Table("novel")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Novel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("noveltext")
    private String noveltext;

    @NotNull(message = "must not be null")
    @Column("novelname")
    private String novelname;

    @Column("noveltype")
    private String noveltype;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Novel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoveltext() {
        return this.noveltext;
    }

    public Novel noveltext(String noveltext) {
        this.setNoveltext(noveltext);
        return this;
    }

    public void setNoveltext(String noveltext) {
        this.noveltext = noveltext;
    }

    public String getNovelname() {
        return this.novelname;
    }

    public Novel novelname(String novelname) {
        this.setNovelname(novelname);
        return this;
    }

    public void setNovelname(String novelname) {
        this.novelname = novelname;
    }

    public String getNoveltype() {
        return this.noveltype;
    }

    public Novel noveltype(String noveltype) {
        this.setNoveltype(noveltype);
        return this;
    }

    public void setNoveltype(String noveltype) {
        this.noveltype = noveltype;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Novel)) {
            return false;
        }
        return getId() != null && getId().equals(((Novel) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Novel{" +
            "id=" + getId() +
            ", noveltext='" + getNoveltext() + "'" +
            ", novelname='" + getNovelname() + "'" +
            ", noveltype='" + getNoveltype() + "'" +
            "}";
    }
}
