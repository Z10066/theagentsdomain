package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.YouTubeVideo;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.YouTubeVideoRepository;
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
 * Integration tests for the {@link YouTubeVideoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class YouTubeVideoResourceIT {

    private static final String DEFAULT_WORKSPACE = "AAAAAAAAAA";
    private static final String UPDATED_WORKSPACE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_THEME = "AAAAAAAAAA";
    private static final String UPDATED_THEME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_MUSIC = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_MUSIC = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEO_TIME = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEOLANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_VIDEOLANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBTITLES = "AAAAAAAAAA";
    private static final String UPDATED_SUBTITLES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/you-tube-videos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private YouTubeVideoRepository youTubeVideoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private YouTubeVideo youTubeVideo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static YouTubeVideo createEntity(EntityManager em) {
        YouTubeVideo youTubeVideo = new YouTubeVideo()
            .workspace(DEFAULT_WORKSPACE)
            .creator(DEFAULT_CREATOR)
            .theme(DEFAULT_THEME)
            .content(DEFAULT_CONTENT)
            .backgroundMusic(DEFAULT_BACKGROUND_MUSIC)
            .videoTime(DEFAULT_VIDEO_TIME)
            .gender(DEFAULT_GENDER)
            .videolanguage(DEFAULT_VIDEOLANGUAGE)
            .subtitles(DEFAULT_SUBTITLES);
        return youTubeVideo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static YouTubeVideo createUpdatedEntity(EntityManager em) {
        YouTubeVideo youTubeVideo = new YouTubeVideo()
            .workspace(UPDATED_WORKSPACE)
            .creator(UPDATED_CREATOR)
            .theme(UPDATED_THEME)
            .content(UPDATED_CONTENT)
            .backgroundMusic(UPDATED_BACKGROUND_MUSIC)
            .videoTime(UPDATED_VIDEO_TIME)
            .gender(UPDATED_GENDER)
            .videolanguage(UPDATED_VIDEOLANGUAGE)
            .subtitles(UPDATED_SUBTITLES);
        return youTubeVideo;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(YouTubeVideo.class).block();
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
        youTubeVideo = createEntity(em);
    }

    @Test
    void createYouTubeVideo() throws Exception {
        int databaseSizeBeforeCreate = youTubeVideoRepository.findAll().collectList().block().size();
        // Create the YouTubeVideo
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeCreate + 1);
        YouTubeVideo testYouTubeVideo = youTubeVideoList.get(youTubeVideoList.size() - 1);
        assertThat(testYouTubeVideo.getWorkspace()).isEqualTo(DEFAULT_WORKSPACE);
        assertThat(testYouTubeVideo.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testYouTubeVideo.getTheme()).isEqualTo(DEFAULT_THEME);
        assertThat(testYouTubeVideo.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testYouTubeVideo.getBackgroundMusic()).isEqualTo(DEFAULT_BACKGROUND_MUSIC);
    }

    @Test
    void createYouTubeVideoWithExistingId() throws Exception {
        // Create the YouTubeVideo with an existing ID
        youTubeVideo.setId(1L);

        int databaseSizeBeforeCreate = youTubeVideoRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkWorkspaceIsRequired() throws Exception {
        int databaseSizeBeforeTest = youTubeVideoRepository.findAll().collectList().block().size();
        // set the field null
        youTubeVideo.setWorkspace(null);

        // Create the YouTubeVideo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = youTubeVideoRepository.findAll().collectList().block().size();
        // set the field null
        youTubeVideo.setCreator(null);

        // Create the YouTubeVideo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkThemeIsRequired() throws Exception {
        int databaseSizeBeforeTest = youTubeVideoRepository.findAll().collectList().block().size();
        // set the field null
        youTubeVideo.setTheme(null);

        // Create the YouTubeVideo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBackgroundMusicIsRequired() throws Exception {
        int databaseSizeBeforeTest = youTubeVideoRepository.findAll().collectList().block().size();
        // set the field null
        youTubeVideo.setBackgroundMusic(null);

        // Create the YouTubeVideo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllYouTubeVideosAsStream() {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        List<YouTubeVideo> youTubeVideoList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(YouTubeVideo.class)
            .getResponseBody()
            .filter(youTubeVideo::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(youTubeVideoList).isNotNull();
        assertThat(youTubeVideoList).hasSize(1);
        YouTubeVideo testYouTubeVideo = youTubeVideoList.get(0);
        assertThat(testYouTubeVideo.getWorkspace()).isEqualTo(DEFAULT_WORKSPACE);
        assertThat(testYouTubeVideo.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testYouTubeVideo.getTheme()).isEqualTo(DEFAULT_THEME);
        assertThat(testYouTubeVideo.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testYouTubeVideo.getBackgroundMusic()).isEqualTo(DEFAULT_BACKGROUND_MUSIC);
    }

    @Test
    void getAllYouTubeVideos() {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        // Get all the youTubeVideoList
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
            .value(hasItem(youTubeVideo.getId().intValue()))
            .jsonPath("$.[*].workspace")
            .value(hasItem(DEFAULT_WORKSPACE))
            .jsonPath("$.[*].creator")
            .value(hasItem(DEFAULT_CREATOR))
            .jsonPath("$.[*].theme")
            .value(hasItem(DEFAULT_THEME))
            .jsonPath("$.[*].content")
            .value(hasItem(DEFAULT_CONTENT.toString()))
            .jsonPath("$.[*].backgroundMusic")
            .value(hasItem(DEFAULT_BACKGROUND_MUSIC))
            .jsonPath("$.[*].videoTime")
            .value(hasItem(DEFAULT_VIDEO_TIME))
            .jsonPath("$.[*].gender")
            .value(hasItem(DEFAULT_GENDER))
            .jsonPath("$.[*].videolanguage")
            .value(hasItem(DEFAULT_VIDEOLANGUAGE))
            .jsonPath("$.[*].subtitles")
            .value(hasItem(DEFAULT_SUBTITLES));
    }

    @Test
    void getYouTubeVideo() {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        // Get the youTubeVideo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, youTubeVideo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(youTubeVideo.getId().intValue()))
            .jsonPath("$.workspace")
            .value(is(DEFAULT_WORKSPACE))
            .jsonPath("$.creator")
            .value(is(DEFAULT_CREATOR))
            .jsonPath("$.theme")
            .value(is(DEFAULT_THEME))
            .jsonPath("$.content")
            .value(is(DEFAULT_CONTENT.toString()))
            .jsonPath("$.backgroundMusic")
            .value(is(DEFAULT_BACKGROUND_MUSIC))
            .jsonPath("$.videoTime")
            .value(is(DEFAULT_VIDEO_TIME))
            .jsonPath("$.gender")
            .value(is(DEFAULT_GENDER))
            .jsonPath("$.videolanguage")
            .value(is(DEFAULT_VIDEOLANGUAGE))
            .jsonPath("$.subtitles")
            .value(is(DEFAULT_SUBTITLES));
    }

    @Test
    void getNonExistingYouTubeVideo() {
        // Get the youTubeVideo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingYouTubeVideo() throws Exception {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();

        // Update the youTubeVideo
        YouTubeVideo updatedYouTubeVideo = youTubeVideoRepository.findById(youTubeVideo.getId()).block();
        updatedYouTubeVideo
            .workspace(UPDATED_WORKSPACE)
            .creator(UPDATED_CREATOR)
            .theme(UPDATED_THEME)
            .content(UPDATED_CONTENT)
            .backgroundMusic(UPDATED_BACKGROUND_MUSIC)
            .videoTime(UPDATED_VIDEO_TIME)
            .gender(UPDATED_GENDER)
            .videolanguage(UPDATED_VIDEOLANGUAGE)
            .subtitles(UPDATED_SUBTITLES);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedYouTubeVideo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedYouTubeVideo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
        YouTubeVideo testYouTubeVideo = youTubeVideoList.get(youTubeVideoList.size() - 1);
        assertThat(testYouTubeVideo.getWorkspace()).isEqualTo(UPDATED_WORKSPACE);
        assertThat(testYouTubeVideo.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testYouTubeVideo.getTheme()).isEqualTo(UPDATED_THEME);
        assertThat(testYouTubeVideo.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testYouTubeVideo.getBackgroundMusic()).isEqualTo(UPDATED_BACKGROUND_MUSIC);
    }

    @Test
    void putNonExistingYouTubeVideo() throws Exception {
        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();
        youTubeVideo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, youTubeVideo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchYouTubeVideo() throws Exception {
        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();
        youTubeVideo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamYouTubeVideo() throws Exception {
        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();
        youTubeVideo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateYouTubeVideoWithPatch() throws Exception {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();

        // Update the youTubeVideo using partial update
        YouTubeVideo partialUpdatedYouTubeVideo = new YouTubeVideo();
        partialUpdatedYouTubeVideo.setId(youTubeVideo.getId());

        partialUpdatedYouTubeVideo.backgroundMusic(UPDATED_BACKGROUND_MUSIC);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedYouTubeVideo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedYouTubeVideo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
        YouTubeVideo testYouTubeVideo = youTubeVideoList.get(youTubeVideoList.size() - 1);
        assertThat(testYouTubeVideo.getWorkspace()).isEqualTo(DEFAULT_WORKSPACE);
        assertThat(testYouTubeVideo.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testYouTubeVideo.getTheme()).isEqualTo(DEFAULT_THEME);
        assertThat(testYouTubeVideo.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testYouTubeVideo.getBackgroundMusic()).isEqualTo(UPDATED_BACKGROUND_MUSIC);
    }

    @Test
    void fullUpdateYouTubeVideoWithPatch() throws Exception {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();

        // Update the youTubeVideo using partial update
        YouTubeVideo partialUpdatedYouTubeVideo = new YouTubeVideo();
        partialUpdatedYouTubeVideo.setId(youTubeVideo.getId());

        partialUpdatedYouTubeVideo
            .workspace(UPDATED_WORKSPACE)
            .creator(UPDATED_CREATOR)
            .theme(UPDATED_THEME)
            .content(UPDATED_CONTENT)
            .backgroundMusic(UPDATED_BACKGROUND_MUSIC)
            .videoTime(UPDATED_VIDEO_TIME)
            .gender(UPDATED_GENDER)
            .videolanguage(UPDATED_VIDEOLANGUAGE)
            .subtitles(UPDATED_SUBTITLES);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedYouTubeVideo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedYouTubeVideo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
        YouTubeVideo testYouTubeVideo = youTubeVideoList.get(youTubeVideoList.size() - 1);
        assertThat(testYouTubeVideo.getWorkspace()).isEqualTo(UPDATED_WORKSPACE);
        assertThat(testYouTubeVideo.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testYouTubeVideo.getTheme()).isEqualTo(UPDATED_THEME);
        assertThat(testYouTubeVideo.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testYouTubeVideo.getBackgroundMusic()).isEqualTo(UPDATED_BACKGROUND_MUSIC);
    }

    @Test
    void patchNonExistingYouTubeVideo() throws Exception {
        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();
        youTubeVideo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, youTubeVideo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchYouTubeVideo() throws Exception {
        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();
        youTubeVideo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamYouTubeVideo() throws Exception {
        int databaseSizeBeforeUpdate = youTubeVideoRepository.findAll().collectList().block().size();
        youTubeVideo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(youTubeVideo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the YouTubeVideo in the database
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteYouTubeVideo() {
        // Initialize the database
        youTubeVideoRepository.save(youTubeVideo).block();

        int databaseSizeBeforeDelete = youTubeVideoRepository.findAll().collectList().block().size();

        // Delete the youTubeVideo
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, youTubeVideo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<YouTubeVideo> youTubeVideoList = youTubeVideoRepository.findAll().collectList().block();
        assertThat(youTubeVideoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
