package com.highwayac.com.repository;

import com.highwayac.com.domain.Workspace;
import com.highwayac.com.repository.rowmapper.WorkspaceRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring Data R2DBC custom repository implementation for the Workspace entity.
 */
@SuppressWarnings("unused")
class WorkspaceRepositoryInternalImpl extends SimpleR2dbcRepository<Workspace, Long> implements WorkspaceRepositoryInternal {

    private final Logger log = LoggerFactory.getLogger(WorkspaceRepositoryInternalImpl.class);
    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final WorkspaceRowMapper workspaceMapper;

    private static final Table entityTable = Table.aliased("workspace", EntityManager.ENTITY_ALIAS);

    public WorkspaceRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        WorkspaceRowMapper workspaceMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Workspace.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.workspaceMapper = workspaceMapper;
    }

    @Override
    public Flux<Workspace> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Workspace> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = WorkspaceSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Workspace.class, pageable, whereClause);
        log.debug("REST request to get Workspace by select: {}", select.toString());
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Workspace> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Workspace> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Flux<Workspace> findByIdentifier(String identifier) {
        log.debug("REST request to get Workspace by identifier: {}", identifier);
        Comparison whereClause = Conditions.isEqual(entityTable.column("identifier"), SQL.literalOf(identifier.toString()));
        log.debug("REST request to get Workspace by whereClause: {}", whereClause.toString());
        return createQuery(null, whereClause).all();
    }


    private Workspace process(Row row, RowMetadata metadata) {
        Workspace entity = workspaceMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Workspace> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
