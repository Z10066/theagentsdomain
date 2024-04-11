package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.SubscriptionService;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.SubscriptionServiceRepository;
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
 * Integration tests for the {@link SubscriptionServiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SubscriptionServiceResourceIT {

    private static final String DEFAULT_SERVICE_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_LEVEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_USAGE_TIME = 1;
    private static final Integer UPDATED_TOTAL_USAGE_TIME = 2;

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/subscription-services";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SubscriptionServiceRepository subscriptionServiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SubscriptionService subscriptionService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionService createEntity(EntityManager em) {
        SubscriptionService subscriptionService = new SubscriptionService()
            .serviceLevel(DEFAULT_SERVICE_LEVEL)
            .totalUsageTime(DEFAULT_TOTAL_USAGE_TIME)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME);
        return subscriptionService;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionService createUpdatedEntity(EntityManager em) {
        SubscriptionService subscriptionService = new SubscriptionService()
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .totalUsageTime(UPDATED_TOTAL_USAGE_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        return subscriptionService;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SubscriptionService.class).block();
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
        subscriptionService = createEntity(em);
    }

    @Test
    void createSubscriptionService() throws Exception {
        int databaseSizeBeforeCreate = subscriptionServiceRepository.findAll().collectList().block().size();
        // Create the SubscriptionService
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriptionService testSubscriptionService = subscriptionServiceList.get(subscriptionServiceList.size() - 1);
        assertThat(testSubscriptionService.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testSubscriptionService.getTotalUsageTime()).isEqualTo(DEFAULT_TOTAL_USAGE_TIME);
        assertThat(testSubscriptionService.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testSubscriptionService.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    void createSubscriptionServiceWithExistingId() throws Exception {
        // Create the SubscriptionService with an existing ID
        subscriptionService.setId(1L);

        int databaseSizeBeforeCreate = subscriptionServiceRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkServiceLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionServiceRepository.findAll().collectList().block().size();
        // set the field null
        subscriptionService.setServiceLevel(null);

        // Create the SubscriptionService, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllSubscriptionServicesAsStream() {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        List<SubscriptionService> subscriptionServiceList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(SubscriptionService.class)
            .getResponseBody()
            .filter(subscriptionService::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(subscriptionServiceList).isNotNull();
        assertThat(subscriptionServiceList).hasSize(1);
        SubscriptionService testSubscriptionService = subscriptionServiceList.get(0);
        assertThat(testSubscriptionService.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testSubscriptionService.getTotalUsageTime()).isEqualTo(DEFAULT_TOTAL_USAGE_TIME);
        assertThat(testSubscriptionService.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testSubscriptionService.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    void getAllSubscriptionServices() {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        // Get all the subscriptionServiceList
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
            .value(hasItem(subscriptionService.getId().intValue()))
            .jsonPath("$.[*].serviceLevel")
            .value(hasItem(DEFAULT_SERVICE_LEVEL))
            .jsonPath("$.[*].totalUsageTime")
            .value(hasItem(DEFAULT_TOTAL_USAGE_TIME))
            .jsonPath("$.[*].startTime")
            .value(hasItem(DEFAULT_START_TIME.toString()))
            .jsonPath("$.[*].endTime")
            .value(hasItem(DEFAULT_END_TIME.toString()));
    }

    @Test
    void getSubscriptionService() {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        // Get the subscriptionService
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, subscriptionService.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(subscriptionService.getId().intValue()))
            .jsonPath("$.serviceLevel")
            .value(is(DEFAULT_SERVICE_LEVEL))
            .jsonPath("$.totalUsageTime")
            .value(is(DEFAULT_TOTAL_USAGE_TIME))
            .jsonPath("$.startTime")
            .value(is(DEFAULT_START_TIME.toString()))
            .jsonPath("$.endTime")
            .value(is(DEFAULT_END_TIME.toString()));
    }

    @Test
    void getNonExistingSubscriptionService() {
        // Get the subscriptionService
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSubscriptionService() throws Exception {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();

        // Update the subscriptionService
        SubscriptionService updatedSubscriptionService = subscriptionServiceRepository.findById(subscriptionService.getId()).block();
        updatedSubscriptionService
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .totalUsageTime(UPDATED_TOTAL_USAGE_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedSubscriptionService.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedSubscriptionService))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionService testSubscriptionService = subscriptionServiceList.get(subscriptionServiceList.size() - 1);
        assertThat(testSubscriptionService.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testSubscriptionService.getTotalUsageTime()).isEqualTo(UPDATED_TOTAL_USAGE_TIME);
        assertThat(testSubscriptionService.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testSubscriptionService.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    void putNonExistingSubscriptionService() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();
        subscriptionService.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, subscriptionService.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSubscriptionService() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();
        subscriptionService.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSubscriptionService() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();
        subscriptionService.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSubscriptionServiceWithPatch() throws Exception {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();

        // Update the subscriptionService using partial update
        SubscriptionService partialUpdatedSubscriptionService = new SubscriptionService();
        partialUpdatedSubscriptionService.setId(subscriptionService.getId());

        partialUpdatedSubscriptionService.totalUsageTime(UPDATED_TOTAL_USAGE_TIME).endTime(UPDATED_END_TIME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSubscriptionService.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSubscriptionService))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionService testSubscriptionService = subscriptionServiceList.get(subscriptionServiceList.size() - 1);
        assertThat(testSubscriptionService.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testSubscriptionService.getTotalUsageTime()).isEqualTo(UPDATED_TOTAL_USAGE_TIME);
        assertThat(testSubscriptionService.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testSubscriptionService.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    void fullUpdateSubscriptionServiceWithPatch() throws Exception {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();

        // Update the subscriptionService using partial update
        SubscriptionService partialUpdatedSubscriptionService = new SubscriptionService();
        partialUpdatedSubscriptionService.setId(subscriptionService.getId());

        partialUpdatedSubscriptionService
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .totalUsageTime(UPDATED_TOTAL_USAGE_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSubscriptionService.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSubscriptionService))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionService testSubscriptionService = subscriptionServiceList.get(subscriptionServiceList.size() - 1);
        assertThat(testSubscriptionService.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testSubscriptionService.getTotalUsageTime()).isEqualTo(UPDATED_TOTAL_USAGE_TIME);
        assertThat(testSubscriptionService.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testSubscriptionService.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    void patchNonExistingSubscriptionService() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();
        subscriptionService.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, subscriptionService.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSubscriptionService() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();
        subscriptionService.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSubscriptionService() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionServiceRepository.findAll().collectList().block().size();
        subscriptionService.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(subscriptionService))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SubscriptionService in the database
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSubscriptionService() {
        // Initialize the database
        subscriptionServiceRepository.save(subscriptionService).block();

        int databaseSizeBeforeDelete = subscriptionServiceRepository.findAll().collectList().block().size();

        // Delete the subscriptionService
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, subscriptionService.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SubscriptionService> subscriptionServiceList = subscriptionServiceRepository.findAll().collectList().block();
        assertThat(subscriptionServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
