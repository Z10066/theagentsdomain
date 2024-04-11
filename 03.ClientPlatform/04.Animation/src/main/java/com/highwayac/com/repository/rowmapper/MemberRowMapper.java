package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Member;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Member}, with proper type conversions.
 */
@Service
public class MemberRowMapper implements BiFunction<Row, String, Member> {

    private final ColumnConverter converter;

    public MemberRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Member} stored in the database.
     */
    @Override
    public Member apply(Row row, String prefix) {
        Member entity = new Member();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUsername(converter.fromRow(row, prefix + "_username", String.class));
        entity.setFullName(converter.fromRow(row, prefix + "_full_name", String.class));
        entity.setRole(converter.fromRow(row, prefix + "_role", String.class));
        entity.setActiveStatus(converter.fromRow(row, prefix + "_active_status", Boolean.class));
        entity.setWorkspaceId(converter.fromRow(row, prefix + "_workspace_id", Long.class));
        return entity;
    }
}
