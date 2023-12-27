package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.TableNumber;
import com.sbm.sevenroomstohub.repository.TableNumberRepository;
import com.sbm.sevenroomstohub.service.dto.TableNumberDTO;
import com.sbm.sevenroomstohub.service.mapper.TableNumberMapper;
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
 * Integration tests for the {@link TableNumberResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TableNumberResourceIT {

    private static final Integer DEFAULT_TABLE_NUM = 1;
    private static final Integer UPDATED_TABLE_NUM = 2;

    private static final String ENTITY_API_URL = "/api/table-numbers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TableNumberRepository tableNumberRepository;

    @Autowired
    private TableNumberMapper tableNumberMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTableNumberMockMvc;

    private TableNumber tableNumber;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableNumber createEntity(EntityManager em) {
        TableNumber tableNumber = new TableNumber().tableNum(DEFAULT_TABLE_NUM);
        return tableNumber;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableNumber createUpdatedEntity(EntityManager em) {
        TableNumber tableNumber = new TableNumber().tableNum(UPDATED_TABLE_NUM);
        return tableNumber;
    }

    @BeforeEach
    public void initTest() {
        tableNumber = createEntity(em);
    }

    @Test
    @Transactional
    void createTableNumber() throws Exception {
        int databaseSizeBeforeCreate = tableNumberRepository.findAll().size();
        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);
        restTableNumberMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeCreate + 1);
        TableNumber testTableNumber = tableNumberList.get(tableNumberList.size() - 1);
        assertThat(testTableNumber.getTableNum()).isEqualTo(DEFAULT_TABLE_NUM);
    }

    @Test
    @Transactional
    void createTableNumberWithExistingId() throws Exception {
        // Create the TableNumber with an existing ID
        tableNumber.setId(1L);
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        int databaseSizeBeforeCreate = tableNumberRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTableNumberMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTableNumbers() throws Exception {
        // Initialize the database
        tableNumberRepository.saveAndFlush(tableNumber);

        // Get all the tableNumberList
        restTableNumberMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableNumber.getId().intValue())))
            .andExpect(jsonPath("$.[*].tableNum").value(hasItem(DEFAULT_TABLE_NUM)));
    }

    @Test
    @Transactional
    void getTableNumber() throws Exception {
        // Initialize the database
        tableNumberRepository.saveAndFlush(tableNumber);

        // Get the tableNumber
        restTableNumberMockMvc
            .perform(get(ENTITY_API_URL_ID, tableNumber.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tableNumber.getId().intValue()))
            .andExpect(jsonPath("$.tableNum").value(DEFAULT_TABLE_NUM));
    }

    @Test
    @Transactional
    void getNonExistingTableNumber() throws Exception {
        // Get the tableNumber
        restTableNumberMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTableNumber() throws Exception {
        // Initialize the database
        tableNumberRepository.saveAndFlush(tableNumber);

        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();

        // Update the tableNumber
        TableNumber updatedTableNumber = tableNumberRepository.findById(tableNumber.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTableNumber are not directly saved in db
        em.detach(updatedTableNumber);
        updatedTableNumber.tableNum(UPDATED_TABLE_NUM);
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(updatedTableNumber);

        restTableNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tableNumberDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isOk());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
        TableNumber testTableNumber = tableNumberList.get(tableNumberList.size() - 1);
        assertThat(testTableNumber.getTableNum()).isEqualTo(UPDATED_TABLE_NUM);
    }

    @Test
    @Transactional
    void putNonExistingTableNumber() throws Exception {
        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();
        tableNumber.setId(longCount.incrementAndGet());

        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tableNumberDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTableNumber() throws Exception {
        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();
        tableNumber.setId(longCount.incrementAndGet());

        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTableNumber() throws Exception {
        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();
        tableNumber.setId(longCount.incrementAndGet());

        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumberMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableNumberDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTableNumberWithPatch() throws Exception {
        // Initialize the database
        tableNumberRepository.saveAndFlush(tableNumber);

        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();

        // Update the tableNumber using partial update
        TableNumber partialUpdatedTableNumber = new TableNumber();
        partialUpdatedTableNumber.setId(tableNumber.getId());

        partialUpdatedTableNumber.tableNum(UPDATED_TABLE_NUM);

        restTableNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTableNumber.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTableNumber))
            )
            .andExpect(status().isOk());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
        TableNumber testTableNumber = tableNumberList.get(tableNumberList.size() - 1);
        assertThat(testTableNumber.getTableNum()).isEqualTo(UPDATED_TABLE_NUM);
    }

    @Test
    @Transactional
    void fullUpdateTableNumberWithPatch() throws Exception {
        // Initialize the database
        tableNumberRepository.saveAndFlush(tableNumber);

        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();

        // Update the tableNumber using partial update
        TableNumber partialUpdatedTableNumber = new TableNumber();
        partialUpdatedTableNumber.setId(tableNumber.getId());

        partialUpdatedTableNumber.tableNum(UPDATED_TABLE_NUM);

        restTableNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTableNumber.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTableNumber))
            )
            .andExpect(status().isOk());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
        TableNumber testTableNumber = tableNumberList.get(tableNumberList.size() - 1);
        assertThat(testTableNumber.getTableNum()).isEqualTo(UPDATED_TABLE_NUM);
    }

    @Test
    @Transactional
    void patchNonExistingTableNumber() throws Exception {
        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();
        tableNumber.setId(longCount.incrementAndGet());

        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tableNumberDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTableNumber() throws Exception {
        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();
        tableNumber.setId(longCount.incrementAndGet());

        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTableNumber() throws Exception {
        int databaseSizeBeforeUpdate = tableNumberRepository.findAll().size();
        tableNumber.setId(longCount.incrementAndGet());

        // Create the TableNumber
        TableNumberDTO tableNumberDTO = tableNumberMapper.toDto(tableNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumberMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tableNumberDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TableNumber in the database
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTableNumber() throws Exception {
        // Initialize the database
        tableNumberRepository.saveAndFlush(tableNumber);

        int databaseSizeBeforeDelete = tableNumberRepository.findAll().size();

        // Delete the tableNumber
        restTableNumberMockMvc
            .perform(delete(ENTITY_API_URL_ID, tableNumber.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TableNumber> tableNumberList = tableNumberRepository.findAll();
        assertThat(tableNumberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
