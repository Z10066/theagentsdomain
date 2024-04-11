package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Material;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.MaterialRepository;
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
 * Integration tests for the {@link MaterialResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class MaterialResourceIT {

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

    private static final String ENTITY_API_URL = "/api/materials";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Material material;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Material createEntity(EntityManager em) {
        Material material = new Material()
            .title(DEFAULT_TITLE)
            .creator(DEFAULT_CREATOR)
            .description(DEFAULT_DESCRIPTION)
            .copyright(DEFAULT_COPYRIGHT)
            .watchLink(DEFAULT_WATCH_LINK);
        return material;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Material createUpdatedEntity(EntityManager em) {
        Material material = new Material()
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);
        return material;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Material.class).block();
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
        material = createEntity(em);
    }

    @Test
    void createMaterial() throws Exception {
        int databaseSizeBeforeCreate = materialRepository.findAll().collectList().block().size();
        // Create the Material
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeCreate + 1);
        Material testMaterial = materialList.get(materialList.size() - 1);
        assertThat(testMaterial.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMaterial.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMaterial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMaterial.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testMaterial.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void createMaterialWithExistingId() throws Exception {
        // Create the Material with an existing ID
        material.setId(1L);

        int databaseSizeBeforeCreate = materialRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = materialRepository.findAll().collectList().block().size();
        // set the field null
        material.setTitle(null);

        // Create the Material, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = materialRepository.findAll().collectList().block().size();
        // set the field null
        material.setCreator(null);

        // Create the Material, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMaterialsAsStream() {
        // Initialize the database
        materialRepository.save(material).block();

        List<Material> materialList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Material.class)
            .getResponseBody()
            .filter(material::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(materialList).isNotNull();
        assertThat(materialList).hasSize(1);
        Material testMaterial = materialList.get(0);
        assertThat(testMaterial.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMaterial.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMaterial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMaterial.getCopyright()).isEqualTo(DEFAULT_COPYRIGHT);
        assertThat(testMaterial.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void getAllMaterials() {
        // Initialize the database
        materialRepository.save(material).block();

        // Get all the materialList
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
            .value(hasItem(material.getId().intValue()))
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
    void getMaterial() {
        // Initialize the database
        materialRepository.save(material).block();

        // Get the material
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, material.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(material.getId().intValue()))
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
    void getNonExistingMaterial() {
        // Get the material
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingMaterial() throws Exception {
        // Initialize the database
        materialRepository.save(material).block();

        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();

        // Update the material
        Material updatedMaterial = materialRepository.findById(material.getId()).block();
        updatedMaterial
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedMaterial.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedMaterial))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
        Material testMaterial = materialList.get(materialList.size() - 1);
        assertThat(testMaterial.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMaterial.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMaterial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMaterial.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testMaterial.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void putNonExistingMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();
        material.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, material.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();
        material.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();
        material.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMaterialWithPatch() throws Exception {
        // Initialize the database
        materialRepository.save(material).block();

        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();

        // Update the material using partial update
        Material partialUpdatedMaterial = new Material();
        partialUpdatedMaterial.setId(material.getId());

        partialUpdatedMaterial.title(UPDATED_TITLE).creator(UPDATED_CREATOR).copyright(UPDATED_COPYRIGHT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMaterial.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMaterial))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
        Material testMaterial = materialList.get(materialList.size() - 1);
        assertThat(testMaterial.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMaterial.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMaterial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMaterial.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testMaterial.getWatchLink()).isEqualTo(DEFAULT_WATCH_LINK);
    }

    @Test
    void fullUpdateMaterialWithPatch() throws Exception {
        // Initialize the database
        materialRepository.save(material).block();

        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();

        // Update the material using partial update
        Material partialUpdatedMaterial = new Material();
        partialUpdatedMaterial.setId(material.getId());

        partialUpdatedMaterial
            .title(UPDATED_TITLE)
            .creator(UPDATED_CREATOR)
            .description(UPDATED_DESCRIPTION)
            .copyright(UPDATED_COPYRIGHT)
            .watchLink(UPDATED_WATCH_LINK);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMaterial.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMaterial))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
        Material testMaterial = materialList.get(materialList.size() - 1);
        assertThat(testMaterial.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMaterial.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMaterial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMaterial.getCopyright()).isEqualTo(UPDATED_COPYRIGHT);
        assertThat(testMaterial.getWatchLink()).isEqualTo(UPDATED_WATCH_LINK);
    }

    @Test
    void patchNonExistingMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();
        material.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, material.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();
        material.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().collectList().block().size();
        material.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(material))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMaterial() {
        // Initialize the database
        materialRepository.save(material).block();

        int databaseSizeBeforeDelete = materialRepository.findAll().collectList().block().size();

        // Delete the material
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, material.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Material> materialList = materialRepository.findAll().collectList().block();
        assertThat(materialList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
