package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Workspace;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Workspace}, with proper type conversions.
 */
@Service
public class WorkspaceRowMapper implements BiFunction<Row, String, Workspace> {

    private final ColumnConverter converter;

    public WorkspaceRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Workspace} stored in the database.
     */
    @Override
    public Workspace apply(Row row, String prefix) {
        Workspace entity = new Workspace();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setIdentifier(converter.fromRow(row, prefix + "_identifier", String.class));
        entity.setBetaFeatures(converter.fromRow(row, prefix + "_beta_features", Boolean.class));
        entity.setCollaborationCursor(converter.fromRow(row, prefix + "_collaboration_cursor", Boolean.class));
        entity.setDefaultExportVisibility(converter.fromRow(row, prefix + "_default_export_visibility", Boolean.class));
        entity.setPublicAccess(converter.fromRow(row, prefix + "_public_access", Boolean.class));
        return entity;
    }
}
