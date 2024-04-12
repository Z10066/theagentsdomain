package com.highwayac.com.repository;

import com.highwayac.com.domain.Usage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Usage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsageRepository extends ReactiveCrudRepository<Usage, Long>, UsageRepositoryInternal {
    @Query("SELECT * FROM usage entity WHERE entity.subscription_service_id = :id")
    Flux<Usage> findBySubscriptionService(Long id);

    @Query("SELECT * FROM usage entity WHERE entity.subscription_service_id IS NULL")
    Flux<Usage> findAllWhereSubscriptionServiceIsNull();

    @Override
    <S extends Usage> Mono<S> save(S entity);

    @Override
    Flux<Usage> findAll();

    @Override
    Mono<Usage> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface UsageRepositoryInternal {
    <S extends Usage> Mono<S> save(S entity);

    Flux<Usage> findAllBy(Pageable pageable);

    Flux<Usage> findAll();

    Mono<Usage> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Usage> findAllBy(Pageable pageable, Criteria criteria);
}
