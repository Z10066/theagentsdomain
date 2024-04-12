package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.ExtraInfo;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ExtraInfo}, with proper type conversions.
 */
@Service
public class ExtraInfoRowMapper implements BiFunction<Row, String, ExtraInfo> {

    private final ColumnConverter converter;

    public ExtraInfoRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ExtraInfo} stored in the database.
     */
    @Override
    public ExtraInfo apply(Row row, String prefix) {
        ExtraInfo entity = new ExtraInfo();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAudienceFeedback(converter.fromRow(row, prefix + "_audience_feedback", String.class));
        entity.setRelatedVideos(converter.fromRow(row, prefix + "_related_videos", String.class));
        return entity;
    }
}
