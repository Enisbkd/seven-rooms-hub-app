package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.ResPosTicket;
import com.sbm.sevenroomstohub.repository.ResPosTicketRepository;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ResPosTicket}.
 */
@RestController
@RequestMapping("/api/res-pos-tickets")
@Transactional
public class ResPosTicketResource {

    private final Logger log = LoggerFactory.getLogger(ResPosTicketResource.class);

    private static final String ENTITY_NAME = "resPosTicket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResPosTicketRepository resPosTicketRepository;

    public ResPosTicketResource(ResPosTicketRepository resPosTicketRepository) {
        this.resPosTicketRepository = resPosTicketRepository;
    }

    /**
     * {@code POST  /res-pos-tickets} : Create a new resPosTicket.
     *
     * @param resPosTicket the resPosTicket to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resPosTicket, or with status {@code 400 (Bad Request)} if the resPosTicket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResPosTicket> createResPosTicket(@RequestBody ResPosTicket resPosTicket) throws URISyntaxException {
        log.debug("REST request to save ResPosTicket : {}", resPosTicket);
        if (resPosTicket.getId() != null) {
            throw new BadRequestAlertException("A new resPosTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResPosTicket result = resPosTicketRepository.save(resPosTicket);
        return ResponseEntity
            .created(new URI("/api/res-pos-tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /res-pos-tickets/:id} : Updates an existing resPosTicket.
     *
     * @param id the id of the resPosTicket to save.
     * @param resPosTicket the resPosTicket to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosTicket,
     * or with status {@code 400 (Bad Request)} if the resPosTicket is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resPosTicket couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResPosTicket> updateResPosTicket(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResPosTicket resPosTicket
    ) throws URISyntaxException {
        log.debug("REST request to update ResPosTicket : {}, {}", id, resPosTicket);
        if (resPosTicket.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosTicket.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosTicketRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResPosTicket result = resPosTicketRepository.save(resPosTicket);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosTicket.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /res-pos-tickets/:id} : Partial updates given fields of an existing resPosTicket, field will ignore if it is null
     *
     * @param id the id of the resPosTicket to save.
     * @param resPosTicket the resPosTicket to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosTicket,
     * or with status {@code 400 (Bad Request)} if the resPosTicket is not valid,
     * or with status {@code 404 (Not Found)} if the resPosTicket is not found,
     * or with status {@code 500 (Internal Server Error)} if the resPosTicket couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResPosTicket> partialUpdateResPosTicket(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResPosTicket resPosTicket
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResPosTicket partially : {}, {}", id, resPosTicket);
        if (resPosTicket.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosTicket.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosTicketRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResPosTicket> result = resPosTicketRepository
            .findById(resPosTicket.getId())
            .map(existingResPosTicket -> {
                if (resPosTicket.getStatus() != null) {
                    existingResPosTicket.setStatus(resPosTicket.getStatus());
                }
                if (resPosTicket.getAdminFee() != null) {
                    existingResPosTicket.setAdminFee(resPosTicket.getAdminFee());
                }
                if (resPosTicket.getCode() != null) {
                    existingResPosTicket.setCode(resPosTicket.getCode());
                }
                if (resPosTicket.getTableNo() != null) {
                    existingResPosTicket.setTableNo(resPosTicket.getTableNo());
                }
                if (resPosTicket.getTax() != null) {
                    existingResPosTicket.setTax(resPosTicket.getTax());
                }
                if (resPosTicket.getBusinessId() != null) {
                    existingResPosTicket.setBusinessId(resPosTicket.getBusinessId());
                }
                if (resPosTicket.getLocalPosticketId() != null) {
                    existingResPosTicket.setLocalPosticketId(resPosTicket.getLocalPosticketId());
                }
                if (resPosTicket.getEmployeeName() != null) {
                    existingResPosTicket.setEmployeeName(resPosTicket.getEmployeeName());
                }
                if (resPosTicket.getTotal() != null) {
                    existingResPosTicket.setTotal(resPosTicket.getTotal());
                }
                if (resPosTicket.getSubtotal() != null) {
                    existingResPosTicket.setSubtotal(resPosTicket.getSubtotal());
                }
                if (resPosTicket.getStartTime() != null) {
                    existingResPosTicket.setStartTime(resPosTicket.getStartTime());
                }
                if (resPosTicket.getServiceCharge() != null) {
                    existingResPosTicket.setServiceCharge(resPosTicket.getServiceCharge());
                }
                if (resPosTicket.getEndtime() != null) {
                    existingResPosTicket.setEndtime(resPosTicket.getEndtime());
                }
                if (resPosTicket.getTechLineage() != null) {
                    existingResPosTicket.setTechLineage(resPosTicket.getTechLineage());
                }
                if (resPosTicket.getTechCreatedDate() != null) {
                    existingResPosTicket.setTechCreatedDate(resPosTicket.getTechCreatedDate());
                }
                if (resPosTicket.getTechUpdatedDate() != null) {
                    existingResPosTicket.setTechUpdatedDate(resPosTicket.getTechUpdatedDate());
                }
                if (resPosTicket.getTechMapping() != null) {
                    existingResPosTicket.setTechMapping(resPosTicket.getTechMapping());
                }
                if (resPosTicket.getTechComment() != null) {
                    existingResPosTicket.setTechComment(resPosTicket.getTechComment());
                }

                return existingResPosTicket;
            })
            .map(resPosTicketRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosTicket.getId().toString())
        );
    }

    /**
     * {@code GET  /res-pos-tickets} : get all the resPosTickets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resPosTickets in body.
     */
    @GetMapping("")
    public List<ResPosTicket> getAllResPosTickets() {
        log.debug("REST request to get all ResPosTickets");
        return resPosTicketRepository.findAll();
    }

    /**
     * {@code GET  /res-pos-tickets/:id} : get the "id" resPosTicket.
     *
     * @param id the id of the resPosTicket to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resPosTicket, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResPosTicket> getResPosTicket(@PathVariable("id") Long id) {
        log.debug("REST request to get ResPosTicket : {}", id);
        Optional<ResPosTicket> resPosTicket = resPosTicketRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resPosTicket);
    }

    /**
     * {@code DELETE  /res-pos-tickets/:id} : delete the "id" resPosTicket.
     *
     * @param id the id of the resPosTicket to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResPosTicket(@PathVariable("id") Long id) {
        log.debug("REST request to delete ResPosTicket : {}", id);
        resPosTicketRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
