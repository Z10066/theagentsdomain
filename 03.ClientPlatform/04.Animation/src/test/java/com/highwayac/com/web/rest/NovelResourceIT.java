package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Novel;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.NovelRepository;
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
 * Integration tests for the {@link NovelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class NovelResourceIT {

    private static final String DEFAULT_NOVELTEXT = "AAAAAAAAAA";
    private static final String UPDATED_NOVELTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_NOVELNAME = "AAAAAAAAAA";
    private static final String UPDATED_NOVELNAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/novels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Novel novel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Novel createEntity(EntityManager em) {
        Novel novel = new Novel().noveltext(DEFAULT_NOVELTEXT).novelname(DEFAULT_NOVELNAME);
        return novel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Novel createUpdatedEntity(EntityManager em) {
        Novel novel = new Novel().noveltext(UPDATED_NOVELTEXT).novelname(UPDATED_NOVELNAME);
        return novel;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Novel.class).block();
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
        novel = createEntity(em);
    }

    @Test
    void createNovel() throws Exception {
        int databaseSizeBeforeCreate = novelRepository.findAll().collectList().block().size();
        // Create the Novel
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeCreate + 1);
        Novel testNovel = novelList.get(novelList.size() - 1);
        assertThat(testNovel.getNoveltext()).isEqualTo(DEFAULT_NOVELTEXT);
        assertThat(testNovel.getNovelname()).isEqualTo(DEFAULT_NOVELNAME);
    }

    @Test
    void createNovelWithExistingId() throws Exception {
        // Create the Novel with an existing ID
        novel.setId(1L);

        int databaseSizeBeforeCreate = novelRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNoveltextIsRequired() throws Exception {
        int databaseSizeBeforeTest = novelRepository.findAll().collectList().block().size();
        // set the field null
        novel.setNoveltext(null);

        // Create the Novel, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNovelnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = novelRepository.findAll().collectList().block().size();
        // set the field null
        novel.setNovelname(null);

        // Create the Novel, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllNovelsAsStream() {
        // Initialize the database
        novelRepository.save(novel).block();

        List<Novel> novelList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Novel.class)
            .getResponseBody()
            .filter(novel::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(novelList).isNotNull();
        assertThat(novelList).hasSize(1);
        Novel testNovel = novelList.get(0);
        assertThat(testNovel.getNoveltext()).isEqualTo(DEFAULT_NOVELTEXT);
        assertThat(testNovel.getNovelname()).isEqualTo(DEFAULT_NOVELNAME);
    }

    @Test
    void getAllNovels() {
        // Initialize the database
        novelRepository.save(novel).block();

        // Get all the novelList
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
            .value(hasItem(novel.getId().intValue()))
            .jsonPath("$.[*].noveltext")
            .value(hasItem(DEFAULT_NOVELTEXT))
            .jsonPath("$.[*].novelname")
            .value(hasItem(DEFAULT_NOVELNAME));
    }

    @Test
    void getNovel() {
        // Initialize the database
        novelRepository.save(novel).block();

        // Get the novel
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, novel.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(novel.getId().intValue()))
            .jsonPath("$.noveltext")
            .value(is(DEFAULT_NOVELTEXT))
            .jsonPath("$.novelname")
            .value(is(DEFAULT_NOVELNAME));
    }

    @Test
    void getNonExistingNovel() {
        // Get the novel
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingNovel() throws Exception {
        // Initialize the database
        novelRepository.save(novel).block();

        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();

        // Update the novel
        Novel updatedNovel = novelRepository.findById(novel.getId()).block();
        updatedNovel.noveltext(UPDATED_NOVELTEXT).novelname(UPDATED_NOVELNAME);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedNovel.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedNovel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
        Novel testNovel = novelList.get(novelList.size() - 1);
        assertThat(testNovel.getNoveltext()).isEqualTo(UPDATED_NOVELTEXT);
        assertThat(testNovel.getNovelname()).isEqualTo(UPDATED_NOVELNAME);
    }

    @Test
    void putNonExistingNovel() throws Exception {
        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();
        novel.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, novel.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchNovel() throws Exception {
        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();
        novel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamNovel() throws Exception {
        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();
        novel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateNovelWithPatch() throws Exception {
        // Initialize the database
        novelRepository.save(novel).block();

        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();

        // Update the novel using partial update
        Novel partialUpdatedNovel = new Novel();
        partialUpdatedNovel.setId(novel.getId());

        partialUpdatedNovel.novelname(UPDATED_NOVELNAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedNovel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedNovel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
        Novel testNovel = novelList.get(novelList.size() - 1);
        assertThat(testNovel.getNoveltext()).isEqualTo(DEFAULT_NOVELTEXT);
        assertThat(testNovel.getNovelname()).isEqualTo(UPDATED_NOVELNAME);
    }

    @Test
    void fullUpdateNovelWithPatch() throws Exception {
        // Initialize the database
        novelRepository.save(novel).block();

        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();

        // Update the novel using partial update
        Novel partialUpdatedNovel = new Novel();
        partialUpdatedNovel.setId(novel.getId());

        partialUpdatedNovel.noveltext(UPDATED_NOVELTEXT).novelname(UPDATED_NOVELNAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedNovel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedNovel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
        Novel testNovel = novelList.get(novelList.size() - 1);
        assertThat(testNovel.getNoveltext()).isEqualTo(UPDATED_NOVELTEXT);
        assertThat(testNovel.getNovelname()).isEqualTo(UPDATED_NOVELNAME);
    }

    @Test
    void patchNonExistingNovel() throws Exception {
        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();
        novel.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, novel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchNovel() throws Exception {
        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();
        novel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamNovel() throws Exception {
        int databaseSizeBeforeUpdate = novelRepository.findAll().collectList().block().size();
        novel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(novel))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Novel in the database
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteNovel() {
        // Initialize the database
        novelRepository.save(novel).block();

        int databaseSizeBeforeDelete = novelRepository.findAll().collectList().block().size();

        // Delete the novel
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, novel.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Novel> novelList = novelRepository.findAll().collectList().block();
        assertThat(novelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
