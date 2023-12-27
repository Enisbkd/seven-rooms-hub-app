package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.MemberGroup;
import com.sbm.sevenroomstohub.repository.MemberGroupRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MemberGroupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MemberGroupResourceIT {

    private static final String ENTITY_API_URL = "/api/member-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MemberGroupRepository memberGroupRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMemberGroupMockMvc;

    private MemberGroup memberGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberGroup createEntity(EntityManager em) {
        MemberGroup memberGroup = new MemberGroup();
        return memberGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberGroup createUpdatedEntity(EntityManager em) {
        MemberGroup memberGroup = new MemberGroup();
        return memberGroup;
    }

    @BeforeEach
    public void initTest() {
        memberGroup = createEntity(em);
    }

    @Test
    @Transactional
    void createMemberGroup() throws Exception {
        int databaseSizeBeforeCreate = memberGroupRepository.findAll().size();
        // Create the MemberGroup
        restMemberGroupMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberGroup)))
            .andExpect(status().isCreated());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MemberGroup testMemberGroup = memberGroupList.get(memberGroupList.size() - 1);
    }

    @Test
    @Transactional
    void createMemberGroupWithExistingId() throws Exception {
        // Create the MemberGroup with an existing ID
        memberGroup.setId(1L);

        int databaseSizeBeforeCreate = memberGroupRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemberGroupMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberGroup)))
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMemberGroups() throws Exception {
        // Initialize the database
        memberGroupRepository.saveAndFlush(memberGroup);

        // Get all the memberGroupList
        restMemberGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(memberGroup.getId().intValue())));
    }

    @Test
    @Transactional
    void getMemberGroup() throws Exception {
        // Initialize the database
        memberGroupRepository.saveAndFlush(memberGroup);

        // Get the memberGroup
        restMemberGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, memberGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(memberGroup.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingMemberGroup() throws Exception {
        // Get the memberGroup
        restMemberGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMemberGroup() throws Exception {
        // Initialize the database
        memberGroupRepository.saveAndFlush(memberGroup);

        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();

        // Update the memberGroup
        MemberGroup updatedMemberGroup = memberGroupRepository.findById(memberGroup.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMemberGroup are not directly saved in db
        em.detach(updatedMemberGroup);

        restMemberGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMemberGroup.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMemberGroup))
            )
            .andExpect(status().isOk());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
        MemberGroup testMemberGroup = memberGroupList.get(memberGroupList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingMemberGroup() throws Exception {
        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();
        memberGroup.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, memberGroup.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMemberGroup() throws Exception {
        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMemberGroup() throws Exception {
        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberGroup)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMemberGroupWithPatch() throws Exception {
        // Initialize the database
        memberGroupRepository.saveAndFlush(memberGroup);

        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();

        // Update the memberGroup using partial update
        MemberGroup partialUpdatedMemberGroup = new MemberGroup();
        partialUpdatedMemberGroup.setId(memberGroup.getId());

        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMemberGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberGroup))
            )
            .andExpect(status().isOk());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
        MemberGroup testMemberGroup = memberGroupList.get(memberGroupList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateMemberGroupWithPatch() throws Exception {
        // Initialize the database
        memberGroupRepository.saveAndFlush(memberGroup);

        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();

        // Update the memberGroup using partial update
        MemberGroup partialUpdatedMemberGroup = new MemberGroup();
        partialUpdatedMemberGroup.setId(memberGroup.getId());

        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMemberGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberGroup))
            )
            .andExpect(status().isOk());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
        MemberGroup testMemberGroup = memberGroupList.get(memberGroupList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingMemberGroup() throws Exception {
        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();
        memberGroup.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, memberGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMemberGroup() throws Exception {
        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMemberGroup() throws Exception {
        int databaseSizeBeforeUpdate = memberGroupRepository.findAll().size();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(memberGroup))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MemberGroup in the database
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMemberGroup() throws Exception {
        // Initialize the database
        memberGroupRepository.saveAndFlush(memberGroup);

        int databaseSizeBeforeDelete = memberGroupRepository.findAll().size();

        // Delete the memberGroup
        restMemberGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, memberGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MemberGroup> memberGroupList = memberGroupRepository.findAll();
        assertThat(memberGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
