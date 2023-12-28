package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.TableNumRepository;
import com.sbm.sevenroomstohub.service.TableNumService;
import com.sbm.sevenroomstohub.service.dto.TableNumDTO;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.TableNum}.
 */
@RestController
@RequestMapping("/api/table-nums")
public class TableNumResource {

    private final Logger log = LoggerFactory.getLogger(TableNumResource.class);

    private static final String ENTITY_NAME = "tableNum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableNumService tableNumService;

    private final TableNumRepository tableNumRepository;

    public TableNumResource(TableNumService tableNumService, TableNumRepository tableNumRepository) {
        this.tableNumService = tableNumService;
        this.tableNumRepository = tableNumRepository;
    }

    /**
     * {@code POST  /table-nums} : Create a new tableNum.
     *
     * @param tableNumDTO the tableNumDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableNumDTO, or with status {@code 400 (Bad Request)} if the tableNum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TableNumDTO> createTableNum(@RequestBody TableNumDTO tableNumDTO) throws URISyntaxException {
        log.debug("REST request to save TableNum : {}", tableNumDTO);
        if (tableNumDTO.getId() != null) {
            throw new BadRequestAlertException("A new tableNum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableNumDTO result = tableNumService.save(tableNumDTO);
        return ResponseEntity
            .created(new URI("/api/table-nums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-nums/:id} : Updates an existing tableNum.
     *
     * @param id the id of the tableNumDTO to save.
     * @param tableNumDTO the tableNumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableNumDTO,
     * or with status {@code 400 (Bad Request)} if the tableNumDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableNumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TableNumDTO> updateTableNum(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableNumDTO tableNumDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TableNum : {}, {}", id, tableNumDTO);
        if (tableNumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableNumDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableNumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableNumDTO result = tableNumService.update(tableNumDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableNumDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-nums/:id} : Partial updates given fields of an existing tableNum, field will ignore if it is null
     *
     * @param id the id of the tableNumDTO to save.
     * @param tableNumDTO the tableNumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableNumDTO,
     * or with status {@code 400 (Bad Request)} if the tableNumDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tableNumDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableNumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TableNumDTO> partialUpdateTableNum(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableNumDTO tableNumDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableNum partially : {}, {}", id, tableNumDTO);
        if (tableNumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableNumDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableNumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableNumDTO> result = tableNumService.partialUpdate(tableNumDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableNumDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /table-nums} : get all the tableNums.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableNums in body.
     */
    @GetMapping("")
    public List<TableNumDTO> getAllTableNums() {
        log.debug("REST request to get all TableNums");
        return tableNumService.findAll();
    }

    /**
     * {@code GET  /table-nums/:id} : get the "id" tableNum.
     *
     * @param id the id of the tableNumDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableNumDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TableNumDTO> getTableNum(@PathVariable("id") Long id) {
        log.debug("REST request to get TableNum : {}", id);
        Optional<TableNumDTO> tableNumDTO = tableNumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableNumDTO);
    }

    /**
     * {@code DELETE  /table-nums/:id} : delete the "id" tableNum.
     *
     * @param id the id of the tableNumDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableNum(@PathVariable("id") Long id) {
        log.debug("REST request to delete TableNum : {}", id);
        tableNumService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
