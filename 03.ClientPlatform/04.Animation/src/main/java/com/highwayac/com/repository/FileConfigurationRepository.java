package com.highwayac.com.repository;

import com.highwayac.com.domain.FileConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FileConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileConfigurationRepository extends ReactiveCrudRepository<FileConfiguration, Long>, FileConfigurationRepositoryInternal {
    Flux<FileConfiguration> findAllBy(Pageable pageable);

    @Override
    <S extends FileConfiguration> Mono<S> save(S entity);

    @Override
    Flux<FileConfiguration> findAll();

    @Override
    Mono<FileConfiguration> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FileConfigurationRepositoryInternal {
    <S extends FileConfiguration> Mono<S> save(S entity);

    Flux<FileConfiguration> findAllBy(Pageable pageable);

    Flux<FileConfiguration> findAll();

    Mono<FileConfiguration> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FileConfiguration> findAllBy(Pageable pageable, Criteria criteria);
}
