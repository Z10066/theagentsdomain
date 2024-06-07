package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class NovelSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("noveltext", table, columnPrefix + "_noveltext"));
        columns.add(Column.aliased("novelname", table, columnPrefix + "_novelname"));
        columns.add(Column.aliased("noveltype", table, columnPrefix + "_noveltype"));

        return columns;
    }
}
