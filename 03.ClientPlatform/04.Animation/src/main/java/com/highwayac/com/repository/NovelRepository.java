package com.highwayac.com.repository;

import com.highwayac.com.domain.Novel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Novel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NovelRepository extends ReactiveCrudRepository<Novel, Long>, NovelRepositoryInternal {
    @Override
    <S extends Novel> Mono<S> save(S entity);

    @Override
    Flux<Novel> findAll();

    @Override
    Mono<Novel> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface NovelRepositoryInternal {
    <S extends Novel> Mono<S> save(S entity);

    Flux<Novel> findAllBy(Pageable pageable);

    Flux<Novel> findAll();

    Mono<Novel> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Novel> findAllBy(Pageable pageable, Criteria criteria);
}
