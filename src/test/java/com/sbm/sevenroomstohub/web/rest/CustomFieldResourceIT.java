package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.CustomField;
import com.sbm.sevenroomstohub.repository.CustomFieldRepository;
import com.sbm.sevenroomstohub.service.dto.CustomFieldDTO;
import com.sbm.sevenroomstohub.service.mapper.CustomFieldMapper;
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
 * Integration tests for the {@link CustomFieldResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomFieldResourceIT {

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

    private static final String ENTITY_API_URL = "/api/custom-fields";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CustomFieldRepository customFieldRepository;

    @Autowired
    private CustomFieldMapper customFieldMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomFieldMockMvc;

    private CustomField customField;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomField createEntity(EntityManager em) {
        CustomField customField = new CustomField()
            .systemName(DEFAULT_SYSTEM_NAME)
            .displayOrder(DEFAULT_DISPLAY_ORDER)
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return customField;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomField createUpdatedEntity(EntityManager em) {
        CustomField customField = new CustomField()
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return customField;
    }

    @BeforeEach
    public void initTest() {
        customField = createEntity(em);
    }

    @Test
    @Transactional
    void createCustomField() throws Exception {
        int databaseSizeBeforeCreate = customFieldRepository.findAll().size();
        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);
        restCustomFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeCreate + 1);
        CustomField testCustomField = customFieldList.get(customFieldList.size() - 1);
        assertThat(testCustomField.getSystemName()).isEqualTo(DEFAULT_SYSTEM_NAME);
        assertThat(testCustomField.getDisplayOrder()).isEqualTo(DEFAULT_DISPLAY_ORDER);
        assertThat(testCustomField.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomField.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testCustomField.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testCustomField.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testCustomField.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testCustomField.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testCustomField.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createCustomFieldWithExistingId() throws Exception {
        // Create the CustomField with an existing ID
        customField.setId(1L);
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        int databaseSizeBeforeCreate = customFieldRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomFields() throws Exception {
        // Initialize the database
        customFieldRepository.saveAndFlush(customField);

        // Get all the customFieldList
        restCustomFieldMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customField.getId().intValue())))
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
    void getCustomField() throws Exception {
        // Initialize the database
        customFieldRepository.saveAndFlush(customField);

        // Get the customField
        restCustomFieldMockMvc
            .perform(get(ENTITY_API_URL_ID, customField.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customField.getId().intValue()))
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
    void getNonExistingCustomField() throws Exception {
        // Get the customField
        restCustomFieldMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomField() throws Exception {
        // Initialize the database
        customFieldRepository.saveAndFlush(customField);

        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();

        // Update the customField
        CustomField updatedCustomField = customFieldRepository.findById(customField.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomField are not directly saved in db
        em.detach(updatedCustomField);
        updatedCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(updatedCustomField);

        restCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customFieldDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isOk());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
        CustomField testCustomField = customFieldList.get(customFieldList.size() - 1);
        assertThat(testCustomField.getSystemName()).isEqualTo(UPDATED_SYSTEM_NAME);
        assertThat(testCustomField.getDisplayOrder()).isEqualTo(UPDATED_DISPLAY_ORDER);
        assertThat(testCustomField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomField.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testCustomField.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testCustomField.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testCustomField.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testCustomField.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testCustomField.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingCustomField() throws Exception {
        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();
        customField.setId(longCount.incrementAndGet());

        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customFieldDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomField() throws Exception {
        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();
        customField.setId(longCount.incrementAndGet());

        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomField() throws Exception {
        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();
        customField.setId(longCount.incrementAndGet());

        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customFieldDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomFieldWithPatch() throws Exception {
        // Initialize the database
        customFieldRepository.saveAndFlush(customField);

        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();

        // Update the customField using partial update
        CustomField partialUpdatedCustomField = new CustomField();
        partialUpdatedCustomField.setId(customField.getId());

        partialUpdatedCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE);

        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomField))
            )
            .andExpect(status().isOk());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
        CustomField testCustomField = customFieldList.get(customFieldList.size() - 1);
        assertThat(testCustomField.getSystemName()).isEqualTo(UPDATED_SYSTEM_NAME);
        assertThat(testCustomField.getDisplayOrder()).isEqualTo(DEFAULT_DISPLAY_ORDER);
        assertThat(testCustomField.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomField.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testCustomField.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testCustomField.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testCustomField.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testCustomField.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testCustomField.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateCustomFieldWithPatch() throws Exception {
        // Initialize the database
        customFieldRepository.saveAndFlush(customField);

        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();

        // Update the customField using partial update
        CustomField partialUpdatedCustomField = new CustomField();
        partialUpdatedCustomField.setId(customField.getId());

        partialUpdatedCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomField))
            )
            .andExpect(status().isOk());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
        CustomField testCustomField = customFieldList.get(customFieldList.size() - 1);
        assertThat(testCustomField.getSystemName()).isEqualTo(UPDATED_SYSTEM_NAME);
        assertThat(testCustomField.getDisplayOrder()).isEqualTo(UPDATED_DISPLAY_ORDER);
        assertThat(testCustomField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomField.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testCustomField.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testCustomField.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testCustomField.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testCustomField.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testCustomField.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingCustomField() throws Exception {
        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();
        customField.setId(longCount.incrementAndGet());

        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customFieldDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomField() throws Exception {
        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();
        customField.setId(longCount.incrementAndGet());

        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomField() throws Exception {
        int databaseSizeBeforeUpdate = customFieldRepository.findAll().size();
        customField.setId(longCount.incrementAndGet());

        // Create the CustomField
        CustomFieldDTO customFieldDTO = customFieldMapper.toDto(customField);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(customFieldDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomField in the database
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomField() throws Exception {
        // Initialize the database
        customFieldRepository.saveAndFlush(customField);

        int databaseSizeBeforeDelete = customFieldRepository.findAll().size();

        // Delete the customField
        restCustomFieldMockMvc
            .perform(delete(ENTITY_API_URL_ID, customField.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomField> customFieldList = customFieldRepository.findAll();
        assertThat(customFieldList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
