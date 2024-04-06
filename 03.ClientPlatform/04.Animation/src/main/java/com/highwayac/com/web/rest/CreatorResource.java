package com.highwayac.com.web.rest;

import com.highwayac.com.repository.CreatorRepository;
import com.highwayac.com.service.CreatorService;
import com.highwayac.com.service.dto.CreatorDTO;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.ForwardedHeaderUtils;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.highwayac.com.domain.Creator}.
 */
@RestController
@RequestMapping("/api/creators")
public class CreatorResource {

    private final Logger log = LoggerFactory.getLogger(CreatorResource.class);

    private static final String ENTITY_NAME = "creator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreatorService creatorService;

    private final CreatorRepository creatorRepository;

    public CreatorResource(CreatorService creatorService, CreatorRepository creatorRepository) {
        this.creatorService = creatorService;
        this.creatorRepository = creatorRepository;
    }

    /**
     * {@code POST  /creators} : Create a new creator.
     *
     * @param creatorDTO the creatorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creatorDTO, or with status {@code 400 (Bad Request)} if the creator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CreatorDTO>> createCreator(@Valid @RequestBody CreatorDTO creatorDTO) throws URISyntaxException {
        log.debug("REST request to save Creator : {}", creatorDTO);
        if (creatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new creator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return creatorService
            .save(creatorDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/creators/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /creators/:id} : Updates an existing creator.
     *
     * @param id the id of the creatorDTO to save.
     * @param creatorDTO the creatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creatorDTO,
     * or with status {@code 400 (Bad Request)} if the creatorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreatorDTO>> updateCreator(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CreatorDTO creatorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Creator : {}, {}", id, creatorDTO);
        if (creatorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creatorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return creatorRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return creatorService
                    .update(creatorDTO)
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
     * {@code PATCH  /creators/:id} : Partial updates given fields of an existing creator, field will ignore if it is null
     *
     * @param id the id of the creatorDTO to save.
     * @param creatorDTO the creatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creatorDTO,
     * or with status {@code 400 (Bad Request)} if the creatorDTO is not valid,
     * or with status {@code 404 (Not Found)} if the creatorDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the creatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreatorDTO>> partialUpdateCreator(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CreatorDTO creatorDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Creator partially : {}, {}", id, creatorDTO);
        if (creatorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creatorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return creatorRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreatorDTO> result = creatorService.partialUpdate(creatorDTO);

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
     * {@code GET  /creators} : get all the creators.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creators in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CreatorDTO>>> getAllCreators(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("video-is-null".equals(filter)) {
            log.debug("REST request to get all Creators where video is null");
            return creatorService.findAllWhereVideoIsNull().collectList().map(ResponseEntity::ok);
        }
        log.debug("REST request to get a page of Creators");
        return creatorService
            .countAll()
            .zipWith(creatorService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /creators/:id} : get the "id" creator.
     *
     * @param id the id of the creatorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creatorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreatorDTO>> getCreator(@PathVariable("id") Long id) {
        log.debug("REST request to get Creator : {}", id);
        Mono<CreatorDTO> creatorDTO = creatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creatorDTO);
    }

    /**
     * {@code DELETE  /creators/:id} : delete the "id" creator.
     *
     * @param id the id of the creatorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCreator(@PathVariable("id") Long id) {
        log.debug("REST request to delete Creator : {}", id);
        return creatorService
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
