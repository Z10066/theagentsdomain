package com.highwayac.com.repository.rowmapper;

import com.highwayac.com.domain.LinkedAccount;
import com.highwayac.com.domain.enumeration.AccountType;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link LinkedAccount}, with proper type conversions.
 */
@Service
public class LinkedAccountRowMapper implements BiFunction<Row, String, LinkedAccount> {

    private final ColumnConverter converter;

    public LinkedAccountRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link LinkedAccount} stored in the database.
     */
    @Override
    public LinkedAccount apply(Row row, String prefix) {
        LinkedAccount entity = new LinkedAccount();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAccountType(converter.fromRow(row, prefix + "_account_type", AccountType.class));
        entity.setAccountIdentifier(converter.fromRow(row, prefix + "_account_identifier", String.class));
        entity.setMemberId(converter.fromRow(row, prefix + "_member_id", Long.class));
        return entity;
    }
}
