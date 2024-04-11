package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class VideoProductionSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("title", table, columnPrefix + "_title"));
        columns.add(Column.aliased("creator", table, columnPrefix + "_creator"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("copyright", table, columnPrefix + "_copyright"));
        columns.add(Column.aliased("watch_link", table, columnPrefix + "_watch_link"));

        columns.add(Column.aliased("extra_info_id", table, columnPrefix + "_extra_info_id"));
        columns.add(Column.aliased("workspace_id", table, columnPrefix + "_workspace_id"));
        return columns;
    }
}
