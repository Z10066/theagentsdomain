package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class MemberSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("username", table, columnPrefix + "_username"));
        columns.add(Column.aliased("full_name", table, columnPrefix + "_full_name"));
        columns.add(Column.aliased("role", table, columnPrefix + "_role"));
        columns.add(Column.aliased("active_status", table, columnPrefix + "_active_status"));

        columns.add(Column.aliased("workspace_id", table, columnPrefix + "_workspace_id"));
        return columns;
    }
}
