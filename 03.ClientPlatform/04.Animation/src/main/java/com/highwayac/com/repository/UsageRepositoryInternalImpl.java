package com.highwayac.com.repository;

import com.highwayac.com.domain.Usage;
import com.highwayac.com.repository.rowmapper.SubscriptionServiceRowMapper;
import com.highwayac.com.repository.rowmapper.UsageRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Usage entity.
 */
@SuppressWarnings("unused")
class UsageRepositoryInternalImpl extends SimpleR2dbcRepository<Usage, Long> implements UsageRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final SubscriptionServiceRowMapper subscriptionserviceMapper;
    private final UsageRowMapper usageMapper;

    private static final Table entityTable = Table.aliased("usage", EntityManager.ENTITY_ALIAS);
    private static final Table subscriptionServiceTable = Table.aliased("subscription_service", "subscriptionService");

    public UsageRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        SubscriptionServiceRowMapper subscriptionserviceMapper,
        UsageRowMapper usageMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Usage.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.subscriptionserviceMapper = subscriptionserviceMapper;
        this.usageMapper = usageMapper;
    }

    @Override
    public Flux<Usage> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Usage> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = UsageSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(SubscriptionServiceSqlHelper.getColumns(subscriptionServiceTable, "subscriptionService"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(subscriptionServiceTable)
            .on(Column.create("subscription_service_id", entityTable))
            .equals(Column.create("id", subscriptionServiceTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Usage.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Usage> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Usage> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Usage process(Row row, RowMetadata metadata) {
        Usage entity = usageMapper.apply(row, "e");
        entity.setSubscriptionService(subscriptionserviceMapper.apply(row, "subscriptionService"));
        return entity;
    }

    @Override
    public <S extends Usage> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
