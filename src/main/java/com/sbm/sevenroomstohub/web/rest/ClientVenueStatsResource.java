package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.ClientVenueStatsRepository;
import com.sbm.sevenroomstohub.service.ClientVenueStatsService;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ClientVenueStats}.
 */
@RestController
@RequestMapping("/api/client-venue-stats")
public class ClientVenueStatsResource {

    private final Logger log = LoggerFactory.getLogger(ClientVenueStatsResource.class);

    private static final String ENTITY_NAME = "clientVenueStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientVenueStatsService clientVenueStatsService;

    private final ClientVenueStatsRepository clientVenueStatsRepository;

    public ClientVenueStatsResource(
        ClientVenueStatsService clientVenueStatsService,
        ClientVenueStatsRepository clientVenueStatsRepository
    ) {
        this.clientVenueStatsService = clientVenueStatsService;
        this.clientVenueStatsRepository = clientVenueStatsRepository;
    }

    /**
     * {@code POST  /client-venue-stats} : Create a new clientVenueStats.
     *
     * @param clientVenueStatsDTO the clientVenueStatsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientVenueStatsDTO, or with status {@code 400 (Bad Request)} if the clientVenueStats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClientVenueStatsDTO> createClientVenueStats(@RequestBody ClientVenueStatsDTO clientVenueStatsDTO)
        throws URISyntaxException {
        log.debug("REST request to save ClientVenueStats : {}", clientVenueStatsDTO);
        if (clientVenueStatsDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientVenueStats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientVenueStatsDTO result = clientVenueStatsService.save(clientVenueStatsDTO);
        return ResponseEntity
            .created(new URI("/api/client-venue-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-venue-stats/:id} : Updates an existing clientVenueStats.
     *
     * @param id the id of the clientVenueStatsDTO to save.
     * @param clientVenueStatsDTO the clientVenueStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientVenueStatsDTO,
     * or with status {@code 400 (Bad Request)} if the clientVenueStatsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientVenueStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientVenueStatsDTO> updateClientVenueStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientVenueStatsDTO clientVenueStatsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClientVenueStats : {}, {}", id, clientVenueStatsDTO);
        if (clientVenueStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientVenueStatsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientVenueStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClientVenueStatsDTO result = clientVenueStatsService.update(clientVenueStatsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientVenueStatsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /client-venue-stats/:id} : Partial updates given fields of an existing clientVenueStats, field will ignore if it is null
     *
     * @param id the id of the clientVenueStatsDTO to save.
     * @param clientVenueStatsDTO the clientVenueStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientVenueStatsDTO,
     * or with status {@code 400 (Bad Request)} if the clientVenueStatsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the clientVenueStatsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientVenueStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientVenueStatsDTO> partialUpdateClientVenueStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientVenueStatsDTO clientVenueStatsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientVenueStats partially : {}, {}", id, clientVenueStatsDTO);
        if (clientVenueStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientVenueStatsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientVenueStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientVenueStatsDTO> result = clientVenueStatsService.partialUpdate(clientVenueStatsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientVenueStatsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /client-venue-stats} : get all the clientVenueStats.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientVenueStats in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ClientVenueStatsDTO>> getAllClientVenueStats(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("client-is-null".equals(filter)) {
            log.debug("REST request to get all ClientVenueStatss where client is null");
            return new ResponseEntity<>(clientVenueStatsService.findAllWhereClientIsNull(), HttpStatus.OK);
        }
        log.debug("REST request to get a page of ClientVenueStats");
        Page<ClientVenueStatsDTO> page = clientVenueStatsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /client-venue-stats/:id} : get the "id" clientVenueStats.
     *
     * @param id the id of the clientVenueStatsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientVenueStatsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientVenueStatsDTO> getClientVenueStats(@PathVariable("id") Long id) {
        log.debug("REST request to get ClientVenueStats : {}", id);
        Optional<ClientVenueStatsDTO> clientVenueStatsDTO = clientVenueStatsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientVenueStatsDTO);
    }

    /**
     * {@code DELETE  /client-venue-stats/:id} : delete the "id" clientVenueStats.
     *
     * @param id the id of the clientVenueStatsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientVenueStats(@PathVariable("id") Long id) {
        log.debug("REST request to delete ClientVenueStats : {}", id);
        clientVenueStatsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
