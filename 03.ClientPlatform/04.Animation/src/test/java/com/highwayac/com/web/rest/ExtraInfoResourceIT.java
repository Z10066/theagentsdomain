package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.ExtraInfo;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.ExtraInfoRepository;
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
 * Integration tests for the {@link ExtraInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ExtraInfoResourceIT {

    private static final String DEFAULT_AUDIENCE_FEEDBACK = "AAAAAAAAAA";
    private static final String UPDATED_AUDIENCE_FEEDBACK = "BBBBBBBBBB";

    private static final String DEFAULT_RELATED_VIDEOS = "AAAAAAAAAA";
    private static final String UPDATED_RELATED_VIDEOS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/extra-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExtraInfoRepository extraInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ExtraInfo extraInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtraInfo createEntity(EntityManager em) {
        ExtraInfo extraInfo = new ExtraInfo().audienceFeedback(DEFAULT_AUDIENCE_FEEDBACK).relatedVideos(DEFAULT_RELATED_VIDEOS);
        return extraInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtraInfo createUpdatedEntity(EntityManager em) {
        ExtraInfo extraInfo = new ExtraInfo().audienceFeedback(UPDATED_AUDIENCE_FEEDBACK).relatedVideos(UPDATED_RELATED_VIDEOS);
        return extraInfo;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ExtraInfo.class).block();
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
        extraInfo = createEntity(em);
    }

    @Test
    void createExtraInfo() throws Exception {
        int databaseSizeBeforeCreate = extraInfoRepository.findAll().collectList().block().size();
        // Create the ExtraInfo
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ExtraInfo testExtraInfo = extraInfoList.get(extraInfoList.size() - 1);
        assertThat(testExtraInfo.getAudienceFeedback()).isEqualTo(DEFAULT_AUDIENCE_FEEDBACK);
        assertThat(testExtraInfo.getRelatedVideos()).isEqualTo(DEFAULT_RELATED_VIDEOS);
    }

    @Test
    void createExtraInfoWithExistingId() throws Exception {
        // Create the ExtraInfo with an existing ID
        extraInfo.setId(1L);

        int databaseSizeBeforeCreate = extraInfoRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllExtraInfosAsStream() {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        List<ExtraInfo> extraInfoList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ExtraInfo.class)
            .getResponseBody()
            .filter(extraInfo::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(extraInfoList).isNotNull();
        assertThat(extraInfoList).hasSize(1);
        ExtraInfo testExtraInfo = extraInfoList.get(0);
        assertThat(testExtraInfo.getAudienceFeedback()).isEqualTo(DEFAULT_AUDIENCE_FEEDBACK);
        assertThat(testExtraInfo.getRelatedVideos()).isEqualTo(DEFAULT_RELATED_VIDEOS);
    }

    @Test
    void getAllExtraInfos() {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        // Get all the extraInfoList
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
            .value(hasItem(extraInfo.getId().intValue()))
            .jsonPath("$.[*].audienceFeedback")
            .value(hasItem(DEFAULT_AUDIENCE_FEEDBACK))
            .jsonPath("$.[*].relatedVideos")
            .value(hasItem(DEFAULT_RELATED_VIDEOS));
    }

    @Test
    void getExtraInfo() {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        // Get the extraInfo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, extraInfo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(extraInfo.getId().intValue()))
            .jsonPath("$.audienceFeedback")
            .value(is(DEFAULT_AUDIENCE_FEEDBACK))
            .jsonPath("$.relatedVideos")
            .value(is(DEFAULT_RELATED_VIDEOS));
    }

    @Test
    void getNonExistingExtraInfo() {
        // Get the extraInfo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingExtraInfo() throws Exception {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();

        // Update the extraInfo
        ExtraInfo updatedExtraInfo = extraInfoRepository.findById(extraInfo.getId()).block();
        updatedExtraInfo.audienceFeedback(UPDATED_AUDIENCE_FEEDBACK).relatedVideos(UPDATED_RELATED_VIDEOS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedExtraInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedExtraInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
        ExtraInfo testExtraInfo = extraInfoList.get(extraInfoList.size() - 1);
        assertThat(testExtraInfo.getAudienceFeedback()).isEqualTo(UPDATED_AUDIENCE_FEEDBACK);
        assertThat(testExtraInfo.getRelatedVideos()).isEqualTo(UPDATED_RELATED_VIDEOS);
    }

    @Test
    void putNonExistingExtraInfo() throws Exception {
        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();
        extraInfo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, extraInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchExtraInfo() throws Exception {
        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();
        extraInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamExtraInfo() throws Exception {
        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();
        extraInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateExtraInfoWithPatch() throws Exception {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();

        // Update the extraInfo using partial update
        ExtraInfo partialUpdatedExtraInfo = new ExtraInfo();
        partialUpdatedExtraInfo.setId(extraInfo.getId());

        partialUpdatedExtraInfo.audienceFeedback(UPDATED_AUDIENCE_FEEDBACK);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedExtraInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedExtraInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
        ExtraInfo testExtraInfo = extraInfoList.get(extraInfoList.size() - 1);
        assertThat(testExtraInfo.getAudienceFeedback()).isEqualTo(UPDATED_AUDIENCE_FEEDBACK);
        assertThat(testExtraInfo.getRelatedVideos()).isEqualTo(DEFAULT_RELATED_VIDEOS);
    }

    @Test
    void fullUpdateExtraInfoWithPatch() throws Exception {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();

        // Update the extraInfo using partial update
        ExtraInfo partialUpdatedExtraInfo = new ExtraInfo();
        partialUpdatedExtraInfo.setId(extraInfo.getId());

        partialUpdatedExtraInfo.audienceFeedback(UPDATED_AUDIENCE_FEEDBACK).relatedVideos(UPDATED_RELATED_VIDEOS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedExtraInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedExtraInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
        ExtraInfo testExtraInfo = extraInfoList.get(extraInfoList.size() - 1);
        assertThat(testExtraInfo.getAudienceFeedback()).isEqualTo(UPDATED_AUDIENCE_FEEDBACK);
        assertThat(testExtraInfo.getRelatedVideos()).isEqualTo(UPDATED_RELATED_VIDEOS);
    }

    @Test
    void patchNonExistingExtraInfo() throws Exception {
        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();
        extraInfo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, extraInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchExtraInfo() throws Exception {
        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();
        extraInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamExtraInfo() throws Exception {
        int databaseSizeBeforeUpdate = extraInfoRepository.findAll().collectList().block().size();
        extraInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(extraInfo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ExtraInfo in the database
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteExtraInfo() {
        // Initialize the database
        extraInfoRepository.save(extraInfo).block();

        int databaseSizeBeforeDelete = extraInfoRepository.findAll().collectList().block().size();

        // Delete the extraInfo
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, extraInfo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ExtraInfo> extraInfoList = extraInfoRepository.findAll().collectList().block();
        assertThat(extraInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
