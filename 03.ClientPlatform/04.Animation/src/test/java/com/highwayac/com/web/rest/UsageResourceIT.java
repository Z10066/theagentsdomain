package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Usage;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.UsageRepository;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link UsageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class UsageResourceIT {

    private static final String DEFAULT_USAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_USAGE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_USAGE_TIME = 1;
    private static final Integer UPDATED_USAGE_TIME = 2;

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/usages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UsageRepository usageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Usage usage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usage createEntity(EntityManager em) {
        Usage usage = new Usage()
            .usageType(DEFAULT_USAGE_TYPE)
            .usageTime(DEFAULT_USAGE_TIME)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME);
        return usage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usage createUpdatedEntity(EntityManager em) {
        Usage usage = new Usage()
            .usageType(UPDATED_USAGE_TYPE)
            .usageTime(UPDATED_USAGE_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        return usage;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Usage.class).block();
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
        usage = createEntity(em);
    }

    @Test
    void createUsage() throws Exception {
        int databaseSizeBeforeCreate = usageRepository.findAll().collectList().block().size();
        // Create the Usage
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeCreate + 1);
        Usage testUsage = usageList.get(usageList.size() - 1);
        assertThat(testUsage.getUsageType()).isEqualTo(DEFAULT_USAGE_TYPE);
        assertThat(testUsage.getUsageTime()).isEqualTo(DEFAULT_USAGE_TIME);
        assertThat(testUsage.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testUsage.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    void createUsageWithExistingId() throws Exception {
        // Create the Usage with an existing ID
        usage.setId(1L);

        int databaseSizeBeforeCreate = usageRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkUsageTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = usageRepository.findAll().collectList().block().size();
        // set the field null
        usage.setUsageType(null);

        // Create the Usage, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllUsagesAsStream() {
        // Initialize the database
        usageRepository.save(usage).block();

        List<Usage> usageList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Usage.class)
            .getResponseBody()
            .filter(usage::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(usageList).isNotNull();
        assertThat(usageList).hasSize(1);
        Usage testUsage = usageList.get(0);
        assertThat(testUsage.getUsageType()).isEqualTo(DEFAULT_USAGE_TYPE);
        assertThat(testUsage.getUsageTime()).isEqualTo(DEFAULT_USAGE_TIME);
        assertThat(testUsage.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testUsage.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    void getAllUsages() {
        // Initialize the database
        usageRepository.save(usage).block();

        // Get all the usageList
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
            .value(hasItem(usage.getId().intValue()))
            .jsonPath("$.[*].usageType")
            .value(hasItem(DEFAULT_USAGE_TYPE))
            .jsonPath("$.[*].usageTime")
            .value(hasItem(DEFAULT_USAGE_TIME))
            .jsonPath("$.[*].startTime")
            .value(hasItem(DEFAULT_START_TIME.toString()))
            .jsonPath("$.[*].endTime")
            .value(hasItem(DEFAULT_END_TIME.toString()));
    }

    @Test
    void getUsage() {
        // Initialize the database
        usageRepository.save(usage).block();

        // Get the usage
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, usage.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(usage.getId().intValue()))
            .jsonPath("$.usageType")
            .value(is(DEFAULT_USAGE_TYPE))
            .jsonPath("$.usageTime")
            .value(is(DEFAULT_USAGE_TIME))
            .jsonPath("$.startTime")
            .value(is(DEFAULT_START_TIME.toString()))
            .jsonPath("$.endTime")
            .value(is(DEFAULT_END_TIME.toString()));
    }

    @Test
    void getNonExistingUsage() {
        // Get the usage
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingUsage() throws Exception {
        // Initialize the database
        usageRepository.save(usage).block();

        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();

        // Update the usage
        Usage updatedUsage = usageRepository.findById(usage.getId()).block();
        updatedUsage.usageType(UPDATED_USAGE_TYPE).usageTime(UPDATED_USAGE_TIME).startTime(UPDATED_START_TIME).endTime(UPDATED_END_TIME);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedUsage.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedUsage))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
        Usage testUsage = usageList.get(usageList.size() - 1);
        assertThat(testUsage.getUsageType()).isEqualTo(UPDATED_USAGE_TYPE);
        assertThat(testUsage.getUsageTime()).isEqualTo(UPDATED_USAGE_TIME);
        assertThat(testUsage.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testUsage.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    void putNonExistingUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();
        usage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, usage.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();
        usage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();
        usage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateUsageWithPatch() throws Exception {
        // Initialize the database
        usageRepository.save(usage).block();

        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();

        // Update the usage using partial update
        Usage partialUpdatedUsage = new Usage();
        partialUpdatedUsage.setId(usage.getId());

        partialUpdatedUsage.usageTime(UPDATED_USAGE_TIME).startTime(UPDATED_START_TIME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedUsage.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedUsage))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
        Usage testUsage = usageList.get(usageList.size() - 1);
        assertThat(testUsage.getUsageType()).isEqualTo(DEFAULT_USAGE_TYPE);
        assertThat(testUsage.getUsageTime()).isEqualTo(UPDATED_USAGE_TIME);
        assertThat(testUsage.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testUsage.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    void fullUpdateUsageWithPatch() throws Exception {
        // Initialize the database
        usageRepository.save(usage).block();

        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();

        // Update the usage using partial update
        Usage partialUpdatedUsage = new Usage();
        partialUpdatedUsage.setId(usage.getId());

        partialUpdatedUsage
            .usageType(UPDATED_USAGE_TYPE)
            .usageTime(UPDATED_USAGE_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedUsage.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedUsage))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
        Usage testUsage = usageList.get(usageList.size() - 1);
        assertThat(testUsage.getUsageType()).isEqualTo(UPDATED_USAGE_TYPE);
        assertThat(testUsage.getUsageTime()).isEqualTo(UPDATED_USAGE_TIME);
        assertThat(testUsage.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testUsage.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    void patchNonExistingUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();
        usage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, usage.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();
        usage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().collectList().block().size();
        usage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(usage))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteUsage() {
        // Initialize the database
        usageRepository.save(usage).block();

        int databaseSizeBeforeDelete = usageRepository.findAll().collectList().block().size();

        // Delete the usage
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, usage.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Usage> usageList = usageRepository.findAll().collectList().block();
        assertThat(usageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
