package com.highwayac.com.web.rest;

import com.highwayac.com.domain.ExtraInfo;
import com.highwayac.com.repository.ExtraInfoRepository;
import com.highwayac.com.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.highwayac.com.domain.ExtraInfo}.
 */
@RestController
@RequestMapping("/api/extra-infos")
@Transactional
public class ExtraInfoResource {

    private final Logger log = LoggerFactory.getLogger(ExtraInfoResource.class);

    private static final String ENTITY_NAME = "extraInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExtraInfoRepository extraInfoRepository;

    public ExtraInfoResource(ExtraInfoRepository extraInfoRepository) {
        this.extraInfoRepository = extraInfoRepository;
    }

    /**
     * {@code POST  /extra-infos} : Create a new extraInfo.
     *
     * @param extraInfo the extraInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extraInfo, or with status {@code 400 (Bad Request)} if the extraInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ExtraInfo>> createExtraInfo(@RequestBody ExtraInfo extraInfo) throws URISyntaxException {
        log.debug("REST request to save ExtraInfo : {}", extraInfo);
        if (extraInfo.getId() != null) {
            throw new BadRequestAlertException("A new extraInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return extraInfoRepository
            .save(extraInfo)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/extra-infos/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /extra-infos/:id} : Updates an existing extraInfo.
     *
     * @param id the id of the extraInfo to save.
     * @param extraInfo the extraInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extraInfo,
     * or with status {@code 400 (Bad Request)} if the extraInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the extraInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ExtraInfo>> updateExtraInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExtraInfo extraInfo
    ) throws URISyntaxException {
        log.debug("REST request to update ExtraInfo : {}, {}", id, extraInfo);
        if (extraInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, extraInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return extraInfoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return extraInfoRepository
                    .save(extraInfo)
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
     * {@code PATCH  /extra-infos/:id} : Partial updates given fields of an existing extraInfo, field will ignore if it is null
     *
     * @param id the id of the extraInfo to save.
     * @param extraInfo the extraInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extraInfo,
     * or with status {@code 400 (Bad Request)} if the extraInfo is not valid,
     * or with status {@code 404 (Not Found)} if the extraInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the extraInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ExtraInfo>> partialUpdateExtraInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExtraInfo extraInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExtraInfo partially : {}, {}", id, extraInfo);
        if (extraInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, extraInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return extraInfoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ExtraInfo> result = extraInfoRepository
                    .findById(extraInfo.getId())
                    .map(existingExtraInfo -> {
                        if (extraInfo.getAudienceFeedback() != null) {
                            existingExtraInfo.setAudienceFeedback(extraInfo.getAudienceFeedback());
                        }
                        if (extraInfo.getRelatedVideos() != null) {
                            existingExtraInfo.setRelatedVideos(extraInfo.getRelatedVideos());
                        }

                        return existingExtraInfo;
                    })
                    .flatMap(extraInfoRepository::save);

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
     * {@code GET  /extra-infos} : get all the extraInfos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extraInfos in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<ExtraInfo>> getAllExtraInfos(@RequestParam(name = "filter", required = false) String filter) {
        if ("videoproduction-is-null".equals(filter)) {
            log.debug("REST request to get all ExtraInfos where videoProduction is null");
            return extraInfoRepository.findAllWhereVideoProductionIsNull().collectList();
        }

        if ("material-is-null".equals(filter)) {
            log.debug("REST request to get all ExtraInfos where material is null");
            return extraInfoRepository.findAllWhereMaterialIsNull().collectList();
        }

        if ("history-is-null".equals(filter)) {
            log.debug("REST request to get all ExtraInfos where history is null");
            return extraInfoRepository.findAllWhereHistoryIsNull().collectList();
        }
        log.debug("REST request to get all ExtraInfos");
        return extraInfoRepository.findAll().collectList();
    }

    /**
     * {@code GET  /extra-infos} : get all the extraInfos as a stream.
     * @return the {@link Flux} of extraInfos.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ExtraInfo> getAllExtraInfosAsStream() {
        log.debug("REST request to get all ExtraInfos as a stream");
        return extraInfoRepository.findAll();
    }

    /**
     * {@code GET  /extra-infos/:id} : get the "id" extraInfo.
     *
     * @param id the id of the extraInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extraInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ExtraInfo>> getExtraInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get ExtraInfo : {}", id);
        Mono<ExtraInfo> extraInfo = extraInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extraInfo);
    }

    /**
     * {@code DELETE  /extra-infos/:id} : delete the "id" extraInfo.
     *
     * @param id the id of the extraInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteExtraInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete ExtraInfo : {}", id);
        return extraInfoRepository
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
