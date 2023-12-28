package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.repository.ClientRepository;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientMapper;
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
 * Integration tests for the {@link ClientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientResourceIT {

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELETED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DELETED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_SALUTATION = "AAAAAAAAAA";
    private static final String UPDATED_SALUTATION = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BIRTHDAY_DAY = 1;
    private static final Integer UPDATED_BIRTHDAY_DAY = 2;

    private static final Integer DEFAULT_BIRTHDAY_MONTH = 1;
    private static final Integer UPDATED_BIRTHDAY_MONTH = 2;

    private static final Integer DEFAULT_BIRTHDAY_ALT_MONTH = 1;
    private static final Integer UPDATED_BIRTHDAY_ALT_MONTH = 2;

    private static final Integer DEFAULT_ANNIVERSARY_DAY = 1;
    private static final Integer UPDATED_ANNIVERSARY_DAY = 2;

    private static final Integer DEFAULT_ANNIVERSARY_MONTH = 1;
    private static final Integer UPDATED_ANNIVERSARY_MONTH = 2;

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ALT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ALT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBERLOCALE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBERLOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBERALT = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBERALT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBERALTLOCALE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBERALTLOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CONTACT_PRIVATE = false;
    private static final Boolean UPDATED_IS_CONTACT_PRIVATE = true;

    private static final Boolean DEFAULT_IS_ONETIME_GUEST = false;
    private static final Boolean UPDATED_IS_ONETIME_GUEST = true;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LOYALTY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOYALTY_RANK = 1;
    private static final Integer UPDATED_LOYALTY_RANK = 2;

    private static final String DEFAULT_LOYALTY_TIER = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_TIER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MARKETING_OPTIN = false;
    private static final Boolean UPDATED_MARKETING_OPTIN = true;

    private static final String DEFAULT_MARKETING_OPTINTS = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_OPTINTS = "BBBBBBBBBB";

    private static final String DEFAULT_MARKETING_OPT_OUTTS = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_OPT_OUTTS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HAS_BILLING_PROFILE = false;
    private static final Boolean UPDATED_HAS_BILLING_PROFILE = true;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_PRIVATE_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_PRIVATE_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_VISITS = 1D;
    private static final Double UPDATED_TOTAL_VISITS = 2D;

    private static final Double DEFAULT_TOTAL_COVERS = 1D;
    private static final Double UPDATED_TOTAL_COVERS = 2D;

    private static final Double DEFAULT_TOTAL_CANCELLATIONS = 1D;
    private static final Double UPDATED_TOTAL_CANCELLATIONS = 2D;

    private static final Double DEFAULT_TOTAL_NO_SHOWS = 1D;
    private static final Double UPDATED_TOTAL_NO_SHOWS = 2D;

    private static final Double DEFAULT_TOTAL_SPEND = 1D;
    private static final Double UPDATED_TOTAL_SPEND = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_PER_COVER = 1D;
    private static final Double UPDATED_TOTAL_SPEND_PER_COVER = 2D;

    private static final Double DEFAULT_TOTALSPEND_PER_VISIT = 1D;
    private static final Double UPDATED_TOTALSPEND_PER_VISIT = 2D;

    private static final Double DEFAULT_AVG_RATING = 1D;
    private static final Double UPDATED_AVG_RATING = 2D;

    private static final String DEFAULT_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_BIRTHDAY_ALT_DAY = 1;
    private static final Integer UPDATED_BIRTHDAY_ALT_DAY = 2;

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_ORDER_COUNT = 1;
    private static final Integer UPDATED_TOTAL_ORDER_COUNT = 2;

    private static final String DEFAULT_PREFERRED_LANGUAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_LANGUAGE_CODE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/clients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientMockMvc;

    private Client client;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .clientId(DEFAULT_CLIENT_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deletedDate(DEFAULT_DELETED_DATE)
            .lastname(DEFAULT_LASTNAME)
            .firstname(DEFAULT_FIRSTNAME)
            .gender(DEFAULT_GENDER)
            .salutation(DEFAULT_SALUTATION)
            .title(DEFAULT_TITLE)
            .birthdayDay(DEFAULT_BIRTHDAY_DAY)
            .birthdayMonth(DEFAULT_BIRTHDAY_MONTH)
            .birthdayAltMonth(DEFAULT_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(DEFAULT_ANNIVERSARY_DAY)
            .anniversaryMonth(DEFAULT_ANNIVERSARY_MONTH)
            .company(DEFAULT_COMPANY)
            .email(DEFAULT_EMAIL)
            .emailAlt(DEFAULT_EMAIL_ALT)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .phoneNumberlocale(DEFAULT_PHONE_NUMBERLOCALE)
            .phoneNumberalt(DEFAULT_PHONE_NUMBERALT)
            .phoneNumberaltlocale(DEFAULT_PHONE_NUMBERALTLOCALE)
            .address(DEFAULT_ADDRESS)
            .address2(DEFAULT_ADDRESS_2)
            .city(DEFAULT_CITY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .isContactPrivate(DEFAULT_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(DEFAULT_IS_ONETIME_GUEST)
            .status(DEFAULT_STATUS)
            .loyaltyId(DEFAULT_LOYALTY_ID)
            .loyaltyRank(DEFAULT_LOYALTY_RANK)
            .loyaltyTier(DEFAULT_LOYALTY_TIER)
            .marketingOptin(DEFAULT_MARKETING_OPTIN)
            .marketingOptints(DEFAULT_MARKETING_OPTINTS)
            .marketingOptOutts(DEFAULT_MARKETING_OPT_OUTTS)
            .hasBillingProfile(DEFAULT_HAS_BILLING_PROFILE)
            .notes(DEFAULT_NOTES)
            .privateNotes(DEFAULT_PRIVATE_NOTES)
            .tags(DEFAULT_TAGS)
            .totalVisits(DEFAULT_TOTAL_VISITS)
            .totalCovers(DEFAULT_TOTAL_COVERS)
            .totalCancellations(DEFAULT_TOTAL_CANCELLATIONS)
            .totalNoShows(DEFAULT_TOTAL_NO_SHOWS)
            .totalSpend(DEFAULT_TOTAL_SPEND)
            .totalSpendPerCover(DEFAULT_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(DEFAULT_TOTALSPEND_PER_VISIT)
            .avgRating(DEFAULT_AVG_RATING)
            .referenceCode(DEFAULT_REFERENCE_CODE)
            .externalUserId(DEFAULT_EXTERNAL_USER_ID)
            .venueGroupId(DEFAULT_VENUE_GROUP_ID)
            .birthdayAltDay(DEFAULT_BIRTHDAY_ALT_DAY)
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .totalOrderCount(DEFAULT_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(DEFAULT_PREFERRED_LANGUAGE_CODE)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return client;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createUpdatedEntity(EntityManager em) {
        Client client = new Client()
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deletedDate(UPDATED_DELETED_DATE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .title(UPDATED_TITLE)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .birthdayAltMonth(UPDATED_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .marketingOptOutts(UPDATED_MARKETING_OPT_OUTTS)
            .hasBillingProfile(UPDATED_HAS_BILLING_PROFILE)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .birthdayAltDay(UPDATED_BIRTHDAY_ALT_DAY)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(UPDATED_PREFERRED_LANGUAGE_CODE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return client;
    }

    @BeforeEach
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();
        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);
        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testClient.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClient.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClient.getDeletedDate()).isEqualTo(DEFAULT_DELETED_DATE);
        assertThat(testClient.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testClient.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testClient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testClient.getSalutation()).isEqualTo(DEFAULT_SALUTATION);
        assertThat(testClient.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testClient.getBirthdayDay()).isEqualTo(DEFAULT_BIRTHDAY_DAY);
        assertThat(testClient.getBirthdayMonth()).isEqualTo(DEFAULT_BIRTHDAY_MONTH);
        assertThat(testClient.getBirthdayAltMonth()).isEqualTo(DEFAULT_BIRTHDAY_ALT_MONTH);
        assertThat(testClient.getAnniversaryDay()).isEqualTo(DEFAULT_ANNIVERSARY_DAY);
        assertThat(testClient.getAnniversaryMonth()).isEqualTo(DEFAULT_ANNIVERSARY_MONTH);
        assertThat(testClient.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testClient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testClient.getEmailAlt()).isEqualTo(DEFAULT_EMAIL_ALT);
        assertThat(testClient.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testClient.getPhoneNumberlocale()).isEqualTo(DEFAULT_PHONE_NUMBERLOCALE);
        assertThat(testClient.getPhoneNumberalt()).isEqualTo(DEFAULT_PHONE_NUMBERALT);
        assertThat(testClient.getPhoneNumberaltlocale()).isEqualTo(DEFAULT_PHONE_NUMBERALTLOCALE);
        assertThat(testClient.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testClient.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testClient.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testClient.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testClient.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testClient.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testClient.getIsContactPrivate()).isEqualTo(DEFAULT_IS_CONTACT_PRIVATE);
        assertThat(testClient.getIsOnetimeGuest()).isEqualTo(DEFAULT_IS_ONETIME_GUEST);
        assertThat(testClient.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClient.getLoyaltyId()).isEqualTo(DEFAULT_LOYALTY_ID);
        assertThat(testClient.getLoyaltyRank()).isEqualTo(DEFAULT_LOYALTY_RANK);
        assertThat(testClient.getLoyaltyTier()).isEqualTo(DEFAULT_LOYALTY_TIER);
        assertThat(testClient.getMarketingOptin()).isEqualTo(DEFAULT_MARKETING_OPTIN);
        assertThat(testClient.getMarketingOptints()).isEqualTo(DEFAULT_MARKETING_OPTINTS);
        assertThat(testClient.getMarketingOptOutts()).isEqualTo(DEFAULT_MARKETING_OPT_OUTTS);
        assertThat(testClient.getHasBillingProfile()).isEqualTo(DEFAULT_HAS_BILLING_PROFILE);
        assertThat(testClient.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testClient.getPrivateNotes()).isEqualTo(DEFAULT_PRIVATE_NOTES);
        assertThat(testClient.getTags()).isEqualTo(DEFAULT_TAGS);
        assertThat(testClient.getTotalVisits()).isEqualTo(DEFAULT_TOTAL_VISITS);
        assertThat(testClient.getTotalCovers()).isEqualTo(DEFAULT_TOTAL_COVERS);
        assertThat(testClient.getTotalCancellations()).isEqualTo(DEFAULT_TOTAL_CANCELLATIONS);
        assertThat(testClient.getTotalNoShows()).isEqualTo(DEFAULT_TOTAL_NO_SHOWS);
        assertThat(testClient.getTotalSpend()).isEqualTo(DEFAULT_TOTAL_SPEND);
        assertThat(testClient.getTotalSpendPerCover()).isEqualTo(DEFAULT_TOTAL_SPEND_PER_COVER);
        assertThat(testClient.getTotalspendPerVisit()).isEqualTo(DEFAULT_TOTALSPEND_PER_VISIT);
        assertThat(testClient.getAvgRating()).isEqualTo(DEFAULT_AVG_RATING);
        assertThat(testClient.getReferenceCode()).isEqualTo(DEFAULT_REFERENCE_CODE);
        assertThat(testClient.getExternalUserId()).isEqualTo(DEFAULT_EXTERNAL_USER_ID);
        assertThat(testClient.getVenueGroupId()).isEqualTo(DEFAULT_VENUE_GROUP_ID);
        assertThat(testClient.getBirthdayAltDay()).isEqualTo(DEFAULT_BIRTHDAY_ALT_DAY);
        assertThat(testClient.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testClient.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testClient.getTotalOrderCount()).isEqualTo(DEFAULT_TOTAL_ORDER_COUNT);
        assertThat(testClient.getPreferredLanguageCode()).isEqualTo(DEFAULT_PREFERRED_LANGUAGE_CODE);
        assertThat(testClient.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClient.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testClient.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testClient.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testClient.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createClientWithExistingId() throws Exception {
        // Create the Client with an existing ID
        client.setId(1L);
        ClientDTO clientDTO = clientMapper.toDto(client);

        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE)))
            .andExpect(jsonPath("$.[*].deletedDate").value(hasItem(DEFAULT_DELETED_DATE)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].birthdayDay").value(hasItem(DEFAULT_BIRTHDAY_DAY)))
            .andExpect(jsonPath("$.[*].birthdayMonth").value(hasItem(DEFAULT_BIRTHDAY_MONTH)))
            .andExpect(jsonPath("$.[*].birthdayAltMonth").value(hasItem(DEFAULT_BIRTHDAY_ALT_MONTH)))
            .andExpect(jsonPath("$.[*].anniversaryDay").value(hasItem(DEFAULT_ANNIVERSARY_DAY)))
            .andExpect(jsonPath("$.[*].anniversaryMonth").value(hasItem(DEFAULT_ANNIVERSARY_MONTH)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].emailAlt").value(hasItem(DEFAULT_EMAIL_ALT)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].phoneNumberlocale").value(hasItem(DEFAULT_PHONE_NUMBERLOCALE)))
            .andExpect(jsonPath("$.[*].phoneNumberalt").value(hasItem(DEFAULT_PHONE_NUMBERALT)))
            .andExpect(jsonPath("$.[*].phoneNumberaltlocale").value(hasItem(DEFAULT_PHONE_NUMBERALTLOCALE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].isContactPrivate").value(hasItem(DEFAULT_IS_CONTACT_PRIVATE.booleanValue())))
            .andExpect(jsonPath("$.[*].isOnetimeGuest").value(hasItem(DEFAULT_IS_ONETIME_GUEST.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].loyaltyId").value(hasItem(DEFAULT_LOYALTY_ID)))
            .andExpect(jsonPath("$.[*].loyaltyRank").value(hasItem(DEFAULT_LOYALTY_RANK)))
            .andExpect(jsonPath("$.[*].loyaltyTier").value(hasItem(DEFAULT_LOYALTY_TIER)))
            .andExpect(jsonPath("$.[*].marketingOptin").value(hasItem(DEFAULT_MARKETING_OPTIN.booleanValue())))
            .andExpect(jsonPath("$.[*].marketingOptints").value(hasItem(DEFAULT_MARKETING_OPTINTS)))
            .andExpect(jsonPath("$.[*].marketingOptOutts").value(hasItem(DEFAULT_MARKETING_OPT_OUTTS)))
            .andExpect(jsonPath("$.[*].hasBillingProfile").value(hasItem(DEFAULT_HAS_BILLING_PROFILE.booleanValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].privateNotes").value(hasItem(DEFAULT_PRIVATE_NOTES)))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS)))
            .andExpect(jsonPath("$.[*].totalVisits").value(hasItem(DEFAULT_TOTAL_VISITS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalCovers").value(hasItem(DEFAULT_TOTAL_COVERS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalCancellations").value(hasItem(DEFAULT_TOTAL_CANCELLATIONS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalNoShows").value(hasItem(DEFAULT_TOTAL_NO_SHOWS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpend").value(hasItem(DEFAULT_TOTAL_SPEND.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendPerCover").value(hasItem(DEFAULT_TOTAL_SPEND_PER_COVER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalspendPerVisit").value(hasItem(DEFAULT_TOTALSPEND_PER_VISIT.doubleValue())))
            .andExpect(jsonPath("$.[*].avgRating").value(hasItem(DEFAULT_AVG_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].referenceCode").value(hasItem(DEFAULT_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].externalUserId").value(hasItem(DEFAULT_EXTERNAL_USER_ID)))
            .andExpect(jsonPath("$.[*].venueGroupId").value(hasItem(DEFAULT_VENUE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].birthdayAltDay").value(hasItem(DEFAULT_BIRTHDAY_ALT_DAY)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].totalOrderCount").value(hasItem(DEFAULT_TOTAL_ORDER_COUNT)))
            .andExpect(jsonPath("$.[*].preferredLanguageCode").value(hasItem(DEFAULT_PREFERRED_LANGUAGE_CODE)))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc
            .perform(get(ENTITY_API_URL_ID, client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE))
            .andExpect(jsonPath("$.deletedDate").value(DEFAULT_DELETED_DATE))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.salutation").value(DEFAULT_SALUTATION))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.birthdayDay").value(DEFAULT_BIRTHDAY_DAY))
            .andExpect(jsonPath("$.birthdayMonth").value(DEFAULT_BIRTHDAY_MONTH))
            .andExpect(jsonPath("$.birthdayAltMonth").value(DEFAULT_BIRTHDAY_ALT_MONTH))
            .andExpect(jsonPath("$.anniversaryDay").value(DEFAULT_ANNIVERSARY_DAY))
            .andExpect(jsonPath("$.anniversaryMonth").value(DEFAULT_ANNIVERSARY_MONTH))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.emailAlt").value(DEFAULT_EMAIL_ALT))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.phoneNumberlocale").value(DEFAULT_PHONE_NUMBERLOCALE))
            .andExpect(jsonPath("$.phoneNumberalt").value(DEFAULT_PHONE_NUMBERALT))
            .andExpect(jsonPath("$.phoneNumberaltlocale").value(DEFAULT_PHONE_NUMBERALTLOCALE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.isContactPrivate").value(DEFAULT_IS_CONTACT_PRIVATE.booleanValue()))
            .andExpect(jsonPath("$.isOnetimeGuest").value(DEFAULT_IS_ONETIME_GUEST.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.loyaltyId").value(DEFAULT_LOYALTY_ID))
            .andExpect(jsonPath("$.loyaltyRank").value(DEFAULT_LOYALTY_RANK))
            .andExpect(jsonPath("$.loyaltyTier").value(DEFAULT_LOYALTY_TIER))
            .andExpect(jsonPath("$.marketingOptin").value(DEFAULT_MARKETING_OPTIN.booleanValue()))
            .andExpect(jsonPath("$.marketingOptints").value(DEFAULT_MARKETING_OPTINTS))
            .andExpect(jsonPath("$.marketingOptOutts").value(DEFAULT_MARKETING_OPT_OUTTS))
            .andExpect(jsonPath("$.hasBillingProfile").value(DEFAULT_HAS_BILLING_PROFILE.booleanValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.privateNotes").value(DEFAULT_PRIVATE_NOTES))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS))
            .andExpect(jsonPath("$.totalVisits").value(DEFAULT_TOTAL_VISITS.doubleValue()))
            .andExpect(jsonPath("$.totalCovers").value(DEFAULT_TOTAL_COVERS.doubleValue()))
            .andExpect(jsonPath("$.totalCancellations").value(DEFAULT_TOTAL_CANCELLATIONS.doubleValue()))
            .andExpect(jsonPath("$.totalNoShows").value(DEFAULT_TOTAL_NO_SHOWS.doubleValue()))
            .andExpect(jsonPath("$.totalSpend").value(DEFAULT_TOTAL_SPEND.doubleValue()))
            .andExpect(jsonPath("$.totalSpendPerCover").value(DEFAULT_TOTAL_SPEND_PER_COVER.doubleValue()))
            .andExpect(jsonPath("$.totalspendPerVisit").value(DEFAULT_TOTALSPEND_PER_VISIT.doubleValue()))
            .andExpect(jsonPath("$.avgRating").value(DEFAULT_AVG_RATING.doubleValue()))
            .andExpect(jsonPath("$.referenceCode").value(DEFAULT_REFERENCE_CODE))
            .andExpect(jsonPath("$.externalUserId").value(DEFAULT_EXTERNAL_USER_ID))
            .andExpect(jsonPath("$.venueGroupId").value(DEFAULT_VENUE_GROUP_ID))
            .andExpect(jsonPath("$.birthdayAltDay").value(DEFAULT_BIRTHDAY_ALT_DAY))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.totalOrderCount").value(DEFAULT_TOTAL_ORDER_COUNT))
            .andExpect(jsonPath("$.preferredLanguageCode").value(DEFAULT_PREFERRED_LANGUAGE_CODE))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deletedDate(UPDATED_DELETED_DATE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .title(UPDATED_TITLE)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .birthdayAltMonth(UPDATED_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .marketingOptOutts(UPDATED_MARKETING_OPT_OUTTS)
            .hasBillingProfile(UPDATED_HAS_BILLING_PROFILE)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .birthdayAltDay(UPDATED_BIRTHDAY_ALT_DAY)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(UPDATED_PREFERRED_LANGUAGE_CODE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        ClientDTO clientDTO = clientMapper.toDto(updatedClient);

        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testClient.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClient.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClient.getDeletedDate()).isEqualTo(UPDATED_DELETED_DATE);
        assertThat(testClient.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testClient.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testClient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testClient.getSalutation()).isEqualTo(UPDATED_SALUTATION);
        assertThat(testClient.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testClient.getBirthdayDay()).isEqualTo(UPDATED_BIRTHDAY_DAY);
        assertThat(testClient.getBirthdayMonth()).isEqualTo(UPDATED_BIRTHDAY_MONTH);
        assertThat(testClient.getBirthdayAltMonth()).isEqualTo(UPDATED_BIRTHDAY_ALT_MONTH);
        assertThat(testClient.getAnniversaryDay()).isEqualTo(UPDATED_ANNIVERSARY_DAY);
        assertThat(testClient.getAnniversaryMonth()).isEqualTo(UPDATED_ANNIVERSARY_MONTH);
        assertThat(testClient.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClient.getEmailAlt()).isEqualTo(UPDATED_EMAIL_ALT);
        assertThat(testClient.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testClient.getPhoneNumberlocale()).isEqualTo(UPDATED_PHONE_NUMBERLOCALE);
        assertThat(testClient.getPhoneNumberalt()).isEqualTo(UPDATED_PHONE_NUMBERALT);
        assertThat(testClient.getPhoneNumberaltlocale()).isEqualTo(UPDATED_PHONE_NUMBERALTLOCALE);
        assertThat(testClient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testClient.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testClient.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testClient.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testClient.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testClient.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testClient.getIsContactPrivate()).isEqualTo(UPDATED_IS_CONTACT_PRIVATE);
        assertThat(testClient.getIsOnetimeGuest()).isEqualTo(UPDATED_IS_ONETIME_GUEST);
        assertThat(testClient.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClient.getLoyaltyId()).isEqualTo(UPDATED_LOYALTY_ID);
        assertThat(testClient.getLoyaltyRank()).isEqualTo(UPDATED_LOYALTY_RANK);
        assertThat(testClient.getLoyaltyTier()).isEqualTo(UPDATED_LOYALTY_TIER);
        assertThat(testClient.getMarketingOptin()).isEqualTo(UPDATED_MARKETING_OPTIN);
        assertThat(testClient.getMarketingOptints()).isEqualTo(UPDATED_MARKETING_OPTINTS);
        assertThat(testClient.getMarketingOptOutts()).isEqualTo(UPDATED_MARKETING_OPT_OUTTS);
        assertThat(testClient.getHasBillingProfile()).isEqualTo(UPDATED_HAS_BILLING_PROFILE);
        assertThat(testClient.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testClient.getPrivateNotes()).isEqualTo(UPDATED_PRIVATE_NOTES);
        assertThat(testClient.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testClient.getTotalVisits()).isEqualTo(UPDATED_TOTAL_VISITS);
        assertThat(testClient.getTotalCovers()).isEqualTo(UPDATED_TOTAL_COVERS);
        assertThat(testClient.getTotalCancellations()).isEqualTo(UPDATED_TOTAL_CANCELLATIONS);
        assertThat(testClient.getTotalNoShows()).isEqualTo(UPDATED_TOTAL_NO_SHOWS);
        assertThat(testClient.getTotalSpend()).isEqualTo(UPDATED_TOTAL_SPEND);
        assertThat(testClient.getTotalSpendPerCover()).isEqualTo(UPDATED_TOTAL_SPEND_PER_COVER);
        assertThat(testClient.getTotalspendPerVisit()).isEqualTo(UPDATED_TOTALSPEND_PER_VISIT);
        assertThat(testClient.getAvgRating()).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testClient.getReferenceCode()).isEqualTo(UPDATED_REFERENCE_CODE);
        assertThat(testClient.getExternalUserId()).isEqualTo(UPDATED_EXTERNAL_USER_ID);
        assertThat(testClient.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testClient.getBirthdayAltDay()).isEqualTo(UPDATED_BIRTHDAY_ALT_DAY);
        assertThat(testClient.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testClient.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testClient.getTotalOrderCount()).isEqualTo(UPDATED_TOTAL_ORDER_COUNT);
        assertThat(testClient.getPreferredLanguageCode()).isEqualTo(UPDATED_PREFERRED_LANGUAGE_CODE);
        assertThat(testClient.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClient.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClient.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClient.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClient.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(longCount.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(longCount.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(longCount.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientWithPatch() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client using partial update
        Client partialUpdatedClient = new Client();
        partialUpdatedClient.setId(client.getId());

        partialUpdatedClient
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .techMapping(UPDATED_TECH_MAPPING);

        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testClient.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClient.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClient.getDeletedDate()).isEqualTo(DEFAULT_DELETED_DATE);
        assertThat(testClient.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testClient.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testClient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testClient.getSalutation()).isEqualTo(UPDATED_SALUTATION);
        assertThat(testClient.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testClient.getBirthdayDay()).isEqualTo(UPDATED_BIRTHDAY_DAY);
        assertThat(testClient.getBirthdayMonth()).isEqualTo(UPDATED_BIRTHDAY_MONTH);
        assertThat(testClient.getBirthdayAltMonth()).isEqualTo(DEFAULT_BIRTHDAY_ALT_MONTH);
        assertThat(testClient.getAnniversaryDay()).isEqualTo(DEFAULT_ANNIVERSARY_DAY);
        assertThat(testClient.getAnniversaryMonth()).isEqualTo(UPDATED_ANNIVERSARY_MONTH);
        assertThat(testClient.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClient.getEmailAlt()).isEqualTo(DEFAULT_EMAIL_ALT);
        assertThat(testClient.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testClient.getPhoneNumberlocale()).isEqualTo(UPDATED_PHONE_NUMBERLOCALE);
        assertThat(testClient.getPhoneNumberalt()).isEqualTo(DEFAULT_PHONE_NUMBERALT);
        assertThat(testClient.getPhoneNumberaltlocale()).isEqualTo(UPDATED_PHONE_NUMBERALTLOCALE);
        assertThat(testClient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testClient.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testClient.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testClient.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testClient.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testClient.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testClient.getIsContactPrivate()).isEqualTo(DEFAULT_IS_CONTACT_PRIVATE);
        assertThat(testClient.getIsOnetimeGuest()).isEqualTo(UPDATED_IS_ONETIME_GUEST);
        assertThat(testClient.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClient.getLoyaltyId()).isEqualTo(DEFAULT_LOYALTY_ID);
        assertThat(testClient.getLoyaltyRank()).isEqualTo(UPDATED_LOYALTY_RANK);
        assertThat(testClient.getLoyaltyTier()).isEqualTo(DEFAULT_LOYALTY_TIER);
        assertThat(testClient.getMarketingOptin()).isEqualTo(UPDATED_MARKETING_OPTIN);
        assertThat(testClient.getMarketingOptints()).isEqualTo(DEFAULT_MARKETING_OPTINTS);
        assertThat(testClient.getMarketingOptOutts()).isEqualTo(DEFAULT_MARKETING_OPT_OUTTS);
        assertThat(testClient.getHasBillingProfile()).isEqualTo(DEFAULT_HAS_BILLING_PROFILE);
        assertThat(testClient.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testClient.getPrivateNotes()).isEqualTo(UPDATED_PRIVATE_NOTES);
        assertThat(testClient.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testClient.getTotalVisits()).isEqualTo(DEFAULT_TOTAL_VISITS);
        assertThat(testClient.getTotalCovers()).isEqualTo(UPDATED_TOTAL_COVERS);
        assertThat(testClient.getTotalCancellations()).isEqualTo(UPDATED_TOTAL_CANCELLATIONS);
        assertThat(testClient.getTotalNoShows()).isEqualTo(UPDATED_TOTAL_NO_SHOWS);
        assertThat(testClient.getTotalSpend()).isEqualTo(UPDATED_TOTAL_SPEND);
        assertThat(testClient.getTotalSpendPerCover()).isEqualTo(UPDATED_TOTAL_SPEND_PER_COVER);
        assertThat(testClient.getTotalspendPerVisit()).isEqualTo(DEFAULT_TOTALSPEND_PER_VISIT);
        assertThat(testClient.getAvgRating()).isEqualTo(DEFAULT_AVG_RATING);
        assertThat(testClient.getReferenceCode()).isEqualTo(UPDATED_REFERENCE_CODE);
        assertThat(testClient.getExternalUserId()).isEqualTo(DEFAULT_EXTERNAL_USER_ID);
        assertThat(testClient.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testClient.getBirthdayAltDay()).isEqualTo(DEFAULT_BIRTHDAY_ALT_DAY);
        assertThat(testClient.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testClient.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testClient.getTotalOrderCount()).isEqualTo(UPDATED_TOTAL_ORDER_COUNT);
        assertThat(testClient.getPreferredLanguageCode()).isEqualTo(DEFAULT_PREFERRED_LANGUAGE_CODE);
        assertThat(testClient.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testClient.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testClient.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testClient.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClient.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateClientWithPatch() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client using partial update
        Client partialUpdatedClient = new Client();
        partialUpdatedClient.setId(client.getId());

        partialUpdatedClient
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deletedDate(UPDATED_DELETED_DATE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .title(UPDATED_TITLE)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .birthdayAltMonth(UPDATED_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .marketingOptOutts(UPDATED_MARKETING_OPT_OUTTS)
            .hasBillingProfile(UPDATED_HAS_BILLING_PROFILE)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .birthdayAltDay(UPDATED_BIRTHDAY_ALT_DAY)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(UPDATED_PREFERRED_LANGUAGE_CODE)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testClient.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClient.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClient.getDeletedDate()).isEqualTo(UPDATED_DELETED_DATE);
        assertThat(testClient.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testClient.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testClient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testClient.getSalutation()).isEqualTo(UPDATED_SALUTATION);
        assertThat(testClient.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testClient.getBirthdayDay()).isEqualTo(UPDATED_BIRTHDAY_DAY);
        assertThat(testClient.getBirthdayMonth()).isEqualTo(UPDATED_BIRTHDAY_MONTH);
        assertThat(testClient.getBirthdayAltMonth()).isEqualTo(UPDATED_BIRTHDAY_ALT_MONTH);
        assertThat(testClient.getAnniversaryDay()).isEqualTo(UPDATED_ANNIVERSARY_DAY);
        assertThat(testClient.getAnniversaryMonth()).isEqualTo(UPDATED_ANNIVERSARY_MONTH);
        assertThat(testClient.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClient.getEmailAlt()).isEqualTo(UPDATED_EMAIL_ALT);
        assertThat(testClient.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testClient.getPhoneNumberlocale()).isEqualTo(UPDATED_PHONE_NUMBERLOCALE);
        assertThat(testClient.getPhoneNumberalt()).isEqualTo(UPDATED_PHONE_NUMBERALT);
        assertThat(testClient.getPhoneNumberaltlocale()).isEqualTo(UPDATED_PHONE_NUMBERALTLOCALE);
        assertThat(testClient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testClient.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testClient.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testClient.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testClient.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testClient.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testClient.getIsContactPrivate()).isEqualTo(UPDATED_IS_CONTACT_PRIVATE);
        assertThat(testClient.getIsOnetimeGuest()).isEqualTo(UPDATED_IS_ONETIME_GUEST);
        assertThat(testClient.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClient.getLoyaltyId()).isEqualTo(UPDATED_LOYALTY_ID);
        assertThat(testClient.getLoyaltyRank()).isEqualTo(UPDATED_LOYALTY_RANK);
        assertThat(testClient.getLoyaltyTier()).isEqualTo(UPDATED_LOYALTY_TIER);
        assertThat(testClient.getMarketingOptin()).isEqualTo(UPDATED_MARKETING_OPTIN);
        assertThat(testClient.getMarketingOptints()).isEqualTo(UPDATED_MARKETING_OPTINTS);
        assertThat(testClient.getMarketingOptOutts()).isEqualTo(UPDATED_MARKETING_OPT_OUTTS);
        assertThat(testClient.getHasBillingProfile()).isEqualTo(UPDATED_HAS_BILLING_PROFILE);
        assertThat(testClient.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testClient.getPrivateNotes()).isEqualTo(UPDATED_PRIVATE_NOTES);
        assertThat(testClient.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testClient.getTotalVisits()).isEqualTo(UPDATED_TOTAL_VISITS);
        assertThat(testClient.getTotalCovers()).isEqualTo(UPDATED_TOTAL_COVERS);
        assertThat(testClient.getTotalCancellations()).isEqualTo(UPDATED_TOTAL_CANCELLATIONS);
        assertThat(testClient.getTotalNoShows()).isEqualTo(UPDATED_TOTAL_NO_SHOWS);
        assertThat(testClient.getTotalSpend()).isEqualTo(UPDATED_TOTAL_SPEND);
        assertThat(testClient.getTotalSpendPerCover()).isEqualTo(UPDATED_TOTAL_SPEND_PER_COVER);
        assertThat(testClient.getTotalspendPerVisit()).isEqualTo(UPDATED_TOTALSPEND_PER_VISIT);
        assertThat(testClient.getAvgRating()).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testClient.getReferenceCode()).isEqualTo(UPDATED_REFERENCE_CODE);
        assertThat(testClient.getExternalUserId()).isEqualTo(UPDATED_EXTERNAL_USER_ID);
        assertThat(testClient.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testClient.getBirthdayAltDay()).isEqualTo(UPDATED_BIRTHDAY_ALT_DAY);
        assertThat(testClient.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testClient.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testClient.getTotalOrderCount()).isEqualTo(UPDATED_TOTAL_ORDER_COUNT);
        assertThat(testClient.getPreferredLanguageCode()).isEqualTo(UPDATED_PREFERRED_LANGUAGE_CODE);
        assertThat(testClient.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testClient.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testClient.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testClient.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testClient.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(longCount.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(longCount.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(longCount.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Delete the client
        restClientMockMvc
            .perform(delete(ENTITY_API_URL_ID, client.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
