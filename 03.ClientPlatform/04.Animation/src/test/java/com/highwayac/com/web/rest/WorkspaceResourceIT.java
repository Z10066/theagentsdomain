package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Workspace;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.WorkspaceRepository;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link WorkspaceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class WorkspaceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BETA_FEATURES = false;
    private static final Boolean UPDATED_BETA_FEATURES = true;

    private static final Boolean DEFAULT_COLLABORATION_CURSOR = false;
    private static final Boolean UPDATED_COLLABORATION_CURSOR = true;

    private static final Boolean DEFAULT_DEFAULT_EXPORT_VISIBILITY = false;
    private static final Boolean UPDATED_DEFAULT_EXPORT_VISIBILITY = true;

    private static final Boolean DEFAULT_PUBLIC_ACCESS = false;
    private static final Boolean UPDATED_PUBLIC_ACCESS = true;

    private static final String ENTITY_API_URL = "/api/workspaces";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Workspace workspace;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workspace createEntity(EntityManager em) {
        Workspace workspace = new Workspace()
            .name(DEFAULT_NAME)
            .identifier(DEFAULT_IDENTIFIER)
            .betaFeatures(DEFAULT_BETA_FEATURES)
            .collaborationCursor(DEFAULT_COLLABORATION_CURSOR)
            .defaultExportVisibility(DEFAULT_DEFAULT_EXPORT_VISIBILITY)
            .publicAccess(DEFAULT_PUBLIC_ACCESS);
        return workspace;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workspace createUpdatedEntity(EntityManager em) {
        Workspace workspace = new Workspace()
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .betaFeatures(UPDATED_BETA_FEATURES)
            .collaborationCursor(UPDATED_COLLABORATION_CURSOR)
            .defaultExportVisibility(UPDATED_DEFAULT_EXPORT_VISIBILITY)
            .publicAccess(UPDATED_PUBLIC_ACCESS);
        return workspace;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Workspace.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        workspace = createEntity(em);
    }

    @Test
    void createWorkspace() throws Exception {
        int databaseSizeBeforeCreate = workspaceRepository.findAll().collectList().block().size();
        // Create the Workspace
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeCreate + 1);
        Workspace testWorkspace = workspaceList.get(workspaceList.size() - 1);
        assertThat(testWorkspace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWorkspace.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testWorkspace.getBetaFeatures()).isEqualTo(DEFAULT_BETA_FEATURES);
        assertThat(testWorkspace.getCollaborationCursor()).isEqualTo(DEFAULT_COLLABORATION_CURSOR);
        assertThat(testWorkspace.getDefaultExportVisibility()).isEqualTo(DEFAULT_DEFAULT_EXPORT_VISIBILITY);
        assertThat(testWorkspace.getPublicAccess()).isEqualTo(DEFAULT_PUBLIC_ACCESS);
    }

    @Test
    void createWorkspaceWithExistingId() throws Exception {
        // Create the Workspace with an existing ID
        workspace.setId(1L);

        int databaseSizeBeforeCreate = workspaceRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = workspaceRepository.findAll().collectList().block().size();
        // set the field null
        workspace.setName(null);

        // Create the Workspace, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = workspaceRepository.findAll().collectList().block().size();
        // set the field null
        workspace.setIdentifier(null);

        // Create the Workspace, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllWorkspacesAsStream() {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        List<Workspace> workspaceList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Workspace.class)
            .getResponseBody()
            .filter(workspace::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(workspaceList).isNotNull();
        assertThat(workspaceList).hasSize(1);
        Workspace testWorkspace = workspaceList.get(0);
        assertThat(testWorkspace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWorkspace.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testWorkspace.getBetaFeatures()).isEqualTo(DEFAULT_BETA_FEATURES);
        assertThat(testWorkspace.getCollaborationCursor()).isEqualTo(DEFAULT_COLLABORATION_CURSOR);
        assertThat(testWorkspace.getDefaultExportVisibility()).isEqualTo(DEFAULT_DEFAULT_EXPORT_VISIBILITY);
        assertThat(testWorkspace.getPublicAccess()).isEqualTo(DEFAULT_PUBLIC_ACCESS);
    }

    @Test
    void getAllWorkspaces() {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        // Get all the workspaceList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(workspace.getId().intValue()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].identifier")
            .value(hasItem(DEFAULT_IDENTIFIER))
            .jsonPath("$.[*].betaFeatures")
            .value(hasItem(DEFAULT_BETA_FEATURES.booleanValue()))
            .jsonPath("$.[*].collaborationCursor")
            .value(hasItem(DEFAULT_COLLABORATION_CURSOR.booleanValue()))
            .jsonPath("$.[*].defaultExportVisibility")
            .value(hasItem(DEFAULT_DEFAULT_EXPORT_VISIBILITY.booleanValue()))
            .jsonPath("$.[*].publicAccess")
            .value(hasItem(DEFAULT_PUBLIC_ACCESS.booleanValue()));
    }

    @Test
    void getWorkspace() {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        // Get the workspace
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, workspace.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(workspace.getId().intValue()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.identifier")
            .value(is(DEFAULT_IDENTIFIER))
            .jsonPath("$.betaFeatures")
            .value(is(DEFAULT_BETA_FEATURES.booleanValue()))
            .jsonPath("$.collaborationCursor")
            .value(is(DEFAULT_COLLABORATION_CURSOR.booleanValue()))
            .jsonPath("$.defaultExportVisibility")
            .value(is(DEFAULT_DEFAULT_EXPORT_VISIBILITY.booleanValue()))
            .jsonPath("$.publicAccess")
            .value(is(DEFAULT_PUBLIC_ACCESS.booleanValue()));
    }

    @Test
    void getNonExistingWorkspace() {
        // Get the workspace
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingWorkspace() throws Exception {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();

        // Update the workspace
        Workspace updatedWorkspace = workspaceRepository.findById(workspace.getId()).block();
        updatedWorkspace
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .betaFeatures(UPDATED_BETA_FEATURES)
            .collaborationCursor(UPDATED_COLLABORATION_CURSOR)
            .defaultExportVisibility(UPDATED_DEFAULT_EXPORT_VISIBILITY)
            .publicAccess(UPDATED_PUBLIC_ACCESS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedWorkspace.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedWorkspace))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
        Workspace testWorkspace = workspaceList.get(workspaceList.size() - 1);
        assertThat(testWorkspace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWorkspace.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testWorkspace.getBetaFeatures()).isEqualTo(UPDATED_BETA_FEATURES);
        assertThat(testWorkspace.getCollaborationCursor()).isEqualTo(UPDATED_COLLABORATION_CURSOR);
        assertThat(testWorkspace.getDefaultExportVisibility()).isEqualTo(UPDATED_DEFAULT_EXPORT_VISIBILITY);
        assertThat(testWorkspace.getPublicAccess()).isEqualTo(UPDATED_PUBLIC_ACCESS);
    }

    @Test
    void putNonExistingWorkspace() throws Exception {
        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();
        workspace.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, workspace.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchWorkspace() throws Exception {
        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();
        workspace.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamWorkspace() throws Exception {
        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();
        workspace.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateWorkspaceWithPatch() throws Exception {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();

        // Update the workspace using partial update
        Workspace partialUpdatedWorkspace = new Workspace();
        partialUpdatedWorkspace.setId(workspace.getId());

        partialUpdatedWorkspace
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .defaultExportVisibility(UPDATED_DEFAULT_EXPORT_VISIBILITY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedWorkspace.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkspace))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
        Workspace testWorkspace = workspaceList.get(workspaceList.size() - 1);
        assertThat(testWorkspace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWorkspace.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testWorkspace.getBetaFeatures()).isEqualTo(DEFAULT_BETA_FEATURES);
        assertThat(testWorkspace.getCollaborationCursor()).isEqualTo(DEFAULT_COLLABORATION_CURSOR);
        assertThat(testWorkspace.getDefaultExportVisibility()).isEqualTo(UPDATED_DEFAULT_EXPORT_VISIBILITY);
        assertThat(testWorkspace.getPublicAccess()).isEqualTo(DEFAULT_PUBLIC_ACCESS);
    }

    @Test
    void fullUpdateWorkspaceWithPatch() throws Exception {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();

        // Update the workspace using partial update
        Workspace partialUpdatedWorkspace = new Workspace();
        partialUpdatedWorkspace.setId(workspace.getId());

        partialUpdatedWorkspace
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .betaFeatures(UPDATED_BETA_FEATURES)
            .collaborationCursor(UPDATED_COLLABORATION_CURSOR)
            .defaultExportVisibility(UPDATED_DEFAULT_EXPORT_VISIBILITY)
            .publicAccess(UPDATED_PUBLIC_ACCESS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedWorkspace.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkspace))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
        Workspace testWorkspace = workspaceList.get(workspaceList.size() - 1);
        assertThat(testWorkspace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWorkspace.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testWorkspace.getBetaFeatures()).isEqualTo(UPDATED_BETA_FEATURES);
        assertThat(testWorkspace.getCollaborationCursor()).isEqualTo(UPDATED_COLLABORATION_CURSOR);
        assertThat(testWorkspace.getDefaultExportVisibility()).isEqualTo(UPDATED_DEFAULT_EXPORT_VISIBILITY);
        assertThat(testWorkspace.getPublicAccess()).isEqualTo(UPDATED_PUBLIC_ACCESS);
    }

    @Test
    void patchNonExistingWorkspace() throws Exception {
        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();
        workspace.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, workspace.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchWorkspace() throws Exception {
        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();
        workspace.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamWorkspace() throws Exception {
        int databaseSizeBeforeUpdate = workspaceRepository.findAll().collectList().block().size();
        workspace.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workspace))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Workspace in the database
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteWorkspace() {
        // Initialize the database
        workspaceRepository.save(workspace).block();

        int databaseSizeBeforeDelete = workspaceRepository.findAll().collectList().block().size();

        // Delete the workspace
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, workspace.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Workspace> workspaceList = workspaceRepository.findAll().collectList().block();
        assertThat(workspaceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
