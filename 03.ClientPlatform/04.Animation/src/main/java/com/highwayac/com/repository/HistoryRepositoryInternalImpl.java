package com.highwayac.com.repository;

import com.highwayac.com.domain.History;
import com.highwayac.com.repository.rowmapper.ExtraInfoRowMapper;
import com.highwayac.com.repository.rowmapper.HistoryRowMapper;
import com.highwayac.com.repository.rowmapper.WorkspaceRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the History entity.
 */
@SuppressWarnings("unused")
class HistoryRepositoryInternalImpl extends SimpleR2dbcRepository<History, Long> implements HistoryRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ExtraInfoRowMapper extrainfoMapper;
    private final WorkspaceRowMapper workspaceMapper;
    private final HistoryRowMapper historyMapper;

    private static final Table entityTable = Table.aliased("history", EntityManager.ENTITY_ALIAS);
    private static final Table extraInfoTable = Table.aliased("extra_info", "extraInfo");
    private static final Table workspaceTable = Table.aliased("workspace", "workspace");

    public HistoryRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ExtraInfoRowMapper extrainfoMapper,
        WorkspaceRowMapper workspaceMapper,
        HistoryRowMapper historyMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(History.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.extrainfoMapper = extrainfoMapper;
        this.workspaceMapper = workspaceMapper;
        this.historyMapper = historyMapper;
    }

    @Override
    public Flux<History> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<History> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = HistorySqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(ExtraInfoSqlHelper.getColumns(extraInfoTable, "extraInfo"));
        columns.addAll(WorkspaceSqlHelper.getColumns(workspaceTable, "workspace"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(extraInfoTable)
            .on(Column.create("extra_info_id", entityTable))
            .equals(Column.create("id", extraInfoTable))
            .leftOuterJoin(workspaceTable)
            .on(Column.create("workspace_id", entityTable))
            .equals(Column.create("id", workspaceTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, History.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<History> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<History> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private History process(Row row, RowMetadata metadata) {
        History entity = historyMapper.apply(row, "e");
        entity.setExtraInfo(extrainfoMapper.apply(row, "extraInfo"));
        entity.setWorkspace(workspaceMapper.apply(row, "workspace"));
        return entity;
    }

    @Override
    public <S extends History> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
