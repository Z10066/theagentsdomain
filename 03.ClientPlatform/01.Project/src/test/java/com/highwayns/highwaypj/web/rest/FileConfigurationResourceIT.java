package com.highwayns.highwaypj.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayns.highwaypj.IntegrationTest;
import com.highwayns.highwaypj.domain.FileConfiguration;
import com.highwayns.highwaypj.repository.EntityManager;
import com.highwayns.highwaypj.repository.FileConfigurationRepository;
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
 * Integration tests for the {@link FileConfigurationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FileConfigurationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_TYPES = "AAAAAAAAAA";
    private static final String UPDATED_TYPES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/file-configurations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FileConfigurationRepository fileConfigurationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FileConfiguration fileConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileConfiguration createEntity(EntityManager em) {
        FileConfiguration fileConfiguration = new FileConfiguration()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .path(DEFAULT_PATH)
            .types(DEFAULT_TYPES);
        return fileConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileConfiguration createUpdatedEntity(EntityManager em) {
        FileConfiguration fileConfiguration = new FileConfiguration()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .path(UPDATED_PATH)
            .types(UPDATED_TYPES);
        return fileConfiguration;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FileConfiguration.class).block();
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
        fileConfiguration = createEntity(em);
    }

    @Test
    void createFileConfiguration() throws Exception {
        int databaseSizeBeforeCreate = fileConfigurationRepository.findAll().collectList().block().size();
        // Create the FileConfiguration
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        FileConfiguration testFileConfiguration = fileConfigurationList.get(fileConfigurationList.size() - 1);
        assertThat(testFileConfiguration.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFileConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFileConfiguration.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testFileConfiguration.getTypes()).isEqualTo(DEFAULT_TYPES);
    }

    @Test
    void createFileConfigurationWithExistingId() throws Exception {
        // Create the FileConfiguration with an existing ID
        fileConfiguration.setId(1L);

        int databaseSizeBeforeCreate = fileConfigurationRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileConfigurationRepository.findAll().collectList().block().size();
        // set the field null
        fileConfiguration.setName(null);

        // Create the FileConfiguration, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPathIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileConfigurationRepository.findAll().collectList().block().size();
        // set the field null
        fileConfiguration.setPath(null);

        // Create the FileConfiguration, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTypesIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileConfigurationRepository.findAll().collectList().block().size();
        // set the field null
        fileConfiguration.setTypes(null);

        // Create the FileConfiguration, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFileConfigurations() {
        // Initialize the database
        fileConfigurationRepository.save(fileConfiguration).block();

        // Get all the fileConfigurationList
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
            .value(hasItem(fileConfiguration.getId().intValue()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].path")
            .value(hasItem(DEFAULT_PATH))
            .jsonPath("$.[*].types")
            .value(hasItem(DEFAULT_TYPES));
    }

    @Test
    void getFileConfiguration() {
        // Initialize the database
        fileConfigurationRepository.save(fileConfiguration).block();

        // Get the fileConfiguration
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, fileConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(fileConfiguration.getId().intValue()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.path")
            .value(is(DEFAULT_PATH))
            .jsonPath("$.types")
            .value(is(DEFAULT_TYPES));
    }

    @Test
    void getNonExistingFileConfiguration() {
        // Get the fileConfiguration
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFileConfiguration() throws Exception {
        // Initialize the database
        fileConfigurationRepository.save(fileConfiguration).block();

        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();

        // Update the fileConfiguration
        FileConfiguration updatedFileConfiguration = fileConfigurationRepository.findById(fileConfiguration.getId()).block();
        updatedFileConfiguration.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).path(UPDATED_PATH).types(UPDATED_TYPES);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedFileConfiguration.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedFileConfiguration))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
        FileConfiguration testFileConfiguration = fileConfigurationList.get(fileConfigurationList.size() - 1);
        assertThat(testFileConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFileConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFileConfiguration.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testFileConfiguration.getTypes()).isEqualTo(UPDATED_TYPES);
    }

    @Test
    void putNonExistingFileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();
        fileConfiguration.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fileConfiguration.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();
        fileConfiguration.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();
        fileConfiguration.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFileConfigurationWithPatch() throws Exception {
        // Initialize the database
        fileConfigurationRepository.save(fileConfiguration).block();

        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();

        // Update the fileConfiguration using partial update
        FileConfiguration partialUpdatedFileConfiguration = new FileConfiguration();
        partialUpdatedFileConfiguration.setId(fileConfiguration.getId());

        partialUpdatedFileConfiguration.name(UPDATED_NAME).path(UPDATED_PATH);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFileConfiguration.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFileConfiguration))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
        FileConfiguration testFileConfiguration = fileConfigurationList.get(fileConfigurationList.size() - 1);
        assertThat(testFileConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFileConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFileConfiguration.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testFileConfiguration.getTypes()).isEqualTo(DEFAULT_TYPES);
    }

    @Test
    void fullUpdateFileConfigurationWithPatch() throws Exception {
        // Initialize the database
        fileConfigurationRepository.save(fileConfiguration).block();

        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();

        // Update the fileConfiguration using partial update
        FileConfiguration partialUpdatedFileConfiguration = new FileConfiguration();
        partialUpdatedFileConfiguration.setId(fileConfiguration.getId());

        partialUpdatedFileConfiguration.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).path(UPDATED_PATH).types(UPDATED_TYPES);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFileConfiguration.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFileConfiguration))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
        FileConfiguration testFileConfiguration = fileConfigurationList.get(fileConfigurationList.size() - 1);
        assertThat(testFileConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFileConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFileConfiguration.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testFileConfiguration.getTypes()).isEqualTo(UPDATED_TYPES);
    }

    @Test
    void patchNonExistingFileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();
        fileConfiguration.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, fileConfiguration.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();
        fileConfiguration.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = fileConfigurationRepository.findAll().collectList().block().size();
        fileConfiguration.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fileConfiguration))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FileConfiguration in the database
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFileConfiguration() {
        // Initialize the database
        fileConfigurationRepository.save(fileConfiguration).block();

        int databaseSizeBeforeDelete = fileConfigurationRepository.findAll().collectList().block().size();

        // Delete the fileConfiguration
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, fileConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FileConfiguration> fileConfigurationList = fileConfigurationRepository.findAll().collectList().block();
        assertThat(fileConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
