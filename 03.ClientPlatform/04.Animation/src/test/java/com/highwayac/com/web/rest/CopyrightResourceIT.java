package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Copyright;
import com.highwayac.com.repository.CopyrightRepository;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.service.dto.CopyrightDTO;
import com.highwayac.com.service.mapper.CopyrightMapper;
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
 * Integration tests for the {@link CopyrightResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CopyrightResourceIT {

    private static final String DEFAULT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_DETAILS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/copyrights";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CopyrightRepository copyrightRepository;

    @Autowired
    private CopyrightMapper copyrightMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Copyright copyright;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Copyright createEntity(EntityManager em) {
        Copyright copyright = new Copyright().details(DEFAULT_DETAILS);
        return copyright;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Copyright createUpdatedEntity(EntityManager em) {
        Copyright copyright = new Copyright().details(UPDATED_DETAILS);
        return copyright;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Copyright.class).block();
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
        copyright = createEntity(em);
    }

    @Test
    void createCopyright() throws Exception {
        int databaseSizeBeforeCreate = copyrightRepository.findAll().collectList().block().size();
        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeCreate + 1);
        Copyright testCopyright = copyrightList.get(copyrightList.size() - 1);
        assertThat(testCopyright.getDetails()).isEqualTo(DEFAULT_DETAILS);
    }

    @Test
    void createCopyrightWithExistingId() throws Exception {
        // Create the Copyright with an existing ID
        copyright.setId(1L);
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        int databaseSizeBeforeCreate = copyrightRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCopyrightsAsStream() {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        List<Copyright> copyrightList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CopyrightDTO.class)
            .getResponseBody()
            .map(copyrightMapper::toEntity)
            .filter(copyright::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(copyrightList).isNotNull();
        assertThat(copyrightList).hasSize(1);
        Copyright testCopyright = copyrightList.get(0);
        assertThat(testCopyright.getDetails()).isEqualTo(DEFAULT_DETAILS);
    }

    @Test
    void getAllCopyrights() {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        // Get all the copyrightList
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
            .value(hasItem(copyright.getId().intValue()))
            .jsonPath("$.[*].details")
            .value(hasItem(DEFAULT_DETAILS.toString()));
    }

    @Test
    void getCopyright() {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        // Get the copyright
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, copyright.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(copyright.getId().intValue()))
            .jsonPath("$.details")
            .value(is(DEFAULT_DETAILS.toString()));
    }

    @Test
    void getNonExistingCopyright() {
        // Get the copyright
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCopyright() throws Exception {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();

        // Update the copyright
        Copyright updatedCopyright = copyrightRepository.findById(copyright.getId()).block();
        updatedCopyright.details(UPDATED_DETAILS);
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(updatedCopyright);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, copyrightDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
        Copyright testCopyright = copyrightList.get(copyrightList.size() - 1);
        assertThat(testCopyright.getDetails()).isEqualTo(UPDATED_DETAILS);
    }

    @Test
    void putNonExistingCopyright() throws Exception {
        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();
        copyright.setId(longCount.incrementAndGet());

        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, copyrightDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCopyright() throws Exception {
        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();
        copyright.setId(longCount.incrementAndGet());

        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCopyright() throws Exception {
        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();
        copyright.setId(longCount.incrementAndGet());

        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCopyrightWithPatch() throws Exception {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();

        // Update the copyright using partial update
        Copyright partialUpdatedCopyright = new Copyright();
        partialUpdatedCopyright.setId(copyright.getId());

        partialUpdatedCopyright.details(UPDATED_DETAILS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCopyright.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCopyright))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
        Copyright testCopyright = copyrightList.get(copyrightList.size() - 1);
        assertThat(testCopyright.getDetails()).isEqualTo(UPDATED_DETAILS);
    }

    @Test
    void fullUpdateCopyrightWithPatch() throws Exception {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();

        // Update the copyright using partial update
        Copyright partialUpdatedCopyright = new Copyright();
        partialUpdatedCopyright.setId(copyright.getId());

        partialUpdatedCopyright.details(UPDATED_DETAILS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCopyright.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCopyright))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
        Copyright testCopyright = copyrightList.get(copyrightList.size() - 1);
        assertThat(testCopyright.getDetails()).isEqualTo(UPDATED_DETAILS);
    }

    @Test
    void patchNonExistingCopyright() throws Exception {
        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();
        copyright.setId(longCount.incrementAndGet());

        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, copyrightDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCopyright() throws Exception {
        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();
        copyright.setId(longCount.incrementAndGet());

        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCopyright() throws Exception {
        int databaseSizeBeforeUpdate = copyrightRepository.findAll().collectList().block().size();
        copyright.setId(longCount.incrementAndGet());

        // Create the Copyright
        CopyrightDTO copyrightDTO = copyrightMapper.toDto(copyright);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(copyrightDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Copyright in the database
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCopyright() {
        // Initialize the database
        copyrightRepository.save(copyright).block();

        int databaseSizeBeforeDelete = copyrightRepository.findAll().collectList().block().size();

        // Delete the copyright
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, copyright.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Copyright> copyrightList = copyrightRepository.findAll().collectList().block();
        assertThat(copyrightList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
