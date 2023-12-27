package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.ResPosticketsItemRepository;
import com.sbm.sevenroomstohub.service.ResPosticketsItemService;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ResPosticketsItem}.
 */
@RestController
@RequestMapping("/api/res-postickets-items")
public class ResPosticketsItemResource {

    private final Logger log = LoggerFactory.getLogger(ResPosticketsItemResource.class);

    private static final String ENTITY_NAME = "resPosticketsItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResPosticketsItemService resPosticketsItemService;

    private final ResPosticketsItemRepository resPosticketsItemRepository;

    public ResPosticketsItemResource(
        ResPosticketsItemService resPosticketsItemService,
        ResPosticketsItemRepository resPosticketsItemRepository
    ) {
        this.resPosticketsItemService = resPosticketsItemService;
        this.resPosticketsItemRepository = resPosticketsItemRepository;
    }

    /**
     * {@code POST  /res-postickets-items} : Create a new resPosticketsItem.
     *
     * @param resPosticketsItemDTO the resPosticketsItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resPosticketsItemDTO, or with status {@code 400 (Bad Request)} if the resPosticketsItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResPosticketsItemDTO> createResPosticketsItem(@RequestBody ResPosticketsItemDTO resPosticketsItemDTO)
        throws URISyntaxException {
        log.debug("REST request to save ResPosticketsItem : {}", resPosticketsItemDTO);
        if (resPosticketsItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new resPosticketsItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResPosticketsItemDTO result = resPosticketsItemService.save(resPosticketsItemDTO);
        return ResponseEntity
            .created(new URI("/api/res-postickets-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /res-postickets-items/:id} : Updates an existing resPosticketsItem.
     *
     * @param id the id of the resPosticketsItemDTO to save.
     * @param resPosticketsItemDTO the resPosticketsItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosticketsItemDTO,
     * or with status {@code 400 (Bad Request)} if the resPosticketsItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resPosticketsItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResPosticketsItemDTO> updateResPosticketsItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResPosticketsItemDTO resPosticketsItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ResPosticketsItem : {}, {}", id, resPosticketsItemDTO);
        if (resPosticketsItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosticketsItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosticketsItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResPosticketsItemDTO result = resPosticketsItemService.update(resPosticketsItemDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosticketsItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /res-postickets-items/:id} : Partial updates given fields of an existing resPosticketsItem, field will ignore if it is null
     *
     * @param id the id of the resPosticketsItemDTO to save.
     * @param resPosticketsItemDTO the resPosticketsItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosticketsItemDTO,
     * or with status {@code 400 (Bad Request)} if the resPosticketsItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the resPosticketsItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the resPosticketsItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResPosticketsItemDTO> partialUpdateResPosticketsItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResPosticketsItemDTO resPosticketsItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResPosticketsItem partially : {}, {}", id, resPosticketsItemDTO);
        if (resPosticketsItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosticketsItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosticketsItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResPosticketsItemDTO> result = resPosticketsItemService.partialUpdate(resPosticketsItemDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosticketsItemDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /res-postickets-items} : get all the resPosticketsItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resPosticketsItems in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ResPosticketsItemDTO>> getAllResPosticketsItems(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ResPosticketsItems");
        Page<ResPosticketsItemDTO> page = resPosticketsItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /res-postickets-items/:id} : get the "id" resPosticketsItem.
     *
     * @param id the id of the resPosticketsItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resPosticketsItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResPosticketsItemDTO> getResPosticketsItem(@PathVariable("id") Long id) {
        log.debug("REST request to get ResPosticketsItem : {}", id);
        Optional<ResPosticketsItemDTO> resPosticketsItemDTO = resPosticketsItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resPosticketsItemDTO);
    }

    /**
     * {@code DELETE  /res-postickets-items/:id} : delete the "id" resPosticketsItem.
     *
     * @param id the id of the resPosticketsItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResPosticketsItem(@PathVariable("id") Long id) {
        log.debug("REST request to delete ResPosticketsItem : {}", id);
        resPosticketsItemService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
