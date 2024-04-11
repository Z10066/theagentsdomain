package com.highwayac.com.web.rest;

import com.highwayac.com.domain.SubscriptionService;
import com.highwayac.com.repository.SubscriptionServiceRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.SubscriptionService}.
 */
@RestController
@RequestMapping("/api/subscription-services")
@Transactional
public class SubscriptionServiceResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionServiceResource.class);

    private static final String ENTITY_NAME = "subscriptionService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriptionServiceRepository subscriptionServiceRepository;

    public SubscriptionServiceResource(SubscriptionServiceRepository subscriptionServiceRepository) {
        this.subscriptionServiceRepository = subscriptionServiceRepository;
    }

    /**
     * {@code POST  /subscription-services} : Create a new subscriptionService.
     *
     * @param subscriptionService the subscriptionService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriptionService, or with status {@code 400 (Bad Request)} if the subscriptionService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<SubscriptionService>> createSubscriptionService(@Valid @RequestBody SubscriptionService subscriptionService)
        throws URISyntaxException {
        log.debug("REST request to save SubscriptionService : {}", subscriptionService);
        if (subscriptionService.getId() != null) {
            throw new BadRequestAlertException("A new subscriptionService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return subscriptionServiceRepository
            .save(subscriptionService)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/subscription-services/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /subscription-services/:id} : Updates an existing subscriptionService.
     *
     * @param id the id of the subscriptionService to save.
     * @param subscriptionService the subscriptionService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptionService,
     * or with status {@code 400 (Bad Request)} if the subscriptionService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriptionService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<SubscriptionService>> updateSubscriptionService(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SubscriptionService subscriptionService
    ) throws URISyntaxException {
        log.debug("REST request to update SubscriptionService : {}, {}", id, subscriptionService);
        if (subscriptionService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subscriptionService.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return subscriptionServiceRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return subscriptionServiceRepository
                    .save(subscriptionService)
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
     * {@code PATCH  /subscription-services/:id} : Partial updates given fields of an existing subscriptionService, field will ignore if it is null
     *
     * @param id the id of the subscriptionService to save.
     * @param subscriptionService the subscriptionService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptionService,
     * or with status {@code 400 (Bad Request)} if the subscriptionService is not valid,
     * or with status {@code 404 (Not Found)} if the subscriptionService is not found,
     * or with status {@code 500 (Internal Server Error)} if the subscriptionService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SubscriptionService>> partialUpdateSubscriptionService(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SubscriptionService subscriptionService
    ) throws URISyntaxException {
        log.debug("REST request to partial update SubscriptionService partially : {}, {}", id, subscriptionService);
        if (subscriptionService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subscriptionService.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return subscriptionServiceRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SubscriptionService> result = subscriptionServiceRepository
                    .findById(subscriptionService.getId())
                    .map(existingSubscriptionService -> {
                        if (subscriptionService.getServiceLevel() != null) {
                            existingSubscriptionService.setServiceLevel(subscriptionService.getServiceLevel());
                        }
                        if (subscriptionService.getTotalUsageTime() != null) {
                            existingSubscriptionService.setTotalUsageTime(subscriptionService.getTotalUsageTime());
                        }
                        if (subscriptionService.getStartTime() != null) {
                            existingSubscriptionService.setStartTime(subscriptionService.getStartTime());
                        }
                        if (subscriptionService.getEndTime() != null) {
                            existingSubscriptionService.setEndTime(subscriptionService.getEndTime());
                        }

                        return existingSubscriptionService;
                    })
                    .flatMap(subscriptionServiceRepository::save);

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
     * {@code GET  /subscription-services} : get all the subscriptionServices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscriptionServices in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<SubscriptionService>> getAllSubscriptionServices() {
        log.debug("REST request to get all SubscriptionServices");
        return subscriptionServiceRepository.findAll().collectList();
    }

    /**
     * {@code GET  /subscription-services} : get all the subscriptionServices as a stream.
     * @return the {@link Flux} of subscriptionServices.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<SubscriptionService> getAllSubscriptionServicesAsStream() {
        log.debug("REST request to get all SubscriptionServices as a stream");
        return subscriptionServiceRepository.findAll();
    }

    /**
     * {@code GET  /subscription-services/:id} : get the "id" subscriptionService.
     *
     * @param id the id of the subscriptionService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriptionService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<SubscriptionService>> getSubscriptionService(@PathVariable("id") Long id) {
        log.debug("REST request to get SubscriptionService : {}", id);
        Mono<SubscriptionService> subscriptionService = subscriptionServiceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(subscriptionService);
    }

    /**
     * {@code DELETE  /subscription-services/:id} : delete the "id" subscriptionService.
     *
     * @param id the id of the subscriptionService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSubscriptionService(@PathVariable("id") Long id) {
        log.debug("REST request to delete SubscriptionService : {}", id);
        return subscriptionServiceRepository
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
