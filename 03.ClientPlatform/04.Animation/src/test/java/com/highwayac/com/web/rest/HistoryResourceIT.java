package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.History;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.HistoryRepository;
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
 * Integration tests for the {@link HistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class HistoryResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COPYRIGHT = "AAAAAAAAAA";
    private static final String UPDATED_COPYRIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_WATCH_LINK = "AAAAAAAAAA";
    private static final String UPDATED_WATCH_LINK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private History history;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static History createEntity(EntityManager em) {
        History history = new History()
            .title(DEFAULT_TITLE)
            .creator(DEFAULT_CREATOR)
            .description(DEFAULT_DESCRIPTION)
            .copyright(DEFAULT_COPYRIGHT)
            .watchLink(DEFAULT_WATCH_LINK);
        return history;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static History createUpdatedEntity(EntityManager em) {
        History history = new History()
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);
        return history;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(History.class).block();
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
        history = createEntity(em);
    }

    @Test
    void createHistory() throws Exception {
        int databaseSizeBeforeCreate = historyRepository.findAll().collectList().block().size();
        // Create the History
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeCreate + 1);
        History testHistory = historyList.get(historyList.size() - 1);
        assertThat(testHistory.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testHistory.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHistory.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testHistory.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void createHistoryWithExistingId() throws Exception {
        // Create the History with an existing ID
        history.setId(1L);

        int databaseSizeBeforeCreate = historyRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = historyRepository.findAll().collectList().block().size();
        // set the field null
        history.setTitle(null);

        // Create the History, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = historyRepository.findAll().collectList().block().size();
        // set the field null
        history.setCreator(null);

        // Create the History, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllHistoriesAsStream() {
        // Initialize the database
        historyRepository.save(history).block();

        List<History> historyList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(History.class)
            .getResponseBody()
            .filter(history::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(historyList).isNotNull();
        assertThat(historyList).hasSize(1);
        History testHistory = historyList.get(0);
        assertThat(testHistory.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testHistory.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHistory.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testHistory.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void getAllHistories() {
        // Initialize the database
        historyRepository.save(history).block();

        // Get all the historyList
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
            .value(hasItem(history.getId().intValue()))
            .jsonPath("$.[*].title")
            .value(hasItem(DEFAULT_TITLE))
            .jsonPath("$.[*].creator")
            .value(hasItem(DEFAULT_CREATOR))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].copyright")
            .value(hasItem(DEFAULT_COPYRIGHT))
            .jsonPath("$.[*].watchLink")
            .value(hasItem(DEFAULT_WATCH_LINK));
    }

    @Test
    void getHistory() {
        // Initialize the database
        historyRepository.save(history).block();

        // Get the history
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, history.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(history.getId().intValue()))
            .jsonPath("$.title")
            .value(is(DEFAULT_TITLE))
            .jsonPath("$.creator")
            .value(is(DEFAULT_CREATOR))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.copyright")
            .value(is(DEFAULT_COPYRIGHT))
            .jsonPath("$.watchLink")
            .value(is(DEFAULT_WATCH_LINK));
    }

    @Test
    void getNonExistingHistory() {
        // Get the history
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingHistory() throws Exception {
        // Initialize the database
        historyRepository.save(history).block();

        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();

        // Update the history
        History updatedHistory = historyRepository.findById(history.getId()).block();
        updatedHistory
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedHistory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
        History testHistory = historyList.get(historyList.size() - 1);
        assertThat(testHistory.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testHistory.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testHistory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHistory.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testHistory.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void putNonExistingHistory() throws Exception {
        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();
        history.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, history.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchHistory() throws Exception {
        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();
        history.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamHistory() throws Exception {
        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();
        history.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateHistoryWithPatch() throws Exception {
        // Initialize the database
        historyRepository.save(history).block();

        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();

        // Update the history using partial update
        History partialUpdatedHistory = new History();
        partialUpdatedHistory.setId(history.getId());

        partialUpdatedHistory.creator(UPDATED_CREATOR).watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
        History testHistory = historyList.get(historyList.size() - 1);
        assertThat(testHistory.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testHistory.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHistory.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testHistory.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void fullUpdateHistoryWithPatch() throws Exception {
        // Initialize the database
        historyRepository.save(history).block();

        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();

        // Update the history using partial update
        History partialUpdatedHistory = new History();
        partialUpdatedHistory.setId(history.getId());

        partialUpdatedHistory
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
        History testHistory = historyList.get(historyList.size() - 1);
        assertThat(testHistory.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testHistory.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testHistory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHistory.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testHistory.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void patchNonExistingHistory() throws Exception {
        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();
        history.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, history.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchHistory() throws Exception {
        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();
        history.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamHistory() throws Exception {
        int databaseSizeBeforeUpdate = historyRepository.findAll().collectList().block().size();
        history.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(history))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the History in the database
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteHistory() {
        // Initialize the database
        historyRepository.save(history).block();

        int databaseSizeBeforeDelete = historyRepository.findAll().collectList().block().size();

        // Delete the history
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, history.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<History> historyList = historyRepository.findAll().collectList().block();
        assertThat(historyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
