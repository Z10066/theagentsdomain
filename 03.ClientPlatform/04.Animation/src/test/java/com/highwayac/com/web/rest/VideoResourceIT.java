package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Category;
import com.highwayac.com.domain.Copyright;
import com.highwayac.com.domain.Creator;
import com.highwayac.com.domain.Video;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.VideoRepository;
import com.highwayac.com.service.VideoService;
import com.highwayac.com.service.dto.VideoDTO;
import com.highwayac.com.service.mapper.VideoMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/**
 * Integration tests for the {@link VideoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class VideoResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_DURATION = "BBBBBBBBBB";

    private static final String DEFAULT_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_FORMAT = "BBBBBBBBBB";

    private static final String DEFAULT_RESOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_RESOLUTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PUBLISHED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PUBLISHED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VIEWING_LINK = "AAAAAAAAAA";
    private static final String UPDATED_VIEWING_LINK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/videos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VideoRepository videoRepository;

    @Mock
    private VideoRepository videoRepositoryMock;

    @Autowired
    private VideoMapper videoMapper;

    @Mock
    private VideoService videoServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Video video;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Video createEntity(EntityManager em) {
        Video video = new Video()
            .title(DEFAULT_TITLE)
            .duration(DEFAULT_DURATION)
            .format(DEFAULT_FORMAT)
            .resolution(DEFAULT_RESOLUTION)
            .publishedDate(DEFAULT_PUBLISHED_DATE)
            .description(DEFAULT_DESCRIPTION)
            .viewingLink(DEFAULT_VIEWING_LINK);
        // Add required entity
        Creator creator;
        creator = em.insert(CreatorResourceIT.createEntity(em)).block();
        video.setCreator(creator);
        // Add required entity
        Category category;
        category = em.insert(CategoryResourceIT.createEntity(em)).block();
        video.setCategory(category);
        // Add required entity
        Copyright copyright;
        copyright = em.insert(CopyrightResourceIT.createEntity(em)).block();
        video.setCopyright(copyright);
        return video;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Video createUpdatedEntity(EntityManager em) {
        Video video = new Video()
            .title(UPDATED_TITLE)
            .duration(UPDATED_DURATION)
            .format(UPDATED_FORMAT)
            .resolution(UPDATED_RESOLUTION)
            .publishedDate(UPDATED_PUBLISHED_DATE)
            .description(UPDATED_DESCRIPTION)
            .viewingLink(UPDATED_VIEWING_LINK);
        // Add required entity
        Creator creator;
        creator = em.insert(CreatorResourceIT.createUpdatedEntity(em)).block();
        video.setCreator(creator);
        // Add required entity
        Category category;
        category = em.insert(CategoryResourceIT.createUpdatedEntity(em)).block();
        video.setCategory(category);
        // Add required entity
        Copyright copyright;
        copyright = em.insert(CopyrightResourceIT.createUpdatedEntity(em)).block();
        video.setCopyright(copyright);
        return video;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll("rel_video__keyword").block();
            em.deleteAll(Video.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
        CreatorResourceIT.deleteEntities(em);
        CategoryResourceIT.deleteEntities(em);
        CopyrightResourceIT.deleteEntities(em);
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
        video = createEntity(em);
    }

    @Test
    void createVideo() throws Exception {
        int databaseSizeBeforeCreate = videoRepository.findAll().collectList().block().size();
        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeCreate + 1);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVideo.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testVideo.getFormat()).isEqualTo(DEFAULT_FORMAT);
        assertThat(testVideo.getResolution()).isEqualTo(DEFAULT_RESOLUTION);
        assertThat(testVideo.getPublishedDate()).isEqualTo(DEFAULT_PUBLISHED_DATE);
        assertThat(testVideo.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVideo.getViewingLink()).isEqualTo(DEFAULT_VIEWING_LINK);
    }

    @Test
    void createVideoWithExistingId() throws Exception {
        // Create the Video with an existing ID
        video.setId(1L);
        VideoDTO videoDTO = videoMapper.toDto(video);

        int databaseSizeBeforeCreate = videoRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().collectList().block().size();
        // set the field null
        video.setTitle(null);

        // Create the Video, which fails.
        VideoDTO videoDTO = videoMapper.toDto(video);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllVideos() {
        // Initialize the database
        videoRepository.save(video).block();

        // Get all the videoList
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
            .value(hasItem(video.getId().intValue()))
            .jsonPath("$.[*].title")
            .value(hasItem(DEFAULT_TITLE))
            .jsonPath("$.[*].duration")
            .value(hasItem(DEFAULT_DURATION))
            .jsonPath("$.[*].format")
            .value(hasItem(DEFAULT_FORMAT))
            .jsonPath("$.[*].resolution")
            .value(hasItem(DEFAULT_RESOLUTION))
            .jsonPath("$.[*].publishedDate")
            .value(hasItem(DEFAULT_PUBLISHED_DATE.toString()))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION.toString()))
            .jsonPath("$.[*].viewingLink")
            .value(hasItem(DEFAULT_VIEWING_LINK));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllVideosWithEagerRelationshipsIsEnabled() {
        when(videoServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(videoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllVideosWithEagerRelationshipsIsNotEnabled() {
        when(videoServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(videoRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getVideo() {
        // Initialize the database
        videoRepository.save(video).block();

        // Get the video
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, video.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(video.getId().intValue()))
            .jsonPath("$.title")
            .value(is(DEFAULT_TITLE))
            .jsonPath("$.duration")
            .value(is(DEFAULT_DURATION))
            .jsonPath("$.format")
            .value(is(DEFAULT_FORMAT))
            .jsonPath("$.resolution")
            .value(is(DEFAULT_RESOLUTION))
            .jsonPath("$.publishedDate")
            .value(is(DEFAULT_PUBLISHED_DATE.toString()))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION.toString()))
            .jsonPath("$.viewingLink")
            .value(is(DEFAULT_VIEWING_LINK));
    }

    @Test
    void getNonExistingVideo() {
        // Get the video
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingVideo() throws Exception {
        // Initialize the database
        videoRepository.save(video).block();

        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();

        // Update the video
        Video updatedVideo = videoRepository.findById(video.getId()).block();
        updatedVideo
            .title(UPDATED_TITLE)
            .duration(UPDATED_DURATION)
            .format(UPDATED_FORMAT)
            .resolution(UPDATED_RESOLUTION)
            .publishedDate(UPDATED_PUBLISHED_DATE)
            .description(UPDATED_DESCRIPTION)
            .viewingLink(UPDATED_VIEWING_LINK);
        VideoDTO videoDTO = videoMapper.toDto(updatedVideo);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, videoDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVideo.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testVideo.getFormat()).isEqualTo(UPDATED_FORMAT);
        assertThat(testVideo.getResolution()).isEqualTo(UPDATED_RESOLUTION);
        assertThat(testVideo.getPublishedDate()).isEqualTo(UPDATED_PUBLISHED_DATE);
        assertThat(testVideo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVideo.getViewingLink()).isEqualTo(UPDATED_VIEWING_LINK);
    }

    @Test
    void putNonExistingVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();
        video.setId(longCount.incrementAndGet());

        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, videoDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();
        video.setId(longCount.incrementAndGet());

        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();
        video.setId(longCount.incrementAndGet());

        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateVideoWithPatch() throws Exception {
        // Initialize the database
        videoRepository.save(video).block();

        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();

        // Update the video using partial update
        Video partialUpdatedVideo = new Video();
        partialUpdatedVideo.setId(video.getId());

        partialUpdatedVideo.duration(UPDATED_DURATION).publishedDate(UPDATED_PUBLISHED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVideo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVideo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVideo.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testVideo.getFormat()).isEqualTo(DEFAULT_FORMAT);
        assertThat(testVideo.getResolution()).isEqualTo(DEFAULT_RESOLUTION);
        assertThat(testVideo.getPublishedDate()).isEqualTo(UPDATED_PUBLISHED_DATE);
        assertThat(testVideo.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVideo.getViewingLink()).isEqualTo(DEFAULT_VIEWING_LINK);
    }

    @Test
    void fullUpdateVideoWithPatch() throws Exception {
        // Initialize the database
        videoRepository.save(video).block();

        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();

        // Update the video using partial update
        Video partialUpdatedVideo = new Video();
        partialUpdatedVideo.setId(video.getId());

        partialUpdatedVideo
            .title(UPDATED_TITLE)
            .duration(UPDATED_DURATION)
            .format(UPDATED_FORMAT)
            .resolution(UPDATED_RESOLUTION)
            .publishedDate(UPDATED_PUBLISHED_DATE)
            .description(UPDATED_DESCRIPTION)
            .viewingLink(UPDATED_VIEWING_LINK);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVideo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVideo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVideo.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testVideo.getFormat()).isEqualTo(UPDATED_FORMAT);
        assertThat(testVideo.getResolution()).isEqualTo(UPDATED_RESOLUTION);
        assertThat(testVideo.getPublishedDate()).isEqualTo(UPDATED_PUBLISHED_DATE);
        assertThat(testVideo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVideo.getViewingLink()).isEqualTo(UPDATED_VIEWING_LINK);
    }

    @Test
    void patchNonExistingVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();
        video.setId(longCount.incrementAndGet());

        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, videoDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();
        video.setId(longCount.incrementAndGet());

        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().collectList().block().size();
        video.setId(longCount.incrementAndGet());

        // Create the Video
        VideoDTO videoDTO = videoMapper.toDto(video);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(videoDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteVideo() {
        // Initialize the database
        videoRepository.save(video).block();

        int databaseSizeBeforeDelete = videoRepository.findAll().collectList().block().size();

        // Delete the video
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, video.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Video> videoList = videoRepository.findAll().collectList().block();
        assertThat(videoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
