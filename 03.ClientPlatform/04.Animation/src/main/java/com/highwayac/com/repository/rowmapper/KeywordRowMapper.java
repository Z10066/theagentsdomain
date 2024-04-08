package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Keyword;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Keyword}, with proper type conversions.
 */
@Service
public class KeywordRowMapper implements BiFunction<Row, String, Keyword> {

    private final ColumnConverter converter;

    public KeywordRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Keyword} stored in the database.
     */
    @Override
    public Keyword apply(Row row, String prefix) {
        Keyword entity = new Keyword();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setWord(converter.fromRow(row, prefix + "_word", String.class));
        return entity;
    }
}
