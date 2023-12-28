package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ResCustomField;
import com.sbm.sevenroomstohub.repository.ResCustomFieldRepository;
import com.sbm.sevenroomstohub.service.dto.ResCustomFieldDTO;
import com.sbm.sevenroomstohub.service.mapper.ResCustomFieldMapper;
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
 * Integration tests for the {@link ResCustomFieldResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResCustomFieldResourceIT {

    private static final String DEFAULT_SYSTEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DISPLAY_ORDER = 1;
    private static final Integer UPDATED_DISPLAY_ORDER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/res-custom-fields";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResCustomFieldRepository resCustomFieldRepository;

    @Autowired
    private ResCustomFieldMapper resCustomFieldMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResCustomFieldMockMvc;

    private ResCustomField resCustomField;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResCustomField createEntity(EntityManager em) {
        ResCustomField resCustomField = new ResCustomField()
            .systemName(DEFAULT_SYSTEM_NAME)
            .displayOrder(DEFAULT_DISPLAY_ORDER)
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return resCustomField;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResCustomField createUpdatedEntity(EntityManager em) {
        ResCustomField resCustomField = new ResCustomField()
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return resCustomField;
    }

    @BeforeEach
    public void initTest() {
        resCustomField = createEntity(em);
    }

    @Test
    @Transactional
    void createResCustomField() throws Exception {
        int databaseSizeBeforeCreate = resCustomFieldRepository.findAll().size();
        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);
        restResCustomFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeCreate + 1);
        ResCustomField testResCustomField = resCustomFieldList.get(resCustomFieldList.size() - 1);
        assertThat(testResCustomField.getSystemName()).isEqualTo(DEFAULT_SYSTEM_NAME);
        assertThat(testResCustomField.getDisplayOrder()).isEqualTo(DEFAULT_DISPLAY_ORDER);
        assertThat(testResCustomField.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testResCustomField.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testResCustomField.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testResCustomField.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testResCustomField.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testResCustomField.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testResCustomField.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createResCustomFieldWithExistingId() throws Exception {
        // Create the ResCustomField with an existing ID
        resCustomField.setId(1L);
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        int databaseSizeBeforeCreate = resCustomFieldRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResCustomFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResCustomFields() throws Exception {
        // Initialize the database
        resCustomFieldRepository.saveAndFlush(resCustomField);

        // Get all the resCustomFieldList
        restResCustomFieldMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resCustomField.getId().intValue())))
            .andExpect(jsonPath("$.[*].systemName").value(hasItem(DEFAULT_SYSTEM_NAME)))
            .andExpect(jsonPath("$.[*].displayOrder").value(hasItem(DEFAULT_DISPLAY_ORDER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getResCustomField() throws Exception {
        // Initialize the database
        resCustomFieldRepository.saveAndFlush(resCustomField);

        // Get the resCustomField
        restResCustomFieldMockMvc
            .perform(get(ENTITY_API_URL_ID, resCustomField.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resCustomField.getId().intValue()))
            .andExpect(jsonPath("$.systemName").value(DEFAULT_SYSTEM_NAME))
            .andExpect(jsonPath("$.displayOrder").value(DEFAULT_DISPLAY_ORDER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingResCustomField() throws Exception {
        // Get the resCustomField
        restResCustomFieldMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResCustomField() throws Exception {
        // Initialize the database
        resCustomFieldRepository.saveAndFlush(resCustomField);

        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();

        // Update the resCustomField
        ResCustomField updatedResCustomField = resCustomFieldRepository.findById(resCustomField.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResCustomField are not directly saved in db
        em.detach(updatedResCustomField);
        updatedResCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(updatedResCustomField);

        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resCustomFieldDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
        ResCustomField testResCustomField = resCustomFieldList.get(resCustomFieldList.size() - 1);
        assertThat(testResCustomField.getSystemName()).isEqualTo(UPDATED_SYSTEM_NAME);
        assertThat(testResCustomField.getDisplayOrder()).isEqualTo(UPDATED_DISPLAY_ORDER);
        assertThat(testResCustomField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResCustomField.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testResCustomField.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResCustomField.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResCustomField.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResCustomField.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResCustomField.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingResCustomField() throws Exception {
        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();
        resCustomField.setId(longCount.incrementAndGet());

        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resCustomFieldDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResCustomField() throws Exception {
        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();
        resCustomField.setId(longCount.incrementAndGet());

        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResCustomField() throws Exception {
        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();
        resCustomField.setId(longCount.incrementAndGet());

        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResCustomFieldWithPatch() throws Exception {
        // Initialize the database
        resCustomFieldRepository.saveAndFlush(resCustomField);

        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();

        // Update the resCustomField using partial update
        ResCustomField partialUpdatedResCustomField = new ResCustomField();
        partialUpdatedResCustomField.setId(resCustomField.getId());

        partialUpdatedResCustomField.value(UPDATED_VALUE).techMapping(UPDATED_TECH_MAPPING);

        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResCustomField))
            )
            .andExpect(status().isOk());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
        ResCustomField testResCustomField = resCustomFieldList.get(resCustomFieldList.size() - 1);
        assertThat(testResCustomField.getSystemName()).isEqualTo(DEFAULT_SYSTEM_NAME);
        assertThat(testResCustomField.getDisplayOrder()).isEqualTo(DEFAULT_DISPLAY_ORDER);
        assertThat(testResCustomField.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testResCustomField.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testResCustomField.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testResCustomField.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testResCustomField.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testResCustomField.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResCustomField.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateResCustomFieldWithPatch() throws Exception {
        // Initialize the database
        resCustomFieldRepository.saveAndFlush(resCustomField);

        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();

        // Update the resCustomField using partial update
        ResCustomField partialUpdatedResCustomField = new ResCustomField();
        partialUpdatedResCustomField.setId(resCustomField.getId());

        partialUpdatedResCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResCustomField))
            )
            .andExpect(status().isOk());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
        ResCustomField testResCustomField = resCustomFieldList.get(resCustomFieldList.size() - 1);
        assertThat(testResCustomField.getSystemName()).isEqualTo(UPDATED_SYSTEM_NAME);
        assertThat(testResCustomField.getDisplayOrder()).isEqualTo(UPDATED_DISPLAY_ORDER);
        assertThat(testResCustomField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResCustomField.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testResCustomField.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResCustomField.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResCustomField.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResCustomField.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResCustomField.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingResCustomField() throws Exception {
        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();
        resCustomField.setId(longCount.incrementAndGet());

        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resCustomFieldDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResCustomField() throws Exception {
        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();
        resCustomField.setId(longCount.incrementAndGet());

        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResCustomField() throws Exception {
        int databaseSizeBeforeUpdate = resCustomFieldRepository.findAll().size();
        resCustomField.setId(longCount.incrementAndGet());

        // Create the ResCustomField
        ResCustomFieldDTO resCustomFieldDTO = resCustomFieldMapper.toDto(resCustomField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resCustomFieldDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResCustomField in the database
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResCustomField() throws Exception {
        // Initialize the database
        resCustomFieldRepository.saveAndFlush(resCustomField);

        int databaseSizeBeforeDelete = resCustomFieldRepository.findAll().size();

        // Delete the resCustomField
        restResCustomFieldMockMvc
            .perform(delete(ENTITY_API_URL_ID, resCustomField.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResCustomField> resCustomFieldList = resCustomFieldRepository.findAll();
        assertThat(resCustomFieldList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
