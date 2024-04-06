package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Creator;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Creator}, with proper type conversions.
 */
@Service
public class CreatorRowMapper implements BiFunction<Row, String, Creator> {

    private final ColumnConverter converter;

    public CreatorRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Creator} stored in the database.
     */
    @Override
    public Creator apply(Row row, String prefix) {
        Creator entity = new Creator();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
