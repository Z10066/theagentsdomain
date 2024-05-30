package com.highwayac.com.repository;

import com.highwayac.com.domain.StripeCheckoutSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the StripeCheckoutSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StripeCheckoutSessionRepository
    extends ReactiveCrudRepository<StripeCheckoutSession, Long>, StripeCheckoutSessionRepositoryInternal {
    @Override
    <S extends StripeCheckoutSession> Mono<S> save(S entity);

    @Override
    Flux<StripeCheckoutSession> findAll();

    @Override
    Mono<StripeCheckoutSession> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface StripeCheckoutSessionRepositoryInternal {
    <S extends StripeCheckoutSession> Mono<S> save(S entity);

    Flux<StripeCheckoutSession> findAllBy(Pageable pageable);

    Flux<StripeCheckoutSession> findAll();

    Mono<StripeCheckoutSession> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<StripeCheckoutSession> findAllBy(Pageable pageable, Criteria criteria);
}
