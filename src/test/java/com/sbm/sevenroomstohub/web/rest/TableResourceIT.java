package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.Table;
import com.sbm.sevenroomstohub.repository.TableRepository;
import com.sbm.sevenroomstohub.service.dto.TableDTO;
import com.sbm.sevenroomstohub.service.mapper.TableMapper;
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
 * Integration tests for the {@link TableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TableResourceIT {

    private static final Integer DEFAULT_TABLE_NUMBER = 1;
    private static final Integer UPDATED_TABLE_NUMBER = 2;

    private static final String ENTITY_API_URL = "/api/tables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTableMockMvc;

    private Table table;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Table createEntity(EntityManager em) {
        Table table = new Table().tableNumber(DEFAULT_TABLE_NUMBER);
        return table;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Table createUpdatedEntity(EntityManager em) {
        Table table = new Table().tableNumber(UPDATED_TABLE_NUMBER);
        return table;
    }

    @BeforeEach
    public void initTest() {
        table = createEntity(em);
    }

    @Test
    @Transactional
    void createTable() throws Exception {
        int databaseSizeBeforeCreate = tableRepository.findAll().size();
        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);
        restTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableDTO)))
            .andExpect(status().isCreated());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeCreate + 1);
        Table testTable = tableList.get(tableList.size() - 1);
        assertThat(testTable.getTableNumber()).isEqualTo(DEFAULT_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void createTableWithExistingId() throws Exception {
        // Create the Table with an existing ID
        table.setId(1L);
        TableDTO tableDTO = tableMapper.toDto(table);

        int databaseSizeBeforeCreate = tableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTables() throws Exception {
        // Initialize the database
        tableRepository.saveAndFlush(table);

        // Get all the tableList
        restTableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(table.getId().intValue())))
            .andExpect(jsonPath("$.[*].tableNumber").value(hasItem(DEFAULT_TABLE_NUMBER)));
    }

    @Test
    @Transactional
    void getTable() throws Exception {
        // Initialize the database
        tableRepository.saveAndFlush(table);

        // Get the table
        restTableMockMvc
            .perform(get(ENTITY_API_URL_ID, table.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(table.getId().intValue()))
            .andExpect(jsonPath("$.tableNumber").value(DEFAULT_TABLE_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingTable() throws Exception {
        // Get the table
        restTableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTable() throws Exception {
        // Initialize the database
        tableRepository.saveAndFlush(table);

        int databaseSizeBeforeUpdate = tableRepository.findAll().size();

        // Update the table
        Table updatedTable = tableRepository.findById(table.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTable are not directly saved in db
        em.detach(updatedTable);
        updatedTable.tableNumber(UPDATED_TABLE_NUMBER);
        TableDTO tableDTO = tableMapper.toDto(updatedTable);

        restTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tableDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableDTO))
            )
            .andExpect(status().isOk());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
        Table testTable = tableList.get(tableList.size() - 1);
        assertThat(testTable.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingTable() throws Exception {
        int databaseSizeBeforeUpdate = tableRepository.findAll().size();
        table.setId(longCount.incrementAndGet());

        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tableDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTable() throws Exception {
        int databaseSizeBeforeUpdate = tableRepository.findAll().size();
        table.setId(longCount.incrementAndGet());

        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTable() throws Exception {
        int databaseSizeBeforeUpdate = tableRepository.findAll().size();
        table.setId(longCount.incrementAndGet());

        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTableWithPatch() throws Exception {
        // Initialize the database
        tableRepository.saveAndFlush(table);

        int databaseSizeBeforeUpdate = tableRepository.findAll().size();

        // Update the table using partial update
        Table partialUpdatedTable = new Table();
        partialUpdatedTable.setId(table.getId());

        partialUpdatedTable.tableNumber(UPDATED_TABLE_NUMBER);

        restTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTable))
            )
            .andExpect(status().isOk());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
        Table testTable = tableList.get(tableList.size() - 1);
        assertThat(testTable.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateTableWithPatch() throws Exception {
        // Initialize the database
        tableRepository.saveAndFlush(table);

        int databaseSizeBeforeUpdate = tableRepository.findAll().size();

        // Update the table using partial update
        Table partialUpdatedTable = new Table();
        partialUpdatedTable.setId(table.getId());

        partialUpdatedTable.tableNumber(UPDATED_TABLE_NUMBER);

        restTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTable))
            )
            .andExpect(status().isOk());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
        Table testTable = tableList.get(tableList.size() - 1);
        assertThat(testTable.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingTable() throws Exception {
        int databaseSizeBeforeUpdate = tableRepository.findAll().size();
        table.setId(longCount.incrementAndGet());

        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tableDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTable() throws Exception {
        int databaseSizeBeforeUpdate = tableRepository.findAll().size();
        table.setId(longCount.incrementAndGet());

        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTable() throws Exception {
        int databaseSizeBeforeUpdate = tableRepository.findAll().size();
        table.setId(longCount.incrementAndGet());

        // Create the Table
        TableDTO tableDTO = tableMapper.toDto(table);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tableDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Table in the database
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTable() throws Exception {
        // Initialize the database
        tableRepository.saveAndFlush(table);

        int databaseSizeBeforeDelete = tableRepository.findAll().size();

        // Delete the table
        restTableMockMvc
            .perform(delete(ENTITY_API_URL_ID, table.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Table> tableList = tableRepository.findAll();
        assertThat(tableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
