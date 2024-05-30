package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class StripeCheckoutSessionSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("created", table, columnPrefix + "_created"));
        columns.add(Column.aliased("amount_subtotal", table, columnPrefix + "_amount_subtotal"));
        columns.add(Column.aliased("amount_total", table, columnPrefix + "_amount_total"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("name", table, columnPrefix + "_name"));
        columns.add(Column.aliased("client_reference_id", table, columnPrefix + "_client_reference_id"));
        columns.add(Column.aliased("payment_intent", table, columnPrefix + "_payment_intent"));
        columns.add(Column.aliased("payment_status", table, columnPrefix + "_payment_status"));

        return columns;
    }
}
