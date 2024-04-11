package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.LinkedAccount;
import com.highwayac.com.domain.enumeration.AccountType;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.LinkedAccountRepository;
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
 * Integration tests for the {@link LinkedAccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class LinkedAccountResourceIT {

    private static final AccountType DEFAULT_ACCOUNT_TYPE = AccountType.YOUTUBE;
    private static final AccountType UPDATED_ACCOUNT_TYPE = AccountType.GOOGLE;

    private static final String DEFAULT_ACCOUNT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_IDENTIFIER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/linked-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LinkedAccountRepository linkedAccountRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private LinkedAccount linkedAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LinkedAccount createEntity(EntityManager em) {
        LinkedAccount linkedAccount = new LinkedAccount().accountType(DEFAULT_ACCOUNT_TYPE).accountIdentifier(DEFAULT_ACCOUNT_IDENTIFIER);
        return linkedAccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LinkedAccount createUpdatedEntity(EntityManager em) {
        LinkedAccount linkedAccount = new LinkedAccount().accountType(UPDATED_ACCOUNT_TYPE).accountIdentifier(UPDATED_ACCOUNT_IDENTIFIER);
        return linkedAccount;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(LinkedAccount.class).block();
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
        linkedAccount = createEntity(em);
    }

    @Test
    void createLinkedAccount() throws Exception {
        int databaseSizeBeforeCreate = linkedAccountRepository.findAll().collectList().block().size();
        // Create the LinkedAccount
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeCreate + 1);
        LinkedAccount testLinkedAccount = linkedAccountList.get(linkedAccountList.size() - 1);
        assertThat(testLinkedAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testLinkedAccount.getAccountIdentifier()).isEqualTo(DEFAULT_ACCOUNT_IDENTIFIER);
    }

    @Test
    void createLinkedAccountWithExistingId() throws Exception {
        // Create the LinkedAccount with an existing ID
        linkedAccount.setId(1L);

        int databaseSizeBeforeCreate = linkedAccountRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkAccountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = linkedAccountRepository.findAll().collectList().block().size();
        // set the field null
        linkedAccount.setAccountType(null);

        // Create the LinkedAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAccountIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = linkedAccountRepository.findAll().collectList().block().size();
        // set the field null
        linkedAccount.setAccountIdentifier(null);

        // Create the LinkedAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllLinkedAccountsAsStream() {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        List<LinkedAccount> linkedAccountList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(LinkedAccount.class)
            .getResponseBody()
            .filter(linkedAccount::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(linkedAccountList).isNotNull();
        assertThat(linkedAccountList).hasSize(1);
        LinkedAccount testLinkedAccount = linkedAccountList.get(0);
        assertThat(testLinkedAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testLinkedAccount.getAccountIdentifier()).isEqualTo(DEFAULT_ACCOUNT_IDENTIFIER);
    }

    @Test
    void getAllLinkedAccounts() {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        // Get all the linkedAccountList
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
            .value(hasItem(linkedAccount.getId().intValue()))
            .jsonPath("$.[*].accountType")
            .value(hasItem(DEFAULT_ACCOUNT_TYPE.toString()))
            .jsonPath("$.[*].accountIdentifier")
            .value(hasItem(DEFAULT_ACCOUNT_IDENTIFIER));
    }

    @Test
    void getLinkedAccount() {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        // Get the linkedAccount
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, linkedAccount.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(linkedAccount.getId().intValue()))
            .jsonPath("$.accountType")
            .value(is(DEFAULT_ACCOUNT_TYPE.toString()))
            .jsonPath("$.accountIdentifier")
            .value(is(DEFAULT_ACCOUNT_IDENTIFIER));
    }

    @Test
    void getNonExistingLinkedAccount() {
        // Get the linkedAccount
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingLinkedAccount() throws Exception {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();

        // Update the linkedAccount
        LinkedAccount updatedLinkedAccount = linkedAccountRepository.findById(linkedAccount.getId()).block();
        updatedLinkedAccount.accountType(UPDATED_ACCOUNT_TYPE).accountIdentifier(UPDATED_ACCOUNT_IDENTIFIER);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedLinkedAccount.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedLinkedAccount))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
        LinkedAccount testLinkedAccount = linkedAccountList.get(linkedAccountList.size() - 1);
        assertThat(testLinkedAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testLinkedAccount.getAccountIdentifier()).isEqualTo(UPDATED_ACCOUNT_IDENTIFIER);
    }

    @Test
    void putNonExistingLinkedAccount() throws Exception {
        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();
        linkedAccount.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, linkedAccount.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLinkedAccount() throws Exception {
        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();
        linkedAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLinkedAccount() throws Exception {
        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();
        linkedAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLinkedAccountWithPatch() throws Exception {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();

        // Update the linkedAccount using partial update
        LinkedAccount partialUpdatedLinkedAccount = new LinkedAccount();
        partialUpdatedLinkedAccount.setId(linkedAccount.getId());

        partialUpdatedLinkedAccount.accountType(UPDATED_ACCOUNT_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLinkedAccount.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedLinkedAccount))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
        LinkedAccount testLinkedAccount = linkedAccountList.get(linkedAccountList.size() - 1);
        assertThat(testLinkedAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testLinkedAccount.getAccountIdentifier()).isEqualTo(DEFAULT_ACCOUNT_IDENTIFIER);
    }

    @Test
    void fullUpdateLinkedAccountWithPatch() throws Exception {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();

        // Update the linkedAccount using partial update
        LinkedAccount partialUpdatedLinkedAccount = new LinkedAccount();
        partialUpdatedLinkedAccount.setId(linkedAccount.getId());

        partialUpdatedLinkedAccount.accountType(UPDATED_ACCOUNT_TYPE).accountIdentifier(UPDATED_ACCOUNT_IDENTIFIER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLinkedAccount.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedLinkedAccount))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
        LinkedAccount testLinkedAccount = linkedAccountList.get(linkedAccountList.size() - 1);
        assertThat(testLinkedAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testLinkedAccount.getAccountIdentifier()).isEqualTo(UPDATED_ACCOUNT_IDENTIFIER);
    }

    @Test
    void patchNonExistingLinkedAccount() throws Exception {
        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();
        linkedAccount.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, linkedAccount.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLinkedAccount() throws Exception {
        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();
        linkedAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLinkedAccount() throws Exception {
        int databaseSizeBeforeUpdate = linkedAccountRepository.findAll().collectList().block().size();
        linkedAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(linkedAccount))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the LinkedAccount in the database
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLinkedAccount() {
        // Initialize the database
        linkedAccountRepository.save(linkedAccount).block();

        int databaseSizeBeforeDelete = linkedAccountRepository.findAll().collectList().block().size();

        // Delete the linkedAccount
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, linkedAccount.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<LinkedAccount> linkedAccountList = linkedAccountRepository.findAll().collectList().block();
        assertThat(linkedAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
