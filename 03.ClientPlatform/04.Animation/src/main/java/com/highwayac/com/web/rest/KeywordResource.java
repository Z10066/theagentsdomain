package com.highwayac.com.web.rest;

import com.highwayac.com.repository.KeywordRepository;
import com.highwayac.com.service.KeywordService;
import com.highwayac.com.service.dto.KeywordDTO;
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
 * REST controller for managing {@link com.highwayac.com.domain.Keyword}.
 */
@RestController
@RequestMapping("/api/keywords")
public class KeywordResource {

    private final Logger log = LoggerFactory.getLogger(KeywordResource.class);

    private static final String ENTITY_NAME = "keyword";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KeywordService keywordService;

    private final KeywordRepository keywordRepository;

    public KeywordResource(KeywordService keywordService, KeywordRepository keywordRepository) {
        this.keywordService = keywordService;
        this.keywordRepository = keywordRepository;
    }

    /**
     * {@code POST  /keywords} : Create a new keyword.
     *
     * @param keywordDTO the keywordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new keywordDTO, or with status {@code 400 (Bad Request)} if the keyword has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<KeywordDTO>> createKeyword(@Valid @RequestBody KeywordDTO keywordDTO) throws URISyntaxException {
        log.debug("REST request to save Keyword : {}", keywordDTO);
        if (keywordDTO.getId() != null) {
            throw new BadRequestAlertException("A new keyword cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return keywordService
            .save(keywordDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/keywords/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /keywords/:id} : Updates an existing keyword.
     *
     * @param id the id of the keywordDTO to save.
     * @param keywordDTO the keywordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated keywordDTO,
     * or with status {@code 400 (Bad Request)} if the keywordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the keywordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<KeywordDTO>> updateKeyword(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody KeywordDTO keywordDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Keyword : {}, {}", id, keywordDTO);
        if (keywordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, keywordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return keywordRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return keywordService
                    .update(keywordDTO)
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
     * {@code PATCH  /keywords/:id} : Partial updates given fields of an existing keyword, field will ignore if it is null
     *
     * @param id the id of the keywordDTO to save.
     * @param keywordDTO the keywordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated keywordDTO,
     * or with status {@code 400 (Bad Request)} if the keywordDTO is not valid,
     * or with status {@code 404 (Not Found)} if the keywordDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the keywordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<KeywordDTO>> partialUpdateKeyword(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody KeywordDTO keywordDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Keyword partially : {}, {}", id, keywordDTO);
        if (keywordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, keywordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return keywordRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<KeywordDTO> result = keywordService.partialUpdate(keywordDTO);

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
     * {@code GET  /keywords} : get all the keywords.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of keywords in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<KeywordDTO>>> getAllKeywords(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Keywords");
        return keywordService
            .countAll()
            .zipWith(keywordService.findAll(pageable).collectList())
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
     * {@code GET  /keywords/:id} : get the "id" keyword.
     *
     * @param id the id of the keywordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the keywordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<KeywordDTO>> getKeyword(@PathVariable("id") Long id) {
        log.debug("REST request to get Keyword : {}", id);
        Mono<KeywordDTO> keywordDTO = keywordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(keywordDTO);
    }

    /**
     * {@code DELETE  /keywords/:id} : delete the "id" keyword.
     *
     * @param id the id of the keywordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteKeyword(@PathVariable("id") Long id) {
        log.debug("REST request to delete Keyword : {}", id);
        return keywordService
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
