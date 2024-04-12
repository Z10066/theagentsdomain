package com.highwayac.com.web.rest;

import com.highwayac.com.domain.Usage;
import com.highwayac.com.repository.UsageRepository;
import com.highwayac.com.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.highwayac.com.domain.Usage}.
 */
@RestController
@RequestMapping("/api/usages")
@Transactional
public class UsageResource {

    private final Logger log = LoggerFactory.getLogger(UsageResource.class);

    private static final String ENTITY_NAME = "usage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsageRepository usageRepository;

    public UsageResource(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    /**
     * {@code POST  /usages} : Create a new usage.
     *
     * @param usage the usage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usage, or with status {@code 400 (Bad Request)} if the usage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<Usage>> createUsage(@Valid @RequestBody Usage usage) throws URISyntaxException {
        log.debug("REST request to save Usage : {}", usage);
        if (usage.getId() != null) {
            throw new BadRequestAlertException("A new usage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return usageRepository
            .save(usage)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/usages/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /usages/:id} : Updates an existing usage.
     *
     * @param id the id of the usage to save.
     * @param usage the usage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usage,
     * or with status {@code 400 (Bad Request)} if the usage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Usage>> updateUsage(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Usage usage
    ) throws URISyntaxException {
        log.debug("REST request to update Usage : {}, {}", id, usage);
        if (usage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, usage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return usageRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return usageRepository
                    .save(usage)
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
     * {@code PATCH  /usages/:id} : Partial updates given fields of an existing usage, field will ignore if it is null
     *
     * @param id the id of the usage to save.
     * @param usage the usage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usage,
     * or with status {@code 400 (Bad Request)} if the usage is not valid,
     * or with status {@code 404 (Not Found)} if the usage is not found,
     * or with status {@code 500 (Internal Server Error)} if the usage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Usage>> partialUpdateUsage(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Usage usage
    ) throws URISyntaxException {
        log.debug("REST request to partial update Usage partially : {}, {}", id, usage);
        if (usage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, usage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return usageRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Usage> result = usageRepository
                    .findById(usage.getId())
                    .map(existingUsage -> {
                        if (usage.getUsageType() != null) {
                            existingUsage.setUsageType(usage.getUsageType());
                        }
                        if (usage.getUsageTime() != null) {
                            existingUsage.setUsageTime(usage.getUsageTime());
                        }
                        if (usage.getStartTime() != null) {
                            existingUsage.setStartTime(usage.getStartTime());
                        }
                        if (usage.getEndTime() != null) {
                            existingUsage.setEndTime(usage.getEndTime());
                        }

                        return existingUsage;
                    })
                    .flatMap(usageRepository::save);

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
     * {@code GET  /usages} : get all the usages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usages in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Usage>> getAllUsages() {
        log.debug("REST request to get all Usages");
        return usageRepository.findAll().collectList();
    }

    /**
     * {@code GET  /usages} : get all the usages as a stream.
     * @return the {@link Flux} of usages.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Usage> getAllUsagesAsStream() {
        log.debug("REST request to get all Usages as a stream");
        return usageRepository.findAll();
    }

    /**
     * {@code GET  /usages/:id} : get the "id" usage.
     *
     * @param id the id of the usage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Usage>> getUsage(@PathVariable("id") Long id) {
        log.debug("REST request to get Usage : {}", id);
        Mono<Usage> usage = usageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(usage);
    }

    /**
     * {@code DELETE  /usages/:id} : delete the "id" usage.
     *
     * @param id the id of the usage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUsage(@PathVariable("id") Long id) {
        log.debug("REST request to delete Usage : {}", id);
        return usageRepository
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
