package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Metadata;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Metadata}, with proper type conversions.
 */
@Service
public class MetadataRowMapper implements BiFunction<Row, String, Metadata> {

    private final ColumnConverter converter;

    public MetadataRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Metadata} stored in the database.
     */
    @Override
    public Metadata apply(Row row, String prefix) {
        Metadata entity = new Metadata();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAudienceFeedback(converter.fromRow(row, prefix + "_audience_feedback", String.class));
        entity.setRelatedVideos(converter.fromRow(row, prefix + "_related_videos", String.class));
        return entity;
    }
}
