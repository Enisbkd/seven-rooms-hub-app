package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ResTag;
import com.sbm.sevenroomstohub.repository.ResTagRepository;
import com.sbm.sevenroomstohub.service.dto.ResTagDTO;
import com.sbm.sevenroomstohub.service.mapper.ResTagMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link ResTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResTagResourceIT {

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_TAG_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_TECH_LINEAGE = "AAAAAAAAAA";
    private static final String UPDATED_TECH_LINEAGE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TECH_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TECH_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TECH_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TECH_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TECH_MAPPING = "AAAAAAAAAA";
    private static final String UPDATED_TECH_MAPPING = "BBBBBBBBBB";

    private static final String DEFAULT_TECH_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_TECH_COMMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/res-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResTagRepository resTagRepository;

    @Autowired
    private ResTagMapper resTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResTagMockMvc;

    private ResTag resTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTag createEntity(EntityManager em) {
        ResTag resTag = new ResTag()
            .tag(DEFAULT_TAG)
            .tagDisplay(DEFAULT_TAG_DISPLAY)
            .group(DEFAULT_GROUP)
            .groupDisplay(DEFAULT_GROUP_DISPLAY)
            .color(DEFAULT_COLOR)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return resTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTag createUpdatedEntity(EntityManager em) {
        ResTag resTag = new ResTag()
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return resTag;
    }

    @BeforeEach
    public void initTest() {
        resTag = createEntity(em);
    }

    @Test
    @Transactional
    void createResTag() throws Exception {
        int databaseSizeBeforeCreate = resTagRepository.findAll().size();
        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);
        restResTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resTagDTO)))
            .andExpect(status().isCreated());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeCreate + 1);
        ResTag testResTag = resTagList.get(resTagList.size() - 1);
        assertThat(testResTag.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testResTag.getTagDisplay()).isEqualTo(DEFAULT_TAG_DISPLAY);
        assertThat(testResTag.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testResTag.getGroupDisplay()).isEqualTo(DEFAULT_GROUP_DISPLAY);
        assertThat(testResTag.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testResTag.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testResTag.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testResTag.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testResTag.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testResTag.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createResTagWithExistingId() throws Exception {
        // Create the ResTag with an existing ID
        resTag.setId(1L);
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        int databaseSizeBeforeCreate = resTagRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResTags() throws Exception {
        // Initialize the database
        resTagRepository.saveAndFlush(resTag);

        // Get all the resTagList
        restResTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].tagDisplay").value(hasItem(DEFAULT_TAG_DISPLAY)))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP)))
            .andExpect(jsonPath("$.[*].groupDisplay").value(hasItem(DEFAULT_GROUP_DISPLAY)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getResTag() throws Exception {
        // Initialize the database
        resTagRepository.saveAndFlush(resTag);

        // Get the resTag
        restResTagMockMvc
            .perform(get(ENTITY_API_URL_ID, resTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resTag.getId().intValue()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.tagDisplay").value(DEFAULT_TAG_DISPLAY))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP))
            .andExpect(jsonPath("$.groupDisplay").value(DEFAULT_GROUP_DISPLAY))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingResTag() throws Exception {
        // Get the resTag
        restResTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResTag() throws Exception {
        // Initialize the database
        resTagRepository.saveAndFlush(resTag);

        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();

        // Update the resTag
        ResTag updatedResTag = resTagRepository.findById(resTag.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResTag are not directly saved in db
        em.detach(updatedResTag);
        updatedResTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        ResTagDTO resTagDTO = resTagMapper.toDto(updatedResTag);

        restResTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resTagDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
        ResTag testResTag = resTagList.get(resTagList.size() - 1);
        assertThat(testResTag.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testResTag.getTagDisplay()).isEqualTo(UPDATED_TAG_DISPLAY);
        assertThat(testResTag.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testResTag.getGroupDisplay()).isEqualTo(UPDATED_GROUP_DISPLAY);
        assertThat(testResTag.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testResTag.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResTag.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResTag.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResTag.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResTag.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingResTag() throws Exception {
        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();
        resTag.setId(longCount.incrementAndGet());

        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResTag() throws Exception {
        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();
        resTag.setId(longCount.incrementAndGet());

        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResTag() throws Exception {
        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();
        resTag.setId(longCount.incrementAndGet());

        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resTagDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResTagWithPatch() throws Exception {
        // Initialize the database
        resTagRepository.saveAndFlush(resTag);

        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();

        // Update the resTag using partial update
        ResTag partialUpdatedResTag = new ResTag();
        partialUpdatedResTag.setId(resTag.getId());

        partialUpdatedResTag
            .tag(UPDATED_TAG)
            .group(UPDATED_GROUP)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING);

        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResTag))
            )
            .andExpect(status().isOk());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
        ResTag testResTag = resTagList.get(resTagList.size() - 1);
        assertThat(testResTag.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testResTag.getTagDisplay()).isEqualTo(DEFAULT_TAG_DISPLAY);
        assertThat(testResTag.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testResTag.getGroupDisplay()).isEqualTo(DEFAULT_GROUP_DISPLAY);
        assertThat(testResTag.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testResTag.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResTag.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResTag.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResTag.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResTag.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateResTagWithPatch() throws Exception {
        // Initialize the database
        resTagRepository.saveAndFlush(resTag);

        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();

        // Update the resTag using partial update
        ResTag partialUpdatedResTag = new ResTag();
        partialUpdatedResTag.setId(resTag.getId());

        partialUpdatedResTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResTag))
            )
            .andExpect(status().isOk());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
        ResTag testResTag = resTagList.get(resTagList.size() - 1);
        assertThat(testResTag.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testResTag.getTagDisplay()).isEqualTo(UPDATED_TAG_DISPLAY);
        assertThat(testResTag.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testResTag.getGroupDisplay()).isEqualTo(UPDATED_GROUP_DISPLAY);
        assertThat(testResTag.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testResTag.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResTag.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResTag.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResTag.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResTag.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingResTag() throws Exception {
        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();
        resTag.setId(longCount.incrementAndGet());

        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resTagDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResTag() throws Exception {
        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();
        resTag.setId(longCount.incrementAndGet());

        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResTag() throws Exception {
        int databaseSizeBeforeUpdate = resTagRepository.findAll().size();
        resTag.setId(longCount.incrementAndGet());

        // Create the ResTag
        ResTagDTO resTagDTO = resTagMapper.toDto(resTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(resTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTag in the database
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResTag() throws Exception {
        // Initialize the database
        resTagRepository.saveAndFlush(resTag);

        int databaseSizeBeforeDelete = resTagRepository.findAll().size();

        // Delete the resTag
        restResTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, resTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResTag> resTagList = resTagRepository.findAll();
        assertThat(resTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
