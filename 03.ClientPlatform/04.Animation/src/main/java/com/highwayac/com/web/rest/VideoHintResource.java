package com.highwayac.com.web.rest;

import com.highwayac.com.domain.VideoHint;
import com.highwayac.com.repository.VideoHintRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.VideoHint}.
 */
@RestController
@RequestMapping("/api/video-hints")
@Transactional
public class VideoHintResource {

    private final Logger log = LoggerFactory.getLogger(VideoHintResource.class);

    private static final String ENTITY_NAME = "videoHint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoHintRepository videoHintRepository;

    public VideoHintResource(VideoHintRepository videoHintRepository) {
        this.videoHintRepository = videoHintRepository;
    }

    /**
     * {@code POST  /video-hints} : Create a new videoHint.
     *
     * @param videoHint the videoHint to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoHint, or with status {@code 400 (Bad Request)} if the videoHint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<VideoHint>> createVideoHint(@Valid @RequestBody VideoHint videoHint) throws URISyntaxException {
        log.debug("REST request to save VideoHint : {}", videoHint);
        if (videoHint.getId() != null) {
            throw new BadRequestAlertException("A new videoHint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return videoHintRepository
            .save(videoHint)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/video-hints/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /video-hints/:id} : Updates an existing videoHint.
     *
     * @param id the id of the videoHint to save.
     * @param videoHint the videoHint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoHint,
     * or with status {@code 400 (Bad Request)} if the videoHint is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoHint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VideoHint>> updateVideoHint(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VideoHint videoHint
    ) throws URISyntaxException {
        log.debug("REST request to update VideoHint : {}, {}", id, videoHint);
        if (videoHint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoHint.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return videoHintRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return videoHintRepository
                    .save(videoHint)
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
     * {@code PATCH  /video-hints/:id} : Partial updates given fields of an existing videoHint, field will ignore if it is null
     *
     * @param id the id of the videoHint to save.
     * @param videoHint the videoHint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoHint,
     * or with status {@code 400 (Bad Request)} if the videoHint is not valid,
     * or with status {@code 404 (Not Found)} if the videoHint is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoHint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<VideoHint>> partialUpdateVideoHint(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VideoHint videoHint
    ) throws URISyntaxException {
        log.debug("REST request to partial update VideoHint partially : {}, {}", id, videoHint);
        if (videoHint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoHint.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return videoHintRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<VideoHint> result = videoHintRepository
                    .findById(videoHint.getId())
                    .map(existingVideoHint -> {
                        if (videoHint.getWorkspace() != null) {
                            existingVideoHint.setWorkspace(videoHint.getWorkspace());
                        }
                        if (videoHint.getCreator() != null) {
                            existingVideoHint.setCreator(videoHint.getCreator());
                        }
                        if (videoHint.getCreationContent() != null) {
                            existingVideoHint.setCreationContent(videoHint.getCreationContent());
                        }
                        if (videoHint.getBackgroundMusic() != null) {
                            existingVideoHint.setBackgroundMusic(videoHint.getBackgroundMusic());
                        }

                        return existingVideoHint;
                    })
                    .flatMap(videoHintRepository::save);

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
     * {@code GET  /video-hints} : get all the videoHints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoHints in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<VideoHint>> getAllVideoHints() {
        log.debug("REST request to get all VideoHints");
        return videoHintRepository.findAll().collectList();
    }

    /**
     * {@code GET  /video-hints} : get all the videoHints as a stream.
     * @return the {@link Flux} of videoHints.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<VideoHint> getAllVideoHintsAsStream() {
        log.debug("REST request to get all VideoHints as a stream");
        return videoHintRepository.findAll();
    }

    /**
     * {@code GET  /video-hints/:id} : get the "id" videoHint.
     *
     * @param id the id of the videoHint to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoHint, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VideoHint>> getVideoHint(@PathVariable("id") Long id) {
        log.debug("REST request to get VideoHint : {}", id);
        Mono<VideoHint> videoHint = videoHintRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(videoHint);
    }

    /**
     * {@code DELETE  /video-hints/:id} : delete the "id" videoHint.
     *
     * @param id the id of the videoHint to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVideoHint(@PathVariable("id") Long id) {
        log.debug("REST request to delete VideoHint : {}", id);
        return videoHintRepository
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
