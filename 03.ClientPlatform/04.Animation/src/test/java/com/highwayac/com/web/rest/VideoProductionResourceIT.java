package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.VideoProduction;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.VideoProductionRepository;
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
 * Integration tests for the {@link VideoProductionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class VideoProductionResourceIT {

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

    private static final String ENTITY_API_URL = "/api/video-productions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VideoProductionRepository videoProductionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private VideoProduction videoProduction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoProduction createEntity(EntityManager em) {
        VideoProduction videoProduction = new VideoProduction()
            .title(DEFAULT_TITLE)
            .creator(DEFAULT_CREATOR)
            .description(DEFAULT_DESCRIPTION)
            .copyright(DEFAULT_COPYRIGHT)
            .watchLink(DEFAULT_WATCH_LINK);
        return videoProduction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoProduction createUpdatedEntity(EntityManager em) {
        VideoProduction videoProduction = new VideoProduction()
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);
        return videoProduction;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(VideoProduction.class).block();
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
        videoProduction = createEntity(em);
    }

    @Test
    void createVideoProduction() throws Exception {
        int databaseSizeBeforeCreate = videoProductionRepository.findAll().collectList().block().size();
        // Create the VideoProduction
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeCreate + 1);
        VideoProduction testVideoProduction = videoProductionList.get(videoProductionList.size() - 1);
        assertThat(testVideoProduction.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVideoProduction.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVideoProduction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVideoProduction.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testVideoProduction.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void createVideoProductionWithExistingId() throws Exception {
        // Create the VideoProduction with an existing ID
        videoProduction.setId(1L);

        int databaseSizeBeforeCreate = videoProductionRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoProductionRepository.findAll().collectList().block().size();
        // set the field null
        videoProduction.setTitle(null);

        // Create the VideoProduction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoProductionRepository.findAll().collectList().block().size();
        // set the field null
        videoProduction.setCreator(null);

        // Create the VideoProduction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllVideoProductionsAsStream() {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        List<VideoProduction> videoProductionList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(VideoProduction.class)
            .getResponseBody()
            .filter(videoProduction::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(videoProductionList).isNotNull();
        assertThat(videoProductionList).hasSize(1);
        VideoProduction testVideoProduction = videoProductionList.get(0);
        assertThat(testVideoProduction.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVideoProduction.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVideoProduction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVideoProduction.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testVideoProduction.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void getAllVideoProductions() {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        // Get all the videoProductionList
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
            .value(hasItem(videoProduction.getId().intValue()))
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
    void getVideoProduction() {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        // Get the videoProduction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, videoProduction.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(videoProduction.getId().intValue()))
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
    void getNonExistingVideoProduction() {
        // Get the videoProduction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingVideoProduction() throws Exception {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();

        // Update the videoProduction
        VideoProduction updatedVideoProduction = videoProductionRepository.findById(videoProduction.getId()).block();
        updatedVideoProduction
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedVideoProduction.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedVideoProduction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
        VideoProduction testVideoProduction = videoProductionList.get(videoProductionList.size() - 1);
        assertThat(testVideoProduction.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVideoProduction.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testVideoProduction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVideoProduction.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testVideoProduction.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void putNonExistingVideoProduction() throws Exception {
        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();
        videoProduction.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, videoProduction.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchVideoProduction() throws Exception {
        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();
        videoProduction.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamVideoProduction() throws Exception {
        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();
        videoProduction.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateVideoProductionWithPatch() throws Exception {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();

        // Update the videoProduction using partial update
        VideoProduction partialUpdatedVideoProduction = new VideoProduction();
        partialUpdatedVideoProduction.setId(videoProduction.getId());

        partialUpdatedVideoProduction.description(UPDATED_DESCRIPTION).copyright(UPDATED_COPYRIGHT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVideoProduction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoProduction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
        VideoProduction testVideoProduction = videoProductionList.get(videoProductionList.size() - 1);
        assertThat(testVideoProduction.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVideoProduction.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVideoProduction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVideoProduction.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testVideoProduction.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void fullUpdateVideoProductionWithPatch() throws Exception {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();

        // Update the videoProduction using partial update
        VideoProduction partialUpdatedVideoProduction = new VideoProduction();
        partialUpdatedVideoProduction.setId(videoProduction.getId());

        partialUpdatedVideoProduction
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVideoProduction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoProduction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
        VideoProduction testVideoProduction = videoProductionList.get(videoProductionList.size() - 1);
        assertThat(testVideoProduction.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVideoProduction.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testVideoProduction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVideoProduction.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testVideoProduction.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void patchNonExistingVideoProduction() throws Exception {
        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();
        videoProduction.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, videoProduction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchVideoProduction() throws Exception {
        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();
        videoProduction.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamVideoProduction() throws Exception {
        int databaseSizeBeforeUpdate = videoProductionRepository.findAll().collectList().block().size();
        videoProduction.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoProduction))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the VideoProduction in the database
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteVideoProduction() {
        // Initialize the database
        videoProductionRepository.save(videoProduction).block();

        int databaseSizeBeforeDelete = videoProductionRepository.findAll().collectList().block().size();

        // Delete the videoProduction
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, videoProduction.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<VideoProduction> videoProductionList = videoProductionRepository.findAll().collectList().block();
        assertThat(videoProductionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
