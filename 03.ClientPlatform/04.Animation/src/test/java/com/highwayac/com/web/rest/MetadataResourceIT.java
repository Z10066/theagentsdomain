package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Metadata;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.MetadataRepository;
import com.highwayac.com.service.dto.MetadataDTO;
import com.highwayac.com.service.mapper.MetadataMapper;
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
 * Integration tests for the {@link MetadataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class MetadataResourceIT {

    private static final String DEFAULT_AUDIENCE_FEEDBACK = "AAAAAAAAAA";
    private static final String UPDATED_AUDIENCE_FEEDBACK = "BBBBBBBBBB";

    private static final String DEFAULT_RELATED_VIDEOS = "AAAAAAAAAA";
    private static final String UPDATED_RELATED_VIDEOS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/metadata";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private MetadataMapper metadataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Metadata metadata;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metadata createEntity(EntityManager em) {
        Metadata metadata = new Metadata().audienceFeedback(DEFAULT_AUDIENCE_FEEDBACK).relatedVideos(DEFAULT_RELATED_VIDEOS);
        return metadata;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metadata createUpdatedEntity(EntityManager em) {
        Metadata metadata = new Metadata().audienceFeedback(UPDATED_AUDIENCE_FEEDBACK).relatedVideos(UPDATED_RELATED_VIDEOS);
        return metadata;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Metadata.class).block();
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
        metadata = createEntity(em);
    }

    @Test
    void createMetadata() throws Exception {
        int databaseSizeBeforeCreate = metadataRepository.findAll().collectList().block().size();
        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeCreate + 1);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getAudienceFeedback()).isEqualTo(DEFAULT_AUDIENCE_FEEDBACK);
        assertThat(testMetadata.getRelatedVideos()).isEqualTo(DEFAULT_RELATED_VIDEOS);
    }

    @Test
    void createMetadataWithExistingId() throws Exception {
        // Create the Metadata with an existing ID
        metadata.setId(1L);
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        int databaseSizeBeforeCreate = metadataRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllMetadata() {
        // Initialize the database
        metadataRepository.save(metadata).block();

        // Get all the metadataList
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
            .value(hasItem(metadata.getId().intValue()))
            .jsonPath("$.[*].audienceFeedback")
            .value(hasItem(DEFAULT_AUDIENCE_FEEDBACK.toString()))
            .jsonPath("$.[*].relatedVideos")
            .value(hasItem(DEFAULT_RELATED_VIDEOS));
    }

    @Test
    void getMetadata() {
        // Initialize the database
        metadataRepository.save(metadata).block();

        // Get the metadata
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, metadata.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(metadata.getId().intValue()))
            .jsonPath("$.audienceFeedback")
            .value(is(DEFAULT_AUDIENCE_FEEDBACK.toString()))
            .jsonPath("$.relatedVideos")
            .value(is(DEFAULT_RELATED_VIDEOS));
    }

    @Test
    void getNonExistingMetadata() {
        // Get the metadata
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingMetadata() throws Exception {
        // Initialize the database
        metadataRepository.save(metadata).block();

        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();

        // Update the metadata
        Metadata updatedMetadata = metadataRepository.findById(metadata.getId()).block();
        updatedMetadata.audienceFeedback(UPDATED_AUDIENCE_FEEDBACK).relatedVideos(UPDATED_RELATED_VIDEOS);
        MetadataDTO metadataDTO = metadataMapper.toDto(updatedMetadata);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, metadataDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getAudienceFeedback()).isEqualTo(UPDATED_AUDIENCE_FEEDBACK);
        assertThat(testMetadata.getRelatedVideos()).isEqualTo(UPDATED_RELATED_VIDEOS);
    }

    @Test
    void putNonExistingMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();
        metadata.setId(longCount.incrementAndGet());

        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, metadataDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();
        metadata.setId(longCount.incrementAndGet());

        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();
        metadata.setId(longCount.incrementAndGet());

        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMetadataWithPatch() throws Exception {
        // Initialize the database
        metadataRepository.save(metadata).block();

        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();

        // Update the metadata using partial update
        Metadata partialUpdatedMetadata = new Metadata();
        partialUpdatedMetadata.setId(metadata.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMetadata.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMetadata))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getAudienceFeedback()).isEqualTo(DEFAULT_AUDIENCE_FEEDBACK);
        assertThat(testMetadata.getRelatedVideos()).isEqualTo(DEFAULT_RELATED_VIDEOS);
    }

    @Test
    void fullUpdateMetadataWithPatch() throws Exception {
        // Initialize the database
        metadataRepository.save(metadata).block();

        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();

        // Update the metadata using partial update
        Metadata partialUpdatedMetadata = new Metadata();
        partialUpdatedMetadata.setId(metadata.getId());

        partialUpdatedMetadata.audienceFeedback(UPDATED_AUDIENCE_FEEDBACK).relatedVideos(UPDATED_RELATED_VIDEOS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMetadata.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMetadata))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getAudienceFeedback()).isEqualTo(UPDATED_AUDIENCE_FEEDBACK);
        assertThat(testMetadata.getRelatedVideos()).isEqualTo(UPDATED_RELATED_VIDEOS);
    }

    @Test
    void patchNonExistingMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();
        metadata.setId(longCount.incrementAndGet());

        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, metadataDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();
        metadata.setId(longCount.incrementAndGet());

        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().collectList().block().size();
        metadata.setId(longCount.incrementAndGet());

        // Create the Metadata
        MetadataDTO metadataDTO = metadataMapper.toDto(metadata);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(metadataDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMetadata() {
        // Initialize the database
        metadataRepository.save(metadata).block();

        int databaseSizeBeforeDelete = metadataRepository.findAll().collectList().block().size();

        // Delete the metadata
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, metadata.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Metadata> metadataList = metadataRepository.findAll().collectList().block();
        assertThat(metadataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
