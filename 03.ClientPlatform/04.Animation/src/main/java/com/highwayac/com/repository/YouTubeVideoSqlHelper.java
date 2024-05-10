package com.highwayac.com.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class YouTubeVideoSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("workspace", table, columnPrefix + "_workspace"));
        columns.add(Column.aliased("creator", table, columnPrefix + "_creator"));
        columns.add(Column.aliased("theme", table, columnPrefix + "_theme"));
        columns.add(Column.aliased("content", table, columnPrefix + "_content"));
        columns.add(Column.aliased("background_music", table, columnPrefix + "_background_music"));
        columns.add(Column.aliased("video_time", table, columnPrefix + "_video_time"));
        columns.add(Column.aliased("gender", table, columnPrefix + "_gender"));
        columns.add(Column.aliased("videolanguage", table, columnPrefix + "_videolanguage"));
        columns.add(Column.aliased("subtitles", table, columnPrefix + "_subtitles"));

        return columns;
    }
}
