package com.highwayac.com.repository;

import com.highwayac.com.domain.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Video entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoRepository extends ReactiveCrudRepository<Video, Long>, VideoRepositoryInternal {
    Flux<Video> findAllBy(Pageable pageable);

    @Override
    Mono<Video> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Video> findAllWithEagerRelationships();

    @Override
    Flux<Video> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM video entity WHERE entity.creator_id = :id")
    Flux<Video> findByCreator(Long id);

    @Query("SELECT * FROM video entity WHERE entity.creator_id IS NULL")
    Flux<Video> findAllWhereCreatorIsNull();

    @Query("SELECT * FROM video entity WHERE entity.category_id = :id")
    Flux<Video> findByCategory(Long id);

    @Query("SELECT * FROM video entity WHERE entity.category_id IS NULL")
    Flux<Video> findAllWhereCategoryIsNull();

    @Query("SELECT * FROM video entity WHERE entity.copyright_id = :id")
    Flux<Video> findByCopyright(Long id);

    @Query("SELECT * FROM video entity WHERE entity.copyright_id IS NULL")
    Flux<Video> findAllWhereCopyrightIsNull();

    @Query("SELECT * FROM video entity WHERE entity.extra_info_id = :id")
    Flux<Video> findByExtraInfo(Long id);

    @Query("SELECT * FROM video entity WHERE entity.extra_info_id IS NULL")
    Flux<Video> findAllWhereExtraInfoIsNull();

    @Query(
        "SELECT entity.* FROM video entity JOIN rel_video__keyword joinTable ON entity.id = joinTable.keyword_id WHERE joinTable.keyword_id = :id"
    )
    Flux<Video> findByKeyword(Long id);

    @Override
    <S extends Video> Mono<S> save(S entity);

    @Override
    Flux<Video> findAll();

    @Override
    Mono<Video> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface VideoRepositoryInternal {
    <S extends Video> Mono<S> save(S entity);

    Flux<Video> findAllBy(Pageable pageable);

    Flux<Video> findAll();

    Mono<Video> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Video> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Video> findOneWithEagerRelationships(Long id);

    Flux<Video> findAllWithEagerRelationships();

    Flux<Video> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
