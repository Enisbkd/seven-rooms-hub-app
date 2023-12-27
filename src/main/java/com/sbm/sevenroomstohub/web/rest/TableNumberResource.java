package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.TableNumber;
import com.sbm.sevenroomstohub.repository.TableNumberRepository;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.TableNumber}.
 */
@RestController
@RequestMapping("/api/table-numbers")
@Transactional
public class TableNumberResource {

    private final Logger log = LoggerFactory.getLogger(TableNumberResource.class);

    private static final String ENTITY_NAME = "tableNumber";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableNumberRepository tableNumberRepository;

    public TableNumberResource(TableNumberRepository tableNumberRepository) {
        this.tableNumberRepository = tableNumberRepository;
    }

    /**
     * {@code POST  /table-numbers} : Create a new tableNumber.
     *
     * @param tableNumber the tableNumber to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableNumber, or with status {@code 400 (Bad Request)} if the tableNumber has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TableNumber> createTableNumber(@RequestBody TableNumber tableNumber) throws URISyntaxException {
        log.debug("REST request to save TableNumber : {}", tableNumber);
        if (tableNumber.getId() != null) {
            throw new BadRequestAlertException("A new tableNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableNumber result = tableNumberRepository.save(tableNumber);
        return ResponseEntity
            .created(new URI("/api/table-numbers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-numbers/:id} : Updates an existing tableNumber.
     *
     * @param id the id of the tableNumber to save.
     * @param tableNumber the tableNumber to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableNumber,
     * or with status {@code 400 (Bad Request)} if the tableNumber is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableNumber couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TableNumber> updateTableNumber(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableNumber tableNumber
    ) throws URISyntaxException {
        log.debug("REST request to update TableNumber : {}, {}", id, tableNumber);
        if (tableNumber.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableNumber.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableNumberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableNumber result = tableNumberRepository.save(tableNumber);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableNumber.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-numbers/:id} : Partial updates given fields of an existing tableNumber, field will ignore if it is null
     *
     * @param id the id of the tableNumber to save.
     * @param tableNumber the tableNumber to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableNumber,
     * or with status {@code 400 (Bad Request)} if the tableNumber is not valid,
     * or with status {@code 404 (Not Found)} if the tableNumber is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableNumber couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TableNumber> partialUpdateTableNumber(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableNumber tableNumber
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableNumber partially : {}, {}", id, tableNumber);
        if (tableNumber.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableNumber.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableNumberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableNumber> result = tableNumberRepository
            .findById(tableNumber.getId())
            .map(existingTableNumber -> {
                if (tableNumber.getTableNum() != null) {
                    existingTableNumber.setTableNum(tableNumber.getTableNum());
                }

                return existingTableNumber;
            })
            .map(tableNumberRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableNumber.getId().toString())
        );
    }

    /**
     * {@code GET  /table-numbers} : get all the tableNumbers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableNumbers in body.
     */
    @GetMapping("")
    public List<TableNumber> getAllTableNumbers() {
        log.debug("REST request to get all TableNumbers");
        return tableNumberRepository.findAll();
    }

    /**
     * {@code GET  /table-numbers/:id} : get the "id" tableNumber.
     *
     * @param id the id of the tableNumber to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableNumber, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TableNumber> getTableNumber(@PathVariable("id") Long id) {
        log.debug("REST request to get TableNumber : {}", id);
        Optional<TableNumber> tableNumber = tableNumberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tableNumber);
    }

    /**
     * {@code DELETE  /table-numbers/:id} : delete the "id" tableNumber.
     *
     * @param id the id of the tableNumber to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableNumber(@PathVariable("id") Long id) {
        log.debug("REST request to delete TableNumber : {}", id);
        tableNumberRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
