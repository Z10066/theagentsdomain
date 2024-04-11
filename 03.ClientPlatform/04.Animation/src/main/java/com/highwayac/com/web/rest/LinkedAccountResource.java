package com.highwayac.com.web.rest;

import com.highwayac.com.domain.LinkedAccount;
import com.highwayac.com.repository.LinkedAccountRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.LinkedAccount}.
 */
@RestController
@RequestMapping("/api/linked-accounts")
@Transactional
public class LinkedAccountResource {

    private final Logger log = LoggerFactory.getLogger(LinkedAccountResource.class);

    private static final String ENTITY_NAME = "linkedAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LinkedAccountRepository linkedAccountRepository;

    public LinkedAccountResource(LinkedAccountRepository linkedAccountRepository) {
        this.linkedAccountRepository = linkedAccountRepository;
    }

    /**
     * {@code POST  /linked-accounts} : Create a new linkedAccount.
     *
     * @param linkedAccount the linkedAccount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new linkedAccount, or with status {@code 400 (Bad Request)} if the linkedAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<LinkedAccount>> createLinkedAccount(@Valid @RequestBody LinkedAccount linkedAccount)
        throws URISyntaxException {
        log.debug("REST request to save LinkedAccount : {}", linkedAccount);
        if (linkedAccount.getId() != null) {
            throw new BadRequestAlertException("A new linkedAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return linkedAccountRepository
            .save(linkedAccount)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/linked-accounts/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /linked-accounts/:id} : Updates an existing linkedAccount.
     *
     * @param id the id of the linkedAccount to save.
     * @param linkedAccount the linkedAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linkedAccount,
     * or with status {@code 400 (Bad Request)} if the linkedAccount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the linkedAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<LinkedAccount>> updateLinkedAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LinkedAccount linkedAccount
    ) throws URISyntaxException {
        log.debug("REST request to update LinkedAccount : {}, {}", id, linkedAccount);
        if (linkedAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, linkedAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return linkedAccountRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return linkedAccountRepository
                    .save(linkedAccount)
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
     * {@code PATCH  /linked-accounts/:id} : Partial updates given fields of an existing linkedAccount, field will ignore if it is null
     *
     * @param id the id of the linkedAccount to save.
     * @param linkedAccount the linkedAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linkedAccount,
     * or with status {@code 400 (Bad Request)} if the linkedAccount is not valid,
     * or with status {@code 404 (Not Found)} if the linkedAccount is not found,
     * or with status {@code 500 (Internal Server Error)} if the linkedAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<LinkedAccount>> partialUpdateLinkedAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LinkedAccount linkedAccount
    ) throws URISyntaxException {
        log.debug("REST request to partial update LinkedAccount partially : {}, {}", id, linkedAccount);
        if (linkedAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, linkedAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return linkedAccountRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<LinkedAccount> result = linkedAccountRepository
                    .findById(linkedAccount.getId())
                    .map(existingLinkedAccount -> {
                        if (linkedAccount.getAccountType() != null) {
                            existingLinkedAccount.setAccountType(linkedAccount.getAccountType());
                        }
                        if (linkedAccount.getAccountIdentifier() != null) {
                            existingLinkedAccount.setAccountIdentifier(linkedAccount.getAccountIdentifier());
                        }

                        return existingLinkedAccount;
                    })
                    .flatMap(linkedAccountRepository::save);

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
     * {@code GET  /linked-accounts} : get all the linkedAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of linkedAccounts in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<LinkedAccount>> getAllLinkedAccounts() {
        log.debug("REST request to get all LinkedAccounts");
        return linkedAccountRepository.findAll().collectList();
    }

    /**
     * {@code GET  /linked-accounts} : get all the linkedAccounts as a stream.
     * @return the {@link Flux} of linkedAccounts.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<LinkedAccount> getAllLinkedAccountsAsStream() {
        log.debug("REST request to get all LinkedAccounts as a stream");
        return linkedAccountRepository.findAll();
    }

    /**
     * {@code GET  /linked-accounts/:id} : get the "id" linkedAccount.
     *
     * @param id the id of the linkedAccount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the linkedAccount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<LinkedAccount>> getLinkedAccount(@PathVariable("id") Long id) {
        log.debug("REST request to get LinkedAccount : {}", id);
        Mono<LinkedAccount> linkedAccount = linkedAccountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(linkedAccount);
    }

    /**
     * {@code DELETE  /linked-accounts/:id} : delete the "id" linkedAccount.
     *
     * @param id the id of the linkedAccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteLinkedAccount(@PathVariable("id") Long id) {
        log.debug("REST request to delete LinkedAccount : {}", id);
        return linkedAccountRepository
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
