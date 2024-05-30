package com.highwayns.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InviteusersSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("invitername", table, columnPrefix + "_invitername"));
        columns.add(Column.aliased("workspaces", table, columnPrefix + "_workspaces"));
        columns.add(Column.aliased("invitertime", table, columnPrefix + "_invitertime"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("inviterid", table, columnPrefix + "_inviterid"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));

        return columns;
    }
}
