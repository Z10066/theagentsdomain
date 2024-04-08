package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Copyright;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Copyright}, with proper type conversions.
 */
@Service
public class CopyrightRowMapper implements BiFunction<Row, String, Copyright> {

    private final ColumnConverter converter;

    public CopyrightRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Copyright} stored in the database.
     */
    @Override
    public Copyright apply(Row row, String prefix) {
        Copyright entity = new Copyright();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setDetails(converter.fromRow(row, prefix + "_details", String.class));
        return entity;
    }
}
