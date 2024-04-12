package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Material;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Material}, with proper type conversions.
 */
@Service
public class MaterialRowMapper implements BiFunction<Row, String, Material> {

    private final ColumnConverter converter;

    public MaterialRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Material} stored in the database.
     */
    @Override
    public Material apply(Row row, String prefix) {
        Material entity = new Material();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTitle(converter.fromRow(row, prefix + "_title", String.class));
        entity.setCreator(converter.fromRow(row, prefix + "_creator", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setCopyright(converter.fromRow(row, prefix + "_copyright", String.class));
        entity.setWatchLink(converter.fromRow(row, prefix + "_watch_link", String.class));
        entity.setExtraInfoId(converter.fromRow(row, prefix + "_extra_info_id", Long.class));
        entity.setWorkspaceId(converter.fromRow(row, prefix + "_workspace_id", Long.class));
        return entity;
    }
}
