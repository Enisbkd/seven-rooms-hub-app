package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.TableNumberRepository;
import com.sbm.sevenroomstohub.service.TableNumberService;
import com.sbm.sevenroomstohub.service.dto.TableNumberDTO;
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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.TableNumber}.
 */
@RestController
@RequestMapping("/api/table-numbers")
public class TableNumberResource {

    private final Logger log = LoggerFactory.getLogger(TableNumberResource.class);

    private static final String ENTITY_NAME = "tableNumber";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableNumberService tableNumberService;

    private final TableNumberRepository tableNumberRepository;

    public TableNumberResource(TableNumberService tableNumberService, TableNumberRepository tableNumberRepository) {
        this.tableNumberService = tableNumberService;
        this.tableNumberRepository = tableNumberRepository;
    }

    /**
     * {@code POST  /table-numbers} : Create a new tableNumber.
     *
     * @param tableNumberDTO the tableNumberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableNumberDTO, or with status {@code 400 (Bad Request)} if the tableNumber has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TableNumberDTO> createTableNumber(@RequestBody TableNumberDTO tableNumberDTO) throws URISyntaxException {
        log.debug("REST request to save TableNumber : {}", tableNumberDTO);
        if (tableNumberDTO.getId() != null) {
            throw new BadRequestAlertException("A new tableNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableNumberDTO result = tableNumberService.save(tableNumberDTO);
        return ResponseEntity
            .created(new URI("/api/table-numbers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-numbers/:id} : Updates an existing tableNumber.
     *
     * @param id the id of the tableNumberDTO to save.
     * @param tableNumberDTO the tableNumberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableNumberDTO,
     * or with status {@code 400 (Bad Request)} if the tableNumberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableNumberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TableNumberDTO> updateTableNumber(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableNumberDTO tableNumberDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TableNumber : {}, {}", id, tableNumberDTO);
        if (tableNumberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableNumberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableNumberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableNumberDTO result = tableNumberService.update(tableNumberDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableNumberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-numbers/:id} : Partial updates given fields of an existing tableNumber, field will ignore if it is null
     *
     * @param id the id of the tableNumberDTO to save.
     * @param tableNumberDTO the tableNumberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableNumberDTO,
     * or with status {@code 400 (Bad Request)} if the tableNumberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tableNumberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableNumberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TableNumberDTO> partialUpdateTableNumber(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableNumberDTO tableNumberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableNumber partially : {}, {}", id, tableNumberDTO);
        if (tableNumberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableNumberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableNumberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableNumberDTO> result = tableNumberService.partialUpdate(tableNumberDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableNumberDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /table-numbers} : get all the tableNumbers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableNumbers in body.
     */
    @GetMapping("")
    public List<TableNumberDTO> getAllTableNumbers() {
        log.debug("REST request to get all TableNumbers");
        return tableNumberService.findAll();
    }

    /**
     * {@code GET  /table-numbers/:id} : get the "id" tableNumber.
     *
     * @param id the id of the tableNumberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableNumberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TableNumberDTO> getTableNumber(@PathVariable("id") Long id) {
        log.debug("REST request to get TableNumber : {}", id);
        Optional<TableNumberDTO> tableNumberDTO = tableNumberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableNumberDTO);
    }

    /**
     * {@code DELETE  /table-numbers/:id} : delete the "id" tableNumber.
     *
     * @param id the id of the tableNumberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableNumber(@PathVariable("id") Long id) {
        log.debug("REST request to delete TableNumber : {}", id);
        tableNumberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
