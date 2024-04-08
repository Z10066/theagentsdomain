package com.highwayac.com.repository;

import com.highwayac.com.domain.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Keyword entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KeywordRepository extends ReactiveCrudRepository<Keyword, Long>, KeywordRepositoryInternal {
    Flux<Keyword> findAllBy(Pageable pageable);

    @Override
    <S extends Keyword> Mono<S> save(S entity);

    @Override
    Flux<Keyword> findAll();

    @Override
    Mono<Keyword> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface KeywordRepositoryInternal {
    <S extends Keyword> Mono<S> save(S entity);

    Flux<Keyword> findAllBy(Pageable pageable);

    Flux<Keyword> findAll();

    Mono<Keyword> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Keyword> findAllBy(Pageable pageable, Criteria criteria);
}
