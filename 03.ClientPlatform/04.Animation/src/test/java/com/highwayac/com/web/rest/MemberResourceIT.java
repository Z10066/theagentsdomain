package com.highwayac.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.highwayac.com.IntegrationTest;
import com.highwayac.com.domain.Member;
import com.highwayac.com.repository.EntityManager;
import com.highwayac.com.repository.MemberRepository;
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
 * Integration tests for the {@link MemberResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class MemberResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_STATUS = false;
    private static final Boolean UPDATED_ACTIVE_STATUS = true;

    private static final String ENTITY_API_URL = "/api/members";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Member member;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Member createEntity(EntityManager em) {
        Member member = new Member()
            .username(DEFAULT_USERNAME)
            .fullName(DEFAULT_FULL_NAME)
            .role(DEFAULT_ROLE)
            .activeStatus(DEFAULT_ACTIVE_STATUS);
        return member;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Member createUpdatedEntity(EntityManager em) {
        Member member = new Member()
            .username(UPDATED_USERNAME)
            .fullName(UPDATED_FULL_NAME)
            .role(UPDATED_ROLE)
            .activeStatus(UPDATED_ACTIVE_STATUS);
        return member;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Member.class).block();
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
        member = createEntity(em);
    }

    @Test
    void createMember() throws Exception {
        int databaseSizeBeforeCreate = memberRepository.findAll().collectList().block().size();
        // Create the Member
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeCreate + 1);
        Member testMember = memberList.get(memberList.size() - 1);
        assertThat(testMember.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testMember.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testMember.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMember.getActiveStatus()).isEqualTo(DEFAULT_ACTIVE_STATUS);
    }

    @Test
    void createMemberWithExistingId() throws Exception {
        // Create the Member with an existing ID
        member.setId(1L);

        int databaseSizeBeforeCreate = memberRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().collectList().block().size();
        // set the field null
        member.setUsername(null);

        // Create the Member, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().collectList().block().size();
        // set the field null
        member.setFullName(null);

        // Create the Member, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMembersAsStream() {
        // Initialize the database
        memberRepository.save(member).block();

        List<Member> memberList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Member.class)
            .getResponseBody()
            .filter(member::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(memberList).isNotNull();
        assertThat(memberList).hasSize(1);
        Member testMember = memberList.get(0);
        assertThat(testMember.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testMember.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testMember.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMember.getActiveStatus()).isEqualTo(DEFAULT_ACTIVE_STATUS);
    }

    @Test
    void getAllMembers() {
        // Initialize the database
        memberRepository.save(member).block();

        // Get all the memberList
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
            .value(hasItem(member.getId().intValue()))
            .jsonPath("$.[*].username")
            .value(hasItem(DEFAULT_USERNAME))
            .jsonPath("$.[*].fullName")
            .value(hasItem(DEFAULT_FULL_NAME))
            .jsonPath("$.[*].role")
            .value(hasItem(DEFAULT_ROLE))
            .jsonPath("$.[*].activeStatus")
            .value(hasItem(DEFAULT_ACTIVE_STATUS.booleanValue()));
    }

    @Test
    void getMember() {
        // Initialize the database
        memberRepository.save(member).block();

        // Get the member
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, member.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(member.getId().intValue()))
            .jsonPath("$.username")
            .value(is(DEFAULT_USERNAME))
            .jsonPath("$.fullName")
            .value(is(DEFAULT_FULL_NAME))
            .jsonPath("$.role")
            .value(is(DEFAULT_ROLE))
            .jsonPath("$.activeStatus")
            .value(is(DEFAULT_ACTIVE_STATUS.booleanValue()));
    }

    @Test
    void getNonExistingMember() {
        // Get the member
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingMember() throws Exception {
        // Initialize the database
        memberRepository.save(member).block();

        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();

        // Update the member
        Member updatedMember = memberRepository.findById(member.getId()).block();
        updatedMember.username(UPDATED_USERNAME).fullName(UPDATED_FULL_NAME).role(UPDATED_ROLE).activeStatus(UPDATED_ACTIVE_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedMember.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedMember))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
        Member testMember = memberList.get(memberList.size() - 1);
        assertThat(testMember.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testMember.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testMember.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testMember.getActiveStatus()).isEqualTo(UPDATED_ACTIVE_STATUS);
    }

    @Test
    void putNonExistingMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();
        member.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, member.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();
        member.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();
        member.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMemberWithPatch() throws Exception {
        // Initialize the database
        memberRepository.save(member).block();

        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();

        // Update the member using partial update
        Member partialUpdatedMember = new Member();
        partialUpdatedMember.setId(member.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMember.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMember))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
        Member testMember = memberList.get(memberList.size() - 1);
        assertThat(testMember.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testMember.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testMember.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMember.getActiveStatus()).isEqualTo(DEFAULT_ACTIVE_STATUS);
    }

    @Test
    void fullUpdateMemberWithPatch() throws Exception {
        // Initialize the database
        memberRepository.save(member).block();

        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();

        // Update the member using partial update
        Member partialUpdatedMember = new Member();
        partialUpdatedMember.setId(member.getId());

        partialUpdatedMember.username(UPDATED_USERNAME).fullName(UPDATED_FULL_NAME).role(UPDATED_ROLE).activeStatus(UPDATED_ACTIVE_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMember.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMember))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
        Member testMember = memberList.get(memberList.size() - 1);
        assertThat(testMember.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testMember.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testMember.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testMember.getActiveStatus()).isEqualTo(UPDATED_ACTIVE_STATUS);
    }

    @Test
    void patchNonExistingMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();
        member.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, member.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();
        member.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().collectList().block().size();
        member.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(member))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMember() {
        // Initialize the database
        memberRepository.save(member).block();

        int databaseSizeBeforeDelete = memberRepository.findAll().collectList().block().size();

        // Delete the member
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, member.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Member> memberList = memberRepository.findAll().collectList().block();
        assertThat(memberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
