package com.highwayac.com.repository;

import com.highwayac.com.domain.LinkedAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the LinkedAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LinkedAccountRepository extends ReactiveCrudRepository<LinkedAccount, Long>, LinkedAccountRepositoryInternal {
    @Query("SELECT * FROM linked_account entity WHERE entity.member_id = :id")
    Flux<LinkedAccount> findByMember(Long id);

    @Query("SELECT * FROM linked_account entity WHERE entity.member_id IS NULL")
    Flux<LinkedAccount> findAllWhereMemberIsNull();

    @Override
    <S extends LinkedAccount> Mono<S> save(S entity);

    @Override
    Flux<LinkedAccount> findAll();

    @Override
    Mono<LinkedAccount> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface LinkedAccountRepositoryInternal {
    <S extends LinkedAccount> Mono<S> save(S entity);

    Flux<LinkedAccount> findAllBy(Pageable pageable);

    Flux<LinkedAccount> findAll();

    Mono<LinkedAccount> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<LinkedAccount> findAllBy(Pageable pageable, Criteria criteria);
}
