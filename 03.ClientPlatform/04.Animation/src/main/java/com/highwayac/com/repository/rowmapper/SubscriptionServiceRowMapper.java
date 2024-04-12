package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.SubscriptionService;
import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SubscriptionService}, with proper type conversions.
 */
@Service
public class SubscriptionServiceRowMapper implements BiFunction<Row, String, SubscriptionService> {

    private final ColumnConverter converter;

    public SubscriptionServiceRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SubscriptionService} stored in the database.
     */
    @Override
    public SubscriptionService apply(Row row, String prefix) {
        SubscriptionService entity = new SubscriptionService();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setServiceLevel(converter.fromRow(row, prefix + "_service_level", String.class));
        entity.setTotalUsageTime(converter.fromRow(row, prefix + "_total_usage_time", Integer.class));
        entity.setStartTime(converter.fromRow(row, prefix + "_start_time", Instant.class));
        entity.setEndTime(converter.fromRow(row, prefix + "_end_time", Instant.class));
        return entity;
    }
}
