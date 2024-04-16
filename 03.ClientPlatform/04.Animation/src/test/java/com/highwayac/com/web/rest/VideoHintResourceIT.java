package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.VideoHint;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.VideoHintRepository;
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
 * Integration tests for the {@link VideoHintResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class VideoHintResourceIT {

    private static final String DEFAULT_WORKSPACE = "AAAAAAAAAA";
    private static final String UPDATED_WORKSPACE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_CREATION_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CREATION_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_MUSIC = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_MUSIC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/video-hints";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VideoHintRepository videoHintRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private VideoHint videoHint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoHint createEntity(EntityManager em) {
        VideoHint videoHint = new VideoHint()
            .workspace(DEFAULT_WORKSPACE)
            .creator(DEFAULT_CREATOR)
            .creationContent(DEFAULT_CREATION_CONTENT)
            .backgroundMusic(DEFAULT_BACKGROUND_MUSIC);
        return videoHint;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoHint createUpdatedEntity(EntityManager em) {
        VideoHint videoHint = new VideoHint()
            .workspace(UPDATED_WORKSPACE)
            .creator(UPDATED_CREATOR)
            .creationContent(UPDATED_CREATION_CONTENT)
            .backgroundMusic(UPDATED_BACKGROUND_MUSIC);
        return videoHint;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(VideoHint.class).block();
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
        videoHint = createEntity(em);
    }

    @Test
    void createVideoHint() throws Exception {
        int databaseSizeBeforeCreate = videoHintRepository.findAll().collectList().block().size();
        // Create the VideoHint
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeCreate + 1);
        VideoHint testVideoHint = videoHintList.get(videoHintList.size() - 1);
        assertThat(testVideoHint.getWorkspace()).isEqualTo(DEFAULT_WORKSPACE);
        assertThat(testVideoHint.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVideoHint.getCreationContent()).isEqualTo(DEFAULT_CREATION_CONTENT);
        assertThat(testVideoHint.getBackgroundMusic()).isEqualTo(DEFAULT_BACKGROUND_MUSIC);
    }

    @Test
    void createVideoHintWithExistingId() throws Exception {
        // Create the VideoHint with an existing ID
        videoHint.setId(1L);

        int databaseSizeBeforeCreate = videoHintRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkWorkspaceIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoHintRepository.findAll().collectList().block().size();
        // set the field null
        videoHint.setWorkspace(null);

        // Create the VideoHint, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoHintRepository.findAll().collectList().block().size();
        // set the field null
        videoHint.setCreator(null);

        // Create the VideoHint, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBackgroundMusicIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoHintRepository.findAll().collectList().block().size();
        // set the field null
        videoHint.setBackgroundMusic(null);

        // Create the VideoHint, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllVideoHintsAsStream() {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        List<VideoHint> videoHintList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(VideoHint.class)
            .getResponseBody()
            .filter(videoHint::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(videoHintList).isNotNull();
        assertThat(videoHintList).hasSize(1);
        VideoHint testVideoHint = videoHintList.get(0);
        assertThat(testVideoHint.getWorkspace()).isEqualTo(DEFAULT_WORKSPACE);
        assertThat(testVideoHint.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVideoHint.getCreationContent()).isEqualTo(DEFAULT_CREATION_CONTENT);
        assertThat(testVideoHint.getBackgroundMusic()).isEqualTo(DEFAULT_BACKGROUND_MUSIC);
    }

    @Test
    void getAllVideoHints() {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        // Get all the videoHintList
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
            .value(hasItem(videoHint.getId().intValue()))
            .jsonPath("$.[*].workspace")
            .value(hasItem(DEFAULT_WORKSPACE))
            .jsonPath("$.[*].creator")
            .value(hasItem(DEFAULT_CREATOR))
            .jsonPath("$.[*].creationContent")
            .value(hasItem(DEFAULT_CREATION_CONTENT.toString()))
            .jsonPath("$.[*].backgroundMusic")
            .value(hasItem(DEFAULT_BACKGROUND_MUSIC));
    }

    @Test
    void getVideoHint() {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        // Get the videoHint
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, videoHint.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(videoHint.getId().intValue()))
            .jsonPath("$.workspace")
            .value(is(DEFAULT_WORKSPACE))
            .jsonPath("$.creator")
            .value(is(DEFAULT_CREATOR))
            .jsonPath("$.creationContent")
            .value(is(DEFAULT_CREATION_CONTENT.toString()))
            .jsonPath("$.backgroundMusic")
            .value(is(DEFAULT_BACKGROUND_MUSIC));
    }

    @Test
    void getNonExistingVideoHint() {
        // Get the videoHint
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingVideoHint() throws Exception {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();

        // Update the videoHint
        VideoHint updatedVideoHint = videoHintRepository.findById(videoHint.getId()).block();
        updatedVideoHint
            .workspace(UPDATED_WORKSPACE)
            .creator(UPDATED_CREATOR)
            .creationContent(UPDATED_CREATION_CONTENT)
            .backgroundMusic(UPDATED_BACKGROUND_MUSIC);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedVideoHint.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedVideoHint))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
        VideoHint testVideoHint = videoHintList.get(videoHintList.size() - 1);
        assertThat(testVideoHint.getWorkspace()).isEqualTo(UPDATED_WORKSPACE);
        assertThat(testVideoHint.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testVideoHint.getCreationContent()).isEqualTo(UPDATED_CREATION_CONTENT);
        assertThat(testVideoHint.getBackgroundMusic()).isEqualTo(UPDATED_BACKGROUND_MUSIC);
    }

    @Test
    void putNonExistingVideoHint() throws Exception {
        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();
        videoHint.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, videoHint.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchVideoHint() throws Exception {
        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();
        videoHint.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamVideoHint() throws Exception {
        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();
        videoHint.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateVideoHintWithPatch() throws Exception {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();

        // Update the videoHint using partial update
        VideoHint partialUpdatedVideoHint = new VideoHint();
        partialUpdatedVideoHint.setId(videoHint.getId());

        partialUpdatedVideoHint.workspace(UPDATED_WORKSPACE).creationContent(UPDATED_CREATION_CONTENT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVideoHint.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoHint))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
        VideoHint testVideoHint = videoHintList.get(videoHintList.size() - 1);
        assertThat(testVideoHint.getWorkspace()).isEqualTo(UPDATED_WORKSPACE);
        assertThat(testVideoHint.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVideoHint.getCreationContent()).isEqualTo(UPDATED_CREATION_CONTENT);
        assertThat(testVideoHint.getBackgroundMusic()).isEqualTo(DEFAULT_BACKGROUND_MUSIC);
    }

    @Test
    void fullUpdateVideoHintWithPatch() throws Exception {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();

        // Update the videoHint using partial update
        VideoHint partialUpdatedVideoHint = new VideoHint();
        partialUpdatedVideoHint.setId(videoHint.getId());

        partialUpdatedVideoHint
            .workspace(UPDATED_WORKSPACE)
            .creator(UPDATED_CREATOR)
            .creationContent(UPDATED_CREATION_CONTENT)
            .backgroundMusic(UPDATED_BACKGROUND_MUSIC);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVideoHint.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoHint))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
        VideoHint testVideoHint = videoHintList.get(videoHintList.size() - 1);
        assertThat(testVideoHint.getWorkspace()).isEqualTo(UPDATED_WORKSPACE);
        assertThat(testVideoHint.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testVideoHint.getCreationContent()).isEqualTo(UPDATED_CREATION_CONTENT);
        assertThat(testVideoHint.getBackgroundMusic()).isEqualTo(UPDATED_BACKGROUND_MUSIC);
    }

    @Test
    void patchNonExistingVideoHint() throws Exception {
        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();
        videoHint.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, videoHint.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchVideoHint() throws Exception {
        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();
        videoHint.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamVideoHint() throws Exception {
        int databaseSizeBeforeUpdate = videoHintRepository.findAll().collectList().block().size();
        videoHint.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoHint))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the VideoHint in the database
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteVideoHint() {
        // Initialize the database
        videoHintRepository.save(videoHint).block();

        int databaseSizeBeforeDelete = videoHintRepository.findAll().collectList().block().size();

        // Delete the videoHint
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, videoHint.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<VideoHint> videoHintList = videoHintRepository.findAll().collectList().block();
        assertThat(videoHintList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
