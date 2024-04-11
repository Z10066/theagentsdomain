package com.highwayac.com.repository;

import com.highwayac.com.domain.VideoProduction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the VideoProduction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoProductionRepository extends ReactiveCrudRepository<VideoProduction, Long>, VideoProductionRepositoryInternal {
    @Query("SELECT * FROM video_production entity WHERE entity.extra_info_id = :id")
    Flux<VideoProduction> findByExtraInfo(Long id);

    @Query("SELECT * FROM video_production entity WHERE entity.extra_info_id IS NULL")
    Flux<VideoProduction> findAllWhereExtraInfoIsNull();

    @Query("SELECT * FROM video_production entity WHERE entity.workspace_id = :id")
    Flux<VideoProduction> findByWorkspace(Long id);

    @Query("SELECT * FROM video_production entity WHERE entity.workspace_id IS NULL")
    Flux<VideoProduction> findAllWhereWorkspaceIsNull();

    @Override
    <S extends VideoProduction> Mono<S> save(S entity);

    @Override
    Flux<VideoProduction> findAll();

    @Override
    Mono<VideoProduction> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface VideoProductionRepositoryInternal {
    <S extends VideoProduction> Mono<S> save(S entity);

    Flux<VideoProduction> findAllBy(Pageable pageable);

    Flux<VideoProduction> findAll();

    Mono<VideoProduction> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<VideoProduction> findAllBy(Pageable pageable, Criteria criteria);
}
