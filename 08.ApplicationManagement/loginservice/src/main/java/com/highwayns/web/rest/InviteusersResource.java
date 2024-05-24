package com.highwayns.web.rest;

import com.highwayns.domain.Inviteusers;
import com.highwayns.repository.InviteusersRepository;
import com.highwayns.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.highwayns.domain.Inviteusers}.
 */
@RestController
@RequestMapping("/api/inviteusers")
@Transactional
public class InviteusersResource {

    private final Logger log = LoggerFactory.getLogger(InviteusersResource.class);

    private static final String ENTITY_NAME = "loginserviceInviteusers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InviteusersRepository inviteusersRepository;

    public InviteusersResource(InviteusersRepository inviteusersRepository) {
        this.inviteusersRepository = inviteusersRepository;
    }

    /**
     * {@code POST  /inviteusers} : Create a new inviteusers.
     *
     * @param inviteusers the inviteusers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inviteusers, or with status {@code 400 (Bad Request)} if the inviteusers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<Inviteusers>> createInviteusers(@Valid @RequestBody Inviteusers inviteusers) throws URISyntaxException {
        log.debug("REST request to save Inviteusers : {}", inviteusers);
        if (inviteusers.getId() != null) {
            throw new BadRequestAlertException("A new inviteusers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return inviteusersRepository
            .save(inviteusers)
            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/inviteusers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /inviteusers/:id} : Updates an existing inviteusers.
     *
     * @param id the id of the inviteusers to save.
     * @param inviteusers the inviteusers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inviteusers,
     * or with status {@code 400 (Bad Request)} if the inviteusers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inviteusers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Inviteusers>> updateInviteusers(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Inviteusers inviteusers
    ) throws URISyntaxException {
        log.debug("REST request to update Inviteusers : {}, {}", id, inviteusers);
        if (inviteusers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inviteusers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return inviteusersRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return inviteusersRepository
                    .save(inviteusers)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(
                        result ->
                            ResponseEntity.ok()
                                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                                .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /inviteusers/:id} : Partial updates given fields of an existing inviteusers, field will ignore if it is null
     *
     * @param id the id of the inviteusers to save.
     * @param inviteusers the inviteusers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inviteusers,
     * or with status {@code 400 (Bad Request)} if the inviteusers is not valid,
     * or with status {@code 404 (Not Found)} if the inviteusers is not found,
     * or with status {@code 500 (Internal Server Error)} if the inviteusers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Inviteusers>> partialUpdateInviteusers(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Inviteusers inviteusers
    ) throws URISyntaxException {
        log.debug("REST request to partial update Inviteusers partially : {}, {}", id, inviteusers);
        if (inviteusers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inviteusers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return inviteusersRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Inviteusers> result = inviteusersRepository
                    .findById(inviteusers.getId())
                    .map(existingInviteusers -> {
                        if (inviteusers.getInvitername() != null) {
                            existingInviteusers.setInvitername(inviteusers.getInvitername());
                        }
                        if (inviteusers.getWorkspaces() != null) {
                            existingInviteusers.setWorkspaces(inviteusers.getWorkspaces());
                        }
                        if (inviteusers.getInvitertime() != null) {
                            existingInviteusers.setInvitertime(inviteusers.getInvitertime());
                        }
                        if (inviteusers.getEmail() != null) {
                            existingInviteusers.setEmail(inviteusers.getEmail());
                        }
                        if (inviteusers.getInviterid() != null) {
                            existingInviteusers.setInviterid(inviteusers.getInviterid());
                        }
                        if (inviteusers.getStatus() != null) {
                            existingInviteusers.setStatus(inviteusers.getStatus());
                        }

                        return existingInviteusers;
                    })
                    .flatMap(inviteusersRepository::save);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(
                        res ->
                            ResponseEntity.ok()
                                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                                .body(res)
                    );
            });
    }

    /**
     * {@code GET  /inviteusers} : get all the inviteusers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inviteusers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Inviteusers>> getAllInviteusers() {
        log.debug("REST request to get all Inviteusers");
        return inviteusersRepository.findAll().collectList();
    }

    /**
     * {@code GET  /inviteusers} : get all the inviteusers as a stream.
     * @return the {@link Flux} of inviteusers.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Inviteusers> getAllInviteusersAsStream() {
        log.debug("REST request to get all Inviteusers as a stream");
        return inviteusersRepository.findAll();
    }

    /**
     * {@code GET  /inviteusers/:id} : get the "id" inviteusers.
     *
     * @param id the id of the inviteusers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inviteusers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Inviteusers>> getInviteusers(@PathVariable("id") Long id) {
        log.debug("REST request to get Inviteusers : {}", id);
        Mono<Inviteusers> inviteusers = inviteusersRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inviteusers);
    }

    /**
     * {@code DELETE  /inviteusers/:id} : delete the "id" inviteusers.
     *
     * @param id the id of the inviteusers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteInviteusers(@PathVariable("id") Long id) {
        log.debug("REST request to delete Inviteusers : {}", id);
        return inviteusersRepository
            .deleteById(id)
            .then(
                Mono.just(
                    ResponseEntity.noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
