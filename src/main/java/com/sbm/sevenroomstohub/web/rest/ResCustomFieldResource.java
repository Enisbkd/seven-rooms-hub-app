package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.ResCustomField;
import com.sbm.sevenroomstohub.repository.ResCustomFieldRepository;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ResCustomField}.
 */
@RestController
@RequestMapping("/api/res-custom-fields")
@Transactional
public class ResCustomFieldResource {

    private final Logger log = LoggerFactory.getLogger(ResCustomFieldResource.class);

    private static final String ENTITY_NAME = "resCustomField";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResCustomFieldRepository resCustomFieldRepository;

    public ResCustomFieldResource(ResCustomFieldRepository resCustomFieldRepository) {
        this.resCustomFieldRepository = resCustomFieldRepository;
    }

    /**
     * {@code POST  /res-custom-fields} : Create a new resCustomField.
     *
     * @param resCustomField the resCustomField to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resCustomField, or with status {@code 400 (Bad Request)} if the resCustomField has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResCustomField> createResCustomField(@RequestBody ResCustomField resCustomField) throws URISyntaxException {
        log.debug("REST request to save ResCustomField : {}", resCustomField);
        if (resCustomField.getId() != null) {
            throw new BadRequestAlertException("A new resCustomField cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResCustomField result = resCustomFieldRepository.save(resCustomField);
        return ResponseEntity
            .created(new URI("/api/res-custom-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /res-custom-fields/:id} : Updates an existing resCustomField.
     *
     * @param id the id of the resCustomField to save.
     * @param resCustomField the resCustomField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resCustomField,
     * or with status {@code 400 (Bad Request)} if the resCustomField is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resCustomField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResCustomField> updateResCustomField(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResCustomField resCustomField
    ) throws URISyntaxException {
        log.debug("REST request to update ResCustomField : {}, {}", id, resCustomField);
        if (resCustomField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resCustomField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resCustomFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResCustomField result = resCustomFieldRepository.save(resCustomField);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resCustomField.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /res-custom-fields/:id} : Partial updates given fields of an existing resCustomField, field will ignore if it is null
     *
     * @param id the id of the resCustomField to save.
     * @param resCustomField the resCustomField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resCustomField,
     * or with status {@code 400 (Bad Request)} if the resCustomField is not valid,
     * or with status {@code 404 (Not Found)} if the resCustomField is not found,
     * or with status {@code 500 (Internal Server Error)} if the resCustomField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResCustomField> partialUpdateResCustomField(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResCustomField resCustomField
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResCustomField partially : {}, {}", id, resCustomField);
        if (resCustomField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resCustomField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resCustomFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResCustomField> result = resCustomFieldRepository
            .findById(resCustomField.getId())
            .map(existingResCustomField -> {
                if (resCustomField.getSystemName() != null) {
                    existingResCustomField.setSystemName(resCustomField.getSystemName());
                }
                if (resCustomField.getDisplayOrder() != null) {
                    existingResCustomField.setDisplayOrder(resCustomField.getDisplayOrder());
                }
                if (resCustomField.getName() != null) {
                    existingResCustomField.setName(resCustomField.getName());
                }
                if (resCustomField.getValue() != null) {
                    existingResCustomField.setValue(resCustomField.getValue());
                }
                if (resCustomField.getTechLineage() != null) {
                    existingResCustomField.setTechLineage(resCustomField.getTechLineage());
                }
                if (resCustomField.getTechCreatedDate() != null) {
                    existingResCustomField.setTechCreatedDate(resCustomField.getTechCreatedDate());
                }
                if (resCustomField.getTechUpdatedDate() != null) {
                    existingResCustomField.setTechUpdatedDate(resCustomField.getTechUpdatedDate());
                }
                if (resCustomField.getTechMapping() != null) {
                    existingResCustomField.setTechMapping(resCustomField.getTechMapping());
                }
                if (resCustomField.getTechComment() != null) {
                    existingResCustomField.setTechComment(resCustomField.getTechComment());
                }

                return existingResCustomField;
            })
            .map(resCustomFieldRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resCustomField.getId().toString())
        );
    }

    /**
     * {@code GET  /res-custom-fields} : get all the resCustomFields.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resCustomFields in body.
     */
    @GetMapping("")
    public List<ResCustomField> getAllResCustomFields() {
        log.debug("REST request to get all ResCustomFields");
        return resCustomFieldRepository.findAll();
    }

    /**
     * {@code GET  /res-custom-fields/:id} : get the "id" resCustomField.
     *
     * @param id the id of the resCustomField to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resCustomField, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResCustomField> getResCustomField(@PathVariable("id") Long id) {
        log.debug("REST request to get ResCustomField : {}", id);
        Optional<ResCustomField> resCustomField = resCustomFieldRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resCustomField);
    }

    /**
     * {@code DELETE  /res-custom-fields/:id} : delete the "id" resCustomField.
     *
     * @param id the id of the resCustomField to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResCustomField(@PathVariable("id") Long id) {
        log.debug("REST request to delete ResCustomField : {}", id);
        resCustomFieldRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
