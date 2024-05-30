package com.highwayns.highwaypj.repository.rowmapper;

import com.highwayns.highwaypj.domain.FileConfiguration;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FileConfiguration}, with proper type conversions.
 */
@Service
public class FileConfigurationRowMapper implements BiFunction<Row, String, FileConfiguration> {

    private final ColumnConverter converter;

    public FileConfigurationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FileConfiguration} stored in the database.
     */
    @Override
    public FileConfiguration apply(Row row, String prefix) {
        FileConfiguration entity = new FileConfiguration();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setPath(converter.fromRow(row, prefix + "_path", String.class));
        entity.setTypes(converter.fromRow(row, prefix + "_types", String.class));
        return entity;
    }
}
