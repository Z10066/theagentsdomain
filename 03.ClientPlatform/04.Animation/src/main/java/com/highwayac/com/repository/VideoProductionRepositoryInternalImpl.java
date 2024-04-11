package com.highwayac.com.repository;

import com.highwayac.com.domain.VideoProduction;
import com.highwayac.com.repository.rowmapper.ExtraInfoRowMapper;
import com.highwayac.com.repository.rowmapper.VideoProductionRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the VideoProduction entity.
 */
@SuppressWarnings("unused")
class VideoProductionRepositoryInternalImpl
    extends SimpleR2dbcRepository<VideoProduction, Long>
    implements VideoProductionRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ExtraInfoRowMapper extrainfoMapper;
    private final WorkspaceRowMapper workspaceMapper;
    private final VideoProductionRowMapper videoproductionMapper;

    private static final Table entityTable = Table.aliased("video_production", EntityManager.ENTITY_ALIAS);
    private static final Table extraInfoTable = Table.aliased("extra_info", "extraInfo");
    private static final Table workspaceTable = Table.aliased("workspace", "workspace");

    public VideoProductionRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ExtraInfoRowMapper extrainfoMapper,
        WorkspaceRowMapper workspaceMapper,
        VideoProductionRowMapper videoproductionMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(VideoProduction.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.extrainfoMapper = extrainfoMapper;
        this.workspaceMapper = workspaceMapper;
        this.videoproductionMapper = videoproductionMapper;
    }

    @Override
    public Flux<VideoProduction> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<VideoProduction> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = VideoProductionSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
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
        String select = entityManager.createSelect(selectFrom, VideoProduction.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<VideoProduction> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<VideoProduction> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private VideoProduction process(Row row, RowMetadata metadata) {
        VideoProduction entity = videoproductionMapper.apply(row, "e");
        entity.setExtraInfo(extrainfoMapper.apply(row, "extraInfo"));
        entity.setWorkspace(workspaceMapper.apply(row, "workspace"));
        return entity;
    }

    @Override
    public <S extends VideoProduction> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
