package com.highwayns.repository.rowmapper;

import com.highwayns.domain.Inviteusers;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Inviteusers}, with proper type conversions.
 */
@Service
public class InviteusersRowMapper implements BiFunction<Row, String, Inviteusers> {

    private final ColumnConverter converter;

    public InviteusersRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Inviteusers} stored in the database.
     */
    @Override
    public Inviteusers apply(Row row, String prefix) {
        Inviteusers entity = new Inviteusers();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setInvitername(converter.fromRow(row, prefix + "_invitername", String.class));
        entity.setWorkspaces(converter.fromRow(row, prefix + "_workspaces", String.class));
        entity.setInvitertime(converter.fromRow(row, prefix + "_invitertime", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setInviterid(converter.fromRow(row, prefix + "_inviterid", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        return entity;
    }
}
