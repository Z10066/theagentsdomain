package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.Usage;
import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Usage}, with proper type conversions.
 */
@Service
public class UsageRowMapper implements BiFunction<Row, String, Usage> {

    private final ColumnConverter converter;

    public UsageRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Usage} stored in the database.
     */
    @Override
    public Usage apply(Row row, String prefix) {
        Usage entity = new Usage();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUsageType(converter.fromRow(row, prefix + "_usage_type", String.class));
        entity.setUsageTime(converter.fromRow(row, prefix + "_usage_time", Integer.class));
        entity.setStartTime(converter.fromRow(row, prefix + "_start_time", Instant.class));
        entity.setEndTime(converter.fromRow(row, prefix + "_end_time", Instant.class));
        entity.setSubscriptionServiceId(converter.fromRow(row, prefix + "_subscription_service_id", Long.class));
        return entity;
    }
}
