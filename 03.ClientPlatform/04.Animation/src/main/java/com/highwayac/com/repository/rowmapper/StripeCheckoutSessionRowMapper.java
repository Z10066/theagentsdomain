package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.StripeCheckoutSession;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link StripeCheckoutSession}, with proper type conversions.
 */
@Service
public class StripeCheckoutSessionRowMapper implements BiFunction<Row, String, StripeCheckoutSession> {

    private final ColumnConverter converter;

    public StripeCheckoutSessionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link StripeCheckoutSession} stored in the database.
     */
    @Override
    public StripeCheckoutSession apply(Row row, String prefix) {
        StripeCheckoutSession entity = new StripeCheckoutSession();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCreated(converter.fromRow(row, prefix + "_created", Long.class));
        entity.setAmountSubtotal(converter.fromRow(row, prefix + "_amount_subtotal", Long.class));
        entity.setAmountTotal(converter.fromRow(row, prefix + "_amount_total", Long.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setClientReferenceId(converter.fromRow(row, prefix + "_client_reference_id", String.class));
        entity.setPaymentIntent(converter.fromRow(row, prefix + "_payment_intent", String.class));
        entity.setPaymentStatus(converter.fromRow(row, prefix + "_payment_status", String.class));
        return entity;
    }
}
