package com.highwayns.web.rest;

import static com.highwayns.domain.InviteusersAsserts.*;
// import static com.highwayns.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highwayns.IntegrationTest;
import com.highwayns.domain.Inviteusers;
import com.highwayns.repository.EntityManager;
import com.highwayns.repository.InviteusersRepository;
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
 * Integration tests for the {@link InviteusersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InviteusersResourceIT {

    private static final String DEFAULT_INVITERNAME = "AAAAAAAAAA";
    private static final String UPDATED_INVITERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKSPACES = "AAAAAAAAAA";
    private static final String UPDATED_WORKSPACES = "BBBBBBBBBB";

    private static final String DEFAULT_INVITERTIME = "AAAAAAAAAA";
    private static final String UPDATED_INVITERTIME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_INVITERID = "AAAAAAAAAA";
    private static final String UPDATED_INVITERID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/inviteusers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InviteusersRepository inviteusersRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Inviteusers inviteusers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inviteusers createEntity(EntityManager em) {
        Inviteusers inviteusers = new Inviteusers()
            .invitername(DEFAULT_INVITERNAME)
            .workspaces(DEFAULT_WORKSPACES)
            .invitertime(DEFAULT_INVITERTIME)
            .email(DEFAULT_EMAIL)
            .inviterid(DEFAULT_INVITERID)
            .status(DEFAULT_STATUS);
        return inviteusers;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inviteusers createUpdatedEntity(EntityManager em) {
        Inviteusers inviteusers = new Inviteusers()
            .invitername(UPDATED_INVITERNAME)
            .workspaces(UPDATED_WORKSPACES)
            .invitertime(UPDATED_INVITERTIME)
            .email(UPDATED_EMAIL)
            .inviterid(UPDATED_INVITERID)
            .status(UPDATED_STATUS);
        return inviteusers;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Inviteusers.class).block();
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
        inviteusers = createEntity(em);
    }

    @Test
    void createInviteusers() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Inviteusers
        var returnedInviteusers = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Inviteusers.class)
            .returnResult()
            .getResponseBody();

        // Validate the Inviteusers in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertInviteusersUpdatableFieldsEquals(returnedInviteusers, getPersistedInviteusers(returnedInviteusers));
    }

    @Test
    void createInviteusersWithExistingId() throws Exception {
        // Create the Inviteusers with an existing ID
        inviteusers.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkInviternameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inviteusers.setInvitername(null);

        // Create the Inviteusers, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkWorkspacesIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inviteusers.setWorkspaces(null);

        // Create the Inviteusers, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkInvitertimeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inviteusers.setInvitertime(null);

        // Create the Inviteusers, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkEmailIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inviteusers.setEmail(null);

        // Create the Inviteusers, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkInviteridIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inviteusers.setInviterid(null);

        // Create the Inviteusers, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStatusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inviteusers.setStatus(null);

        // Create the Inviteusers, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllInviteusersAsStream() {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        List<Inviteusers> inviteusersList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Inviteusers.class)
            .getResponseBody()
            .filter(inviteusers::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(inviteusersList).isNotNull();
        assertThat(inviteusersList).hasSize(1);
        Inviteusers testInviteusers = inviteusersList.get(0);

        // Test fails because reactive api returns an empty object instead of null
        // assertInviteusersAllPropertiesEquals(inviteusers, testInviteusers);
        assertInviteusersUpdatableFieldsEquals(inviteusers, testInviteusers);
    }

    @Test
    void getAllInviteusers() {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        // Get all the inviteusersList
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
            .value(hasItem(inviteusers.getId().intValue()))
            .jsonPath("$.[*].invitername")
            .value(hasItem(DEFAULT_INVITERNAME))
            .jsonPath("$.[*].workspaces")
            .value(hasItem(DEFAULT_WORKSPACES))
            .jsonPath("$.[*].invitertime")
            .value(hasItem(DEFAULT_INVITERTIME))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].inviterid")
            .value(hasItem(DEFAULT_INVITERID))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS));
    }

    @Test
    void getInviteusers() {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        // Get the inviteusers
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, inviteusers.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(inviteusers.getId().intValue()))
            .jsonPath("$.invitername")
            .value(is(DEFAULT_INVITERNAME))
            .jsonPath("$.workspaces")
            .value(is(DEFAULT_WORKSPACES))
            .jsonPath("$.invitertime")
            .value(is(DEFAULT_INVITERTIME))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.inviterid")
            .value(is(DEFAULT_INVITERID))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS));
    }

    @Test
    void getNonExistingInviteusers() {
        // Get the inviteusers
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingInviteusers() throws Exception {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inviteusers
        Inviteusers updatedInviteusers = inviteusersRepository.findById(inviteusers.getId()).block();
        updatedInviteusers
            .invitername(UPDATED_INVITERNAME)
            .workspaces(UPDATED_WORKSPACES)
            .invitertime(UPDATED_INVITERTIME)
            .email(UPDATED_EMAIL)
            .inviterid(UPDATED_INVITERID)
            .status(UPDATED_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedInviteusers.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(updatedInviteusers))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInviteusersToMatchAllProperties(updatedInviteusers);
    }

    @Test
    void putNonExistingInviteusers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inviteusers.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, inviteusers.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInviteusers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inviteusers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInviteusers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inviteusers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInviteusersWithPatch() throws Exception {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inviteusers using partial update
        Inviteusers partialUpdatedInviteusers = new Inviteusers();
        partialUpdatedInviteusers.setId(inviteusers.getId());

        partialUpdatedInviteusers.invitername(UPDATED_INVITERNAME).email(UPDATED_EMAIL).inviterid(UPDATED_INVITERID).status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInviteusers.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedInviteusers))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Inviteusers in the database

        // assertSameRepositoryCount(databaseSizeBeforeUpdate);
        // assertInviteusersUpdatableFieldsEquals(
        //     createUpdateProxyForBean(partialUpdatedInviteusers, inviteusers),
        //     getPersistedInviteusers(inviteusers)
        // );
    }

    @Test
    void fullUpdateInviteusersWithPatch() throws Exception {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inviteusers using partial update
        Inviteusers partialUpdatedInviteusers = new Inviteusers();
        partialUpdatedInviteusers.setId(inviteusers.getId());

        partialUpdatedInviteusers
            .invitername(UPDATED_INVITERNAME)
            .workspaces(UPDATED_WORKSPACES)
            .invitertime(UPDATED_INVITERTIME)
            .email(UPDATED_EMAIL)
            .inviterid(UPDATED_INVITERID)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInviteusers.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedInviteusers))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Inviteusers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInviteusersUpdatableFieldsEquals(partialUpdatedInviteusers, getPersistedInviteusers(partialUpdatedInviteusers));
    }

    @Test
    void patchNonExistingInviteusers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inviteusers.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, inviteusers.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInviteusers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inviteusers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInviteusers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inviteusers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(inviteusers))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Inviteusers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInviteusers() {
        // Initialize the database
        inviteusersRepository.save(inviteusers).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the inviteusers
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, inviteusers.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return inviteusersRepository.count().block();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Inviteusers getPersistedInviteusers(Inviteusers inviteusers) {
        return inviteusersRepository.findById(inviteusers.getId()).block();
    }

    protected void assertPersistedInviteusersToMatchAllProperties(Inviteusers expectedInviteusers) {
        // Test fails because reactive api returns an empty object instead of null
        // assertInviteusersAllPropertiesEquals(expectedInviteusers, getPersistedInviteusers(expectedInviteusers));
        assertInviteusersUpdatableFieldsEquals(expectedInviteusers, getPersistedInviteusers(expectedInviteusers));
    }

    protected void assertPersistedInviteusersToMatchUpdatableProperties(Inviteusers expectedInviteusers) {
        // Test fails because reactive api returns an empty object instead of null
        // assertInviteusersAllUpdatablePropertiesEquals(expectedInviteusers, getPersistedInviteusers(expectedInviteusers));
        assertInviteusersUpdatableFieldsEquals(expectedInviteusers, getPersistedInviteusers(expectedInviteusers));
    }
}
