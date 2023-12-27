package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import com.sbm.sevenroomstohub.repository.ClientVenueStatsRepository;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientVenueStatsMapper;
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
 * Integration tests for the {@link ClientVenueStatsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientVenueStatsResourceIT {

    private static final String DEFAULT_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_AVG_RATING = 1;
    private static final Integer UPDATED_AVG_RATING = 2;

    private static final String DEFAULT_BOOKED_BY_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_BOOKED_BY_NAMES = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_VISIT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_VISIT_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_RATINGS = 1;
    private static final Integer UPDATED_NUM_RATINGS = 2;

    private static final Integer DEFAULT_TOTAL_CANCELLATIONS = 1;
    private static final Integer UPDATED_TOTAL_CANCELLATIONS = 2;

    private static final Integer DEFAULT_TOTAL_COVERS = 1;
    private static final Integer UPDATED_TOTAL_COVERS = 2;

    private static final Integer DEFAULT_TOTAL_NO_SHOWS = 1;
    private static final Integer UPDATED_TOTAL_NO_SHOWS = 2;

    private static final Double DEFAULT_TOTAL_SPEND = 1D;
    private static final Double UPDATED_TOTAL_SPEND = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_LOCAL = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCAL = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_LOCALPER_COVER = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCALPER_COVER = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT = 2D;

    private static final Double DEFAULT_TOTAL_SPENDPER_COVER = 1D;
    private static final Double UPDATED_TOTAL_SPENDPER_COVER = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_PER_VISIT = 1D;
    private static final Double UPDATED_TOTAL_SPEND_PER_VISIT = 2D;

    private static final Integer DEFAULT_TOTAL_VISIT = 1;
    private static final Integer UPDATED_TOTAL_VISIT = 2;

    private static final Boolean DEFAULT_VENUE_MARKETING_OPTIN = false;
    private static final Boolean UPDATED_VENUE_MARKETING_OPTIN = true;

    private static final String DEFAULT_VENUE_MARKETING_OPTINTS = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_MARKETING_OPTINTS = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/client-venue-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientVenueStatsRepository clientVenueStatsRepository;

    @Autowired
    private ClientVenueStatsMapper clientVenueStatsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientVenueStatsMockMvc;

    private ClientVenueStats clientVenueStats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientVenueStats createEntity(EntityManager em) {
        ClientVenueStats clientVenueStats = new ClientVenueStats()
            .venueId(DEFAULT_VENUE_ID)
            .avgRating(DEFAULT_AVG_RATING)
            .bookedByNames(DEFAULT_BOOKED_BY_NAMES)
            .lastVisitDate(DEFAULT_LAST_VISIT_DATE)
            .numRatings(DEFAULT_NUM_RATINGS)
            .totalCancellations(DEFAULT_TOTAL_CANCELLATIONS)
            .totalCovers(DEFAULT_TOTAL_COVERS)
            .totalNoShows(DEFAULT_TOTAL_NO_SHOWS)
            .totalSpend(DEFAULT_TOTAL_SPEND)
            .totalSpendLocal(DEFAULT_TOTAL_SPEND_LOCAL)
            .totalSpendLocalperCover(DEFAULT_TOTAL_SPEND_LOCALPER_COVER)
            .totalSpendLocalPerVisit(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalSpendperCover(DEFAULT_TOTAL_SPENDPER_COVER)
            .totalSpendPerVisit(DEFAULT_TOTAL_SPEND_PER_VISIT)
            .totalVisit(DEFAULT_TOTAL_VISIT)
            .venueMarketingOptin(DEFAULT_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(DEFAULT_VENUE_MARKETING_OPTINTS)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return clientVenueStats;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientVenueStats createUpdatedEntity(EntityManager em) {
        ClientVenueStats clientVenueStats = new ClientVenueStats()
            .venueId(UPDATED_VENUE_ID)
            .avgRating(UPDATED_AVG_RATING)
            .bookedByNames(UPDATED_BOOKED_BY_NAMES)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalSpendperCover(UPDATED_TOTAL_SPENDPER_COVER)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalVisit(UPDATED_TOTAL_VISIT)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return clientVenueStats;
    }

    @BeforeEach
    public void initTest() {
        clientVenueStats = createEntity(em);
    }

    @Test
    @Transactional
    void createClientVenueStats() throws Exception {
        int databaseSizeBeforeCreate = clientVenueStatsRepository.findAll().size();
        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);
        restClientVenueStatsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeCreate + 1);
        ClientVenueStats testClientVenueStats = clientVenueStatsList.get(clientVenueStatsList.size() - 1);
        assertThat(testClientVenueStats.getVenueId()).isEqualTo(DEFAULT_VENUE_ID);
        assertThat(testClientVenueStats.getAvgRating()).isEqualTo(DEFAULT_AVG_RATING);
        assertThat(testClientVenueStats.getBookedByNames()).isEqualTo(DEFAULT_BOOKED_BY_NAMES);
        assertThat(testClientVenueStats.getLastVisitDate()).isEqualTo(DEFAULT_LAST_VISIT_DATE);
        assertThat(testClientVenueStats.getNumRatings()).isEqualTo(DEFAULT_NUM_RATINGS);
        assertThat(testClientVenueStats.getTotalCancellations()).isEqualTo(DEFAULT_TOTAL_CANCELLATIONS);
        assertThat(testClientVenueStats.getTotalCovers()).isEqualTo(DEFAULT_TOTAL_COVERS);
        assertThat(testClientVenueStats.getTotalNoShows()).isEqualTo(DEFAULT_TOTAL_NO_SHOWS);
        assertThat(testClientVenueStats.getTotalSpend()).isEqualTo(DEFAULT_TOTAL_SPEND);
        assertThat(testClientVenueStats.getTotalSpendLocal()).isEqualTo(DEFAULT_TOTAL_SPEND_LOCAL);
        assertThat(testClientVenueStats.getTotalSpendLocalperCover()).isEqualTo(DEFAULT_TOTAL_SPEND_LOCALPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendLocalPerVisit()).isEqualTo(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT);
        assertThat(testClientVenueStats.getTotalSpendperCover()).isEqualTo(DEFAULT_TOTAL_SPENDPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendPerVisit()).isEqualTo(DEFAULT_TOTAL_SPEND_PER_VISIT);
        assertThat(testClientVenueStats.getTotalVisit()).isEqualTo(DEFAULT_TOTAL_VISIT);
        assertThat(testClientVenueStats.getVenueMarketingOptin()).isEqualTo(DEFAULT_VENUE_MARKETING_OPTIN);
        assertThat(testClientVenueStats.getVenueMarketingOptints()).isEqualTo(DEFAULT_VENUE_MARKETING_OPTINTS);
        assertThat(testClientVenueStats.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClientVenueStats.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testClientVenueStats.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testClientVenueStats.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testClientVenueStats.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createClientVenueStatsWithExistingId() throws Exception {
        // Create the ClientVenueStats with an existing ID
        clientVenueStats.setId(1L);
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        int databaseSizeBeforeCreate = clientVenueStatsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientVenueStatsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClientVenueStats() throws Exception {
        // Initialize the database
        clientVenueStatsRepository.saveAndFlush(clientVenueStats);

        // Get all the clientVenueStatsList
        restClientVenueStatsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientVenueStats.getId().intValue())))
            .andExpect(jsonPath("$.[*].venueId").value(hasItem(DEFAULT_VENUE_ID)))
            .andExpect(jsonPath("$.[*].avgRating").value(hasItem(DEFAULT_AVG_RATING)))
            .andExpect(jsonPath("$.[*].bookedByNames").value(hasItem(DEFAULT_BOOKED_BY_NAMES)))
            .andExpect(jsonPath("$.[*].lastVisitDate").value(hasItem(DEFAULT_LAST_VISIT_DATE)))
            .andExpect(jsonPath("$.[*].numRatings").value(hasItem(DEFAULT_NUM_RATINGS)))
            .andExpect(jsonPath("$.[*].totalCancellations").value(hasItem(DEFAULT_TOTAL_CANCELLATIONS)))
            .andExpect(jsonPath("$.[*].totalCovers").value(hasItem(DEFAULT_TOTAL_COVERS)))
            .andExpect(jsonPath("$.[*].totalNoShows").value(hasItem(DEFAULT_TOTAL_NO_SHOWS)))
            .andExpect(jsonPath("$.[*].totalSpend").value(hasItem(DEFAULT_TOTAL_SPEND.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendLocal").value(hasItem(DEFAULT_TOTAL_SPEND_LOCAL.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendLocalperCover").value(hasItem(DEFAULT_TOTAL_SPEND_LOCALPER_COVER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendLocalPerVisit").value(hasItem(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendperCover").value(hasItem(DEFAULT_TOTAL_SPENDPER_COVER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendPerVisit").value(hasItem(DEFAULT_TOTAL_SPEND_PER_VISIT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalVisit").value(hasItem(DEFAULT_TOTAL_VISIT)))
            .andExpect(jsonPath("$.[*].venueMarketingOptin").value(hasItem(DEFAULT_VENUE_MARKETING_OPTIN.booleanValue())))
            .andExpect(jsonPath("$.[*].venueMarketingOptints").value(hasItem(DEFAULT_VENUE_MARKETING_OPTINTS)))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getClientVenueStats() throws Exception {
        // Initialize the database
        clientVenueStatsRepository.saveAndFlush(clientVenueStats);

        // Get the clientVenueStats
        restClientVenueStatsMockMvc
            .perform(get(ENTITY_API_URL_ID, clientVenueStats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientVenueStats.getId().intValue()))
            .andExpect(jsonPath("$.venueId").value(DEFAULT_VENUE_ID))
            .andExpect(jsonPath("$.avgRating").value(DEFAULT_AVG_RATING))
            .andExpect(jsonPath("$.bookedByNames").value(DEFAULT_BOOKED_BY_NAMES))
            .andExpect(jsonPath("$.lastVisitDate").value(DEFAULT_LAST_VISIT_DATE))
            .andExpect(jsonPath("$.numRatings").value(DEFAULT_NUM_RATINGS))
            .andExpect(jsonPath("$.totalCancellations").value(DEFAULT_TOTAL_CANCELLATIONS))
            .andExpect(jsonPath("$.totalCovers").value(DEFAULT_TOTAL_COVERS))
            .andExpect(jsonPath("$.totalNoShows").value(DEFAULT_TOTAL_NO_SHOWS))
            .andExpect(jsonPath("$.totalSpend").value(DEFAULT_TOTAL_SPEND.doubleValue()))
            .andExpect(jsonPath("$.totalSpendLocal").value(DEFAULT_TOTAL_SPEND_LOCAL.doubleValue()))
            .andExpect(jsonPath("$.totalSpendLocalperCover").value(DEFAULT_TOTAL_SPEND_LOCALPER_COVER.doubleValue()))
            .andExpect(jsonPath("$.totalSpendLocalPerVisit").value(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT.doubleValue()))
            .andExpect(jsonPath("$.totalSpendperCover").value(DEFAULT_TOTAL_SPENDPER_COVER.doubleValue()))
            .andExpect(jsonPath("$.totalSpendPerVisit").value(DEFAULT_TOTAL_SPEND_PER_VISIT.doubleValue()))
            .andExpect(jsonPath("$.totalVisit").value(DEFAULT_TOTAL_VISIT))
            .andExpect(jsonPath("$.venueMarketingOptin").value(DEFAULT_VENUE_MARKETING_OPTIN.booleanValue()))
            .andExpect(jsonPath("$.venueMarketingOptints").value(DEFAULT_VENUE_MARKETING_OPTINTS))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingClientVenueStats() throws Exception {
        // Get the clientVenueStats
        restClientVenueStatsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClientVenueStats() throws Exception {
        // Initialize the database
        clientVenueStatsRepository.saveAndFlush(clientVenueStats);

        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();

        // Update the clientVenueStats
        ClientVenueStats updatedClientVenueStats = clientVenueStatsRepository.findById(clientVenueStats.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedClientVenueStats are not directly saved in db
        em.detach(updatedClientVenueStats);
        updatedClientVenueStats
            .venueId(UPDATED_VENUE_ID)
            .avgRating(UPDATED_AVG_RATING)
            .bookedByNames(UPDATED_BOOKED_BY_NAMES)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalSpendperCover(UPDATED_TOTAL_SPENDPER_COVER)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalVisit(UPDATED_TOTAL_VISIT)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(updatedClientVenueStats);

        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientVenueStatsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
        ClientVenueStats testClientVenueStats = clientVenueStatsList.get(clientVenueStatsList.size() - 1);
        assertThat(testClientVenueStats.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testClientVenueStats.getAvgRating()).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testClientVenueStats.getBookedByNames()).isEqualTo(UPDATED_BOOKED_BY_NAMES);
        assertThat(testClientVenueStats.getLastVisitDate()).isEqualTo(UPDATED_LAST_VISIT_DATE);
        assertThat(testClientVenueStats.getNumRatings()).isEqualTo(UPDATED_NUM_RATINGS);
        assertThat(testClientVenueStats.getTotalCancellations()).isEqualTo(UPDATED_TOTAL_CANCELLATIONS);
        assertThat(testClientVenueStats.getTotalCovers()).isEqualTo(UPDATED_TOTAL_COVERS);
        assertThat(testClientVenueStats.getTotalNoShows()).isEqualTo(UPDATED_TOTAL_NO_SHOWS);
        assertThat(testClientVenueStats.getTotalSpend()).isEqualTo(UPDATED_TOTAL_SPEND);
        assertThat(testClientVenueStats.getTotalSpendLocal()).isEqualTo(UPDATED_TOTAL_SPEND_LOCAL);
        assertThat(testClientVenueStats.getTotalSpendLocalperCover()).isEqualTo(UPDATED_TOTAL_SPEND_LOCALPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendLocalPerVisit()).isEqualTo(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT);
        assertThat(testClientVenueStats.getTotalSpendperCover()).isEqualTo(UPDATED_TOTAL_SPENDPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendPerVisit()).isEqualTo(UPDATED_TOTAL_SPEND_PER_VISIT);
        assertThat(testClientVenueStats.getTotalVisit()).isEqualTo(UPDATED_TOTAL_VISIT);
        assertThat(testClientVenueStats.getVenueMarketingOptin()).isEqualTo(UPDATED_VENUE_MARKETING_OPTIN);
        assertThat(testClientVenueStats.getVenueMarketingOptints()).isEqualTo(UPDATED_VENUE_MARKETING_OPTINTS);
        assertThat(testClientVenueStats.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientVenueStats.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientVenueStats.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientVenueStats.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientVenueStats.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingClientVenueStats() throws Exception {
        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();
        clientVenueStats.setId(longCount.incrementAndGet());

        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientVenueStatsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClientVenueStats() throws Exception {
        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();
        clientVenueStats.setId(longCount.incrementAndGet());

        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClientVenueStats() throws Exception {
        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();
        clientVenueStats.setId(longCount.incrementAndGet());

        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientVenueStatsWithPatch() throws Exception {
        // Initialize the database
        clientVenueStatsRepository.saveAndFlush(clientVenueStats);

        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();

        // Update the clientVenueStats using partial update
        ClientVenueStats partialUpdatedClientVenueStats = new ClientVenueStats();
        partialUpdatedClientVenueStats.setId(clientVenueStats.getId());

        partialUpdatedClientVenueStats
            .venueId(UPDATED_VENUE_ID)
            .avgRating(UPDATED_AVG_RATING)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techComment(UPDATED_TECH_COMMENT);

        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientVenueStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientVenueStats))
            )
            .andExpect(status().isOk());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
        ClientVenueStats testClientVenueStats = clientVenueStatsList.get(clientVenueStatsList.size() - 1);
        assertThat(testClientVenueStats.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testClientVenueStats.getAvgRating()).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testClientVenueStats.getBookedByNames()).isEqualTo(DEFAULT_BOOKED_BY_NAMES);
        assertThat(testClientVenueStats.getLastVisitDate()).isEqualTo(UPDATED_LAST_VISIT_DATE);
        assertThat(testClientVenueStats.getNumRatings()).isEqualTo(UPDATED_NUM_RATINGS);
        assertThat(testClientVenueStats.getTotalCancellations()).isEqualTo(UPDATED_TOTAL_CANCELLATIONS);
        assertThat(testClientVenueStats.getTotalCovers()).isEqualTo(UPDATED_TOTAL_COVERS);
        assertThat(testClientVenueStats.getTotalNoShows()).isEqualTo(UPDATED_TOTAL_NO_SHOWS);
        assertThat(testClientVenueStats.getTotalSpend()).isEqualTo(DEFAULT_TOTAL_SPEND);
        assertThat(testClientVenueStats.getTotalSpendLocal()).isEqualTo(UPDATED_TOTAL_SPEND_LOCAL);
        assertThat(testClientVenueStats.getTotalSpendLocalperCover()).isEqualTo(UPDATED_TOTAL_SPEND_LOCALPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendLocalPerVisit()).isEqualTo(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT);
        assertThat(testClientVenueStats.getTotalSpendperCover()).isEqualTo(DEFAULT_TOTAL_SPENDPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendPerVisit()).isEqualTo(DEFAULT_TOTAL_SPEND_PER_VISIT);
        assertThat(testClientVenueStats.getTotalVisit()).isEqualTo(DEFAULT_TOTAL_VISIT);
        assertThat(testClientVenueStats.getVenueMarketingOptin()).isEqualTo(UPDATED_VENUE_MARKETING_OPTIN);
        assertThat(testClientVenueStats.getVenueMarketingOptints()).isEqualTo(UPDATED_VENUE_MARKETING_OPTINTS);
        assertThat(testClientVenueStats.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientVenueStats.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testClientVenueStats.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientVenueStats.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testClientVenueStats.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateClientVenueStatsWithPatch() throws Exception {
        // Initialize the database
        clientVenueStatsRepository.saveAndFlush(clientVenueStats);

        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();

        // Update the clientVenueStats using partial update
        ClientVenueStats partialUpdatedClientVenueStats = new ClientVenueStats();
        partialUpdatedClientVenueStats.setId(clientVenueStats.getId());

        partialUpdatedClientVenueStats
            .venueId(UPDATED_VENUE_ID)
            .avgRating(UPDATED_AVG_RATING)
            .bookedByNames(UPDATED_BOOKED_BY_NAMES)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalSpendperCover(UPDATED_TOTAL_SPENDPER_COVER)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalVisit(UPDATED_TOTAL_VISIT)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientVenueStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientVenueStats))
            )
            .andExpect(status().isOk());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
        ClientVenueStats testClientVenueStats = clientVenueStatsList.get(clientVenueStatsList.size() - 1);
        assertThat(testClientVenueStats.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testClientVenueStats.getAvgRating()).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testClientVenueStats.getBookedByNames()).isEqualTo(UPDATED_BOOKED_BY_NAMES);
        assertThat(testClientVenueStats.getLastVisitDate()).isEqualTo(UPDATED_LAST_VISIT_DATE);
        assertThat(testClientVenueStats.getNumRatings()).isEqualTo(UPDATED_NUM_RATINGS);
        assertThat(testClientVenueStats.getTotalCancellations()).isEqualTo(UPDATED_TOTAL_CANCELLATIONS);
        assertThat(testClientVenueStats.getTotalCovers()).isEqualTo(UPDATED_TOTAL_COVERS);
        assertThat(testClientVenueStats.getTotalNoShows()).isEqualTo(UPDATED_TOTAL_NO_SHOWS);
        assertThat(testClientVenueStats.getTotalSpend()).isEqualTo(UPDATED_TOTAL_SPEND);
        assertThat(testClientVenueStats.getTotalSpendLocal()).isEqualTo(UPDATED_TOTAL_SPEND_LOCAL);
        assertThat(testClientVenueStats.getTotalSpendLocalperCover()).isEqualTo(UPDATED_TOTAL_SPEND_LOCALPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendLocalPerVisit()).isEqualTo(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT);
        assertThat(testClientVenueStats.getTotalSpendperCover()).isEqualTo(UPDATED_TOTAL_SPENDPER_COVER);
        assertThat(testClientVenueStats.getTotalSpendPerVisit()).isEqualTo(UPDATED_TOTAL_SPEND_PER_VISIT);
        assertThat(testClientVenueStats.getTotalVisit()).isEqualTo(UPDATED_TOTAL_VISIT);
        assertThat(testClientVenueStats.getVenueMarketingOptin()).isEqualTo(UPDATED_VENUE_MARKETING_OPTIN);
        assertThat(testClientVenueStats.getVenueMarketingOptints()).isEqualTo(UPDATED_VENUE_MARKETING_OPTINTS);
        assertThat(testClientVenueStats.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClientVenueStats.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClientVenueStats.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClientVenueStats.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClientVenueStats.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingClientVenueStats() throws Exception {
        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();
        clientVenueStats.setId(longCount.incrementAndGet());

        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientVenueStatsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClientVenueStats() throws Exception {
        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();
        clientVenueStats.setId(longCount.incrementAndGet());

        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClientVenueStats() throws Exception {
        int databaseSizeBeforeUpdate = clientVenueStatsRepository.findAll().size();
        clientVenueStats.setId(longCount.incrementAndGet());

        // Create the ClientVenueStats
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsMapper.toDto(clientVenueStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientVenueStatsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientVenueStats in the database
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClientVenueStats() throws Exception {
        // Initialize the database
        clientVenueStatsRepository.saveAndFlush(clientVenueStats);

        int databaseSizeBeforeDelete = clientVenueStatsRepository.findAll().size();

        // Delete the clientVenueStats
        restClientVenueStatsMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientVenueStats.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientVenueStats> clientVenueStatsList = clientVenueStatsRepository.findAll();
        assertThat(clientVenueStatsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
