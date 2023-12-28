package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ClientTag;
import com.sbm.sevenroomstohub.repository.ClientTagRepository;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientTagMapper;
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
 * Integration tests for the {@link ClientTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientTagResourceIT {

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

    private static final String ENTITY_API_URL = "/api/client-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientTagRepository clientTagRepository;

    @Autowired
    private ClientTagMapper clientTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientTagMockMvc;

    private ClientTag clientTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientTag createEntity(EntityManager em) {
        ClientTag clientTag = new ClientTag()
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
        return clientTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientTag createUpdatedEntity(EntityManager em) {
        ClientTag clientTag = new ClientTag()
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
        return clientTag;
    }

    @BeforeEach
    public void initTest() {
        clientTag = createEntity(em);
    }

    @Test
    @Transactional
    void createClientTag() throws Exception {
        int databaseSizeBeforeCreate = clientTagRepository.findAll().size();
        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);
        restClientTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientTagDTO)))
            .andExpect(status().isCreated());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeCreate + 1);
        ClientTag testClientTag = clientTagList.get(clientTagList.size() - 1);
        assertThat(testClientTag.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testClientTag.getTagDisplay()).isEqualTo(DEFAULT_TAG_DISPLAY);
        assertThat(testClientTag.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testClientTag.getGroupDisplay()).isEqualTo(DEFAULT_GROUP_DISPLAY);
        assertThat(testClientTag.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testClientTag.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClientTag.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testClientTag.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testClientTag.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testClientTag.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createClientTagWithExistingId() throws Exception {
        // Create the ClientTag with an existing ID
        clientTag.setId(1L);
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        int databaseSizeBeforeCreate = clientTagRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClientTags() throws Exception {
        // Initialize the database
        clientTagRepository.saveAndFlush(clientTag);

        // Get all the clientTagList
        restClientTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientTag.getId().intValue())))
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
    void getClientTag() throws Exception {
        // Initialize the database
        clientTagRepository.saveAndFlush(clientTag);

        // Get the clientTag
        restClientTagMockMvc
            .perform(get(ENTITY_API_URL_ID, clientTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientTag.getId().intValue()))
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
    void getNonExistingClientTag() throws Exception {
        // Get the clientTag
        restClientTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClientTag() throws Exception {
        // Initialize the database
        clientTagRepository.saveAndFlush(clientTag);

        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();

        // Update the clientTag
        ClientTag updatedClientTag = clientTagRepository.findById(clientTag.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedClientTag are not directly saved in db
        em.detach(updatedClientTag);
        updatedClientTag
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
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(updatedClientTag);

        restClientTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientTagDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
        ClientTag testClientTag = clientTagList.get(clientTagList.size() - 1);
        assertThat(testClientTag.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testClientTag.getTagDisplay()).isEqualTo(UPDATED_TAG_DISPLAY);
        assertThat(testClientTag.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testClientTag.getGroupDisplay()).isEqualTo(UPDATED_GROUP_DISPLAY);
        assertThat(testClientTag.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testClientTag.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientTag.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientTag.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientTag.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientTag.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingClientTag() throws Exception {
        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();
        clientTag.setId(longCount.incrementAndGet());

        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClientTag() throws Exception {
        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();
        clientTag.setId(longCount.incrementAndGet());

        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClientTag() throws Exception {
        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();
        clientTag.setId(longCount.incrementAndGet());

        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientTagDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientTagWithPatch() throws Exception {
        // Initialize the database
        clientTagRepository.saveAndFlush(clientTag);

        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();

        // Update the clientTag using partial update
        ClientTag partialUpdatedClientTag = new ClientTag();
        partialUpdatedClientTag.setId(clientTag.getId());

        partialUpdatedClientTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientTag))
            )
            .andExpect(status().isOk());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
        ClientTag testClientTag = clientTagList.get(clientTagList.size() - 1);
        assertThat(testClientTag.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testClientTag.getTagDisplay()).isEqualTo(UPDATED_TAG_DISPLAY);
        assertThat(testClientTag.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testClientTag.getGroupDisplay()).isEqualTo(UPDATED_GROUP_DISPLAY);
        assertThat(testClientTag.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testClientTag.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClientTag.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientTag.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientTag.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientTag.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateClientTagWithPatch() throws Exception {
        // Initialize the database
        clientTagRepository.saveAndFlush(clientTag);

        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();

        // Update the clientTag using partial update
        ClientTag partialUpdatedClientTag = new ClientTag();
        partialUpdatedClientTag.setId(clientTag.getId());

        partialUpdatedClientTag
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

        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientTag))
            )
            .andExpect(status().isOk());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
        ClientTag testClientTag = clientTagList.get(clientTagList.size() - 1);
        assertThat(testClientTag.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testClientTag.getTagDisplay()).isEqualTo(UPDATED_TAG_DISPLAY);
        assertThat(testClientTag.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testClientTag.getGroupDisplay()).isEqualTo(UPDATED_GROUP_DISPLAY);
        assertThat(testClientTag.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testClientTag.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientTag.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientTag.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientTag.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientTag.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingClientTag() throws Exception {
        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();
        clientTag.setId(longCount.incrementAndGet());

        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientTagDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClientTag() throws Exception {
        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();
        clientTag.setId(longCount.incrementAndGet());

        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClientTag() throws Exception {
        int databaseSizeBeforeUpdate = clientTagRepository.findAll().size();
        clientTag.setId(longCount.incrementAndGet());

        // Create the ClientTag
        ClientTagDTO clientTagDTO = clientTagMapper.toDto(clientTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientTag in the database
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClientTag() throws Exception {
        // Initialize the database
        clientTagRepository.saveAndFlush(clientTag);

        int databaseSizeBeforeDelete = clientTagRepository.findAll().size();

        // Delete the clientTag
        restClientTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientTag> clientTagList = clientTagRepository.findAll();
        assertThat(clientTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
