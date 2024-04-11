package com.highwayac.com.repository;

import com.highwayac.com.domain.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the History entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoryRepository extends ReactiveCrudRepository<History, Long>, HistoryRepositoryInternal {
    @Query("SELECT * FROM history entity WHERE entity.extra_info_id = :id")
    Flux<History> findByExtraInfo(Long id);

    @Query("SELECT * FROM history entity WHERE entity.extra_info_id IS NULL")
    Flux<History> findAllWhereExtraInfoIsNull();

    @Query("SELECT * FROM history entity WHERE entity.workspace_id = :id")
    Flux<History> findByWorkspace(Long id);

    @Query("SELECT * FROM history entity WHERE entity.workspace_id IS NULL")
    Flux<History> findAllWhereWorkspaceIsNull();

    @Override
    <S extends History> Mono<S> save(S entity);

    @Override
    Flux<History> findAll();

    @Override
    Mono<History> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface HistoryRepositoryInternal {
    <S extends History> Mono<S> save(S entity);

    Flux<History> findAllBy(Pageable pageable);

    Flux<History> findAll();

    Mono<History> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<History> findAllBy(Pageable pageable, Criteria criteria);
}
