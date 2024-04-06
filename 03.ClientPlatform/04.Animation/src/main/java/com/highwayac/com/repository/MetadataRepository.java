package com.highwayac.com.repository;

import com.highwayac.com.domain.Metadata;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Metadata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetadataRepository extends ReactiveCrudRepository<Metadata, Long>, MetadataRepositoryInternal {
    Flux<Metadata> findAllBy(Pageable pageable);

    @Query("SELECT * FROM metadata entity WHERE entity.id not in (select video_id from video)")
    Flux<Metadata> findAllWhereVideoIsNull();

    @Override
    <S extends Metadata> Mono<S> save(S entity);

    @Override
    Flux<Metadata> findAll();

    @Override
    Mono<Metadata> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface MetadataRepositoryInternal {
    <S extends Metadata> Mono<S> save(S entity);

    Flux<Metadata> findAllBy(Pageable pageable);

    Flux<Metadata> findAll();

    Mono<Metadata> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Metadata> findAllBy(Pageable pageable, Criteria criteria);
}
