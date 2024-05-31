package com.highwayac.com.web.rest;

import com.highwayac.com.domain.Novel;
import com.highwayac.com.repository.NovelRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.Novel}.
 */
@RestController
@RequestMapping("/api/novels")
@Transactional
public class NovelResource {

    private final Logger log = LoggerFactory.getLogger(NovelResource.class);

    private static final String ENTITY_NAME = "novel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NovelRepository novelRepository;

    public NovelResource(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    /**
     * {@code POST  /novels} : Create a new novel.
     *
     * @param novel the novel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new novel, or with status {@code 400 (Bad Request)} if the novel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<Novel>> createNovel(@Valid @RequestBody Novel novel) throws URISyntaxException {
        log.debug("REST request to save Novel : {}", novel);
        if (novel.getId() != null) {
            throw new BadRequestAlertException("A new novel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return novelRepository
            .save(novel)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/novels/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /novels/:id} : Updates an existing novel.
     *
     * @param id the id of the novel to save.
     * @param novel the novel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated novel,
     * or with status {@code 400 (Bad Request)} if the novel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the novel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Novel>> updateNovel(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Novel novel
    ) throws URISyntaxException {
        log.debug("REST request to update Novel : {}, {}", id, novel);
        if (novel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, novel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return novelRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return novelRepository
                    .save(novel)
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
     * {@code PATCH  /novels/:id} : Partial updates given fields of an existing novel, field will ignore if it is null
     *
     * @param id the id of the novel to save.
     * @param novel the novel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated novel,
     * or with status {@code 400 (Bad Request)} if the novel is not valid,
     * or with status {@code 404 (Not Found)} if the novel is not found,
     * or with status {@code 500 (Internal Server Error)} if the novel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Novel>> partialUpdateNovel(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Novel novel
    ) throws URISyntaxException {
        log.debug("REST request to partial update Novel partially : {}, {}", id, novel);
        if (novel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, novel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return novelRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Novel> result = novelRepository
                    .findById(novel.getId())
                    .map(existingNovel -> {
                        if (novel.getNoveltext() != null) {
                            existingNovel.setNoveltext(novel.getNoveltext());
                        }
                        if (novel.getNovelname() != null) {
                            existingNovel.setNovelname(novel.getNovelname());
                        }

                        return existingNovel;
                    })
                    .flatMap(novelRepository::save);

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
     * {@code GET  /novels} : get all the novels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of novels in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Novel>> getAllNovels() {
        log.debug("REST request to get all Novels");
        return novelRepository.findAll().collectList();
    }

    /**
     * {@code GET  /novels} : get all the novels as a stream.
     * @return the {@link Flux} of novels.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Novel> getAllNovelsAsStream() {
        log.debug("REST request to get all Novels as a stream");
        return novelRepository.findAll();
    }

    /**
     * {@code GET  /novels/:id} : get the "id" novel.
     *
     * @param id the id of the novel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the novel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Novel>> getNovel(@PathVariable("id") Long id) {
        log.debug("REST request to get Novel : {}", id);
        Mono<Novel> novel = novelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(novel);
    }

    /**
     * {@code DELETE  /novels/:id} : delete the "id" novel.
     *
     * @param id the id of the novel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteNovel(@PathVariable("id") Long id) {
        log.debug("REST request to delete Novel : {}", id);
        return novelRepository
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
