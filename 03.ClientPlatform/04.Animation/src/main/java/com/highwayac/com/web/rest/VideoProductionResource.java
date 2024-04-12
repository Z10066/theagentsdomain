package com.highwayac.com.web.rest;

import com.highwayac.com.domain.VideoProduction;
import com.highwayac.com.repository.VideoProductionRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.VideoProduction}.
 */
@RestController
@RequestMapping("/api/video-productions")
@Transactional
public class VideoProductionResource {

    private final Logger log = LoggerFactory.getLogger(VideoProductionResource.class);

    private static final String ENTITY_NAME = "videoProduction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoProductionRepository videoProductionRepository;

    public VideoProductionResource(VideoProductionRepository videoProductionRepository) {
        this.videoProductionRepository = videoProductionRepository;
    }

    /**
     * {@code POST  /video-productions} : Create a new videoProduction.
     *
     * @param videoProduction the videoProduction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoProduction, or with status {@code 400 (Bad Request)} if the videoProduction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<VideoProduction>> createVideoProduction(@Valid @RequestBody VideoProduction videoProduction)
        throws URISyntaxException {
        log.debug("REST request to save VideoProduction : {}", videoProduction);
        if (videoProduction.getId() != null) {
            throw new BadRequestAlertException("A new videoProduction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return videoProductionRepository
            .save(videoProduction)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/video-productions/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /video-productions/:id} : Updates an existing videoProduction.
     *
     * @param id the id of the videoProduction to save.
     * @param videoProduction the videoProduction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoProduction,
     * or with status {@code 400 (Bad Request)} if the videoProduction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoProduction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VideoProduction>> updateVideoProduction(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VideoProduction videoProduction
    ) throws URISyntaxException {
        log.debug("REST request to update VideoProduction : {}, {}", id, videoProduction);
        if (videoProduction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoProduction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return videoProductionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return videoProductionRepository
                    .save(videoProduction)
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
     * {@code PATCH  /video-productions/:id} : Partial updates given fields of an existing videoProduction, field will ignore if it is null
     *
     * @param id the id of the videoProduction to save.
     * @param videoProduction the videoProduction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoProduction,
     * or with status {@code 400 (Bad Request)} if the videoProduction is not valid,
     * or with status {@code 404 (Not Found)} if the videoProduction is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoProduction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<VideoProduction>> partialUpdateVideoProduction(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VideoProduction videoProduction
    ) throws URISyntaxException {
        log.debug("REST request to partial update VideoProduction partially : {}, {}", id, videoProduction);
        if (videoProduction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoProduction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return videoProductionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<VideoProduction> result = videoProductionRepository
                    .findById(videoProduction.getId())
                    .map(existingVideoProduction -> {
                        if (videoProduction.getTitle() != null) {
                            existingVideoProduction.setTitle(videoProduction.getTitle());
                        }
                        if (videoProduction.getCreator() != null) {
                            existingVideoProduction.setCreator(videoProduction.getCreator());
                        }
                        if (videoProduction.getDescription() != null) {
                            existingVideoProduction.setDescription(videoProduction.getDescription());
                        }
                        if (videoProduction.getCopyright() != null) {
                            existingVideoProduction.setCopyright(videoProduction.getCopyright());
                        }
                        if (videoProduction.getWatchLink() != null) {
                            existingVideoProduction.setWatchLink(videoProduction.getWatchLink());
                        }

                        return existingVideoProduction;
                    })
                    .flatMap(videoProductionRepository::save);

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
     * {@code GET  /video-productions} : get all the videoProductions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoProductions in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<VideoProduction>> getAllVideoProductions() {
        log.debug("REST request to get all VideoProductions");
        return videoProductionRepository.findAll().collectList();
    }

    /**
     * {@code GET  /video-productions} : get all the videoProductions as a stream.
     * @return the {@link Flux} of videoProductions.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<VideoProduction> getAllVideoProductionsAsStream() {
        log.debug("REST request to get all VideoProductions as a stream");
        return videoProductionRepository.findAll();
    }

    /**
     * {@code GET  /video-productions/:id} : get the "id" videoProduction.
     *
     * @param id the id of the videoProduction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoProduction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VideoProduction>> getVideoProduction(@PathVariable("id") Long id) {
        log.debug("REST request to get VideoProduction : {}", id);
        Mono<VideoProduction> videoProduction = videoProductionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(videoProduction);
    }

    /**
     * {@code DELETE  /video-productions/:id} : delete the "id" videoProduction.
     *
     * @param id the id of the videoProduction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVideoProduction(@PathVariable("id") Long id) {
        log.debug("REST request to delete VideoProduction : {}", id);
        return videoProductionRepository
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
