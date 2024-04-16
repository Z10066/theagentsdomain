package com.highwayac.com.web.rest;

import com.highwayac.com.domain.SystemSetting;
import com.highwayac.com.repository.SystemSettingRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.SystemSetting}.
 */
@RestController
@RequestMapping("/api/system-settings")
@Transactional
public class SystemSettingResource {

    private final Logger log = LoggerFactory.getLogger(SystemSettingResource.class);

    private static final String ENTITY_NAME = "systemSetting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SystemSettingRepository systemSettingRepository;

    public SystemSettingResource(SystemSettingRepository systemSettingRepository) {
        this.systemSettingRepository = systemSettingRepository;
    }

    /**
     * {@code POST  /system-settings} : Create a new systemSetting.
     *
     * @param systemSetting the systemSetting to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new systemSetting, or with status {@code 400 (Bad Request)} if the systemSetting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<SystemSetting>> createSystemSetting(@Valid @RequestBody SystemSetting systemSetting)
        throws URISyntaxException {
        log.debug("REST request to save SystemSetting : {}", systemSetting);
        if (systemSetting.getId() != null) {
            throw new BadRequestAlertException("A new systemSetting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return systemSettingRepository
            .save(systemSetting)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/system-settings/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /system-settings/:id} : Updates an existing systemSetting.
     *
     * @param id the id of the systemSetting to save.
     * @param systemSetting the systemSetting to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemSetting,
     * or with status {@code 400 (Bad Request)} if the systemSetting is not valid,
     * or with status {@code 500 (Internal Server Error)} if the systemSetting couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<SystemSetting>> updateSystemSetting(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SystemSetting systemSetting
    ) throws URISyntaxException {
        log.debug("REST request to update SystemSetting : {}, {}", id, systemSetting);
        if (systemSetting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, systemSetting.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return systemSettingRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return systemSettingRepository
                    .save(systemSetting)
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
     * {@code PATCH  /system-settings/:id} : Partial updates given fields of an existing systemSetting, field will ignore if it is null
     *
     * @param id the id of the systemSetting to save.
     * @param systemSetting the systemSetting to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemSetting,
     * or with status {@code 400 (Bad Request)} if the systemSetting is not valid,
     * or with status {@code 404 (Not Found)} if the systemSetting is not found,
     * or with status {@code 500 (Internal Server Error)} if the systemSetting couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SystemSetting>> partialUpdateSystemSetting(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SystemSetting systemSetting
    ) throws URISyntaxException {
        log.debug("REST request to partial update SystemSetting partially : {}, {}", id, systemSetting);
        if (systemSetting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, systemSetting.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return systemSettingRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SystemSetting> result = systemSettingRepository
                    .findById(systemSetting.getId())
                    .map(existingSystemSetting -> {
                        if (systemSetting.getName() != null) {
                            existingSystemSetting.setName(systemSetting.getName());
                        }
                        if (systemSetting.getValue() != null) {
                            existingSystemSetting.setValue(systemSetting.getValue());
                        }

                        return existingSystemSetting;
                    })
                    .flatMap(systemSettingRepository::save);

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
     * {@code GET  /system-settings} : get all the systemSettings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of systemSettings in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<SystemSetting>> getAllSystemSettings() {
        log.debug("REST request to get all SystemSettings");
        return systemSettingRepository.findAll().collectList();
    }

    /**
     * {@code GET  /system-settings} : get all the systemSettings as a stream.
     * @return the {@link Flux} of systemSettings.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<SystemSetting> getAllSystemSettingsAsStream() {
        log.debug("REST request to get all SystemSettings as a stream");
        return systemSettingRepository.findAll();
    }

    /**
     * {@code GET  /system-settings/:id} : get the "id" systemSetting.
     *
     * @param id the id of the systemSetting to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the systemSetting, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<SystemSetting>> getSystemSetting(@PathVariable("id") Long id) {
        log.debug("REST request to get SystemSetting : {}", id);
        Mono<SystemSetting> systemSetting = systemSettingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(systemSetting);
    }

    /**
     * {@code DELETE  /system-settings/:id} : delete the "id" systemSetting.
     *
     * @param id the id of the systemSetting to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSystemSetting(@PathVariable("id") Long id) {
        log.debug("REST request to delete SystemSetting : {}", id);
        return systemSettingRepository
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
