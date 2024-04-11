package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class WorkspaceSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("name", table, columnPrefix + "_name"));
        columns.add(Column.aliased("identifier", table, columnPrefix + "_identifier"));
        columns.add(Column.aliased("beta_features", table, columnPrefix + "_beta_features"));
        columns.add(Column.aliased("collaboration_cursor", table, columnPrefix + "_collaboration_cursor"));
        columns.add(Column.aliased("default_export_visibility", table, columnPrefix + "_default_export_visibility"));
        columns.add(Column.aliased("public_access", table, columnPrefix + "_public_access"));

        return columns;
    }
}
