package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.SystemSetting;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.SystemSettingRepository;
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
 * Integration tests for the {@link SystemSettingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SystemSettingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/system-settings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SystemSettingRepository systemSettingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SystemSetting systemSetting;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemSetting createEntity(EntityManager em) {
        SystemSetting systemSetting = new SystemSetting().name(DEFAULT_NAME).value(DEFAULT_VALUE);
        return systemSetting;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemSetting createUpdatedEntity(EntityManager em) {
        SystemSetting systemSetting = new SystemSetting().name(UPDATED_NAME).value(UPDATED_VALUE);
        return systemSetting;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SystemSetting.class).block();
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
        systemSetting = createEntity(em);
    }

    @Test
    void createSystemSetting() throws Exception {
        int databaseSizeBeforeCreate = systemSettingRepository.findAll().collectList().block().size();
        // Create the SystemSetting
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeCreate + 1);
        SystemSetting testSystemSetting = systemSettingList.get(systemSettingList.size() - 1);
        assertThat(testSystemSetting.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemSetting.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    void createSystemSettingWithExistingId() throws Exception {
        // Create the SystemSetting with an existing ID
        systemSetting.setId(1L);

        int databaseSizeBeforeCreate = systemSettingRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemSettingRepository.findAll().collectList().block().size();
        // set the field null
        systemSetting.setName(null);

        // Create the SystemSetting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemSettingRepository.findAll().collectList().block().size();
        // set the field null
        systemSetting.setValue(null);

        // Create the SystemSetting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllSystemSettingsAsStream() {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        List<SystemSetting> systemSettingList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(SystemSetting.class)
            .getResponseBody()
            .filter(systemSetting::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(systemSettingList).isNotNull();
        assertThat(systemSettingList).hasSize(1);
        SystemSetting testSystemSetting = systemSettingList.get(0);
        assertThat(testSystemSetting.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemSetting.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    void getAllSystemSettings() {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        // Get all the systemSettingList
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
            .value(hasItem(systemSetting.getId().intValue()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].value")
            .value(hasItem(DEFAULT_VALUE));
    }

    @Test
    void getSystemSetting() {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        // Get the systemSetting
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, systemSetting.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(systemSetting.getId().intValue()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.value")
            .value(is(DEFAULT_VALUE));
    }

    @Test
    void getNonExistingSystemSetting() {
        // Get the systemSetting
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSystemSetting() throws Exception {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();

        // Update the systemSetting
        SystemSetting updatedSystemSetting = systemSettingRepository.findById(systemSetting.getId()).block();
        updatedSystemSetting.name(UPDATED_NAME).value(UPDATED_VALUE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedSystemSetting.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedSystemSetting))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
        SystemSetting testSystemSetting = systemSettingList.get(systemSettingList.size() - 1);
        assertThat(testSystemSetting.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemSetting.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    void putNonExistingSystemSetting() throws Exception {
        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();
        systemSetting.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, systemSetting.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSystemSetting() throws Exception {
        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();
        systemSetting.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSystemSetting() throws Exception {
        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();
        systemSetting.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSystemSettingWithPatch() throws Exception {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();

        // Update the systemSetting using partial update
        SystemSetting partialUpdatedSystemSetting = new SystemSetting();
        partialUpdatedSystemSetting.setId(systemSetting.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSystemSetting.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSystemSetting))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
        SystemSetting testSystemSetting = systemSettingList.get(systemSettingList.size() - 1);
        assertThat(testSystemSetting.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemSetting.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    void fullUpdateSystemSettingWithPatch() throws Exception {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();

        // Update the systemSetting using partial update
        SystemSetting partialUpdatedSystemSetting = new SystemSetting();
        partialUpdatedSystemSetting.setId(systemSetting.getId());

        partialUpdatedSystemSetting.name(UPDATED_NAME).value(UPDATED_VALUE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSystemSetting.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSystemSetting))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
        SystemSetting testSystemSetting = systemSettingList.get(systemSettingList.size() - 1);
        assertThat(testSystemSetting.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemSetting.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    void patchNonExistingSystemSetting() throws Exception {
        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();
        systemSetting.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, systemSetting.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSystemSetting() throws Exception {
        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();
        systemSetting.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSystemSetting() throws Exception {
        int databaseSizeBeforeUpdate = systemSettingRepository.findAll().collectList().block().size();
        systemSetting.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(systemSetting))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SystemSetting in the database
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSystemSetting() {
        // Initialize the database
        systemSettingRepository.save(systemSetting).block();

        int databaseSizeBeforeDelete = systemSettingRepository.findAll().collectList().block().size();

        // Delete the systemSetting
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, systemSetting.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SystemSetting> systemSettingList = systemSettingRepository.findAll().collectList().block();
        assertThat(systemSettingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
