package com.highwayac.com.repository;

import com.highwayac.com.domain.ExtraInfo;
import com.highwayac.com.repository.rowmapper.ExtraInfoRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the ExtraInfo entity.
 */
@SuppressWarnings("unused")
class ExtraInfoRepositoryInternalImpl extends SimpleR2dbcRepository<ExtraInfo, Long> implements ExtraInfoRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ExtraInfoRowMapper extrainfoMapper;

    private static final Table entityTable = Table.aliased("extra_info", EntityManager.ENTITY_ALIAS);

    public ExtraInfoRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ExtraInfoRowMapper extrainfoMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(ExtraInfo.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.extrainfoMapper = extrainfoMapper;
    }

    @Override
    public Flux<ExtraInfo> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<ExtraInfo> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ExtraInfoSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, ExtraInfo.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<ExtraInfo> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<ExtraInfo> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private ExtraInfo process(Row row, RowMetadata metadata) {
        ExtraInfo entity = extrainfoMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends ExtraInfo> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
