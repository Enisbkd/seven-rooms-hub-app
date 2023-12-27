package com.sbm.sevenroomstohub.web.rest;

import static com.sbm.sevenroomstohub.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.repository.ReservationRepository;
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
 * Integration tests for the {@link ReservationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReservationResourceIT {

    private static final String DEFAULT_RESV_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESV_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_CREATED = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED = "BBBBBBBBBB";

    private static final String DEFAULT_DELETED = "AAAAAAAAAA";
    private static final String UPDATED_DELETED = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final String DEFAULT_CHECK_NUMBERS = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_NUMBERS = "BBBBBBBBBB";

    private static final String DEFAULT_SHIFT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SHIFT_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIFT_PERSISTENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_SHIFT_PERSISTENT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_GUESTS = 1;
    private static final Integer UPDATED_MAX_GUESTS = 2;

    private static final Integer DEFAULT_MFRATIO_MALE = 1;
    private static final Integer UPDATED_MFRATIO_MALE = 2;

    private static final Integer DEFAULT_MFRATIO_FEMALE = 1;
    private static final Integer UPDATED_MFRATIO_FEMALE = 2;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_SIMPLE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_SIMPLE = "BBBBBBBBBB";

    private static final String DEFAULT_TABLE_NUMBERS = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NUMBERS = "BBBBBBBBBB";

    private static final String DEFAULT_ACCESS_PERSISTENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_PERSISTENT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_ARRIVED_GUESTS = 1;
    private static final Integer UPDATED_ARRIVED_GUESTS = 2;

    private static final Boolean DEFAULT_ISVIP = false;
    private static final Boolean UPDATED_ISVIP = true;

    private static final String DEFAULT_BOOKEDBY = "AAAAAAAAAA";
    private static final String UPDATED_BOOKEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_REFERENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

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

    private static final String DEFAULT_LOYALTY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOYALTY_RANK = 1;
    private static final Integer UPDATED_LOYALTY_RANK = 2;

    private static final String DEFAULT_LOYALTY_TIER = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_TIER = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIVAL_TIME = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVAL_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_SEATED_TIME = "AAAAAAAAAA";
    private static final String UPDATED_SEATED_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LEFT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LEFT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_REQUESTS = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_REQUESTS = "BBBBBBBBBB";

    private static final Integer DEFAULT_COMPS = 1;
    private static final Integer UPDATED_COMPS = 2;

    private static final String DEFAULT_COMPS_PRICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COMPS_PRICE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_COST_OPTION = 1;
    private static final Integer UPDATED_COST_OPTION = 2;

    private static final String DEFAULT_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_POLICY = "BBBBBBBBBB";

    private static final Integer DEFAULT_MIN_PRICE = 1;
    private static final Integer UPDATED_MIN_PRICE = 2;

    private static final Double DEFAULT_PRE_PAYMENT = 1D;
    private static final Double UPDATED_PRE_PAYMENT = 2D;

    private static final Double DEFAULT_ONSITE_PAYMENT = 1D;
    private static final Double UPDATED_ONSITE_PAYMENT = 2D;

    private static final Integer DEFAULT_TOTAL_PAYMENT = 1;
    private static final Integer UPDATED_TOTAL_PAYMENT = 2;

    private static final String DEFAULT_PAID_BY = "AAAAAAAAAA";
    private static final String UPDATED_PAID_BY = "BBBBBBBBBB";

    private static final String DEFAULT_SERVED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SERVED_BY = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    private static final String DEFAULT_PROBLEMS = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMS = "BBBBBBBBBB";

    private static final String DEFAULT_AUTO_ASSIGNMENTS = "AAAAAAAAAA";
    private static final String UPDATED_AUTO_ASSIGNMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_REFERENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFY_RESERVATION_LINK = "AAAAAAAAAA";
    private static final String UPDATED_MODIFY_RESERVATION_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RESERVATION_SMS_OPTIN = false;
    private static final Boolean UPDATED_RESERVATION_SMS_OPTIN = true;

    private static final String DEFAULT_RESERVATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESERVATION_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEND_REMINDER_EMAIL = false;
    private static final Boolean UPDATED_SEND_REMINDER_EMAIL = true;

    private static final Boolean DEFAULT_SENDREMINDER_SMS = false;
    private static final Boolean UPDATED_SENDREMINDER_SMS = true;

    private static final String DEFAULT_SOURCE_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/reservations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReservationMockMvc;

    private Reservation reservation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reservation createEntity(EntityManager em) {
        Reservation reservation = new Reservation()
            .resvId(DEFAULT_RESV_ID)
            .created(DEFAULT_CREATED)
            .updated(DEFAULT_UPDATED)
            .deleted(DEFAULT_DELETED)
            .venueGroupClientId(DEFAULT_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(DEFAULT_VENUE_GROUP_ID)
            .venueId(DEFAULT_VENUE_ID)
            .date(DEFAULT_DATE)
            .duration(DEFAULT_DURATION)
            .checkNumbers(DEFAULT_CHECK_NUMBERS)
            .shiftCategory(DEFAULT_SHIFT_CATEGORY)
            .shiftPersistentId(DEFAULT_SHIFT_PERSISTENT_ID)
            .maxGuests(DEFAULT_MAX_GUESTS)
            .mfratioMale(DEFAULT_MFRATIO_MALE)
            .mfratioFemale(DEFAULT_MFRATIO_FEMALE)
            .status(DEFAULT_STATUS)
            .statusDisplay(DEFAULT_STATUS_DISPLAY)
            .statusSimple(DEFAULT_STATUS_SIMPLE)
            .tableNumbers(DEFAULT_TABLE_NUMBERS)
            .accessPersistentId(DEFAULT_ACCESS_PERSISTENT_ID)
            .arrivedGuests(DEFAULT_ARRIVED_GUESTS)
            .isvip(DEFAULT_ISVIP)
            .bookedby(DEFAULT_BOOKEDBY)
            .clientReferenceCode(DEFAULT_CLIENT_REFERENCE_CODE)
            .lastname(DEFAULT_LASTNAME)
            .firstname(DEFAULT_FIRSTNAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .address(DEFAULT_ADDRESS)
            .address2(DEFAULT_ADDRESS_2)
            .city(DEFAULT_CITY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .loyaltyId(DEFAULT_LOYALTY_ID)
            .loyaltyRank(DEFAULT_LOYALTY_RANK)
            .loyaltyTier(DEFAULT_LOYALTY_TIER)
            .notes(DEFAULT_NOTES)
            .arrivalTime(DEFAULT_ARRIVAL_TIME)
            .seatedTime(DEFAULT_SEATED_TIME)
            .leftTime(DEFAULT_LEFT_TIME)
            .clientRequests(DEFAULT_CLIENT_REQUESTS)
            .comps(DEFAULT_COMPS)
            .compsPriceType(DEFAULT_COMPS_PRICE_TYPE)
            .costOption(DEFAULT_COST_OPTION)
            .policy(DEFAULT_POLICY)
            .minPrice(DEFAULT_MIN_PRICE)
            .prePayment(DEFAULT_PRE_PAYMENT)
            .onsitePayment(DEFAULT_ONSITE_PAYMENT)
            .totalPayment(DEFAULT_TOTAL_PAYMENT)
            .paidBy(DEFAULT_PAID_BY)
            .servedBy(DEFAULT_SERVED_BY)
            .rating(DEFAULT_RATING)
            .problems(DEFAULT_PROBLEMS)
            .autoAssignments(DEFAULT_AUTO_ASSIGNMENTS)
            .externalClientId(DEFAULT_EXTERNAL_CLIENT_ID)
            .externalId(DEFAULT_EXTERNAL_ID)
            .externalReferenceCode(DEFAULT_EXTERNAL_REFERENCE_CODE)
            .externalUserId(DEFAULT_EXTERNAL_USER_ID)
            .modifyReservationLink(DEFAULT_MODIFY_RESERVATION_LINK)
            .referenceCode(DEFAULT_REFERENCE_CODE)
            .reservationSmsOptin(DEFAULT_RESERVATION_SMS_OPTIN)
            .reservationType(DEFAULT_RESERVATION_TYPE)
            .sendReminderEmail(DEFAULT_SEND_REMINDER_EMAIL)
            .sendreminderSms(DEFAULT_SENDREMINDER_SMS)
            .sourceClientId(DEFAULT_SOURCE_CLIENT_ID)
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .techLineage(DEFAULT_TECH_LINEAGE)
            .techCreatedDate(DEFAULT_TECH_CREATED_DATE)
            .techUpdatedDate(DEFAULT_TECH_UPDATED_DATE)
            .techMapping(DEFAULT_TECH_MAPPING)
            .techComment(DEFAULT_TECH_COMMENT);
        return reservation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reservation createUpdatedEntity(EntityManager em) {
        Reservation reservation = new Reservation()
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .updated(UPDATED_UPDATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueId(UPDATED_VENUE_ID)
            .date(UPDATED_DATE)
            .duration(UPDATED_DURATION)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .maxGuests(UPDATED_MAX_GUESTS)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .status(UPDATED_STATUS)
            .statusDisplay(UPDATED_STATUS_DISPLAY)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .tableNumbers(UPDATED_TABLE_NUMBERS)
            .accessPersistentId(UPDATED_ACCESS_PERSISTENT_ID)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .isvip(UPDATED_ISVIP)
            .bookedby(UPDATED_BOOKEDBY)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .notes(UPDATED_NOTES)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .compsPriceType(UPDATED_COMPS_PRICE_TYPE)
            .costOption(UPDATED_COST_OPTION)
            .policy(UPDATED_POLICY)
            .minPrice(UPDATED_MIN_PRICE)
            .prePayment(UPDATED_PRE_PAYMENT)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .totalPayment(UPDATED_TOTAL_PAYMENT)
            .paidBy(UPDATED_PAID_BY)
            .servedBy(UPDATED_SERVED_BY)
            .rating(UPDATED_RATING)
            .problems(UPDATED_PROBLEMS)
            .autoAssignments(UPDATED_AUTO_ASSIGNMENTS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .modifyReservationLink(UPDATED_MODIFY_RESERVATION_LINK)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .reservationSmsOptin(UPDATED_RESERVATION_SMS_OPTIN)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);
        return reservation;
    }

    @BeforeEach
    public void initTest() {
        reservation = createEntity(em);
    }

    @Test
    @Transactional
    void createReservation() throws Exception {
        int databaseSizeBeforeCreate = reservationRepository.findAll().size();
        // Create the Reservation
        restReservationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservation)))
            .andExpect(status().isCreated());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeCreate + 1);
        Reservation testReservation = reservationList.get(reservationList.size() - 1);
        assertThat(testReservation.getResvId()).isEqualTo(DEFAULT_RESV_ID);
        assertThat(testReservation.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testReservation.getUpdated()).isEqualTo(DEFAULT_UPDATED);
        assertThat(testReservation.getDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testReservation.getVenueGroupClientId()).isEqualTo(DEFAULT_VENUE_GROUP_CLIENT_ID);
        assertThat(testReservation.getVenueGroupId()).isEqualTo(DEFAULT_VENUE_GROUP_ID);
        assertThat(testReservation.getVenueId()).isEqualTo(DEFAULT_VENUE_ID);
        assertThat(testReservation.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testReservation.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testReservation.getCheckNumbers()).isEqualTo(DEFAULT_CHECK_NUMBERS);
        assertThat(testReservation.getShiftCategory()).isEqualTo(DEFAULT_SHIFT_CATEGORY);
        assertThat(testReservation.getShiftPersistentId()).isEqualTo(DEFAULT_SHIFT_PERSISTENT_ID);
        assertThat(testReservation.getMaxGuests()).isEqualTo(DEFAULT_MAX_GUESTS);
        assertThat(testReservation.getMfratioMale()).isEqualTo(DEFAULT_MFRATIO_MALE);
        assertThat(testReservation.getMfratioFemale()).isEqualTo(DEFAULT_MFRATIO_FEMALE);
        assertThat(testReservation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testReservation.getStatusDisplay()).isEqualTo(DEFAULT_STATUS_DISPLAY);
        assertThat(testReservation.getStatusSimple()).isEqualTo(DEFAULT_STATUS_SIMPLE);
        assertThat(testReservation.getTableNumbers()).isEqualTo(DEFAULT_TABLE_NUMBERS);
        assertThat(testReservation.getAccessPersistentId()).isEqualTo(DEFAULT_ACCESS_PERSISTENT_ID);
        assertThat(testReservation.getArrivedGuests()).isEqualTo(DEFAULT_ARRIVED_GUESTS);
        assertThat(testReservation.getIsvip()).isEqualTo(DEFAULT_ISVIP);
        assertThat(testReservation.getBookedby()).isEqualTo(DEFAULT_BOOKEDBY);
        assertThat(testReservation.getClientReferenceCode()).isEqualTo(DEFAULT_CLIENT_REFERENCE_CODE);
        assertThat(testReservation.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testReservation.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testReservation.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testReservation.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testReservation.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testReservation.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testReservation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testReservation.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testReservation.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testReservation.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testReservation.getLoyaltyId()).isEqualTo(DEFAULT_LOYALTY_ID);
        assertThat(testReservation.getLoyaltyRank()).isEqualTo(DEFAULT_LOYALTY_RANK);
        assertThat(testReservation.getLoyaltyTier()).isEqualTo(DEFAULT_LOYALTY_TIER);
        assertThat(testReservation.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testReservation.getArrivalTime()).isEqualTo(DEFAULT_ARRIVAL_TIME);
        assertThat(testReservation.getSeatedTime()).isEqualTo(DEFAULT_SEATED_TIME);
        assertThat(testReservation.getLeftTime()).isEqualTo(DEFAULT_LEFT_TIME);
        assertThat(testReservation.getClientRequests()).isEqualTo(DEFAULT_CLIENT_REQUESTS);
        assertThat(testReservation.getComps()).isEqualTo(DEFAULT_COMPS);
        assertThat(testReservation.getCompsPriceType()).isEqualTo(DEFAULT_COMPS_PRICE_TYPE);
        assertThat(testReservation.getCostOption()).isEqualTo(DEFAULT_COST_OPTION);
        assertThat(testReservation.getPolicy()).isEqualTo(DEFAULT_POLICY);
        assertThat(testReservation.getMinPrice()).isEqualTo(DEFAULT_MIN_PRICE);
        assertThat(testReservation.getPrePayment()).isEqualTo(DEFAULT_PRE_PAYMENT);
        assertThat(testReservation.getOnsitePayment()).isEqualTo(DEFAULT_ONSITE_PAYMENT);
        assertThat(testReservation.getTotalPayment()).isEqualTo(DEFAULT_TOTAL_PAYMENT);
        assertThat(testReservation.getPaidBy()).isEqualTo(DEFAULT_PAID_BY);
        assertThat(testReservation.getServedBy()).isEqualTo(DEFAULT_SERVED_BY);
        assertThat(testReservation.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testReservation.getProblems()).isEqualTo(DEFAULT_PROBLEMS);
        assertThat(testReservation.getAutoAssignments()).isEqualTo(DEFAULT_AUTO_ASSIGNMENTS);
        assertThat(testReservation.getExternalClientId()).isEqualTo(DEFAULT_EXTERNAL_CLIENT_ID);
        assertThat(testReservation.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testReservation.getExternalReferenceCode()).isEqualTo(DEFAULT_EXTERNAL_REFERENCE_CODE);
        assertThat(testReservation.getExternalUserId()).isEqualTo(DEFAULT_EXTERNAL_USER_ID);
        assertThat(testReservation.getModifyReservationLink()).isEqualTo(DEFAULT_MODIFY_RESERVATION_LINK);
        assertThat(testReservation.getReferenceCode()).isEqualTo(DEFAULT_REFERENCE_CODE);
        assertThat(testReservation.getReservationSmsOptin()).isEqualTo(DEFAULT_RESERVATION_SMS_OPTIN);
        assertThat(testReservation.getReservationType()).isEqualTo(DEFAULT_RESERVATION_TYPE);
        assertThat(testReservation.getSendReminderEmail()).isEqualTo(DEFAULT_SEND_REMINDER_EMAIL);
        assertThat(testReservation.getSendreminderSms()).isEqualTo(DEFAULT_SENDREMINDER_SMS);
        assertThat(testReservation.getSourceClientId()).isEqualTo(DEFAULT_SOURCE_CLIENT_ID);
        assertThat(testReservation.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testReservation.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testReservation.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testReservation.getTechCreatedDate()).isEqualTo(DEFAULT_TECH_CREATED_DATE);
        assertThat(testReservation.getTechUpdatedDate()).isEqualTo(DEFAULT_TECH_UPDATED_DATE);
        assertThat(testReservation.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testReservation.getTechComment()).isEqualTo(DEFAULT_TECH_COMMENT);
    }

    @Test
    @Transactional
    void createReservationWithExistingId() throws Exception {
        // Create the Reservation with an existing ID
        reservation.setId(1L);

        int databaseSizeBeforeCreate = reservationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReservationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservation)))
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReservations() throws Exception {
        // Initialize the database
        reservationRepository.saveAndFlush(reservation);

        // Get all the reservationList
        restReservationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reservation.getId().intValue())))
            .andExpect(jsonPath("$.[*].resvId").value(hasItem(DEFAULT_RESV_ID)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED)))
            .andExpect(jsonPath("$.[*].updated").value(hasItem(DEFAULT_UPDATED)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED)))
            .andExpect(jsonPath("$.[*].venueGroupClientId").value(hasItem(DEFAULT_VENUE_GROUP_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].venueGroupId").value(hasItem(DEFAULT_VENUE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].venueId").value(hasItem(DEFAULT_VENUE_ID)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].checkNumbers").value(hasItem(DEFAULT_CHECK_NUMBERS)))
            .andExpect(jsonPath("$.[*].shiftCategory").value(hasItem(DEFAULT_SHIFT_CATEGORY)))
            .andExpect(jsonPath("$.[*].shiftPersistentId").value(hasItem(DEFAULT_SHIFT_PERSISTENT_ID)))
            .andExpect(jsonPath("$.[*].maxGuests").value(hasItem(DEFAULT_MAX_GUESTS)))
            .andExpect(jsonPath("$.[*].mfratioMale").value(hasItem(DEFAULT_MFRATIO_MALE)))
            .andExpect(jsonPath("$.[*].mfratioFemale").value(hasItem(DEFAULT_MFRATIO_FEMALE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusDisplay").value(hasItem(DEFAULT_STATUS_DISPLAY)))
            .andExpect(jsonPath("$.[*].statusSimple").value(hasItem(DEFAULT_STATUS_SIMPLE)))
            .andExpect(jsonPath("$.[*].tableNumbers").value(hasItem(DEFAULT_TABLE_NUMBERS)))
            .andExpect(jsonPath("$.[*].accessPersistentId").value(hasItem(DEFAULT_ACCESS_PERSISTENT_ID)))
            .andExpect(jsonPath("$.[*].arrivedGuests").value(hasItem(DEFAULT_ARRIVED_GUESTS)))
            .andExpect(jsonPath("$.[*].isvip").value(hasItem(DEFAULT_ISVIP.booleanValue())))
            .andExpect(jsonPath("$.[*].bookedby").value(hasItem(DEFAULT_BOOKEDBY)))
            .andExpect(jsonPath("$.[*].clientReferenceCode").value(hasItem(DEFAULT_CLIENT_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].loyaltyId").value(hasItem(DEFAULT_LOYALTY_ID)))
            .andExpect(jsonPath("$.[*].loyaltyRank").value(hasItem(DEFAULT_LOYALTY_RANK)))
            .andExpect(jsonPath("$.[*].loyaltyTier").value(hasItem(DEFAULT_LOYALTY_TIER)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].arrivalTime").value(hasItem(DEFAULT_ARRIVAL_TIME)))
            .andExpect(jsonPath("$.[*].seatedTime").value(hasItem(DEFAULT_SEATED_TIME)))
            .andExpect(jsonPath("$.[*].leftTime").value(hasItem(DEFAULT_LEFT_TIME)))
            .andExpect(jsonPath("$.[*].clientRequests").value(hasItem(DEFAULT_CLIENT_REQUESTS)))
            .andExpect(jsonPath("$.[*].comps").value(hasItem(DEFAULT_COMPS)))
            .andExpect(jsonPath("$.[*].compsPriceType").value(hasItem(DEFAULT_COMPS_PRICE_TYPE)))
            .andExpect(jsonPath("$.[*].costOption").value(hasItem(DEFAULT_COST_OPTION)))
            .andExpect(jsonPath("$.[*].policy").value(hasItem(DEFAULT_POLICY)))
            .andExpect(jsonPath("$.[*].minPrice").value(hasItem(DEFAULT_MIN_PRICE)))
            .andExpect(jsonPath("$.[*].prePayment").value(hasItem(DEFAULT_PRE_PAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].onsitePayment").value(hasItem(DEFAULT_ONSITE_PAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalPayment").value(hasItem(DEFAULT_TOTAL_PAYMENT)))
            .andExpect(jsonPath("$.[*].paidBy").value(hasItem(DEFAULT_PAID_BY)))
            .andExpect(jsonPath("$.[*].servedBy").value(hasItem(DEFAULT_SERVED_BY)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)))
            .andExpect(jsonPath("$.[*].problems").value(hasItem(DEFAULT_PROBLEMS)))
            .andExpect(jsonPath("$.[*].autoAssignments").value(hasItem(DEFAULT_AUTO_ASSIGNMENTS)))
            .andExpect(jsonPath("$.[*].externalClientId").value(hasItem(DEFAULT_EXTERNAL_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].externalReferenceCode").value(hasItem(DEFAULT_EXTERNAL_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].externalUserId").value(hasItem(DEFAULT_EXTERNAL_USER_ID)))
            .andExpect(jsonPath("$.[*].modifyReservationLink").value(hasItem(DEFAULT_MODIFY_RESERVATION_LINK)))
            .andExpect(jsonPath("$.[*].referenceCode").value(hasItem(DEFAULT_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].reservationSmsOptin").value(hasItem(DEFAULT_RESERVATION_SMS_OPTIN.booleanValue())))
            .andExpect(jsonPath("$.[*].reservationType").value(hasItem(DEFAULT_RESERVATION_TYPE)))
            .andExpect(jsonPath("$.[*].sendReminderEmail").value(hasItem(DEFAULT_SEND_REMINDER_EMAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].sendreminderSms").value(hasItem(DEFAULT_SENDREMINDER_SMS.booleanValue())))
            .andExpect(jsonPath("$.[*].sourceClientId").value(hasItem(DEFAULT_SOURCE_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].techLineage").value(hasItem(DEFAULT_TECH_LINEAGE)))
            .andExpect(jsonPath("$.[*].techCreatedDate").value(hasItem(sameInstant(DEFAULT_TECH_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].techUpdatedDate").value(hasItem(sameInstant(DEFAULT_TECH_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].techMapping").value(hasItem(DEFAULT_TECH_MAPPING)))
            .andExpect(jsonPath("$.[*].techComment").value(hasItem(DEFAULT_TECH_COMMENT)));
    }

    @Test
    @Transactional
    void getReservation() throws Exception {
        // Initialize the database
        reservationRepository.saveAndFlush(reservation);

        // Get the reservation
        restReservationMockMvc
            .perform(get(ENTITY_API_URL_ID, reservation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reservation.getId().intValue()))
            .andExpect(jsonPath("$.resvId").value(DEFAULT_RESV_ID))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED))
            .andExpect(jsonPath("$.updated").value(DEFAULT_UPDATED))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED))
            .andExpect(jsonPath("$.venueGroupClientId").value(DEFAULT_VENUE_GROUP_CLIENT_ID))
            .andExpect(jsonPath("$.venueGroupId").value(DEFAULT_VENUE_GROUP_ID))
            .andExpect(jsonPath("$.venueId").value(DEFAULT_VENUE_ID))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.checkNumbers").value(DEFAULT_CHECK_NUMBERS))
            .andExpect(jsonPath("$.shiftCategory").value(DEFAULT_SHIFT_CATEGORY))
            .andExpect(jsonPath("$.shiftPersistentId").value(DEFAULT_SHIFT_PERSISTENT_ID))
            .andExpect(jsonPath("$.maxGuests").value(DEFAULT_MAX_GUESTS))
            .andExpect(jsonPath("$.mfratioMale").value(DEFAULT_MFRATIO_MALE))
            .andExpect(jsonPath("$.mfratioFemale").value(DEFAULT_MFRATIO_FEMALE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusDisplay").value(DEFAULT_STATUS_DISPLAY))
            .andExpect(jsonPath("$.statusSimple").value(DEFAULT_STATUS_SIMPLE))
            .andExpect(jsonPath("$.tableNumbers").value(DEFAULT_TABLE_NUMBERS))
            .andExpect(jsonPath("$.accessPersistentId").value(DEFAULT_ACCESS_PERSISTENT_ID))
            .andExpect(jsonPath("$.arrivedGuests").value(DEFAULT_ARRIVED_GUESTS))
            .andExpect(jsonPath("$.isvip").value(DEFAULT_ISVIP.booleanValue()))
            .andExpect(jsonPath("$.bookedby").value(DEFAULT_BOOKEDBY))
            .andExpect(jsonPath("$.clientReferenceCode").value(DEFAULT_CLIENT_REFERENCE_CODE))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.loyaltyId").value(DEFAULT_LOYALTY_ID))
            .andExpect(jsonPath("$.loyaltyRank").value(DEFAULT_LOYALTY_RANK))
            .andExpect(jsonPath("$.loyaltyTier").value(DEFAULT_LOYALTY_TIER))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.arrivalTime").value(DEFAULT_ARRIVAL_TIME))
            .andExpect(jsonPath("$.seatedTime").value(DEFAULT_SEATED_TIME))
            .andExpect(jsonPath("$.leftTime").value(DEFAULT_LEFT_TIME))
            .andExpect(jsonPath("$.clientRequests").value(DEFAULT_CLIENT_REQUESTS))
            .andExpect(jsonPath("$.comps").value(DEFAULT_COMPS))
            .andExpect(jsonPath("$.compsPriceType").value(DEFAULT_COMPS_PRICE_TYPE))
            .andExpect(jsonPath("$.costOption").value(DEFAULT_COST_OPTION))
            .andExpect(jsonPath("$.policy").value(DEFAULT_POLICY))
            .andExpect(jsonPath("$.minPrice").value(DEFAULT_MIN_PRICE))
            .andExpect(jsonPath("$.prePayment").value(DEFAULT_PRE_PAYMENT.doubleValue()))
            .andExpect(jsonPath("$.onsitePayment").value(DEFAULT_ONSITE_PAYMENT.doubleValue()))
            .andExpect(jsonPath("$.totalPayment").value(DEFAULT_TOTAL_PAYMENT))
            .andExpect(jsonPath("$.paidBy").value(DEFAULT_PAID_BY))
            .andExpect(jsonPath("$.servedBy").value(DEFAULT_SERVED_BY))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING))
            .andExpect(jsonPath("$.problems").value(DEFAULT_PROBLEMS))
            .andExpect(jsonPath("$.autoAssignments").value(DEFAULT_AUTO_ASSIGNMENTS))
            .andExpect(jsonPath("$.externalClientId").value(DEFAULT_EXTERNAL_CLIENT_ID))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.externalReferenceCode").value(DEFAULT_EXTERNAL_REFERENCE_CODE))
            .andExpect(jsonPath("$.externalUserId").value(DEFAULT_EXTERNAL_USER_ID))
            .andExpect(jsonPath("$.modifyReservationLink").value(DEFAULT_MODIFY_RESERVATION_LINK))
            .andExpect(jsonPath("$.referenceCode").value(DEFAULT_REFERENCE_CODE))
            .andExpect(jsonPath("$.reservationSmsOptin").value(DEFAULT_RESERVATION_SMS_OPTIN.booleanValue()))
            .andExpect(jsonPath("$.reservationType").value(DEFAULT_RESERVATION_TYPE))
            .andExpect(jsonPath("$.sendReminderEmail").value(DEFAULT_SEND_REMINDER_EMAIL.booleanValue()))
            .andExpect(jsonPath("$.sendreminderSms").value(DEFAULT_SENDREMINDER_SMS.booleanValue()))
            .andExpect(jsonPath("$.sourceClientId").value(DEFAULT_SOURCE_CLIENT_ID))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.techLineage").value(DEFAULT_TECH_LINEAGE))
            .andExpect(jsonPath("$.techCreatedDate").value(sameInstant(DEFAULT_TECH_CREATED_DATE)))
            .andExpect(jsonPath("$.techUpdatedDate").value(sameInstant(DEFAULT_TECH_UPDATED_DATE)))
            .andExpect(jsonPath("$.techMapping").value(DEFAULT_TECH_MAPPING))
            .andExpect(jsonPath("$.techComment").value(DEFAULT_TECH_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingReservation() throws Exception {
        // Get the reservation
        restReservationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReservation() throws Exception {
        // Initialize the database
        reservationRepository.saveAndFlush(reservation);

        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();

        // Update the reservation
        Reservation updatedReservation = reservationRepository.findById(reservation.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReservation are not directly saved in db
        em.detach(updatedReservation);
        updatedReservation
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .updated(UPDATED_UPDATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueId(UPDATED_VENUE_ID)
            .date(UPDATED_DATE)
            .duration(UPDATED_DURATION)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .maxGuests(UPDATED_MAX_GUESTS)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .status(UPDATED_STATUS)
            .statusDisplay(UPDATED_STATUS_DISPLAY)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .tableNumbers(UPDATED_TABLE_NUMBERS)
            .accessPersistentId(UPDATED_ACCESS_PERSISTENT_ID)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .isvip(UPDATED_ISVIP)
            .bookedby(UPDATED_BOOKEDBY)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .notes(UPDATED_NOTES)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .compsPriceType(UPDATED_COMPS_PRICE_TYPE)
            .costOption(UPDATED_COST_OPTION)
            .policy(UPDATED_POLICY)
            .minPrice(UPDATED_MIN_PRICE)
            .prePayment(UPDATED_PRE_PAYMENT)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .totalPayment(UPDATED_TOTAL_PAYMENT)
            .paidBy(UPDATED_PAID_BY)
            .servedBy(UPDATED_SERVED_BY)
            .rating(UPDATED_RATING)
            .problems(UPDATED_PROBLEMS)
            .autoAssignments(UPDATED_AUTO_ASSIGNMENTS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .modifyReservationLink(UPDATED_MODIFY_RESERVATION_LINK)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .reservationSmsOptin(UPDATED_RESERVATION_SMS_OPTIN)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restReservationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReservation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReservation))
            )
            .andExpect(status().isOk());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
        Reservation testReservation = reservationList.get(reservationList.size() - 1);
        assertThat(testReservation.getResvId()).isEqualTo(UPDATED_RESV_ID);
        assertThat(testReservation.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testReservation.getUpdated()).isEqualTo(UPDATED_UPDATED);
        assertThat(testReservation.getDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testReservation.getVenueGroupClientId()).isEqualTo(UPDATED_VENUE_GROUP_CLIENT_ID);
        assertThat(testReservation.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testReservation.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testReservation.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testReservation.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testReservation.getCheckNumbers()).isEqualTo(UPDATED_CHECK_NUMBERS);
        assertThat(testReservation.getShiftCategory()).isEqualTo(UPDATED_SHIFT_CATEGORY);
        assertThat(testReservation.getShiftPersistentId()).isEqualTo(UPDATED_SHIFT_PERSISTENT_ID);
        assertThat(testReservation.getMaxGuests()).isEqualTo(UPDATED_MAX_GUESTS);
        assertThat(testReservation.getMfratioMale()).isEqualTo(UPDATED_MFRATIO_MALE);
        assertThat(testReservation.getMfratioFemale()).isEqualTo(UPDATED_MFRATIO_FEMALE);
        assertThat(testReservation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReservation.getStatusDisplay()).isEqualTo(UPDATED_STATUS_DISPLAY);
        assertThat(testReservation.getStatusSimple()).isEqualTo(UPDATED_STATUS_SIMPLE);
        assertThat(testReservation.getTableNumbers()).isEqualTo(UPDATED_TABLE_NUMBERS);
        assertThat(testReservation.getAccessPersistentId()).isEqualTo(UPDATED_ACCESS_PERSISTENT_ID);
        assertThat(testReservation.getArrivedGuests()).isEqualTo(UPDATED_ARRIVED_GUESTS);
        assertThat(testReservation.getIsvip()).isEqualTo(UPDATED_ISVIP);
        assertThat(testReservation.getBookedby()).isEqualTo(UPDATED_BOOKEDBY);
        assertThat(testReservation.getClientReferenceCode()).isEqualTo(UPDATED_CLIENT_REFERENCE_CODE);
        assertThat(testReservation.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testReservation.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testReservation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testReservation.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testReservation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testReservation.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testReservation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testReservation.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testReservation.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testReservation.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testReservation.getLoyaltyId()).isEqualTo(UPDATED_LOYALTY_ID);
        assertThat(testReservation.getLoyaltyRank()).isEqualTo(UPDATED_LOYALTY_RANK);
        assertThat(testReservation.getLoyaltyTier()).isEqualTo(UPDATED_LOYALTY_TIER);
        assertThat(testReservation.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testReservation.getArrivalTime()).isEqualTo(UPDATED_ARRIVAL_TIME);
        assertThat(testReservation.getSeatedTime()).isEqualTo(UPDATED_SEATED_TIME);
        assertThat(testReservation.getLeftTime()).isEqualTo(UPDATED_LEFT_TIME);
        assertThat(testReservation.getClientRequests()).isEqualTo(UPDATED_CLIENT_REQUESTS);
        assertThat(testReservation.getComps()).isEqualTo(UPDATED_COMPS);
        assertThat(testReservation.getCompsPriceType()).isEqualTo(UPDATED_COMPS_PRICE_TYPE);
        assertThat(testReservation.getCostOption()).isEqualTo(UPDATED_COST_OPTION);
        assertThat(testReservation.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testReservation.getMinPrice()).isEqualTo(UPDATED_MIN_PRICE);
        assertThat(testReservation.getPrePayment()).isEqualTo(UPDATED_PRE_PAYMENT);
        assertThat(testReservation.getOnsitePayment()).isEqualTo(UPDATED_ONSITE_PAYMENT);
        assertThat(testReservation.getTotalPayment()).isEqualTo(UPDATED_TOTAL_PAYMENT);
        assertThat(testReservation.getPaidBy()).isEqualTo(UPDATED_PAID_BY);
        assertThat(testReservation.getServedBy()).isEqualTo(UPDATED_SERVED_BY);
        assertThat(testReservation.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testReservation.getProblems()).isEqualTo(UPDATED_PROBLEMS);
        assertThat(testReservation.getAutoAssignments()).isEqualTo(UPDATED_AUTO_ASSIGNMENTS);
        assertThat(testReservation.getExternalClientId()).isEqualTo(UPDATED_EXTERNAL_CLIENT_ID);
        assertThat(testReservation.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testReservation.getExternalReferenceCode()).isEqualTo(UPDATED_EXTERNAL_REFERENCE_CODE);
        assertThat(testReservation.getExternalUserId()).isEqualTo(UPDATED_EXTERNAL_USER_ID);
        assertThat(testReservation.getModifyReservationLink()).isEqualTo(UPDATED_MODIFY_RESERVATION_LINK);
        assertThat(testReservation.getReferenceCode()).isEqualTo(UPDATED_REFERENCE_CODE);
        assertThat(testReservation.getReservationSmsOptin()).isEqualTo(UPDATED_RESERVATION_SMS_OPTIN);
        assertThat(testReservation.getReservationType()).isEqualTo(UPDATED_RESERVATION_TYPE);
        assertThat(testReservation.getSendReminderEmail()).isEqualTo(UPDATED_SEND_REMINDER_EMAIL);
        assertThat(testReservation.getSendreminderSms()).isEqualTo(UPDATED_SENDREMINDER_SMS);
        assertThat(testReservation.getSourceClientId()).isEqualTo(UPDATED_SOURCE_CLIENT_ID);
        assertThat(testReservation.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testReservation.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testReservation.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testReservation.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testReservation.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testReservation.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testReservation.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void putNonExistingReservation() throws Exception {
        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();
        reservation.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reservation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReservation() throws Exception {
        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();
        reservation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReservation() throws Exception {
        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();
        reservation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReservationWithPatch() throws Exception {
        // Initialize the database
        reservationRepository.saveAndFlush(reservation);

        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();

        // Update the reservation using partial update
        Reservation partialUpdatedReservation = new Reservation();
        partialUpdatedReservation.setId(reservation.getId());

        partialUpdatedReservation
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueId(UPDATED_VENUE_ID)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .tableNumbers(UPDATED_TABLE_NUMBERS)
            .isvip(UPDATED_ISVIP)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .policy(UPDATED_POLICY)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .servedBy(UPDATED_SERVED_BY)
            .problems(UPDATED_PROBLEMS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userName(UPDATED_USER_NAME)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techComment(UPDATED_TECH_COMMENT);

        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReservation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReservation))
            )
            .andExpect(status().isOk());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
        Reservation testReservation = reservationList.get(reservationList.size() - 1);
        assertThat(testReservation.getResvId()).isEqualTo(UPDATED_RESV_ID);
        assertThat(testReservation.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testReservation.getUpdated()).isEqualTo(DEFAULT_UPDATED);
        assertThat(testReservation.getDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testReservation.getVenueGroupClientId()).isEqualTo(UPDATED_VENUE_GROUP_CLIENT_ID);
        assertThat(testReservation.getVenueGroupId()).isEqualTo(DEFAULT_VENUE_GROUP_ID);
        assertThat(testReservation.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testReservation.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testReservation.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testReservation.getCheckNumbers()).isEqualTo(UPDATED_CHECK_NUMBERS);
        assertThat(testReservation.getShiftCategory()).isEqualTo(DEFAULT_SHIFT_CATEGORY);
        assertThat(testReservation.getShiftPersistentId()).isEqualTo(UPDATED_SHIFT_PERSISTENT_ID);
        assertThat(testReservation.getMaxGuests()).isEqualTo(DEFAULT_MAX_GUESTS);
        assertThat(testReservation.getMfratioMale()).isEqualTo(DEFAULT_MFRATIO_MALE);
        assertThat(testReservation.getMfratioFemale()).isEqualTo(UPDATED_MFRATIO_FEMALE);
        assertThat(testReservation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testReservation.getStatusDisplay()).isEqualTo(DEFAULT_STATUS_DISPLAY);
        assertThat(testReservation.getStatusSimple()).isEqualTo(UPDATED_STATUS_SIMPLE);
        assertThat(testReservation.getTableNumbers()).isEqualTo(UPDATED_TABLE_NUMBERS);
        assertThat(testReservation.getAccessPersistentId()).isEqualTo(DEFAULT_ACCESS_PERSISTENT_ID);
        assertThat(testReservation.getArrivedGuests()).isEqualTo(DEFAULT_ARRIVED_GUESTS);
        assertThat(testReservation.getIsvip()).isEqualTo(UPDATED_ISVIP);
        assertThat(testReservation.getBookedby()).isEqualTo(DEFAULT_BOOKEDBY);
        assertThat(testReservation.getClientReferenceCode()).isEqualTo(UPDATED_CLIENT_REFERENCE_CODE);
        assertThat(testReservation.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testReservation.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testReservation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testReservation.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testReservation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testReservation.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testReservation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testReservation.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testReservation.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testReservation.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testReservation.getLoyaltyId()).isEqualTo(UPDATED_LOYALTY_ID);
        assertThat(testReservation.getLoyaltyRank()).isEqualTo(DEFAULT_LOYALTY_RANK);
        assertThat(testReservation.getLoyaltyTier()).isEqualTo(UPDATED_LOYALTY_TIER);
        assertThat(testReservation.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testReservation.getArrivalTime()).isEqualTo(UPDATED_ARRIVAL_TIME);
        assertThat(testReservation.getSeatedTime()).isEqualTo(UPDATED_SEATED_TIME);
        assertThat(testReservation.getLeftTime()).isEqualTo(DEFAULT_LEFT_TIME);
        assertThat(testReservation.getClientRequests()).isEqualTo(UPDATED_CLIENT_REQUESTS);
        assertThat(testReservation.getComps()).isEqualTo(DEFAULT_COMPS);
        assertThat(testReservation.getCompsPriceType()).isEqualTo(DEFAULT_COMPS_PRICE_TYPE);
        assertThat(testReservation.getCostOption()).isEqualTo(DEFAULT_COST_OPTION);
        assertThat(testReservation.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testReservation.getMinPrice()).isEqualTo(DEFAULT_MIN_PRICE);
        assertThat(testReservation.getPrePayment()).isEqualTo(DEFAULT_PRE_PAYMENT);
        assertThat(testReservation.getOnsitePayment()).isEqualTo(UPDATED_ONSITE_PAYMENT);
        assertThat(testReservation.getTotalPayment()).isEqualTo(DEFAULT_TOTAL_PAYMENT);
        assertThat(testReservation.getPaidBy()).isEqualTo(DEFAULT_PAID_BY);
        assertThat(testReservation.getServedBy()).isEqualTo(UPDATED_SERVED_BY);
        assertThat(testReservation.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testReservation.getProblems()).isEqualTo(UPDATED_PROBLEMS);
        assertThat(testReservation.getAutoAssignments()).isEqualTo(DEFAULT_AUTO_ASSIGNMENTS);
        assertThat(testReservation.getExternalClientId()).isEqualTo(UPDATED_EXTERNAL_CLIENT_ID);
        assertThat(testReservation.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testReservation.getExternalReferenceCode()).isEqualTo(UPDATED_EXTERNAL_REFERENCE_CODE);
        assertThat(testReservation.getExternalUserId()).isEqualTo(UPDATED_EXTERNAL_USER_ID);
        assertThat(testReservation.getModifyReservationLink()).isEqualTo(DEFAULT_MODIFY_RESERVATION_LINK);
        assertThat(testReservation.getReferenceCode()).isEqualTo(DEFAULT_REFERENCE_CODE);
        assertThat(testReservation.getReservationSmsOptin()).isEqualTo(DEFAULT_RESERVATION_SMS_OPTIN);
        assertThat(testReservation.getReservationType()).isEqualTo(DEFAULT_RESERVATION_TYPE);
        assertThat(testReservation.getSendReminderEmail()).isEqualTo(UPDATED_SEND_REMINDER_EMAIL);
        assertThat(testReservation.getSendreminderSms()).isEqualTo(DEFAULT_SENDREMINDER_SMS);
        assertThat(testReservation.getSourceClientId()).isEqualTo(UPDATED_SOURCE_CLIENT_ID);
        assertThat(testReservation.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testReservation.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testReservation.getTechLineage()).isEqualTo(DEFAULT_TECH_LINEAGE);
        assertThat(testReservation.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testReservation.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testReservation.getTechMapping()).isEqualTo(DEFAULT_TECH_MAPPING);
        assertThat(testReservation.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateReservationWithPatch() throws Exception {
        // Initialize the database
        reservationRepository.saveAndFlush(reservation);

        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();

        // Update the reservation using partial update
        Reservation partialUpdatedReservation = new Reservation();
        partialUpdatedReservation.setId(reservation.getId());

        partialUpdatedReservation
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .updated(UPDATED_UPDATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueId(UPDATED_VENUE_ID)
            .date(UPDATED_DATE)
            .duration(UPDATED_DURATION)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .maxGuests(UPDATED_MAX_GUESTS)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .status(UPDATED_STATUS)
            .statusDisplay(UPDATED_STATUS_DISPLAY)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .tableNumbers(UPDATED_TABLE_NUMBERS)
            .accessPersistentId(UPDATED_ACCESS_PERSISTENT_ID)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .isvip(UPDATED_ISVIP)
            .bookedby(UPDATED_BOOKEDBY)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .notes(UPDATED_NOTES)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .compsPriceType(UPDATED_COMPS_PRICE_TYPE)
            .costOption(UPDATED_COST_OPTION)
            .policy(UPDATED_POLICY)
            .minPrice(UPDATED_MIN_PRICE)
            .prePayment(UPDATED_PRE_PAYMENT)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .totalPayment(UPDATED_TOTAL_PAYMENT)
            .paidBy(UPDATED_PAID_BY)
            .servedBy(UPDATED_SERVED_BY)
            .rating(UPDATED_RATING)
            .problems(UPDATED_PROBLEMS)
            .autoAssignments(UPDATED_AUTO_ASSIGNMENTS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .modifyReservationLink(UPDATED_MODIFY_RESERVATION_LINK)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .reservationSmsOptin(UPDATED_RESERVATION_SMS_OPTIN)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .techLineage(UPDATED_TECH_LINEAGE)
            .techCreatedDate(UPDATED_TECH_CREATED_DATE)
            .techUpdatedDate(UPDATED_TECH_UPDATED_DATE)
            .techMapping(UPDATED_TECH_MAPPING)
            .techComment(UPDATED_TECH_COMMENT);

        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReservation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReservation))
            )
            .andExpect(status().isOk());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
        Reservation testReservation = reservationList.get(reservationList.size() - 1);
        assertThat(testReservation.getResvId()).isEqualTo(UPDATED_RESV_ID);
        assertThat(testReservation.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testReservation.getUpdated()).isEqualTo(UPDATED_UPDATED);
        assertThat(testReservation.getDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testReservation.getVenueGroupClientId()).isEqualTo(UPDATED_VENUE_GROUP_CLIENT_ID);
        assertThat(testReservation.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testReservation.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testReservation.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testReservation.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testReservation.getCheckNumbers()).isEqualTo(UPDATED_CHECK_NUMBERS);
        assertThat(testReservation.getShiftCategory()).isEqualTo(UPDATED_SHIFT_CATEGORY);
        assertThat(testReservation.getShiftPersistentId()).isEqualTo(UPDATED_SHIFT_PERSISTENT_ID);
        assertThat(testReservation.getMaxGuests()).isEqualTo(UPDATED_MAX_GUESTS);
        assertThat(testReservation.getMfratioMale()).isEqualTo(UPDATED_MFRATIO_MALE);
        assertThat(testReservation.getMfratioFemale()).isEqualTo(UPDATED_MFRATIO_FEMALE);
        assertThat(testReservation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReservation.getStatusDisplay()).isEqualTo(UPDATED_STATUS_DISPLAY);
        assertThat(testReservation.getStatusSimple()).isEqualTo(UPDATED_STATUS_SIMPLE);
        assertThat(testReservation.getTableNumbers()).isEqualTo(UPDATED_TABLE_NUMBERS);
        assertThat(testReservation.getAccessPersistentId()).isEqualTo(UPDATED_ACCESS_PERSISTENT_ID);
        assertThat(testReservation.getArrivedGuests()).isEqualTo(UPDATED_ARRIVED_GUESTS);
        assertThat(testReservation.getIsvip()).isEqualTo(UPDATED_ISVIP);
        assertThat(testReservation.getBookedby()).isEqualTo(UPDATED_BOOKEDBY);
        assertThat(testReservation.getClientReferenceCode()).isEqualTo(UPDATED_CLIENT_REFERENCE_CODE);
        assertThat(testReservation.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testReservation.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testReservation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testReservation.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testReservation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testReservation.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testReservation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testReservation.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testReservation.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testReservation.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testReservation.getLoyaltyId()).isEqualTo(UPDATED_LOYALTY_ID);
        assertThat(testReservation.getLoyaltyRank()).isEqualTo(UPDATED_LOYALTY_RANK);
        assertThat(testReservation.getLoyaltyTier()).isEqualTo(UPDATED_LOYALTY_TIER);
        assertThat(testReservation.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testReservation.getArrivalTime()).isEqualTo(UPDATED_ARRIVAL_TIME);
        assertThat(testReservation.getSeatedTime()).isEqualTo(UPDATED_SEATED_TIME);
        assertThat(testReservation.getLeftTime()).isEqualTo(UPDATED_LEFT_TIME);
        assertThat(testReservation.getClientRequests()).isEqualTo(UPDATED_CLIENT_REQUESTS);
        assertThat(testReservation.getComps()).isEqualTo(UPDATED_COMPS);
        assertThat(testReservation.getCompsPriceType()).isEqualTo(UPDATED_COMPS_PRICE_TYPE);
        assertThat(testReservation.getCostOption()).isEqualTo(UPDATED_COST_OPTION);
        assertThat(testReservation.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testReservation.getMinPrice()).isEqualTo(UPDATED_MIN_PRICE);
        assertThat(testReservation.getPrePayment()).isEqualTo(UPDATED_PRE_PAYMENT);
        assertThat(testReservation.getOnsitePayment()).isEqualTo(UPDATED_ONSITE_PAYMENT);
        assertThat(testReservation.getTotalPayment()).isEqualTo(UPDATED_TOTAL_PAYMENT);
        assertThat(testReservation.getPaidBy()).isEqualTo(UPDATED_PAID_BY);
        assertThat(testReservation.getServedBy()).isEqualTo(UPDATED_SERVED_BY);
        assertThat(testReservation.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testReservation.getProblems()).isEqualTo(UPDATED_PROBLEMS);
        assertThat(testReservation.getAutoAssignments()).isEqualTo(UPDATED_AUTO_ASSIGNMENTS);
        assertThat(testReservation.getExternalClientId()).isEqualTo(UPDATED_EXTERNAL_CLIENT_ID);
        assertThat(testReservation.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testReservation.getExternalReferenceCode()).isEqualTo(UPDATED_EXTERNAL_REFERENCE_CODE);
        assertThat(testReservation.getExternalUserId()).isEqualTo(UPDATED_EXTERNAL_USER_ID);
        assertThat(testReservation.getModifyReservationLink()).isEqualTo(UPDATED_MODIFY_RESERVATION_LINK);
        assertThat(testReservation.getReferenceCode()).isEqualTo(UPDATED_REFERENCE_CODE);
        assertThat(testReservation.getReservationSmsOptin()).isEqualTo(UPDATED_RESERVATION_SMS_OPTIN);
        assertThat(testReservation.getReservationType()).isEqualTo(UPDATED_RESERVATION_TYPE);
        assertThat(testReservation.getSendReminderEmail()).isEqualTo(UPDATED_SEND_REMINDER_EMAIL);
        assertThat(testReservation.getSendreminderSms()).isEqualTo(UPDATED_SENDREMINDER_SMS);
        assertThat(testReservation.getSourceClientId()).isEqualTo(UPDATED_SOURCE_CLIENT_ID);
        assertThat(testReservation.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testReservation.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testReservation.getTechLineage()).isEqualTo(UPDATED_TECH_LINEAGE);
        assertThat(testReservation.getTechCreatedDate()).isEqualTo(UPDATED_TECH_CREATED_DATE);
        assertThat(testReservation.getTechUpdatedDate()).isEqualTo(UPDATED_TECH_UPDATED_DATE);
        assertThat(testReservation.getTechMapping()).isEqualTo(UPDATED_TECH_MAPPING);
        assertThat(testReservation.getTechComment()).isEqualTo(UPDATED_TECH_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingReservation() throws Exception {
        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();
        reservation.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reservation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReservation() throws Exception {
        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();
        reservation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReservation() throws Exception {
        int databaseSizeBeforeUpdate = reservationRepository.findAll().size();
        reservation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(reservation))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Reservation in the database
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReservation() throws Exception {
        // Initialize the database
        reservationRepository.saveAndFlush(reservation);

        int databaseSizeBeforeDelete = reservationRepository.findAll().size();

        // Delete the reservation
        restReservationMockMvc
            .perform(delete(ENTITY_API_URL_ID, reservation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
