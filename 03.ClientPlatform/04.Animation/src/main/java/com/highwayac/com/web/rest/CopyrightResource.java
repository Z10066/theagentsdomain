package com.highwayac.com.web.rest;

import com.highwayac.com.repository.CopyrightRepository;
import com.highwayac.com.service.CopyrightService;
import com.highwayac.com.service.dto.CopyrightDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.highwayac.com.domain.Copyright}.
 */
@RestController
@RequestMapping("/api/copyrights")
public class CopyrightResource {

    private final Logger log = LoggerFactory.getLogger(CopyrightResource.class);

    private static final String ENTITY_NAME = "copyright";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CopyrightService copyrightService;

    private final CopyrightRepository copyrightRepository;

    public CopyrightResource(CopyrightService copyrightService, CopyrightRepository copyrightRepository) {
        this.copyrightService = copyrightService;
        this.copyrightRepository = copyrightRepository;
    }

    /**
     * {@code POST  /copyrights} : Create a new copyright.
     *
     * @param copyrightDTO the copyrightDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new copyrightDTO, or with status {@code 400 (Bad Request)} if the copyright has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CopyrightDTO>> createCopyright(@Valid @RequestBody CopyrightDTO copyrightDTO) throws URISyntaxException {
        log.debug("REST request to save Copyright : {}", copyrightDTO);
        if (copyrightDTO.getId() != null) {
            throw new BadRequestAlertException("A new copyright cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return copyrightService
            .save(copyrightDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/copyrights/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /copyrights/:id} : Updates an existing copyright.
     *
     * @param id the id of the copyrightDTO to save.
     * @param copyrightDTO the copyrightDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated copyrightDTO,
     * or with status {@code 400 (Bad Request)} if the copyrightDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the copyrightDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CopyrightDTO>> updateCopyright(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CopyrightDTO copyrightDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Copyright : {}, {}", id, copyrightDTO);
        if (copyrightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, copyrightDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return copyrightRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return copyrightService
                    .update(copyrightDTO)
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
     * {@code PATCH  /copyrights/:id} : Partial updates given fields of an existing copyright, field will ignore if it is null
     *
     * @param id the id of the copyrightDTO to save.
     * @param copyrightDTO the copyrightDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated copyrightDTO,
     * or with status {@code 400 (Bad Request)} if the copyrightDTO is not valid,
     * or with status {@code 404 (Not Found)} if the copyrightDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the copyrightDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CopyrightDTO>> partialUpdateCopyright(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CopyrightDTO copyrightDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Copyright partially : {}, {}", id, copyrightDTO);
        if (copyrightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, copyrightDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return copyrightRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CopyrightDTO> result = copyrightService.partialUpdate(copyrightDTO);

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
     * {@code GET  /copyrights} : get all the copyrights.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of copyrights in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<CopyrightDTO>> getAllCopyrights(@RequestParam(name = "filter", required = false) String filter) {
        if ("video-is-null".equals(filter)) {
            log.debug("REST request to get all Copyrights where video is null");
            return copyrightService.findAllWhereVideoIsNull().collectList();
        }
        log.debug("REST request to get all Copyrights");
        return copyrightService.findAll().collectList();
    }

    /**
     * {@code GET  /copyrights} : get all the copyrights as a stream.
     * @return the {@link Flux} of copyrights.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CopyrightDTO> getAllCopyrightsAsStream() {
        log.debug("REST request to get all Copyrights as a stream");
        return copyrightService.findAll();
    }

    /**
     * {@code GET  /copyrights/:id} : get the "id" copyright.
     *
     * @param id the id of the copyrightDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the copyrightDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CopyrightDTO>> getCopyright(@PathVariable("id") Long id) {
        log.debug("REST request to get Copyright : {}", id);
        Mono<CopyrightDTO> copyrightDTO = copyrightService.findOne(id);
        return ResponseUtil.wrapOrNotFound(copyrightDTO);
    }

    /**
     * {@code DELETE  /copyrights/:id} : delete the "id" copyright.
     *
     * @param id the id of the copyrightDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCopyright(@PathVariable("id") Long id) {
        log.debug("REST request to delete Copyright : {}", id);
        return copyrightService
            .delete(id)
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
