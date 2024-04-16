package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.SystemSetting;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SystemSetting}, with proper type conversions.
 */
@Service
public class SystemSettingRowMapper implements BiFunction<Row, String, SystemSetting> {

    private final ColumnConverter converter;

    public SystemSettingRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SystemSetting} stored in the database.
     */
    @Override
    public SystemSetting apply(Row row, String prefix) {
        SystemSetting entity = new SystemSetting();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setValue(converter.fromRow(row, prefix + "_value", String.class));
        return entity;
    }
}
