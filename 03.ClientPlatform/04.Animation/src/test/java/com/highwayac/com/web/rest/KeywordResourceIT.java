package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Keyword;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.KeywordRepository;
import com.highwayac.com.service.dto.KeywordDTO;
import com.highwayac.com.service.mapper.KeywordMapper;
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
 * Integration tests for the {@link KeywordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class KeywordResourceIT {

    private static final String DEFAULT_WORD = "AAAAAAAAAA";
    private static final String UPDATED_WORD = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/keywords";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private KeywordMapper keywordMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Keyword keyword;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Keyword createEntity(EntityManager em) {
        Keyword keyword = new Keyword().word(DEFAULT_WORD);
        return keyword;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Keyword createUpdatedEntity(EntityManager em) {
        Keyword keyword = new Keyword().word(UPDATED_WORD);
        return keyword;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Keyword.class).block();
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
        keyword = createEntity(em);
    }

    @Test
    void createKeyword() throws Exception {
        int databaseSizeBeforeCreate = keywordRepository.findAll().collectList().block().size();
        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeCreate + 1);
        Keyword testKeyword = keywordList.get(keywordList.size() - 1);
        assertThat(testKeyword.getWord()).isEqualTo(DEFAULT_WORD);
    }

    @Test
    void createKeywordWithExistingId() throws Exception {
        // Create the Keyword with an existing ID
        keyword.setId(1L);
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        int databaseSizeBeforeCreate = keywordRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkWordIsRequired() throws Exception {
        int databaseSizeBeforeTest = keywordRepository.findAll().collectList().block().size();
        // set the field null
        keyword.setWord(null);

        // Create the Keyword, which fails.
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllKeywords() {
        // Initialize the database
        keywordRepository.save(keyword).block();

        // Get all the keywordList
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
            .value(hasItem(keyword.getId().intValue()))
            .jsonPath("$.[*].word")
            .value(hasItem(DEFAULT_WORD));
    }

    @Test
    void getKeyword() {
        // Initialize the database
        keywordRepository.save(keyword).block();

        // Get the keyword
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, keyword.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(keyword.getId().intValue()))
            .jsonPath("$.word")
            .value(is(DEFAULT_WORD));
    }

    @Test
    void getNonExistingKeyword() {
        // Get the keyword
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingKeyword() throws Exception {
        // Initialize the database
        keywordRepository.save(keyword).block();

        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();

        // Update the keyword
        Keyword updatedKeyword = keywordRepository.findById(keyword.getId()).block();
        updatedKeyword.word(UPDATED_WORD);
        KeywordDTO keywordDTO = keywordMapper.toDto(updatedKeyword);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, keywordDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
        Keyword testKeyword = keywordList.get(keywordList.size() - 1);
        assertThat(testKeyword.getWord()).isEqualTo(UPDATED_WORD);
    }

    @Test
    void putNonExistingKeyword() throws Exception {
        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();
        keyword.setId(longCount.incrementAndGet());

        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, keywordDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchKeyword() throws Exception {
        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();
        keyword.setId(longCount.incrementAndGet());

        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamKeyword() throws Exception {
        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();
        keyword.setId(longCount.incrementAndGet());

        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateKeywordWithPatch() throws Exception {
        // Initialize the database
        keywordRepository.save(keyword).block();

        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();

        // Update the keyword using partial update
        Keyword partialUpdatedKeyword = new Keyword();
        partialUpdatedKeyword.setId(keyword.getId());

        partialUpdatedKeyword.word(UPDATED_WORD);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedKeyword.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedKeyword))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
        Keyword testKeyword = keywordList.get(keywordList.size() - 1);
        assertThat(testKeyword.getWord()).isEqualTo(UPDATED_WORD);
    }

    @Test
    void fullUpdateKeywordWithPatch() throws Exception {
        // Initialize the database
        keywordRepository.save(keyword).block();

        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();

        // Update the keyword using partial update
        Keyword partialUpdatedKeyword = new Keyword();
        partialUpdatedKeyword.setId(keyword.getId());

        partialUpdatedKeyword.word(UPDATED_WORD);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedKeyword.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedKeyword))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
        Keyword testKeyword = keywordList.get(keywordList.size() - 1);
        assertThat(testKeyword.getWord()).isEqualTo(UPDATED_WORD);
    }

    @Test
    void patchNonExistingKeyword() throws Exception {
        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();
        keyword.setId(longCount.incrementAndGet());

        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, keywordDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchKeyword() throws Exception {
        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();
        keyword.setId(longCount.incrementAndGet());

        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamKeyword() throws Exception {
        int databaseSizeBeforeUpdate = keywordRepository.findAll().collectList().block().size();
        keyword.setId(longCount.incrementAndGet());

        // Create the Keyword
        KeywordDTO keywordDTO = keywordMapper.toDto(keyword);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(keywordDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Keyword in the database
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteKeyword() {
        // Initialize the database
        keywordRepository.save(keyword).block();

        int databaseSizeBeforeDelete = keywordRepository.findAll().collectList().block().size();

        // Delete the keyword
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, keyword.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Keyword> keywordList = keywordRepository.findAll().collectList().block();
        assertThat(keywordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
