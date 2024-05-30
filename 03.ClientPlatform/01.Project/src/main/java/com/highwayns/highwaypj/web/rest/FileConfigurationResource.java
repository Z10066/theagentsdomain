package com.highwayns.highwaypj.web.rest;

import com.highwayns.highwaypj.domain.FileConfiguration;
import com.highwayns.highwaypj.repository.FileConfigurationRepository;
import com.highwayns.highwaypj.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.ForwardedHeaderUtils;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.highwayns.highwaypj.domain.FileConfiguration}.
 */
@RestController
@RequestMapping("/api/file-configurations")
@Transactional
public class FileConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(FileConfigurationResource.class);

    private static final String ENTITY_NAME = "highwaypjFileConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileConfigurationRepository fileConfigurationRepository;

    public FileConfigurationResource(FileConfigurationRepository fileConfigurationRepository) {
        this.fileConfigurationRepository = fileConfigurationRepository;
    }

    /**
     * {@code POST  /file-configurations} : Create a new fileConfiguration.
     *
     * @param fileConfiguration the fileConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileConfiguration, or with status {@code 400 (Bad Request)} if the fileConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FileConfiguration>> createFileConfiguration(@Valid @RequestBody FileConfiguration fileConfiguration)
        throws URISyntaxException {
        log.debug("REST request to save FileConfiguration : {}", fileConfiguration);
        if (fileConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new fileConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return fileConfigurationRepository
            .save(fileConfiguration)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/file-configurations/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /file-configurations/:id} : Updates an existing fileConfiguration.
     *
     * @param id the id of the fileConfiguration to save.
     * @param fileConfiguration the fileConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileConfiguration,
     * or with status {@code 400 (Bad Request)} if the fileConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FileConfiguration>> updateFileConfiguration(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FileConfiguration fileConfiguration
    ) throws URISyntaxException {
        log.debug("REST request to update FileConfiguration : {}, {}", id, fileConfiguration);
        if (fileConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileConfiguration.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fileConfigurationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return fileConfigurationRepository
                    .save(fileConfiguration)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /file-configurations/:id} : Partial updates given fields of an existing fileConfiguration, field will ignore if it is null
     *
     * @param id the id of the fileConfiguration to save.
     * @param fileConfiguration the fileConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileConfiguration,
     * or with status {@code 400 (Bad Request)} if the fileConfiguration is not valid,
     * or with status {@code 404 (Not Found)} if the fileConfiguration is not found,
     * or with status {@code 500 (Internal Server Error)} if the fileConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FileConfiguration>> partialUpdateFileConfiguration(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FileConfiguration fileConfiguration
    ) throws URISyntaxException {
        log.debug("REST request to partial update FileConfiguration partially : {}, {}", id, fileConfiguration);
        if (fileConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileConfiguration.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fileConfigurationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FileConfiguration> result = fileConfigurationRepository
                    .findById(fileConfiguration.getId())
                    .map(existingFileConfiguration -> {
                        if (fileConfiguration.getName() != null) {
                            existingFileConfiguration.setName(fileConfiguration.getName());
                        }
                        if (fileConfiguration.getDescription() != null) {
                            existingFileConfiguration.setDescription(fileConfiguration.getDescription());
                        }
                        if (fileConfiguration.getPath() != null) {
                            existingFileConfiguration.setPath(fileConfiguration.getPath());
                        }
                        if (fileConfiguration.getTypes() != null) {
                            existingFileConfiguration.setTypes(fileConfiguration.getTypes());
                        }

                        return existingFileConfiguration;
                    })
                    .flatMap(fileConfigurationRepository::save);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /file-configurations} : get all the fileConfigurations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileConfigurations in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FileConfiguration>>> getAllFileConfigurations(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of FileConfigurations");
        return fileConfigurationRepository
            .count()
            .zipWith(fileConfigurationRepository.findAllBy(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /file-configurations/:id} : get the "id" fileConfiguration.
     *
     * @param id the id of the fileConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FileConfiguration>> getFileConfiguration(@PathVariable("id") Long id) {
        log.debug("REST request to get FileConfiguration : {}", id);
        Mono<FileConfiguration> fileConfiguration = fileConfigurationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fileConfiguration);
    }

    /**
     * {@code DELETE  /file-configurations/:id} : delete the "id" fileConfiguration.
     *
     * @param id the id of the fileConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFileConfiguration(@PathVariable("id") Long id) {
        log.debug("REST request to delete FileConfiguration : {}", id);
        return fileConfigurationRepository
            .deleteById(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
