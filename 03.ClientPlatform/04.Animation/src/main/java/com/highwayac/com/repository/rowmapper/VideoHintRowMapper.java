package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.VideoHint;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link VideoHint}, with proper type conversions.
 */
@Service
public class VideoHintRowMapper implements BiFunction<Row, String, VideoHint> {

    private final ColumnConverter converter;

    public VideoHintRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link VideoHint} stored in the database.
     */
    @Override
    public VideoHint apply(Row row, String prefix) {
        VideoHint entity = new VideoHint();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setWorkspace(converter.fromRow(row, prefix + "_workspace", String.class));
        entity.setCreator(converter.fromRow(row, prefix + "_creator", String.class));
        entity.setCreationContent(converter.fromRow(row, prefix + "_creation_content", String.class));
        entity.setBackgroundMusic(converter.fromRow(row, prefix + "_background_music", String.class));
        return entity;
    }
}
