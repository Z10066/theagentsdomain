package com.highwayns.repository;

import com.highwayns.domain.Inviteusers;
import com.highwayns.repository.rowmapper.InviteusersRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Inviteusers entity.
 */
@SuppressWarnings("unused")
class InviteusersRepositoryInternalImpl extends SimpleR2dbcRepository<Inviteusers, Long> implements InviteusersRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final InviteusersRowMapper inviteusersMapper;

    private static final Table entityTable = Table.aliased("inviteusers", EntityManager.ENTITY_ALIAS);

    public InviteusersRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        InviteusersRowMapper inviteusersMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Inviteusers.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.inviteusersMapper = inviteusersMapper;
    }

    @Override
    public Flux<Inviteusers> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Inviteusers> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = InviteusersSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Inviteusers.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Inviteusers> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Inviteusers> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Inviteusers process(Row row, RowMetadata metadata) {
        Inviteusers entity = inviteusersMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Inviteusers> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
