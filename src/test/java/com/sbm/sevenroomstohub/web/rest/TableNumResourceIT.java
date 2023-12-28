package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.TableNum;
import com.sbm.sevenroomstohub.repository.TableNumRepository;
import com.sbm.sevenroomstohub.service.dto.TableNumDTO;
import com.sbm.sevenroomstohub.service.mapper.TableNumMapper;
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
 * Integration tests for the {@link TableNumResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TableNumResourceIT {

    private static final Integer DEFAULT_TABLE_NUMBER = 1;
    private static final Integer UPDATED_TABLE_NUMBER = 2;

    private static final String ENTITY_API_URL = "/api/table-nums";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TableNumRepository tableNumRepository;

    @Autowired
    private TableNumMapper tableNumMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTableNumMockMvc;

    private TableNum tableNum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableNum createEntity(EntityManager em) {
        TableNum tableNum = new TableNum().tableNumber(DEFAULT_TABLE_NUMBER);
        return tableNum;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableNum createUpdatedEntity(EntityManager em) {
        TableNum tableNum = new TableNum().tableNumber(UPDATED_TABLE_NUMBER);
        return tableNum;
    }

    @BeforeEach
    public void initTest() {
        tableNum = createEntity(em);
    }

    @Test
    @Transactional
    void createTableNum() throws Exception {
        int databaseSizeBeforeCreate = tableNumRepository.findAll().size();
        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);
        restTableNumMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableNumDTO)))
            .andExpect(status().isCreated());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeCreate + 1);
        TableNum testTableNum = tableNumList.get(tableNumList.size() - 1);
        assertThat(testTableNum.getTableNumber()).isEqualTo(DEFAULT_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void createTableNumWithExistingId() throws Exception {
        // Create the TableNum with an existing ID
        tableNum.setId(1L);
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        int databaseSizeBeforeCreate = tableNumRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTableNumMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableNumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTableNums() throws Exception {
        // Initialize the database
        tableNumRepository.saveAndFlush(tableNum);

        // Get all the tableNumList
        restTableNumMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableNum.getId().intValue())))
            .andExpect(jsonPath("$.[*].tableNumber").value(hasItem(DEFAULT_TABLE_NUMBER)));
    }

    @Test
    @Transactional
    void getTableNum() throws Exception {
        // Initialize the database
        tableNumRepository.saveAndFlush(tableNum);

        // Get the tableNum
        restTableNumMockMvc
            .perform(get(ENTITY_API_URL_ID, tableNum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tableNum.getId().intValue()))
            .andExpect(jsonPath("$.tableNumber").value(DEFAULT_TABLE_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingTableNum() throws Exception {
        // Get the tableNum
        restTableNumMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTableNum() throws Exception {
        // Initialize the database
        tableNumRepository.saveAndFlush(tableNum);

        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();

        // Update the tableNum
        TableNum updatedTableNum = tableNumRepository.findById(tableNum.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTableNum are not directly saved in db
        em.detach(updatedTableNum);
        updatedTableNum.tableNumber(UPDATED_TABLE_NUMBER);
        TableNumDTO tableNumDTO = tableNumMapper.toDto(updatedTableNum);

        restTableNumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tableNumDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableNumDTO))
            )
            .andExpect(status().isOk());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
        TableNum testTableNum = tableNumList.get(tableNumList.size() - 1);
        assertThat(testTableNum.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingTableNum() throws Exception {
        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();
        tableNum.setId(longCount.incrementAndGet());

        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableNumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tableNumDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableNumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTableNum() throws Exception {
        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();
        tableNum.setId(longCount.incrementAndGet());

        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tableNumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTableNum() throws Exception {
        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();
        tableNum.setId(longCount.incrementAndGet());

        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tableNumDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTableNumWithPatch() throws Exception {
        // Initialize the database
        tableNumRepository.saveAndFlush(tableNum);

        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();

        // Update the tableNum using partial update
        TableNum partialUpdatedTableNum = new TableNum();
        partialUpdatedTableNum.setId(tableNum.getId());

        restTableNumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTableNum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTableNum))
            )
            .andExpect(status().isOk());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
        TableNum testTableNum = tableNumList.get(tableNumList.size() - 1);
        assertThat(testTableNum.getTableNumber()).isEqualTo(DEFAULT_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateTableNumWithPatch() throws Exception {
        // Initialize the database
        tableNumRepository.saveAndFlush(tableNum);

        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();

        // Update the tableNum using partial update
        TableNum partialUpdatedTableNum = new TableNum();
        partialUpdatedTableNum.setId(tableNum.getId());

        partialUpdatedTableNum.tableNumber(UPDATED_TABLE_NUMBER);

        restTableNumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTableNum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTableNum))
            )
            .andExpect(status().isOk());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
        TableNum testTableNum = tableNumList.get(tableNumList.size() - 1);
        assertThat(testTableNum.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingTableNum() throws Exception {
        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();
        tableNum.setId(longCount.incrementAndGet());

        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableNumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tableNumDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tableNumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTableNum() throws Exception {
        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();
        tableNum.setId(longCount.incrementAndGet());

        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tableNumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTableNum() throws Exception {
        int databaseSizeBeforeUpdate = tableNumRepository.findAll().size();
        tableNum.setId(longCount.incrementAndGet());

        // Create the TableNum
        TableNumDTO tableNumDTO = tableNumMapper.toDto(tableNum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTableNumMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tableNumDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TableNum in the database
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTableNum() throws Exception {
        // Initialize the database
        tableNumRepository.saveAndFlush(tableNum);

        int databaseSizeBeforeDelete = tableNumRepository.findAll().size();

        // Delete the tableNum
        restTableNumMockMvc
            .perform(delete(ENTITY_API_URL_ID, tableNum.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TableNum> tableNumList = tableNumRepository.findAll();
        assertThat(tableNumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
