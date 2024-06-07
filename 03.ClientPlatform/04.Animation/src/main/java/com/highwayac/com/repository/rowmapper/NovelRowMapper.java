package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Novel;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Novel}, with proper type conversions.
 */
@Service
public class NovelRowMapper implements BiFunction<Row, String, Novel> {

    private final ColumnConverter converter;

    public NovelRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Novel} stored in the database.
     */
    @Override
    public Novel apply(Row row, String prefix) {
        Novel entity = new Novel();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setNoveltext(converter.fromRow(row, prefix + "_noveltext", String.class));
        entity.setNovelname(converter.fromRow(row, prefix + "_novelname", String.class));
        return entity;
    }
}
