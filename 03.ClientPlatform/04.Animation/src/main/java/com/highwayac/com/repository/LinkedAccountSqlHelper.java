package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class LinkedAccountSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("account_type", table, columnPrefix + "_account_type"));
        columns.add(Column.aliased("account_identifier", table, columnPrefix + "_account_identifier"));

        columns.add(Column.aliased("member_id", table, columnPrefix + "_member_id"));
        return columns;
    }
}
