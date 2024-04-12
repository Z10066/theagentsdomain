package com.highwayac.com.repository;

import com.highwayac.com.domain.ExtraInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ExtraInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtraInfoRepository extends ReactiveCrudRepository<ExtraInfo, Long>, ExtraInfoRepositoryInternal {
    @Query("SELECT * FROM extra_info entity WHERE entity.id not in (select video_production_id from video_production)")
    Flux<ExtraInfo> findAllWhereVideoProductionIsNull();

    @Query("SELECT * FROM extra_info entity WHERE entity.id not in (select material_id from material)")
    Flux<ExtraInfo> findAllWhereMaterialIsNull();

    @Query("SELECT * FROM extra_info entity WHERE entity.id not in (select history_id from history)")
    Flux<ExtraInfo> findAllWhereHistoryIsNull();

    @Override
    <S extends ExtraInfo> Mono<S> save(S entity);

    @Override
    Flux<ExtraInfo> findAll();

    @Override
    Mono<ExtraInfo> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ExtraInfoRepositoryInternal {
    <S extends ExtraInfo> Mono<S> save(S entity);

    Flux<ExtraInfo> findAllBy(Pageable pageable);

    Flux<ExtraInfo> findAll();

    Mono<ExtraInfo> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ExtraInfo> findAllBy(Pageable pageable, Criteria criteria);
}
