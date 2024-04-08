package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Creator;
import com.highwayac.com.repository.CreatorRepository;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.service.dto.CreatorDTO;
import com.highwayac.com.service.mapper.CreatorMapper;
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
 * Integration tests for the {@link CreatorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreatorResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/creators";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Creator creator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creator createEntity(EntityManager em) {
        Creator creator = new Creator().name(DEFAULT_NAME);
        return creator;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creator createUpdatedEntity(EntityManager em) {
        Creator creator = new Creator().name(UPDATED_NAME);
        return creator;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Creator.class).block();
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
        creator = createEntity(em);
    }

    @Test
    void createCreator() throws Exception {
        int databaseSizeBeforeCreate = creatorRepository.findAll().collectList().block().size();
        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeCreate + 1);
        Creator testCreator = creatorList.get(creatorList.size() - 1);
        assertThat(testCreator.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void createCreatorWithExistingId() throws Exception {
        // Create the Creator with an existing ID
        creator.setId(1L);
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        int databaseSizeBeforeCreate = creatorRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = creatorRepository.findAll().collectList().block().size();
        // set the field null
        creator.setName(null);

        // Create the Creator, which fails.
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllCreators() {
        // Initialize the database
        creatorRepository.save(creator).block();

        // Get all the creatorList
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
            .value(hasItem(creator.getId().intValue()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getCreator() {
        // Initialize the database
        creatorRepository.save(creator).block();

        // Get the creator
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, creator.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(creator.getId().intValue()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingCreator() {
        // Get the creator
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreator() throws Exception {
        // Initialize the database
        creatorRepository.save(creator).block();

        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();

        // Update the creator
        Creator updatedCreator = creatorRepository.findById(creator.getId()).block();
        updatedCreator.name(UPDATED_NAME);
        CreatorDTO creatorDTO = creatorMapper.toDto(updatedCreator);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, creatorDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
        Creator testCreator = creatorList.get(creatorList.size() - 1);
        assertThat(testCreator.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    void putNonExistingCreator() throws Exception {
        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();
        creator.setId(longCount.incrementAndGet());

        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, creatorDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreator() throws Exception {
        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();
        creator.setId(longCount.incrementAndGet());

        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreator() throws Exception {
        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();
        creator.setId(longCount.incrementAndGet());

        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreatorWithPatch() throws Exception {
        // Initialize the database
        creatorRepository.save(creator).block();

        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();

        // Update the creator using partial update
        Creator partialUpdatedCreator = new Creator();
        partialUpdatedCreator.setId(creator.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreator.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreator))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
        Creator testCreator = creatorList.get(creatorList.size() - 1);
        assertThat(testCreator.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void fullUpdateCreatorWithPatch() throws Exception {
        // Initialize the database
        creatorRepository.save(creator).block();

        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();

        // Update the creator using partial update
        Creator partialUpdatedCreator = new Creator();
        partialUpdatedCreator.setId(creator.getId());

        partialUpdatedCreator.name(UPDATED_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreator.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreator))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
        Creator testCreator = creatorList.get(creatorList.size() - 1);
        assertThat(testCreator.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    void patchNonExistingCreator() throws Exception {
        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();
        creator.setId(longCount.incrementAndGet());

        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, creatorDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreator() throws Exception {
        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();
        creator.setId(longCount.incrementAndGet());

        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreator() throws Exception {
        int databaseSizeBeforeUpdate = creatorRepository.findAll().collectList().block().size();
        creator.setId(longCount.incrementAndGet());

        // Create the Creator
        CreatorDTO creatorDTO = creatorMapper.toDto(creator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creatorDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Creator in the database
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreator() {
        // Initialize the database
        creatorRepository.save(creator).block();

        int databaseSizeBeforeDelete = creatorRepository.findAll().collectList().block().size();

        // Delete the creator
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, creator.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Creator> creatorList = creatorRepository.findAll().collectList().block();
        assertThat(creatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
