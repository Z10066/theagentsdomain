package com.highwayac.com.web.rest;

import com.highwayac.com.domain.YouTubeVideo;
import com.highwayac.com.repository.YouTubeVideoRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.YouTubeVideo}.
 */
@RestController
@RequestMapping("/api/you-tube-videos")
@Transactional
public class YouTubeVideoResource {

    private final Logger log = LoggerFactory.getLogger(YouTubeVideoResource.class);

    private static final String ENTITY_NAME = "youTubeVideo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YouTubeVideoRepository youTubeVideoRepository;

    public YouTubeVideoResource(YouTubeVideoRepository youTubeVideoRepository) {
        this.youTubeVideoRepository = youTubeVideoRepository;
    }

    /**
     * {@code POST  /you-tube-videos} : Create a new youTubeVideo.
     *
     * @param youTubeVideo the youTubeVideo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new youTubeVideo, or with status {@code 400 (Bad Request)} if the youTubeVideo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<YouTubeVideo>> createYouTubeVideo(@Valid @RequestBody YouTubeVideo youTubeVideo) throws URISyntaxException {
        log.debug("REST request to save YouTubeVideo : {}", youTubeVideo);
        if (youTubeVideo.getId() != null) {
            throw new BadRequestAlertException("A new youTubeVideo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return youTubeVideoRepository
            .save(youTubeVideo)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/you-tube-videos/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /you-tube-videos/:id} : Updates an existing youTubeVideo.
     *
     * @param id the id of the youTubeVideo to save.
     * @param youTubeVideo the youTubeVideo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated youTubeVideo,
     * or with status {@code 400 (Bad Request)} if the youTubeVideo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the youTubeVideo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<YouTubeVideo>> updateYouTubeVideo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody YouTubeVideo youTubeVideo
    ) throws URISyntaxException {
        log.debug("REST request to update YouTubeVideo : {}, {}", id, youTubeVideo);
        if (youTubeVideo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, youTubeVideo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return youTubeVideoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return youTubeVideoRepository
                    .save(youTubeVideo)
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
     * {@code PATCH  /you-tube-videos/:id} : Partial updates given fields of an existing youTubeVideo, field will ignore if it is null
     *
     * @param id the id of the youTubeVideo to save.
     * @param youTubeVideo the youTubeVideo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated youTubeVideo,
     * or with status {@code 400 (Bad Request)} if the youTubeVideo is not valid,
     * or with status {@code 404 (Not Found)} if the youTubeVideo is not found,
     * or with status {@code 500 (Internal Server Error)} if the youTubeVideo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<YouTubeVideo>> partialUpdateYouTubeVideo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody YouTubeVideo youTubeVideo
    ) throws URISyntaxException {
        log.debug("REST request to partial update YouTubeVideo partially : {}, {}", id, youTubeVideo);
        if (youTubeVideo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, youTubeVideo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return youTubeVideoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<YouTubeVideo> result = youTubeVideoRepository
                    .findById(youTubeVideo.getId())
                    .map(existingYouTubeVideo -> {
                        if (youTubeVideo.getWorkspace() != null) {
                            existingYouTubeVideo.setWorkspace(youTubeVideo.getWorkspace());
                        }
                        if (youTubeVideo.getCreator() != null) {
                            existingYouTubeVideo.setCreator(youTubeVideo.getCreator());
                        }
                        if (youTubeVideo.getTheme() != null) {
                            existingYouTubeVideo.setTheme(youTubeVideo.getTheme());
                        }
                        if (youTubeVideo.getContent() != null) {
                            existingYouTubeVideo.setContent(youTubeVideo.getContent());
                        }
                        if (youTubeVideo.getBackgroundMusic() != null) {
                            existingYouTubeVideo.setBackgroundMusic(youTubeVideo.getBackgroundMusic());
                        }
                        if (youTubeVideo.getVideoTime() != null) {
                            existingYouTubeVideo.setVideoTime(youTubeVideo.getVideoTime());
                        }

                        return existingYouTubeVideo;
                    })
                    .flatMap(youTubeVideoRepository::save);

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
     * {@code GET  /you-tube-videos} : get all the youTubeVideos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of youTubeVideos in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<YouTubeVideo>> getAllYouTubeVideos() {
        log.debug("REST request to get all YouTubeVideos");
        return youTubeVideoRepository.findAll().collectList();
    }

    /**
     * {@code GET  /you-tube-videos} : get all the youTubeVideos as a stream.
     * @return the {@link Flux} of youTubeVideos.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<YouTubeVideo> getAllYouTubeVideosAsStream() {
        log.debug("REST request to get all YouTubeVideos as a stream");
        return youTubeVideoRepository.findAll();
    }

    /**
     * {@code GET  /you-tube-videos/:id} : get the "id" youTubeVideo.
     *
     * @param id the id of the youTubeVideo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the youTubeVideo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<YouTubeVideo>> getYouTubeVideo(@PathVariable("id") Long id) {
        log.debug("REST request to get YouTubeVideo : {}", id);
        Mono<YouTubeVideo> youTubeVideo = youTubeVideoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(youTubeVideo);
    }

    /**
     * {@code DELETE  /you-tube-videos/:id} : delete the "id" youTubeVideo.
     *
     * @param id the id of the youTubeVideo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteYouTubeVideo(@PathVariable("id") Long id) {
        log.debug("REST request to delete YouTubeVideo : {}", id);
        return youTubeVideoRepository
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
