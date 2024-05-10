package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.YouTubeVideo;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link YouTubeVideo}, with proper type conversions.
 */
@Service
public class YouTubeVideoRowMapper implements BiFunction<Row, String, YouTubeVideo> {

    private final ColumnConverter converter;

    public YouTubeVideoRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link YouTubeVideo} stored in the database.
     */
    @Override
    public YouTubeVideo apply(Row row, String prefix) {
        YouTubeVideo entity = new YouTubeVideo();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setWorkspace(converter.fromRow(row, prefix + "_workspace", String.class));
        entity.setCreator(converter.fromRow(row, prefix + "_creator", String.class));
        entity.setTheme(converter.fromRow(row, prefix + "_theme", String.class));
        entity.setContent(converter.fromRow(row, prefix + "_content", String.class));
        entity.setBackgroundMusic(converter.fromRow(row, prefix + "_background_music", String.class));
        entity.setVideoTime(converter.fromRow(row, prefix + "_video_time", String.class));
        entity.setGender(converter.fromRow(row, prefix + "_gender", String.class));
        entity.setVideolanguage(converter.fromRow(row, prefix + "_videolanguage", String.class));
        return entity;
    }
}
