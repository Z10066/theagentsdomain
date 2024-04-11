package com.highwayac.com.web.rest;

import com.highwayac.com.domain.History;
import com.highwayac.com.repository.HistoryRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.History}.
 */
@RestController
@RequestMapping("/api/histories")
@Transactional
public class HistoryResource {

    private final Logger log = LoggerFactory.getLogger(HistoryResource.class);

    private static final String ENTITY_NAME = "history";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoryRepository historyRepository;

    public HistoryResource(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * {@code POST  /histories} : Create a new history.
     *
     * @param history the history to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new history, or with status {@code 400 (Bad Request)} if the history has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<History>> createHistory(@Valid @RequestBody History history) throws URISyntaxException {
        log.debug("REST request to save History : {}", history);
        if (history.getId() != null) {
            throw new BadRequestAlertException("A new history cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return historyRepository
            .save(history)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/histories/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /histories/:id} : Updates an existing history.
     *
     * @param id the id of the history to save.
     * @param history the history to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated history,
     * or with status {@code 400 (Bad Request)} if the history is not valid,
     * or with status {@code 500 (Internal Server Error)} if the history couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<History>> updateHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody History history
    ) throws URISyntaxException {
        log.debug("REST request to update History : {}, {}", id, history);
        if (history.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, history.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return historyRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return historyRepository
                    .save(history)
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
     * {@code PATCH  /histories/:id} : Partial updates given fields of an existing history, field will ignore if it is null
     *
     * @param id the id of the history to save.
     * @param history the history to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated history,
     * or with status {@code 400 (Bad Request)} if the history is not valid,
     * or with status {@code 404 (Not Found)} if the history is not found,
     * or with status {@code 500 (Internal Server Error)} if the history couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<History>> partialUpdateHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody History history
    ) throws URISyntaxException {
        log.debug("REST request to partial update History partially : {}, {}", id, history);
        if (history.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, history.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return historyRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<History> result = historyRepository
                    .findById(history.getId())
                    .map(existingHistory -> {
                        if (history.getTitle() != null) {
                            existingHistory.setTitle(history.getTitle());
                        }
                        if (history.getCreator() != null) {
                            existingHistory.setCreator(history.getCreator());
                        }
                        if (history.getDescription() != null) {
                            existingHistory.setDescription(history.getDescription());
                        }
                        if (history.getCopyright() != null) {
                            existingHistory.setCopyright(history.getCopyright());
                        }
                        if (history.getWatchLink() != null) {
                            existingHistory.setWatchLink(history.getWatchLink());
                        }

                        return existingHistory;
                    })
                    .flatMap(historyRepository::save);

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
     * {@code GET  /histories} : get all the histories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of histories in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<History>> getAllHistories() {
        log.debug("REST request to get all Histories");
        return historyRepository.findAll().collectList();
    }

    /**
     * {@code GET  /histories} : get all the histories as a stream.
     * @return the {@link Flux} of histories.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<History> getAllHistoriesAsStream() {
        log.debug("REST request to get all Histories as a stream");
        return historyRepository.findAll();
    }

    /**
     * {@code GET  /histories/:id} : get the "id" history.
     *
     * @param id the id of the history to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the history, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<History>> getHistory(@PathVariable("id") Long id) {
        log.debug("REST request to get History : {}", id);
        Mono<History> history = historyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(history);
    }

    /**
     * {@code DELETE  /histories/:id} : delete the "id" history.
     *
     * @param id the id of the history to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteHistory(@PathVariable("id") Long id) {
        log.debug("REST request to delete History : {}", id);
        return historyRepository
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
