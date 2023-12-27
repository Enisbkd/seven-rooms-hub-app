package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.repository.ReservationRepository;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.Reservation}.
 */
@RestController
@RequestMapping("/api/reservations")
@Transactional
public class ReservationResource {

    private final Logger log = LoggerFactory.getLogger(ReservationResource.class);

    private static final String ENTITY_NAME = "reservation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReservationRepository reservationRepository;

    public ReservationResource(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * {@code POST  /reservations} : Create a new reservation.
     *
     * @param reservation the reservation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reservation, or with status {@code 400 (Bad Request)} if the reservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) throws URISyntaxException {
        log.debug("REST request to save Reservation : {}", reservation);
        if (reservation.getId() != null) {
            throw new BadRequestAlertException("A new reservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Reservation result = reservationRepository.save(reservation);
        return ResponseEntity
            .created(new URI("/api/reservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reservations/:id} : Updates an existing reservation.
     *
     * @param id the id of the reservation to save.
     * @param reservation the reservation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reservation,
     * or with status {@code 400 (Bad Request)} if the reservation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reservation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Reservation reservation
    ) throws URISyntaxException {
        log.debug("REST request to update Reservation : {}, {}", id, reservation);
        if (reservation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reservation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reservationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Reservation result = reservationRepository.save(reservation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reservation.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reservations/:id} : Partial updates given fields of an existing reservation, field will ignore if it is null
     *
     * @param id the id of the reservation to save.
     * @param reservation the reservation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reservation,
     * or with status {@code 400 (Bad Request)} if the reservation is not valid,
     * or with status {@code 404 (Not Found)} if the reservation is not found,
     * or with status {@code 500 (Internal Server Error)} if the reservation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Reservation> partialUpdateReservation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Reservation reservation
    ) throws URISyntaxException {
        log.debug("REST request to partial update Reservation partially : {}, {}", id, reservation);
        if (reservation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reservation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reservationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Reservation> result = reservationRepository
            .findById(reservation.getId())
            .map(existingReservation -> {
                if (reservation.getResvId() != null) {
                    existingReservation.setResvId(reservation.getResvId());
                }
                if (reservation.getCreated() != null) {
                    existingReservation.setCreated(reservation.getCreated());
                }
                if (reservation.getUpdated() != null) {
                    existingReservation.setUpdated(reservation.getUpdated());
                }
                if (reservation.getDeleted() != null) {
                    existingReservation.setDeleted(reservation.getDeleted());
                }
                if (reservation.getVenueGroupClientId() != null) {
                    existingReservation.setVenueGroupClientId(reservation.getVenueGroupClientId());
                }
                if (reservation.getVenueGroupId() != null) {
                    existingReservation.setVenueGroupId(reservation.getVenueGroupId());
                }
                if (reservation.getVenueId() != null) {
                    existingReservation.setVenueId(reservation.getVenueId());
                }
                if (reservation.getDate() != null) {
                    existingReservation.setDate(reservation.getDate());
                }
                if (reservation.getDuration() != null) {
                    existingReservation.setDuration(reservation.getDuration());
                }
                if (reservation.getCheckNumbers() != null) {
                    existingReservation.setCheckNumbers(reservation.getCheckNumbers());
                }
                if (reservation.getShiftCategory() != null) {
                    existingReservation.setShiftCategory(reservation.getShiftCategory());
                }
                if (reservation.getShiftPersistentId() != null) {
                    existingReservation.setShiftPersistentId(reservation.getShiftPersistentId());
                }
                if (reservation.getMaxGuests() != null) {
                    existingReservation.setMaxGuests(reservation.getMaxGuests());
                }
                if (reservation.getMfratioMale() != null) {
                    existingReservation.setMfratioMale(reservation.getMfratioMale());
                }
                if (reservation.getMfratioFemale() != null) {
                    existingReservation.setMfratioFemale(reservation.getMfratioFemale());
                }
                if (reservation.getStatus() != null) {
                    existingReservation.setStatus(reservation.getStatus());
                }
                if (reservation.getStatusDisplay() != null) {
                    existingReservation.setStatusDisplay(reservation.getStatusDisplay());
                }
                if (reservation.getStatusSimple() != null) {
                    existingReservation.setStatusSimple(reservation.getStatusSimple());
                }
                if (reservation.getTableNumbers() != null) {
                    existingReservation.setTableNumbers(reservation.getTableNumbers());
                }
                if (reservation.getAccessPersistentId() != null) {
                    existingReservation.setAccessPersistentId(reservation.getAccessPersistentId());
                }
                if (reservation.getArrivedGuests() != null) {
                    existingReservation.setArrivedGuests(reservation.getArrivedGuests());
                }
                if (reservation.getIsvip() != null) {
                    existingReservation.setIsvip(reservation.getIsvip());
                }
                if (reservation.getBookedby() != null) {
                    existingReservation.setBookedby(reservation.getBookedby());
                }
                if (reservation.getClientReferenceCode() != null) {
                    existingReservation.setClientReferenceCode(reservation.getClientReferenceCode());
                }
                if (reservation.getLastname() != null) {
                    existingReservation.setLastname(reservation.getLastname());
                }
                if (reservation.getFirstname() != null) {
                    existingReservation.setFirstname(reservation.getFirstname());
                }
                if (reservation.getEmail() != null) {
                    existingReservation.setEmail(reservation.getEmail());
                }
                if (reservation.getPhoneNumber() != null) {
                    existingReservation.setPhoneNumber(reservation.getPhoneNumber());
                }
                if (reservation.getAddress() != null) {
                    existingReservation.setAddress(reservation.getAddress());
                }
                if (reservation.getAddress2() != null) {
                    existingReservation.setAddress2(reservation.getAddress2());
                }
                if (reservation.getCity() != null) {
                    existingReservation.setCity(reservation.getCity());
                }
                if (reservation.getPostalCode() != null) {
                    existingReservation.setPostalCode(reservation.getPostalCode());
                }
                if (reservation.getState() != null) {
                    existingReservation.setState(reservation.getState());
                }
                if (reservation.getCountry() != null) {
                    existingReservation.setCountry(reservation.getCountry());
                }
                if (reservation.getLoyaltyId() != null) {
                    existingReservation.setLoyaltyId(reservation.getLoyaltyId());
                }
                if (reservation.getLoyaltyRank() != null) {
                    existingReservation.setLoyaltyRank(reservation.getLoyaltyRank());
                }
                if (reservation.getLoyaltyTier() != null) {
                    existingReservation.setLoyaltyTier(reservation.getLoyaltyTier());
                }
                if (reservation.getNotes() != null) {
                    existingReservation.setNotes(reservation.getNotes());
                }
                if (reservation.getArrivalTime() != null) {
                    existingReservation.setArrivalTime(reservation.getArrivalTime());
                }
                if (reservation.getSeatedTime() != null) {
                    existingReservation.setSeatedTime(reservation.getSeatedTime());
                }
                if (reservation.getLeftTime() != null) {
                    existingReservation.setLeftTime(reservation.getLeftTime());
                }
                if (reservation.getClientRequests() != null) {
                    existingReservation.setClientRequests(reservation.getClientRequests());
                }
                if (reservation.getComps() != null) {
                    existingReservation.setComps(reservation.getComps());
                }
                if (reservation.getCompsPriceType() != null) {
                    existingReservation.setCompsPriceType(reservation.getCompsPriceType());
                }
                if (reservation.getCostOption() != null) {
                    existingReservation.setCostOption(reservation.getCostOption());
                }
                if (reservation.getPolicy() != null) {
                    existingReservation.setPolicy(reservation.getPolicy());
                }
                if (reservation.getMinPrice() != null) {
                    existingReservation.setMinPrice(reservation.getMinPrice());
                }
                if (reservation.getPrePayment() != null) {
                    existingReservation.setPrePayment(reservation.getPrePayment());
                }
                if (reservation.getOnsitePayment() != null) {
                    existingReservation.setOnsitePayment(reservation.getOnsitePayment());
                }
                if (reservation.getTotalPayment() != null) {
                    existingReservation.setTotalPayment(reservation.getTotalPayment());
                }
                if (reservation.getPaidBy() != null) {
                    existingReservation.setPaidBy(reservation.getPaidBy());
                }
                if (reservation.getServedBy() != null) {
                    existingReservation.setServedBy(reservation.getServedBy());
                }
                if (reservation.getRating() != null) {
                    existingReservation.setRating(reservation.getRating());
                }
                if (reservation.getProblems() != null) {
                    existingReservation.setProblems(reservation.getProblems());
                }
                if (reservation.getAutoAssignments() != null) {
                    existingReservation.setAutoAssignments(reservation.getAutoAssignments());
                }
                if (reservation.getExternalClientId() != null) {
                    existingReservation.setExternalClientId(reservation.getExternalClientId());
                }
                if (reservation.getExternalId() != null) {
                    existingReservation.setExternalId(reservation.getExternalId());
                }
                if (reservation.getExternalReferenceCode() != null) {
                    existingReservation.setExternalReferenceCode(reservation.getExternalReferenceCode());
                }
                if (reservation.getExternalUserId() != null) {
                    existingReservation.setExternalUserId(reservation.getExternalUserId());
                }
                if (reservation.getModifyReservationLink() != null) {
                    existingReservation.setModifyReservationLink(reservation.getModifyReservationLink());
                }
                if (reservation.getReferenceCode() != null) {
                    existingReservation.setReferenceCode(reservation.getReferenceCode());
                }
                if (reservation.getReservationSmsOptin() != null) {
                    existingReservation.setReservationSmsOptin(reservation.getReservationSmsOptin());
                }
                if (reservation.getReservationType() != null) {
                    existingReservation.setReservationType(reservation.getReservationType());
                }
                if (reservation.getSendReminderEmail() != null) {
                    existingReservation.setSendReminderEmail(reservation.getSendReminderEmail());
                }
                if (reservation.getSendreminderSms() != null) {
                    existingReservation.setSendreminderSms(reservation.getSendreminderSms());
                }
                if (reservation.getSourceClientId() != null) {
                    existingReservation.setSourceClientId(reservation.getSourceClientId());
                }
                if (reservation.getUserId() != null) {
                    existingReservation.setUserId(reservation.getUserId());
                }
                if (reservation.getUserName() != null) {
                    existingReservation.setUserName(reservation.getUserName());
                }
                if (reservation.getTechLineage() != null) {
                    existingReservation.setTechLineage(reservation.getTechLineage());
                }
                if (reservation.getTechCreatedDate() != null) {
                    existingReservation.setTechCreatedDate(reservation.getTechCreatedDate());
                }
                if (reservation.getTechUpdatedDate() != null) {
                    existingReservation.setTechUpdatedDate(reservation.getTechUpdatedDate());
                }
                if (reservation.getTechMapping() != null) {
                    existingReservation.setTechMapping(reservation.getTechMapping());
                }
                if (reservation.getTechComment() != null) {
                    existingReservation.setTechComment(reservation.getTechComment());
                }

                return existingReservation;
            })
            .map(reservationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reservation.getId().toString())
        );
    }

    /**
     * {@code GET  /reservations} : get all the reservations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reservations in body.
     */
    @GetMapping("")
    public List<Reservation> getAllReservations() {
        log.debug("REST request to get all Reservations");
        return reservationRepository.findAll();
    }

    /**
     * {@code GET  /reservations/:id} : get the "id" reservation.
     *
     * @param id the id of the reservation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reservation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id) {
        log.debug("REST request to get Reservation : {}", id);
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reservation);
    }

    /**
     * {@code DELETE  /reservations/:id} : delete the "id" reservation.
     *
     * @param id the id of the reservation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        log.debug("REST request to delete Reservation : {}", id);
        reservationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
