package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ClientPhoto;
import com.sbm.sevenroomstohub.repository.ClientPhotoRepository;
import com.sbm.sevenroomstohub.service.dto.ClientPhotoDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientPhotoMapper;
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
 * Integration tests for the {@link ClientPhotoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientPhotoResourceIT {

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LARGE = "AAAAAAAAAA";
    private static final String UPDATED_LARGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LARGE_HEIGHT = 1;
    private static final Integer UPDATED_LARGE_HEIGHT = 2;

    private static final Integer DEFAULT_LARGE_WIDTH = 1;
    private static final Integer UPDATED_LARGE_WIDTH = 2;

    private static final String DEFAULT_MEDIUM = "AAAAAAAAAA";
    private static final String UPDATED_MEDIUM = "BBBBBBBBBB";

    private static final Integer DEFAULT_MEDIUM_HEIGHT = 1;
    private static final Integer UPDATED_MEDIUM_HEIGHT = 2;

    private static final Integer DEFAULT_MEDIUM_WIDTH = 1;
    private static final Integer UPDATED_MEDIUM_WIDTH = 2;

    private static final String DEFAULT_SMALL = "AAAAAAAAAA";
    private static final String UPDATED_SMALL = "BBBBBBBBBB";

    private static final Integer DEFAULT_SMALL_HEIGHT = 1;
    private static final Integer UPDATED_SMALL_HEIGHT = 2;

    private static final Integer DEFAULT_SMALL_WIDTH = 1;
    private static final Integer UPDATED_SMALL_WIDTH = 2;

    private static final String DEFAULT_RAW = "AAAAAAAAAA";
    private static final String UPDATED_RAW = "BBBBBBBBBB";

    private static final Integer DEFAULT_CROPX = 1;
    private static final Integer UPDATED_CROPX = 2;

    private static final Integer DEFAULT_CROPY = 1;
    private static final Integer UPDATED_CROPY = 2;

    private static final Double DEFAULT_CROP_HEIGHT = 1D;
    private static final Double UPDATED_CROP_HEIGHT = 2D;

    private static final Double DEFAULT_CROP_WIDTH = 1D;
    private static final Double UPDATED_CROP_WIDTH = 2D;

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

    private static final String ENTITY_API_URL = "/api/client-photos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientPhotoRepository clientPhotoRepository;

    @Autowired
    private ClientPhotoMapper clientPhotoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientPhotoMockMvc;

    private ClientPhoto clientPhoto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientPhoto createEntity(EntityManager em) {
        ClientPhoto clientPhoto = new ClientPhoto()
            .clientId(DEFAULT_CLIENT_ID)
            .large(DEFAULT_LARGE)
            .largeHeight(DEFAULT_LARGE_HEIGHT)
            .largeWidth(DEFAULT_LARGE_WIDTH)
            .medium(DEFAULT_MEDIUM)
            .mediumHeight(DEFAULT_MEDIUM_HEIGHT)
            .mediumWidth(DEFAULT_MEDIUM_WIDTH)
            .small(DEFAULT_SMALL)
            .smallHeight(DEFAULT_SMALL_HEIGHT)
            .smallWidth(DEFAULT_SMALL_WIDTH)
            .raw(DEFAULT_RAW)
            .cropx(DEFAULT_CROPX)
            .cropy(DEFAULT_CROPY)
            .cropHeight(DEFAULT_CROP_HEIGHT)
            .cropWidth(DEFAULT_CROP_WIDTH)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return clientPhoto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientPhoto createUpdatedEntity(EntityManager em) {
        ClientPhoto clientPhoto = new ClientPhoto()
            .clientId(UPDATED_CLIENT_ID)
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .mediumWidth(UPDATED_MEDIUM_WIDTH)
            .small(UPDATED_SMALL)
            .smallHeight(UPDATED_SMALL_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .raw(UPDATED_RAW)
            .cropx(UPDATED_CROPX)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return clientPhoto;
    }

    @BeforeEach
    public void initTest() {
        clientPhoto = createEntity(em);
    }

    @Test
    @Transactional
    void createClientPhoto() throws Exception {
        int databaseSizeBeforeCreate = clientPhotoRepository.findAll().size();
        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);
        restClientPhotoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeCreate + 1);
        ClientPhoto testClientPhoto = clientPhotoList.get(clientPhotoList.size() - 1);
        assertThat(testClientPhoto.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testClientPhoto.getLarge()).isEqualTo(DEFAULT_LARGE);
        assertThat(testClientPhoto.getLargeHeight()).isEqualTo(DEFAULT_LARGE_HEIGHT);
        assertThat(testClientPhoto.getLargeWidth()).isEqualTo(DEFAULT_LARGE_WIDTH);
        assertThat(testClientPhoto.getMedium()).isEqualTo(DEFAULT_MEDIUM);
        assertThat(testClientPhoto.getMediumHeight()).isEqualTo(DEFAULT_MEDIUM_HEIGHT);
        assertThat(testClientPhoto.getMediumWidth()).isEqualTo(DEFAULT_MEDIUM_WIDTH);
        assertThat(testClientPhoto.getSmall()).isEqualTo(DEFAULT_SMALL);
        assertThat(testClientPhoto.getSmallHeight()).isEqualTo(DEFAULT_SMALL_HEIGHT);
        assertThat(testClientPhoto.getSmallWidth()).isEqualTo(DEFAULT_SMALL_WIDTH);
        assertThat(testClientPhoto.getRaw()).isEqualTo(DEFAULT_RAW);
        assertThat(testClientPhoto.getCropx()).isEqualTo(DEFAULT_CROPX);
        assertThat(testClientPhoto.getCropy()).isEqualTo(DEFAULT_CROPY);
        assertThat(testClientPhoto.getCropHeight()).isEqualTo(DEFAULT_CROP_HEIGHT);
        assertThat(testClientPhoto.getCropWidth()).isEqualTo(DEFAULT_CROP_WIDTH);
        assertThat(testClientPhoto.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClientPhoto.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testClientPhoto.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testClientPhoto.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testClientPhoto.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createClientPhotoWithExistingId() throws Exception {
        // Create the ClientPhoto with an existing ID
        clientPhoto.setId(1L);
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        int databaseSizeBeforeCreate = clientPhotoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientPhotoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClientPhotos() throws Exception {
        // Initialize the database
        clientPhotoRepository.saveAndFlush(clientPhoto);

        // Get all the clientPhotoList
        restClientPhotoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientPhoto.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].large").value(hasItem(DEFAULT_LARGE)))
            .andExpect(jsonPath("$.[*].largeHeight").value(hasItem(DEFAULT_LARGE_HEIGHT)))
            .andExpect(jsonPath("$.[*].largeWidth").value(hasItem(DEFAULT_LARGE_WIDTH)))
            .andExpect(jsonPath("$.[*].medium").value(hasItem(DEFAULT_MEDIUM)))
            .andExpect(jsonPath("$.[*].mediumHeight").value(hasItem(DEFAULT_MEDIUM_HEIGHT)))
            .andExpect(jsonPath("$.[*].mediumWidth").value(hasItem(DEFAULT_MEDIUM_WIDTH)))
            .andExpect(jsonPath("$.[*].small").value(hasItem(DEFAULT_SMALL)))
            .andExpect(jsonPath("$.[*].smallHeight").value(hasItem(DEFAULT_SMALL_HEIGHT)))
            .andExpect(jsonPath("$.[*].smallWidth").value(hasItem(DEFAULT_SMALL_WIDTH)))
            .andExpect(jsonPath("$.[*].raw").value(hasItem(DEFAULT_RAW)))
            .andExpect(jsonPath("$.[*].cropx").value(hasItem(DEFAULT_CROPX)))
            .andExpect(jsonPath("$.[*].cropy").value(hasItem(DEFAULT_CROPY)))
            .andExpect(jsonPath("$.[*].cropHeight").value(hasItem(DEFAULT_CROP_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].cropWidth").value(hasItem(DEFAULT_CROP_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getClientPhoto() throws Exception {
        // Initialize the database
        clientPhotoRepository.saveAndFlush(clientPhoto);

        // Get the clientPhoto
        restClientPhotoMockMvc
            .perform(get(ENTITY_API_URL_ID, clientPhoto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientPhoto.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.large").value(DEFAULT_LARGE))
            .andExpect(jsonPath("$.largeHeight").value(DEFAULT_LARGE_HEIGHT))
            .andExpect(jsonPath("$.largeWidth").value(DEFAULT_LARGE_WIDTH))
            .andExpect(jsonPath("$.medium").value(DEFAULT_MEDIUM))
            .andExpect(jsonPath("$.mediumHeight").value(DEFAULT_MEDIUM_HEIGHT))
            .andExpect(jsonPath("$.mediumWidth").value(DEFAULT_MEDIUM_WIDTH))
            .andExpect(jsonPath("$.small").value(DEFAULT_SMALL))
            .andExpect(jsonPath("$.smallHeight").value(DEFAULT_SMALL_HEIGHT))
            .andExpect(jsonPath("$.smallWidth").value(DEFAULT_SMALL_WIDTH))
            .andExpect(jsonPath("$.raw").value(DEFAULT_RAW))
            .andExpect(jsonPath("$.cropx").value(DEFAULT_CROPX))
            .andExpect(jsonPath("$.cropy").value(DEFAULT_CROPY))
            .andExpect(jsonPath("$.cropHeight").value(DEFAULT_CROP_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.cropWidth").value(DEFAULT_CROP_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingClientPhoto() throws Exception {
        // Get the clientPhoto
        restClientPhotoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClientPhoto() throws Exception {
        // Initialize the database
        clientPhotoRepository.saveAndFlush(clientPhoto);

        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();

        // Update the clientPhoto
        ClientPhoto updatedClientPhoto = clientPhotoRepository.findById(clientPhoto.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedClientPhoto are not directly saved in db
        em.detach(updatedClientPhoto);
        updatedClientPhoto
            .clientId(UPDATED_CLIENT_ID)
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .mediumWidth(UPDATED_MEDIUM_WIDTH)
            .small(UPDATED_SMALL)
            .smallHeight(UPDATED_SMALL_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .raw(UPDATED_RAW)
            .cropx(UPDATED_CROPX)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(updatedClientPhoto);

        restClientPhotoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientPhotoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
        ClientPhoto testClientPhoto = clientPhotoList.get(clientPhotoList.size() - 1);
        assertThat(testClientPhoto.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testClientPhoto.getLarge()).isEqualTo(UPDATED_LARGE);
        assertThat(testClientPhoto.getLargeHeight()).isEqualTo(UPDATED_LARGE_HEIGHT);
        assertThat(testClientPhoto.getLargeWidth()).isEqualTo(UPDATED_LARGE_WIDTH);
        assertThat(testClientPhoto.getMedium()).isEqualTo(UPDATED_MEDIUM);
        assertThat(testClientPhoto.getMediumHeight()).isEqualTo(UPDATED_MEDIUM_HEIGHT);
        assertThat(testClientPhoto.getMediumWidth()).isEqualTo(UPDATED_MEDIUM_WIDTH);
        assertThat(testClientPhoto.getSmall()).isEqualTo(UPDATED_SMALL);
        assertThat(testClientPhoto.getSmallHeight()).isEqualTo(UPDATED_SMALL_HEIGHT);
        assertThat(testClientPhoto.getSmallWidth()).isEqualTo(UPDATED_SMALL_WIDTH);
        assertThat(testClientPhoto.getRaw()).isEqualTo(UPDATED_RAW);
        assertThat(testClientPhoto.getCropx()).isEqualTo(UPDATED_CROPX);
        assertThat(testClientPhoto.getCropy()).isEqualTo(UPDATED_CROPY);
        assertThat(testClientPhoto.getCropHeight()).isEqualTo(UPDATED_CROP_HEIGHT);
        assertThat(testClientPhoto.getCropWidth()).isEqualTo(UPDATED_CROP_WIDTH);
        assertThat(testClientPhoto.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientPhoto.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientPhoto.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientPhoto.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientPhoto.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingClientPhoto() throws Exception {
        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();
        clientPhoto.setId(longCount.incrementAndGet());

        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientPhotoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClientPhoto() throws Exception {
        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();
        clientPhoto.setId(longCount.incrementAndGet());

        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClientPhoto() throws Exception {
        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();
        clientPhoto.setId(longCount.incrementAndGet());

        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientPhotoWithPatch() throws Exception {
        // Initialize the database
        clientPhotoRepository.saveAndFlush(clientPhoto);

        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();

        // Update the clientPhoto using partial update
        ClientPhoto partialUpdatedClientPhoto = new ClientPhoto();
        partialUpdatedClientPhoto.setId(clientPhoto.getId());

        partialUpdatedClientPhoto
            .clientId(UPDATED_CLIENT_ID)
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING);

        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientPhoto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientPhoto))
            )
            .andExpect(status().isOk());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
        ClientPhoto testClientPhoto = clientPhotoList.get(clientPhotoList.size() - 1);
        assertThat(testClientPhoto.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testClientPhoto.getLarge()).isEqualTo(UPDATED_LARGE);
        assertThat(testClientPhoto.getLargeHeight()).isEqualTo(UPDATED_LARGE_HEIGHT);
        assertThat(testClientPhoto.getLargeWidth()).isEqualTo(UPDATED_LARGE_WIDTH);
        assertThat(testClientPhoto.getMedium()).isEqualTo(UPDATED_MEDIUM);
        assertThat(testClientPhoto.getMediumHeight()).isEqualTo(DEFAULT_MEDIUM_HEIGHT);
        assertThat(testClientPhoto.getMediumWidth()).isEqualTo(DEFAULT_MEDIUM_WIDTH);
        assertThat(testClientPhoto.getSmall()).isEqualTo(DEFAULT_SMALL);
        assertThat(testClientPhoto.getSmallHeight()).isEqualTo(DEFAULT_SMALL_HEIGHT);
        assertThat(testClientPhoto.getSmallWidth()).isEqualTo(UPDATED_SMALL_WIDTH);
        assertThat(testClientPhoto.getRaw()).isEqualTo(DEFAULT_RAW);
        assertThat(testClientPhoto.getCropx()).isEqualTo(DEFAULT_CROPX);
        assertThat(testClientPhoto.getCropy()).isEqualTo(UPDATED_CROPY);
        assertThat(testClientPhoto.getCropHeight()).isEqualTo(UPDATED_CROP_HEIGHT);
        assertThat(testClientPhoto.getCropWidth()).isEqualTo(DEFAULT_CROP_WIDTH);
        assertThat(testClientPhoto.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClientPhoto.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientPhoto.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testClientPhoto.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientPhoto.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateClientPhotoWithPatch() throws Exception {
        // Initialize the database
        clientPhotoRepository.saveAndFlush(clientPhoto);

        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();

        // Update the clientPhoto using partial update
        ClientPhoto partialUpdatedClientPhoto = new ClientPhoto();
        partialUpdatedClientPhoto.setId(clientPhoto.getId());

        partialUpdatedClientPhoto
            .clientId(UPDATED_CLIENT_ID)
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .mediumWidth(UPDATED_MEDIUM_WIDTH)
            .small(UPDATED_SMALL)
            .smallHeight(UPDATED_SMALL_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .raw(UPDATED_RAW)
            .cropx(UPDATED_CROPX)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientPhoto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientPhoto))
            )
            .andExpect(status().isOk());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
        ClientPhoto testClientPhoto = clientPhotoList.get(clientPhotoList.size() - 1);
        assertThat(testClientPhoto.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testClientPhoto.getLarge()).isEqualTo(UPDATED_LARGE);
        assertThat(testClientPhoto.getLargeHeight()).isEqualTo(UPDATED_LARGE_HEIGHT);
        assertThat(testClientPhoto.getLargeWidth()).isEqualTo(UPDATED_LARGE_WIDTH);
        assertThat(testClientPhoto.getMedium()).isEqualTo(UPDATED_MEDIUM);
        assertThat(testClientPhoto.getMediumHeight()).isEqualTo(UPDATED_MEDIUM_HEIGHT);
        assertThat(testClientPhoto.getMediumWidth()).isEqualTo(UPDATED_MEDIUM_WIDTH);
        assertThat(testClientPhoto.getSmall()).isEqualTo(UPDATED_SMALL);
        assertThat(testClientPhoto.getSmallHeight()).isEqualTo(UPDATED_SMALL_HEIGHT);
        assertThat(testClientPhoto.getSmallWidth()).isEqualTo(UPDATED_SMALL_WIDTH);
        assertThat(testClientPhoto.getRaw()).isEqualTo(UPDATED_RAW);
        assertThat(testClientPhoto.getCropx()).isEqualTo(UPDATED_CROPX);
        assertThat(testClientPhoto.getCropy()).isEqualTo(UPDATED_CROPY);
        assertThat(testClientPhoto.getCropHeight()).isEqualTo(UPDATED_CROP_HEIGHT);
        assertThat(testClientPhoto.getCropWidth()).isEqualTo(UPDATED_CROP_WIDTH);
        assertThat(testClientPhoto.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientPhoto.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientPhoto.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientPhoto.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientPhoto.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingClientPhoto() throws Exception {
        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();
        clientPhoto.setId(longCount.incrementAndGet());

        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientPhotoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClientPhoto() throws Exception {
        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();
        clientPhoto.setId(longCount.incrementAndGet());

        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClientPhoto() throws Exception {
        int databaseSizeBeforeUpdate = clientPhotoRepository.findAll().size();
        clientPhoto.setId(longCount.incrementAndGet());

        // Create the ClientPhoto
        ClientPhotoDTO clientPhotoDTO = clientPhotoMapper.toDto(clientPhoto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientPhotoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientPhoto in the database
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClientPhoto() throws Exception {
        // Initialize the database
        clientPhotoRepository.saveAndFlush(clientPhoto);

        int databaseSizeBeforeDelete = clientPhotoRepository.findAll().size();

        // Delete the clientPhoto
        restClientPhotoMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientPhoto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientPhoto> clientPhotoList = clientPhotoRepository.findAll();
        assertThat(clientPhotoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
