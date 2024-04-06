package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class VideoSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("title", table, columnPrefix + "_title"));
        columns.add(Column.aliased("duration", table, columnPrefix + "_duration"));
        columns.add(Column.aliased("format", table, columnPrefix + "_format"));
        columns.add(Column.aliased("resolution", table, columnPrefix + "_resolution"));
        columns.add(Column.aliased("published_date", table, columnPrefix + "_published_date"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("viewing_link", table, columnPrefix + "_viewing_link"));

        columns.add(Column.aliased("creator_id", table, columnPrefix + "_creator_id"));
        columns.add(Column.aliased("category_id", table, columnPrefix + "_category_id"));
        columns.add(Column.aliased("copyright_id", table, columnPrefix + "_copyright_id"));
        columns.add(Column.aliased("extra_info_id", table, columnPrefix + "_extra_info_id"));
        return columns;
    }
}
