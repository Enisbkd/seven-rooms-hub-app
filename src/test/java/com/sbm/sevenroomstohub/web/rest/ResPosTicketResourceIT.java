package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ResPosTicket;
import com.sbm.sevenroomstohub.repository.ResPosTicketRepository;
import com.sbm.sevenroomstohub.service.dto.ResPosTicketDTO;
import com.sbm.sevenroomstohub.service.mapper.ResPosTicketMapper;
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
 * Integration tests for the {@link ResPosTicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResPosTicketResourceIT {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Double DEFAULT_ADMIN_FEE = 1D;
    private static final Double UPDATED_ADMIN_FEE = 2D;

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final String DEFAULT_TABLE_NO = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX = 1D;
    private static final Double UPDATED_TAX = 2D;

    private static final Integer DEFAULT_BUSINESS_ID = 1;
    private static final Integer UPDATED_BUSINESS_ID = 2;

    private static final String DEFAULT_LOCAL_POSTICKET_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_POSTICKET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    private static final Double DEFAULT_SUBTOTAL = 1D;
    private static final Double UPDATED_SUBTOTAL = 2D;

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final Double DEFAULT_SERVICE_CHARGE = 1D;
    private static final Double UPDATED_SERVICE_CHARGE = 2D;

    private static final String DEFAULT_ENDTIME = "AAAAAAAAAA";
    private static final String UPDATED_ENDTIME = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/res-pos-tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResPosTicketRepository resPosTicketRepository;

    @Autowired
    private ResPosTicketMapper resPosTicketMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResPosTicketMockMvc;

    private ResPosTicket resPosTicket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResPosTicket createEntity(EntityManager em) {
        ResPosTicket resPosTicket = new ResPosTicket()
            .status(DEFAULT_STATUS)
            .adminFee(DEFAULT_ADMIN_FEE)
            .code(DEFAULT_CODE)
            .tableNo(DEFAULT_TABLE_NO)
            .tax(DEFAULT_TAX)
            .businessId(DEFAULT_BUSINESS_ID)
            .localPosticketId(DEFAULT_LOCAL_POSTICKET_ID)
            .employeeName(DEFAULT_EMPLOYEE_NAME)
            .total(DEFAULT_TOTAL)
            .subtotal(DEFAULT_SUBTOTAL)
            .startTime(DEFAULT_START_TIME)
            .serviceCharge(DEFAULT_SERVICE_CHARGE)
            .endtime(DEFAULT_ENDTIME)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return resPosTicket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResPosTicket createUpdatedEntity(EntityManager em) {
        ResPosTicket resPosTicket = new ResPosTicket()
            .status(UPDATED_STATUS)
            .adminFee(UPDATED_ADMIN_FEE)
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .businessId(UPDATED_BUSINESS_ID)
            .localPosticketId(UPDATED_LOCAL_POSTICKET_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .subtotal(UPDATED_SUBTOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return resPosTicket;
    }

    @BeforeEach
    public void initTest() {
        resPosTicket = createEntity(em);
    }

    @Test
    @Transactional
    void createResPosTicket() throws Exception {
        int databaseSizeBeforeCreate = resPosTicketRepository.findAll().size();
        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);
        restResPosTicketMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeCreate + 1);
        ResPosTicket testResPosTicket = resPosTicketList.get(resPosTicketList.size() - 1);
        assertThat(testResPosTicket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResPosTicket.getAdminFee()).isEqualTo(DEFAULT_ADMIN_FEE);
        assertThat(testResPosTicket.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testResPosTicket.getTableNo()).isEqualTo(DEFAULT_TABLE_NO);
        assertThat(testResPosTicket.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testResPosTicket.getBusinessId()).isEqualTo(DEFAULT_BUSINESS_ID);
        assertThat(testResPosTicket.getLocalPosticketId()).isEqualTo(DEFAULT_LOCAL_POSTICKET_ID);
        assertThat(testResPosTicket.getEmployeeName()).isEqualTo(DEFAULT_EMPLOYEE_NAME);
        assertThat(testResPosTicket.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testResPosTicket.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testResPosTicket.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testResPosTicket.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testResPosTicket.getEndtime()).isEqualTo(DEFAULT_ENDTIME);
        assertThat(testResPosTicket.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testResPosTicket.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testResPosTicket.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testResPosTicket.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testResPosTicket.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createResPosTicketWithExistingId() throws Exception {
        // Create the ResPosTicket with an existing ID
        resPosTicket.setId(1L);
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        int databaseSizeBeforeCreate = resPosTicketRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResPosTicketMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResPosTickets() throws Exception {
        // Initialize the database
        resPosTicketRepository.saveAndFlush(resPosTicket);

        // Get all the resPosTicketList
        restResPosTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resPosTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].adminFee").value(hasItem(DEFAULT_ADMIN_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].tableNo").value(hasItem(DEFAULT_TABLE_NO)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].businessId").value(hasItem(DEFAULT_BUSINESS_ID)))
            .andExpect(jsonPath("$.[*].localPosticketId").value(hasItem(DEFAULT_LOCAL_POSTICKET_ID)))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME)))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getResPosTicket() throws Exception {
        // Initialize the database
        resPosTicketRepository.saveAndFlush(resPosTicket);

        // Get the resPosTicket
        restResPosTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, resPosTicket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resPosTicket.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.adminFee").value(DEFAULT_ADMIN_FEE.doubleValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.tableNo").value(DEFAULT_TABLE_NO))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.doubleValue()))
            .andExpect(jsonPath("$.businessId").value(DEFAULT_BUSINESS_ID))
            .andExpect(jsonPath("$.localPosticketId").value(DEFAULT_LOCAL_POSTICKET_ID))
            .andExpect(jsonPath("$.employeeName").value(DEFAULT_EMPLOYEE_NAME))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.doubleValue()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingResPosTicket() throws Exception {
        // Get the resPosTicket
        restResPosTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResPosTicket() throws Exception {
        // Initialize the database
        resPosTicketRepository.saveAndFlush(resPosTicket);

        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();

        // Update the resPosTicket
        ResPosTicket updatedResPosTicket = resPosTicketRepository.findById(resPosTicket.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResPosTicket are not directly saved in db
        em.detach(updatedResPosTicket);
        updatedResPosTicket
            .status(UPDATED_STATUS)
            .adminFee(UPDATED_ADMIN_FEE)
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .businessId(UPDATED_BUSINESS_ID)
            .localPosticketId(UPDATED_LOCAL_POSTICKET_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .subtotal(UPDATED_SUBTOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(updatedResPosTicket);

        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resPosTicketDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
        ResPosTicket testResPosTicket = resPosTicketList.get(resPosTicketList.size() - 1);
        assertThat(testResPosTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResPosTicket.getAdminFee()).isEqualTo(UPDATED_ADMIN_FEE);
        assertThat(testResPosTicket.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testResPosTicket.getTableNo()).isEqualTo(UPDATED_TABLE_NO);
        assertThat(testResPosTicket.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testResPosTicket.getBusinessId()).isEqualTo(UPDATED_BUSINESS_ID);
        assertThat(testResPosTicket.getLocalPosticketId()).isEqualTo(UPDATED_LOCAL_POSTICKET_ID);
        assertThat(testResPosTicket.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testResPosTicket.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testResPosTicket.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testResPosTicket.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testResPosTicket.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testResPosTicket.getEndtime()).isEqualTo(UPDATED_ENDTIME);
        assertThat(testResPosTicket.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResPosTicket.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResPosTicket.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResPosTicket.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResPosTicket.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingResPosTicket() throws Exception {
        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();
        resPosTicket.setId(longCount.incrementAndGet());

        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resPosTicketDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResPosTicket() throws Exception {
        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();
        resPosTicket.setId(longCount.incrementAndGet());

        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResPosTicket() throws Exception {
        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();
        resPosTicket.setId(longCount.incrementAndGet());

        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResPosTicketWithPatch() throws Exception {
        // Initialize the database
        resPosTicketRepository.saveAndFlush(resPosTicket);

        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();

        // Update the resPosTicket using partial update
        ResPosTicket partialUpdatedResPosTicket = new ResPosTicket();
        partialUpdatedResPosTicket.setId(resPosTicket.getId());

        partialUpdatedResPosTicket
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING);

        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResPosTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResPosTicket))
            )
            .andExpect(status().isOk());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
        ResPosTicket testResPosTicket = resPosTicketList.get(resPosTicketList.size() - 1);
        assertThat(testResPosTicket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResPosTicket.getAdminFee()).isEqualTo(DEFAULT_ADMIN_FEE);
        assertThat(testResPosTicket.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testResPosTicket.getTableNo()).isEqualTo(UPDATED_TABLE_NO);
        assertThat(testResPosTicket.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testResPosTicket.getBusinessId()).isEqualTo(DEFAULT_BUSINESS_ID);
        assertThat(testResPosTicket.getLocalPosticketId()).isEqualTo(DEFAULT_LOCAL_POSTICKET_ID);
        assertThat(testResPosTicket.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testResPosTicket.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testResPosTicket.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testResPosTicket.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testResPosTicket.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testResPosTicket.getEndtime()).isEqualTo(UPDATED_ENDTIME);
        assertThat(testResPosTicket.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testResPosTicket.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResPosTicket.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResPosTicket.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResPosTicket.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateResPosTicketWithPatch() throws Exception {
        // Initialize the database
        resPosTicketRepository.saveAndFlush(resPosTicket);

        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();

        // Update the resPosTicket using partial update
        ResPosTicket partialUpdatedResPosTicket = new ResPosTicket();
        partialUpdatedResPosTicket.setId(resPosTicket.getId());

        partialUpdatedResPosTicket
            .status(UPDATED_STATUS)
            .adminFee(UPDATED_ADMIN_FEE)
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .businessId(UPDATED_BUSINESS_ID)
            .localPosticketId(UPDATED_LOCAL_POSTICKET_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .subtotal(UPDATED_SUBTOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResPosTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResPosTicket))
            )
            .andExpect(status().isOk());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
        ResPosTicket testResPosTicket = resPosTicketList.get(resPosTicketList.size() - 1);
        assertThat(testResPosTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResPosTicket.getAdminFee()).isEqualTo(UPDATED_ADMIN_FEE);
        assertThat(testResPosTicket.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testResPosTicket.getTableNo()).isEqualTo(UPDATED_TABLE_NO);
        assertThat(testResPosTicket.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testResPosTicket.getBusinessId()).isEqualTo(UPDATED_BUSINESS_ID);
        assertThat(testResPosTicket.getLocalPosticketId()).isEqualTo(UPDATED_LOCAL_POSTICKET_ID);
        assertThat(testResPosTicket.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testResPosTicket.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testResPosTicket.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testResPosTicket.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testResPosTicket.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testResPosTicket.getEndtime()).isEqualTo(UPDATED_ENDTIME);
        assertThat(testResPosTicket.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testResPosTicket.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testResPosTicket.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testResPosTicket.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testResPosTicket.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingResPosTicket() throws Exception {
        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();
        resPosTicket.setId(longCount.incrementAndGet());

        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resPosTicketDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResPosTicket() throws Exception {
        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();
        resPosTicket.setId(longCount.incrementAndGet());

        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResPosTicket() throws Exception {
        int databaseSizeBeforeUpdate = resPosTicketRepository.findAll().size();
        resPosTicket.setId(longCount.incrementAndGet());

        // Create the ResPosTicket
        ResPosTicketDTO resPosTicketDTO = resPosTicketMapper.toDto(resPosTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resPosTicketDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResPosTicket in the database
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResPosTicket() throws Exception {
        // Initialize the database
        resPosTicketRepository.saveAndFlush(resPosTicket);

        int databaseSizeBeforeDelete = resPosTicketRepository.findAll().size();

        // Delete the resPosTicket
        restResPosTicketMockMvc
            .perform(delete(ENTITY_API_URL_ID, resPosTicket.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResPosTicket> resPosTicketList = resPosTicketRepository.findAll();
        assertThat(resPosTicketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
