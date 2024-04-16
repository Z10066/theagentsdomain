package com.highwayac.com.repository;

import com.highwayac.com.domain.VideoHint;
import com.highwayac.com.repository.rowmapper.VideoHintRowMapper;
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
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the VideoHint entity.
 */
@SuppressWarnings("unused")
class VideoHintRepositoryInternalImpl extends SimpleR2dbcRepository<VideoHint, Long> implements VideoHintRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final VideoHintRowMapper videohintMapper;

    private static final Table entityTable = Table.aliased("video_hint", EntityManager.ENTITY_ALIAS);

    public VideoHintRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        VideoHintRowMapper videohintMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(VideoHint.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.videohintMapper = videohintMapper;
    }

    @Override
    public Flux<VideoHint> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<VideoHint> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = VideoHintSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, VideoHint.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<VideoHint> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<VideoHint> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private VideoHint process(Row row, RowMetadata metadata) {
        VideoHint entity = videohintMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends VideoHint> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
