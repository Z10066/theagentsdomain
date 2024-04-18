package com.highwayac.com.web.rest;

import com.highwayac.com.domain.Workspace;
import com.highwayac.com.repository.WorkspaceRepository;
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
 * REST controller for managing {@link com.highwayac.com.domain.Workspace}.
 */
@RestController
@RequestMapping("/api/workspaces")
@Transactional
public class WorkspaceResource {

    private final Logger log = LoggerFactory.getLogger(WorkspaceResource.class);

    private static final String ENTITY_NAME = "workspace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkspaceRepository workspaceRepository;

    public WorkspaceResource(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    /**
     * {@code POST  /workspaces} : Create a new workspace.
     *
     * @param workspace the workspace to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workspace, or with status {@code 400 (Bad Request)} if the workspace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<Workspace>> createWorkspace(@Valid @RequestBody Workspace workspace) throws URISyntaxException {
        log.debug("REST request to save Workspace : {}", workspace);
        if (workspace.getId() != null) {
            throw new BadRequestAlertException("A new workspace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return workspaceRepository
            .save(workspace)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/workspaces/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /workspaces/:id} : Updates an existing workspace.
     *
     * @param id the id of the workspace to save.
     * @param workspace the workspace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workspace,
     * or with status {@code 400 (Bad Request)} if the workspace is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workspace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Workspace>> updateWorkspace(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Workspace workspace
    ) throws URISyntaxException {
        log.debug("REST request to update Workspace : {}, {}", id, workspace);
        if (workspace.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workspace.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return workspaceRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return workspaceRepository
                    .save(workspace)
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
     * {@code PATCH  /workspaces/:id} : Partial updates given fields of an existing workspace, field will ignore if it is null
     *
     * @param id the id of the workspace to save.
     * @param workspace the workspace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workspace,
     * or with status {@code 400 (Bad Request)} if the workspace is not valid,
     * or with status {@code 404 (Not Found)} if the workspace is not found,
     * or with status {@code 500 (Internal Server Error)} if the workspace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Workspace>> partialUpdateWorkspace(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Workspace workspace
    ) throws URISyntaxException {
        log.debug("REST request to partial update Workspace partially : {}, {}", id, workspace);
        if (workspace.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workspace.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return workspaceRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Workspace> result = workspaceRepository
                    .findById(workspace.getId())
                    .map(existingWorkspace -> {
                        if (workspace.getName() != null) {
                            existingWorkspace.setName(workspace.getName());
                        }
                        if (workspace.getIdentifier() != null) {
                            existingWorkspace.setIdentifier(workspace.getIdentifier());
                        }
                        if (workspace.getBetaFeatures() != null) {
                            existingWorkspace.setBetaFeatures(workspace.getBetaFeatures());
                        }
                        if (workspace.getCollaborationCursor() != null) {
                            existingWorkspace.setCollaborationCursor(workspace.getCollaborationCursor());
                        }
                        if (workspace.getDefaultExportVisibility() != null) {
                            existingWorkspace.setDefaultExportVisibility(workspace.getDefaultExportVisibility());
                        }
                        if (workspace.getPublicAccess() != null) {
                            existingWorkspace.setPublicAccess(workspace.getPublicAccess());
                        }

                        return existingWorkspace;
                    })
                    .flatMap(workspaceRepository::save);

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
     * {@code GET  /workspaces} : get all the workspaces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workspaces in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Workspace>> getAllWorkspaces() {
        log.debug("REST request to get all Workspaces");
        return workspaceRepository.findAll().collectList();
    }

    /**
     * {@code GET  /workspaces} : get all the workspaces as a stream.
     * @return the {@link Flux} of workspaces.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Workspace> getAllWorkspacesAsStream() {
        log.debug("REST request to get all Workspaces as a stream");
        return workspaceRepository.findAll();
    }

    /**
     * {@code GET  /workspaces/:id} : get the "id" workspace.
     *
     * @param id the id of the workspace to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workspace, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Workspace>> getWorkspace(@PathVariable("id") Long id) {
        log.debug("REST request to get Workspace : {}", id);
        Mono<Workspace> workspace = workspaceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workspace);
    }

    /**
     * {@code GET  /workspaces/:identifier} : get the "identifier" workspace.
     *
     * @param identifier the id of the workspace to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workspace, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(value = "/identifier/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Workspace>> getWorkspace(@PathVariable("identifier") String identifier) {
        log.debug("REST request to get Workspace by identifier: {}", identifier);
        Flux<Workspace> workspaces = workspaceRepository.findByIdentifier(identifier);
        log.debug(workspaces.collectList().toString());
        return workspaces.collectList();
    }

    /**
     * {@code DELETE  /workspaces/:id} : delete the "id" workspace.
     *
     * @param id the id of the workspace to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteWorkspace(@PathVariable("id") Long id) {
        log.debug("REST request to delete Workspace : {}", id);
        return workspaceRepository
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
