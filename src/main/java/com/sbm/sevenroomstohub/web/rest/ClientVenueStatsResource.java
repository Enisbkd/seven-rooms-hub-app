package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import com.sbm.sevenroomstohub.repository.ClientVenueStatsRepository;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ClientVenueStats}.
 */
@RestController
@RequestMapping("/api/client-venue-stats")
@Transactional
public class ClientVenueStatsResource {

    private final Logger log = LoggerFactory.getLogger(ClientVenueStatsResource.class);

    private static final String ENTITY_NAME = "clientVenueStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientVenueStatsRepository clientVenueStatsRepository;

    public ClientVenueStatsResource(ClientVenueStatsRepository clientVenueStatsRepository) {
        this.clientVenueStatsRepository = clientVenueStatsRepository;
    }

    /**
     * {@code POST  /client-venue-stats} : Create a new clientVenueStats.
     *
     * @param clientVenueStats the clientVenueStats to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientVenueStats, or with status {@code 400 (Bad Request)} if the clientVenueStats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClientVenueStats> createClientVenueStats(@RequestBody ClientVenueStats clientVenueStats)
        throws URISyntaxException {
        log.debug("REST request to save ClientVenueStats : {}", clientVenueStats);
        if (clientVenueStats.getId() != null) {
            throw new BadRequestAlertException("A new clientVenueStats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientVenueStats result = clientVenueStatsRepository.save(clientVenueStats);
        return ResponseEntity
            .created(new URI("/api/client-venue-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-venue-stats/:id} : Updates an existing clientVenueStats.
     *
     * @param id the id of the clientVenueStats to save.
     * @param clientVenueStats the clientVenueStats to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientVenueStats,
     * or with status {@code 400 (Bad Request)} if the clientVenueStats is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientVenueStats couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientVenueStats> updateClientVenueStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientVenueStats clientVenueStats
    ) throws URISyntaxException {
        log.debug("REST request to update ClientVenueStats : {}, {}", id, clientVenueStats);
        if (clientVenueStats.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientVenueStats.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientVenueStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClientVenueStats result = clientVenueStatsRepository.save(clientVenueStats);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientVenueStats.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /client-venue-stats/:id} : Partial updates given fields of an existing clientVenueStats, field will ignore if it is null
     *
     * @param id the id of the clientVenueStats to save.
     * @param clientVenueStats the clientVenueStats to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientVenueStats,
     * or with status {@code 400 (Bad Request)} if the clientVenueStats is not valid,
     * or with status {@code 404 (Not Found)} if the clientVenueStats is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientVenueStats couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientVenueStats> partialUpdateClientVenueStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientVenueStats clientVenueStats
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientVenueStats partially : {}, {}", id, clientVenueStats);
        if (clientVenueStats.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientVenueStats.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientVenueStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientVenueStats> result = clientVenueStatsRepository
            .findById(clientVenueStats.getId())
            .map(existingClientVenueStats -> {
                if (clientVenueStats.getVenueId() != null) {
                    existingClientVenueStats.setVenueId(clientVenueStats.getVenueId());
                }
                if (clientVenueStats.getAvgRating() != null) {
                    existingClientVenueStats.setAvgRating(clientVenueStats.getAvgRating());
                }
                if (clientVenueStats.getBookedByNames() != null) {
                    existingClientVenueStats.setBookedByNames(clientVenueStats.getBookedByNames());
                }
                if (clientVenueStats.getLastVisitDate() != null) {
                    existingClientVenueStats.setLastVisitDate(clientVenueStats.getLastVisitDate());
                }
                if (clientVenueStats.getNumRatings() != null) {
                    existingClientVenueStats.setNumRatings(clientVenueStats.getNumRatings());
                }
                if (clientVenueStats.getTotalCancellations() != null) {
                    existingClientVenueStats.setTotalCancellations(clientVenueStats.getTotalCancellations());
                }
                if (clientVenueStats.getTotalCovers() != null) {
                    existingClientVenueStats.setTotalCovers(clientVenueStats.getTotalCovers());
                }
                if (clientVenueStats.getTotalNoShows() != null) {
                    existingClientVenueStats.setTotalNoShows(clientVenueStats.getTotalNoShows());
                }
                if (clientVenueStats.getTotalSpend() != null) {
                    existingClientVenueStats.setTotalSpend(clientVenueStats.getTotalSpend());
                }
                if (clientVenueStats.getTotalSpendLocal() != null) {
                    existingClientVenueStats.setTotalSpendLocal(clientVenueStats.getTotalSpendLocal());
                }
                if (clientVenueStats.getTotalSpendLocalperCover() != null) {
                    existingClientVenueStats.setTotalSpendLocalperCover(clientVenueStats.getTotalSpendLocalperCover());
                }
                if (clientVenueStats.getTotalSpendLocalPerVisit() != null) {
                    existingClientVenueStats.setTotalSpendLocalPerVisit(clientVenueStats.getTotalSpendLocalPerVisit());
                }
                if (clientVenueStats.getTotalSpendperCover() != null) {
                    existingClientVenueStats.setTotalSpendperCover(clientVenueStats.getTotalSpendperCover());
                }
                if (clientVenueStats.getTotalSpendPerVisit() != null) {
                    existingClientVenueStats.setTotalSpendPerVisit(clientVenueStats.getTotalSpendPerVisit());
                }
                if (clientVenueStats.getTotalVisit() != null) {
                    existingClientVenueStats.setTotalVisit(clientVenueStats.getTotalVisit());
                }
                if (clientVenueStats.getVenueMarketingOptin() != null) {
                    existingClientVenueStats.setVenueMarketingOptin(clientVenueStats.getVenueMarketingOptin());
                }
                if (clientVenueStats.getVenueMarketingOptints() != null) {
                    existingClientVenueStats.setVenueMarketingOptints(clientVenueStats.getVenueMarketingOptints());
                }
                if (clientVenueStats.getTechLineage() != null) {
                    existingClientVenueStats.setTechLineage(clientVenueStats.getTechLineage());
                }
                if (clientVenueStats.getTechCreatedDate() != null) {
                    existingClientVenueStats.setTechCreatedDate(clientVenueStats.getTechCreatedDate());
                }
                if (clientVenueStats.getTechUpdatedDate() != null) {
                    existingClientVenueStats.setTechUpdatedDate(clientVenueStats.getTechUpdatedDate());
                }
                if (clientVenueStats.getTechMapping() != null) {
                    existingClientVenueStats.setTechMapping(clientVenueStats.getTechMapping());
                }
                if (clientVenueStats.getTechComment() != null) {
                    existingClientVenueStats.setTechComment(clientVenueStats.getTechComment());
                }

                return existingClientVenueStats;
            })
            .map(clientVenueStatsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientVenueStats.getId().toString())
        );
    }

    /**
     * {@code GET  /client-venue-stats} : get all the clientVenueStats.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientVenueStats in body.
     */
    @GetMapping("")
    public List<ClientVenueStats> getAllClientVenueStats(@RequestParam(name = "filter", required = false) String filter) {
        if ("client-is-null".equals(filter)) {
            log.debug("REST request to get all ClientVenueStatss where client is null");
            return StreamSupport
                .stream(clientVenueStatsRepository.findAll().spliterator(), false)
                .filter(clientVenueStats -> clientVenueStats.getClient() == null)
                .toList();
        }
        log.debug("REST request to get all ClientVenueStats");
        return clientVenueStatsRepository.findAll();
    }

    /**
     * {@code GET  /client-venue-stats/:id} : get the "id" clientVenueStats.
     *
     * @param id the id of the clientVenueStats to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientVenueStats, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientVenueStats> getClientVenueStats(@PathVariable("id") Long id) {
        log.debug("REST request to get ClientVenueStats : {}", id);
        Optional<ClientVenueStats> clientVenueStats = clientVenueStatsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(clientVenueStats);
    }

    /**
     * {@code DELETE  /client-venue-stats/:id} : delete the "id" clientVenueStats.
     *
     * @param id the id of the clientVenueStats to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientVenueStats(@PathVariable("id") Long id) {
        log.debug("REST request to delete ClientVenueStats : {}", id);
        clientVenueStatsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
