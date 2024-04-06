package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Video;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Video}, with proper type conversions.
 */
@Service
public class VideoRowMapper implements BiFunction<Row, String, Video> {

    private final ColumnConverter converter;

    public VideoRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Video} stored in the database.
     */
    @Override
    public Video apply(Row row, String prefix) {
        Video entity = new Video();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTitle(converter.fromRow(row, prefix + "_title", String.class));
        entity.setDuration(converter.fromRow(row, prefix + "_duration", String.class));
        entity.setFormat(converter.fromRow(row, prefix + "_format", String.class));
        entity.setResolution(converter.fromRow(row, prefix + "_resolution", String.class));
        entity.setPublishedDate(converter.fromRow(row, prefix + "_published_date", LocalDate.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setViewingLink(converter.fromRow(row, prefix + "_viewing_link", String.class));
        entity.setCreatorId(converter.fromRow(row, prefix + "_creator_id", Long.class));
        entity.setCategoryId(converter.fromRow(row, prefix + "_category_id", Long.class));
        entity.setCopyrightId(converter.fromRow(row, prefix + "_copyright_id", Long.class));
        entity.setExtraInfoId(converter.fromRow(row, prefix + "_extra_info_id", Long.class));
        return entity;
    }
}
