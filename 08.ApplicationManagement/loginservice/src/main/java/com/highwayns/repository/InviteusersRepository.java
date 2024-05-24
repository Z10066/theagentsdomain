package com.highwayns.repository;

import com.highwayns.domain.Inviteusers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Inviteusers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InviteusersRepository extends ReactiveCrudRepository<Inviteusers, Long>, InviteusersRepositoryInternal {
    @Override
    <S extends Inviteusers> Mono<S> save(S entity);

    @Override
    Flux<Inviteusers> findAll();

    @Override
    Mono<Inviteusers> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InviteusersRepositoryInternal {
    <S extends Inviteusers> Mono<S> save(S entity);

    Flux<Inviteusers> findAllBy(Pageable pageable);

    Flux<Inviteusers> findAll();

    Mono<Inviteusers> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Inviteusers> findAllBy(Pageable pageable, Criteria criteria);
}
