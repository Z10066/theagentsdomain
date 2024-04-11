package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class UsageSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("usage_type", table, columnPrefix + "_usage_type"));
        columns.add(Column.aliased("usage_time", table, columnPrefix + "_usage_time"));
        columns.add(Column.aliased("start_time", table, columnPrefix + "_start_time"));
        columns.add(Column.aliased("end_time", table, columnPrefix + "_end_time"));

        columns.add(Column.aliased("subscription_service_id", table, columnPrefix + "_subscription_service_id"));
        return columns;
    }
}
