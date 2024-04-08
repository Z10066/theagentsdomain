package com.highwayac.com.repository;

import com.highwayac.com.domain.Creator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Creator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreatorRepository extends ReactiveCrudRepository<Creator, Long>, CreatorRepositoryInternal {
    Flux<Creator> findAllBy(Pageable pageable);

    @Query("SELECT * FROM creator entity WHERE entity.id not in (select video_id from video)")
    Flux<Creator> findAllWhereVideoIsNull();

    @Override
    <S extends Creator> Mono<S> save(S entity);

    @Override
    Flux<Creator> findAll();

    @Override
    Mono<Creator> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreatorRepositoryInternal {
    <S extends Creator> Mono<S> save(S entity);

    Flux<Creator> findAllBy(Pageable pageable);

    Flux<Creator> findAll();

    Mono<Creator> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Creator> findAllBy(Pageable pageable, Criteria criteria);
}
