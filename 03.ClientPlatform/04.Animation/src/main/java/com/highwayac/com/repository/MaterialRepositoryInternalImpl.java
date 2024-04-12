package com.highwayac.com.repository;

import com.highwayac.com.domain.Material;
import com.highwayac.com.repository.rowmapper.ExtraInfoRowMapper;
import com.highwayac.com.repository.rowmapper.MaterialRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Material entity.
 */
@SuppressWarnings("unused")
class MaterialRepositoryInternalImpl extends SimpleR2dbcRepository<Material, Long> implements MaterialRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ExtraInfoRowMapper extrainfoMapper;
    private final WorkspaceRowMapper workspaceMapper;
    private final MaterialRowMapper materialMapper;

    private static final Table entityTable = Table.aliased("material", EntityManager.ENTITY_ALIAS);
    private static final Table extraInfoTable = Table.aliased("extra_info", "extraInfo");
    private static final Table workspaceTable = Table.aliased("workspace", "workspace");

    public MaterialRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ExtraInfoRowMapper extrainfoMapper,
        WorkspaceRowMapper workspaceMapper,
        MaterialRowMapper materialMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Material.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.extrainfoMapper = extrainfoMapper;
        this.workspaceMapper = workspaceMapper;
        this.materialMapper = materialMapper;
    }

    @Override
    public Flux<Material> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Material> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = MaterialSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
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
        String select = entityManager.createSelect(selectFrom, Material.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Material> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Material> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Material process(Row row, RowMetadata metadata) {
        Material entity = materialMapper.apply(row, "e");
        entity.setExtraInfo(extrainfoMapper.apply(row, "extraInfo"));
        entity.setWorkspace(workspaceMapper.apply(row, "workspace"));
        return entity;
    }

    @Override
    public <S extends Material> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
